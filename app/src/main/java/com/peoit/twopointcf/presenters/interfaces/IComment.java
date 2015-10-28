package com.peoit.twopointcf.presenters.interfaces;

import java.util.Map;

/**
 * Created by ling on 2015/9/9.
 * description:
 */
public interface IComment {

    /**
     * 获取数据
     */
    void getData(Map map,boolean isMore);

    /**
     * 发布评论
     */
    void publishComment( Map map);
}
