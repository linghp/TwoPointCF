package com.peoit.twopointcf.presenters.interfaces;

import com.peoit.twopointcf.entity.InformationCenterBean;

import java.util.List;

/**
 * Created by ling on 2015/9/9.
 * description:
 */
public interface IFindInformationsProject {
    /**
     * 获取数据
     */
    void getData(List<InformationCenterBean> informationBeans);

    /**
     * 获取下一页
     */
    void getDataMore();
}
