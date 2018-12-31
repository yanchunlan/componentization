package com.peakmain.homemodule;

import java.io.Serializable;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */
public class HomeBean implements Serializable {
    private int resource;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
