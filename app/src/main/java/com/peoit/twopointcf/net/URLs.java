package com.peoit.twopointcf.net;

/**
 * URL路径处理类
 *
 * @author ling
 */
public class URLs {
//    public static final String HOST = "http://192.168.0.192:8081";
    public static final String HOST = "http://cfc.jujia999.com:8080";
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
     * 验证登录名是否可用
     */
    public static final String USER_USERNAMEVAILDATE = HOST + "/api/user/userNameValidate";
    /**
     * 验证登录名是否可用
     */
    public static final String USER_VERIFYEMAIL = HOST + "/api/user/verifyEmail";
    /**
     * 登录
     */
    public static final String USER_SIGNIN = HOST + "/api/user/login";
    /**
     * 修改密码或授权密码
     */
    public static final String USER_CHANGEPASSWORD = HOST + "/api/user/changePassword";
    /**
     * 重置 登录密码或授权密码
     */
    public static final String USER_RESETPASSWORD = HOST + "/api/user/resetPassword";
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
    public static final String CHANGEPHONE = HOST + "/api/user/changeMobile";
    /**
     * 修改用户头像
     */
    public static final String CHANGEAVATAR = HOST + "/api/user/changeAvatar";
    /**
     * 获取用户级别
     */
    public static final String GETUSERLEVEL = HOST + "/api/user/getUserLevel";
    /**
     * 获取用户实名认证状态
     */
    public static final String GETUSERISVERIFIED = HOST + "/api/user/isVerified";

    /**
     * 上传实名认证信息
     */
    public static final String USER_VERIFYID = HOST + "/api/user/verifyId";
    /**
     * 验证用户授权密码
     */
    public static final String VEIFYAUTHORIZATIONCODE = HOST + "/api/user/veifyAuthorizationCode";


    /**
     * 创建项目
     */
    public static final String CREATEPROJECT = HOST + "/api/project/createProject";
    /**
     * 修改项目
     */
    public static final String UPDATEPROJECT = HOST + "/api/project/updateProject";
    /**
     * 取消项目
     */
    public static final String CANCELPROJECT = HOST + "/api/project/cancelProject";
    /**
     * 启动项目
     */
    public static final String STARTPROJECT = HOST + "/api/project/startProject";
    /**
     * 发现项目
     * 我发布的项目 参数publisherId
     */
    public static final String FINDPROJECT = HOST + "/api/project/findProjects";

    /**
     * 投资发现的banner
     */
    public static final String BANNERLIST = HOST + "/api/banner/getBannerList";

    /**
     * 投资项目
     */
    public static final String INVESTPROJECT = HOST + "/api/project/invest";

    /**
     * 查询已投资项目
     */
    public static final String FINDINVESTEDPROJECT = HOST + "/api/project/findInvestedProject";

    /**
     * 取消投资
     */
    public static final String CANCELINVEST = HOST + "/api/project/cancelInvest";

    /**
     * 关注项目
     */
    public static final String CONCERNPROJECT = HOST + "/api/project/concernProject";
    /**
     * 判断用户是否关注项目
     */
    public static final String ISCANCELPROJECT = HOST + "/api/project/isConcern";
    /**
     * 用户关注项目列表
     */
    public static final String FINDCONCERNEDPROJECT = HOST + "/api/project/findConcernedProject";
    /**
     * 资讯中心
     */
    public static final String FINDINFORMATIONS = HOST + "/api/information/findInformations";

    /**
     * 支付投资项目保证金
     */
    public static final String PAYMARGIN = HOST + "/api/project/payMargin";

    /**
     * 发布评论
     */
    public static final String PUBLISHCOMMENT= HOST + "/api/comments/publishcomments";
    /**
     * 获取评论
     */
    public static final String LISTCOMMENTS = HOST + "/api/comments/listcomments";



    /**
     * 经营管理
     */
    /**
     * 获取项目公告列表
     */
    public static final String LISTNOTICE= HOST + "/api/notice/listnotice";
    /**
     * 获取财务报表接口
     */
    public static final String LISTREPORT= HOST + "/api/finance/listreport";
    /**
     * 发布财务报告
     */
    public static final String PUBLISHREPORT = HOST + "/api/finance/publishreport";
    /**
     * 发布项目公告
     */
    public static final String PUBLISHNOTICE = HOST + "/api/notice/publishnotice";
    /**
     * 获取投资成功完成的项目
     */
    public static final String FINDINVESTEDSUCCESSPROJECT = HOST + "/api/project/findInvestedSuccessProject";

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
