package com.peoit.twopointcf.presenters.interfaces;

import java.io.File;
import java.util.Map;

/**
 * Created by ling on 2015/9/9.
 * description:
 */
public interface IPublishProject {
    /**
     * 获取数据
     */
    void upload(String[] fileKeys, File[] files, Map<String, String> params);
    void updateProject(String[] fileKeys, File[] files, Map<String, String> params);
}
