package com.test.land.landparent.admin.Test.classs;

import lombok.Data;

/**
 * Created by 10064028 on 2018/3/12.
 */
@Data
public class ProjecHumanEffectivenessVo {

    private Long projectId;
    private String projectName;
    private Double personEfficiency;
    private boolean isRead = false;
}
