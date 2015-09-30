package com.peoit.twopointcf.presenters.interfaces;

import com.peoit.twopointcf.entity.ProjectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/9/9.
 * description:
 */
public interface IFindProject {
    /**
     * 获取数据
     */
    void getData(Map map,List<ProjectBean> projectBeans);

    /**
     * 获取下一页
     */
    void getDataMore(Map map);
}
