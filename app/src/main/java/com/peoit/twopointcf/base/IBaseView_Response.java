package com.peoit.twopointcf.base;

/**
 * Created by ling on 2015/9/7.
 * description:公共的请求返回，界面上的提示
 */
public interface IBaseView_Response {

    /**
     * 显示进度条
     *
     */
    void showProgress(boolean flag, String message);

    /**
     * 隐藏进度条
     */
    void hideProgress();

    /**
     * 根据资源文件id弹出toast
     *
     * @param resId 资源文件id
     */
    void showToast(int resId);

    /**
     * 根据字符串弹出toast
     *
     * @param msg 提示内容
     */
    void showToast(String msg);

    String getStringbyid(int resId);

}
