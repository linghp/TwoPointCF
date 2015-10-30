package com.peoit.twopointcf.presenters.interfaces;

import java.util.Map;

/**
 * 关注项目
 * Created by zyz on 2015/9/24.
 */
public interface IProjectDetail {
    /**
     * 获取数据
     */
    void getData(String url,Map maps);
    /**
     * 支付投资项目保证金
     */
    void payMargin(Map maps);

}
