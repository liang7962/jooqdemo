package com.jin.jooq.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WechatConstants {

    private static String askAuthizedUrl; // 用于获取code
    private static String agreeAuthizedUrl; // 获取授权信息
    private static String accessTokenUrl; // 获取accessToken
    private static String userInfoUrl; //获取用户信息


    private static String domain;
    private static String authizedUrl;
    private static String appId;
    private static String appSecret;

    @Value("${wecahrt.code.url}")
    public void setAskAuthizedUrl(String askAuthizedUrl) {
        WechatConstants.askAuthizedUrl = askAuthizedUrl;
    }

    @Value("${wechat.agree.authized.url}")
    public void setAgreeAuthizedUrl(String agreeAuthizedUrl) {
        WechatConstants.agreeAuthizedUrl = agreeAuthizedUrl;
    }

    @Value("${wechat.access.token.url}")
    public void setAccessTokenUrl(String accessTokenUrl) {
        WechatConstants.accessTokenUrl = accessTokenUrl;
    }

    @Value("${wechat.user.info.url}")
    public void setUserInfoUrl(String userInfoUrl) {
        WechatConstants.userInfoUrl = userInfoUrl;
    }

    @Value("${wechat.domain}")
    public void setDomain(String domain) {
        WechatConstants.domain = domain;
    }

    @Value("${wechat.login.authizedurl}")
    public void setAuthizedUrl(String authizedUrl) {
        WechatConstants.authizedUrl = authizedUrl;
    }

    @Value("${wechat.appid}")
    public void setAppId(String appId) {
        WechatConstants.appId = appId;
    }

    @Value("${wechat.appsecret}")
    public void setAppSecret(String appSecret) {
        WechatConstants.appSecret = appSecret;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getAuthizedUrl() {
        return authizedUrl;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getAskAuthizedUrl() {
        return askAuthizedUrl;
    }

    public static String getAgreeAuthizedUrl() {
        return agreeAuthizedUrl;
    }

    public static String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public static String getUserInfoUrl() {
        return userInfoUrl;
    }
}
