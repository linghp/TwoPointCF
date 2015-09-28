package com.peoit.twopointcf.net;

/**
 * URL路径处理类
 *
 * @author ling
 */
public class URLs {
    public static final String HOST = "http://192.168.0.176:8080/";
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
     * 验证验证码
     */
    public static final String USER_VALIDATECODE = HOST + "api/validateCode";
    /**
     * 登录
     */
    public static final String USER_SIGNIN = HOST + "api/user/login";
    /**
     * 修改密码
     */
    public static final String USER_CHANGEPASSWORD = HOST + "api/user/changePassword";
    /**
     * 第三方登陆
     */
    public static final String OAUTH_SIGNIN = HOST + "xxx";

    /**
     * 上传实名认证信息
     */
//    public static final String USER_SIGNIN = HOST + "xxx";



    /**
     * 创建项目
     */
    public static final String CREATEPROJECT = HOST + "api/project/createProject";
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
