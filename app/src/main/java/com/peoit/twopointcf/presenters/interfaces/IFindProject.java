package com.peoit.twopointcf.presenters.interfaces;

import com.peoit.twopointcf.entity.BannerBean;
import com.peoit.twopointcf.entity.ProjectBean;
import com.peoit.twopointcf.presenters.impl.FindProjectPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by ling on 2015/9/9.
 * description:
 */
public interface IFindProject {
    /**
     * 获取banner
     */
    void getDataBanner(String url,List<BannerBean> bannerBeans,FindProjectPresenter.OnHttpResultBannerListener listener);

    /**
     * 获取数据
     */
    void getData(String url,Map map,List<ProjectBean> projectBeans);

    /**
     * 获取下一页
     */
    void getDataMore(String url,Map map);
}
