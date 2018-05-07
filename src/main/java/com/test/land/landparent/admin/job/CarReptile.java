package com.test.land.landparent.admin.job;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.land.landparent.admin.common.utils.SpringContextHelper;
import com.test.land.landparent.admin.entity.Car;
import com.test.land.landparent.admin.service.CarService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 实现page处理器接口(webMagic)
 */
@Component
public class CarReptile implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、重试次数、抓取间隔、超时时间、请求消息头、UA信息等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(10000).setRetrySleepTime(3).setCycleRetryTimes(3);
    public static int count =0;

    @Override
    //process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        //这段代码就用到了正则表达式，它表示匹配所有"https://github.com/code4craft/webmagic"这样的链接。

        String csdn ="http://blog.csdn.net/\\w+/article/details/\\d+";
        //String car="https://www.autohome.com.cn/\\d+/#pvareaid=103177";
        String car="//www.autohome.com.cn/\\w+/#\\w+=\\w+&pvareaid=\\d+";
        String car3="https://www.autohome.com.cn/grade/carhtml/[A-Z].html";
        //判断是否符合链接
        if (page.getUrl().regex(car3).match()) {
            Html html = page.getHtml();

            List<String> list   = html.xpath("/html/body").links().regex(car).all();
            removeDuplicateWithOrder(list);
            //将符合要求的list添加到待爬链接中
            page.addTargetRequests(list);
            //如果链接满足第二层的要去
        } else if (page.getUrl().regex(car).match()){
            String car_mall_price=null;
            String cat_old_price=null;
            Double car_evaluation=null;
            String CityId=null;
            String provinceId = null;
            String CityName = null;
            //获取车id
            String[] split1 = page.getUrl().toString().split("/");
            String CarId = split1[3];
            //获取市id
            String cId= page.getHtml().xpath("//*[@id=\"liChe168link\"]/a").get();
            if (cId==null){
                CityId=null;
            }else {
                String[] split = cId.split("/");
                CityId =split[3];
            }



            //打印抓取到的数据
            ////*[@class="subnav-title-name"]/a/text()
            ///html/body/div[3]/div[3]/div[1]/div[1]/a/text()
            //抓取汽车厂商
            String carBrand = page.getHtml().xpath("//*[@class=\"subnav-title-name\"]/a/text()").get();
            carBrand= carBrand.replaceAll("-", "");
            //汽车型号
            String carName = page.getHtml().xpath("//*[@class=\"subnav-title-name\"]/a/h1/text()").get();
            //汽车类型
            String carType = page.getHtml().xpath("//*[@class=\"breadnav fn-left\"]/a[2]/text()").get();
            //新车指导价 cat_new_price
            String cat_new_price= page.getHtml().xpath("//*[@class=\"autoseries-info\"]/dl/dt[1]/a[1]/text()").get();
            RestTemplate restTemplate = new RestTemplate();

            //车商城报价 car_mall_price(曲线救国)
            String mall1 = restTemplate.getForObject("https://api.mall.autohome.com.cn/gethomemallad/price/"+CarId+"/"+CityId+"?_appid=cms&callback=mallCallback", String.class);
            String callBack = mall1.replace(";mallCallback", "");
            String s = callBack.substring(1, callBack.length() - 2);
            JSONObject mallp = JSONObject.parseObject(s);
            JSONObject result2 = mallp.getJSONObject("result");

            if (result2 == null || result2.size() == 0) {
                car_mall_price = null;
            } else {
                String k = result2.get("list").toString();
                if (k==null||"".equals(k)||"[]".equals(k)){
                    car_mall_price = null;
                }else {
                    JSONObject list = JSONObject.parseObject(k);
                    if (list == null || list.size() == 0) {
                        car_mall_price = null;
                    } else {
                        DecimalFormat df = new DecimalFormat("######0.00");
                        String min = list.get("minPirce").toString();
                        String max = list.get("maxPrice").toString();
                        car_mall_price = df.format(Double.valueOf(min) / 10000) + "-" + df.format(Double.valueOf(max) / 10000) + "万";
                    }
                }
            }

            //二手车报价 cat_old_price(曲线救国.从发请求拿)
            //获取省id
            String pc2 = restTemplate.getForObject("https://www.autohome.com.cn/Ashx/AjaxHeadArea.ashx?OperType=GetAreaInfo&VarName=areaData&CityId=" + CityId, String.class);
            String[] split4 = pc2.split("=");
            String pc = split4[1];
            JSONObject jsonObject = JSONObject.parseObject(pc);
            JSONArray areaInfo = jsonObject.getJSONArray("AreaInfo");
            if (areaInfo==null||areaInfo.size()==0){
                provinceId=null;
                CityName=null;
            }else{
                JSONObject o = (JSONObject) areaInfo.get(0);
                provinceId = o.get("ProvinceId").toString();
                CityName = o.get("CityName").toString();
            }


            String price = restTemplate.getForObject("https://api.che168.com/auto/ForAutoCarPCInterval.ashx?callback=che168CallBack&_appid=cms&sid=" + CarId + "&pid=" + provinceId + "&cid=" + CityId, String.class);
            JSONObject price2 = strToJsonObj(price);
            JSONObject result = price2.getJSONObject("result");
            if (result==null||result.size()==0){
                cat_old_price=null;
            }else {
                DecimalFormat df   = new DecimalFormat("######0.00");
                String minPrice = result.get("minPrice").toString();
                String maxPrice = result.get("maxPrice").toString();
                cat_old_price=minPrice+"-"+maxPrice+"万";;
            }


            //口碑评分 car_evaluation
            String b = page.getHtml().xpath("//*[@class=\"font-score\"]/text()").get();
            if (b==null){
                car_evaluation=null;
            }else {
                car_evaluation=Double.valueOf(b);
            }

            Car car1 = new Car();
            car1.setCarBrand(carBrand);
            car1.setCarName(carName);
            car1.setCarType(carType);
            car1.setCarNewPrice(cat_new_price);
            car1.setCarMallPrice(car_mall_price);
            car1.setCarOldPrice(cat_old_price);
            car1.setCarEvaluation(car_evaluation);
            car1.setCarCreatetime(new Date());
            car1.setCarUrl(page.getUrl().toString());
            if (CityId==null){
                car1.setCarCityId(null);
            }else {
                car1.setCarCityId(Long.valueOf(CityId));
            }

            car1.setCarCityName(CityName);
            CarService carService = (CarService) SpringContextHelper.getBean("carService2");
            carService.insert(car1);
            count++;
        }

    }

    private JSONObject strToJsonObj(String price) {
        String callBack = price.replace("che168CallBack", "");
        String s = callBack.substring(1, callBack.length() - 1);
        return JSONObject.parseObject(s);
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();//声明一个set类
        List newList = new ArrayList();//声明一个ArrayLIst集合
        for (Object o : list) {
            if (set.add(o)){
                newList.add(o);
            }
        }

        list.clear();//清除所有元素
        list.addAll(newList);//加载所有元素
        System.out.println( " remove duplicate " + list);
    }
}
