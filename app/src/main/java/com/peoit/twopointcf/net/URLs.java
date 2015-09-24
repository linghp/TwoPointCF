package com.peoit.twopointcf.net;

/**
 * URL路径处理类
 *
 * @author ling
 */
public class URLs {
    public static final String HOST = "http://192.168.0.150:8080/";
    public static final String PROJECT_NAME = "";
    public static final String API = "";

    // 用户模块
    /**
     * 注册
     */
    public static final String USER_SIGNUP = HOST + "api/user/createUser";
    /**
     * 获取验证码
     */
    public static final String USER_VLIDATECODE = HOST + "api/identifyingCode";
    /**
     * 登录
     */
    public static final String USER_SIGNIN = HOST + "api/user/login";
    /**
     * 第三方登陆
     */
    public static final String OAUTH_SIGNIN = HOST + "xxx";

    /**
     * 拼接请求路径
     *
     * @PARAM URI
     * @RETURN
     */
    public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }

}
