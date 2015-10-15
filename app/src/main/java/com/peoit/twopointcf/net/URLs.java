package com.peoit.twopointcf.net;

/**
 * URL路径处理类
 *
 * @author ling
 */
public class URLs {
    public static final String HOST = "http://192.168.0.168:8080";
//    public static final String HOST = "http://115.28.245.170:8080/honeycomb";
    public static final String PROJECT_NAME = "";
    public static final String API = "";

    // 用户模块
    /**
     * 注册
     */
    public static final String USER_SIGNUP = HOST + "/api/user/createUser";
    /**
     * 获取验证码
     */
    public static final String USER_VLIDATECODE = HOST + "/api/user/identifyingCode";
    /**
     * 验证验证码
     */
    public static final String USER_VALIDATECODE = HOST + "/api/user/validateCode";
    /**
     * 验证手机号是否已被注册
     */
    public static final String USER_PHONEVALIDATE = HOST + "/api/user/phoneValidate";
    /**
     *验证登录名是否可用
     */
    public static final String USER_USERNAMEVAILDATE = HOST + "/api/user/userNameValidate";
    /**
     * 登录
     */
    public static final String USER_SIGNIN = HOST + "/api/user/login";
    /**
     * 修改密码或授权密码
     */
    public static final String USER_CHANGEPASSWORD = HOST + "/api/user/changePassword";
    /**
     * 修改用户简介
     */
    public static final String CHANGEUSERCAPTION = HOST + "/api/user/changeUserCaption";
    /**
     * 修改邮箱
     */
    public static final String CHANGEEMAIL = HOST + "/api/user/changeEmail";
    /**
     * 修改手机号
     */
    public static final String CHANGEPHONE= HOST + "/api/user/changeMobile";
    /**
     * 修改头像
     */
    public static final String CHANGEAVATAR= HOST + "/api/user/changeAvatar";
    /**
     * 获取用户级别
     */
    public static final String GETUSERLEVEL= HOST + "/api/user/getUserLevel";
    /**
     * 获取用户实名认证状态
     */
    public static final String GETUSERISVERIFIED= HOST + "/api/user/isVerified";
    /**
     * 第三方登陆
     */
    public static final String OAUTH_SIGNIN = HOST + "xxx";

    /**
     * 上传实名认证信息
     */
    public static final String USER_VERIFYID = HOST + "/api/user/verifyId";

    /**
     * 创建项目
     */
    public static final String CREATEPROJECT = HOST + "/api/project/createProject";
    /**
     * 修改项目
     */
    public static final String UPDATEPROJECT = HOST + "/api/project/updateProject";
    /**
     * 发现项目
     * 我发布的项目 参数publisherId
     */
    public static final String FINDPROJECT = HOST + "/api/project/findProjects";

    /**
     * 投资项目
     */
    public static final String INVESTPROJECT = HOST + "/api/project/invest";

    /**
     * 查询已投资项目
     */
    public static final String FINDINVESTEDPROJECT = HOST + "/api/project/findInvestedProject";


    /**
     * 资讯中心
     */
    public static final String FINDINFORMATIONS = HOST + "/api/information/findInformations";

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
