package com.test.land.landparent.admin.Test;

import com.test.land.landparent.admin.Test.classs.ProjecHumanEffectivenessVo;

public class MyTask implements Runnable{

    private ProjecHumanEffectivenessVo phe;

    public MyTask(ProjecHumanEffectivenessVo phe) {
        this.phe = phe;
    }

    @Override
    public void run() {
        System.out.println("??????");
        //读取obj
        if (!phe.isRead()){
                System.out.println("线程:"+Thread.currentThread().getState()+phe);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            phe.setRead(true);
            }
        }
    }

