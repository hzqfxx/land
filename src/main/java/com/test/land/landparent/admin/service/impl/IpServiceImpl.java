package com.test.land.landparent.admin.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.test.land.landparent.admin.entity.Ip;
import com.test.land.landparent.admin.mapper.IpDao;
import com.test.land.landparent.admin.service.IpService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@Service
public class IpServiceImpl extends ServiceImpl<IpDao, Ip> implements IpService {

    @Override
    public List<Ip> getAvailableIP() {
        //获取所有ip,将失效ip过滤
        EntityWrapper<Ip> ew = new EntityWrapper<>();
        ew.eq("state",1);
        List<Ip> ips = this.selectList(ew);

       ArrayList<Ip> ips1 = new ArrayList<>();
        for (Ip ip : ips) {
            String ip1 = ip.getIp();
            int prot = Integer.parseInt(ip.getProt());
            boolean falg = isHostConnectable(ip1, prot);
            if (falg){
                ips1.add(ip);
            }else {
                ip.setState(0L);
                this.updateById(ip);
            }
        }
        return ips;
    }

    public static boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ip:"+host+"可用");
        return true;
    }
}
