package com.jin.jooq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin.jooq.constants.WechatConstants;
import com.jin.jooq.dto.WechatAccessToken;
import com.jin.jooq.dto.WxUserInfo;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class WechatUtil {

    private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    //生成从微信获取获取code的url
    //翻译：通用自动链路
    public static String generateAuthizedLink(String redirectMethod) {
        String url = WechatConstants.getAskAuthizedUrl();
        try {
            if (StringUtils.isNotBlank(url)) {
                String redirect_url = URLEncoder.encode(WechatConstants.getDomain() + (StringUtils.isNotBlank(redirectMethod) ? redirectMethod : WechatConstants.getAskAuthizedUrl()), "utf-8");
                logger.info("生成从微信回调的idvoms的url,url=" + redirect_url);
                String state = "1";
                /*scope分为两种：一种是静默方式（snsapi_base）；一种是非静默方式（snsapi_userinfo），需要用户去手动点击同意才能获取用户的信息String scope = "snsapi_base";*/
                String scope = "snsapi_base";
                url = url.replace("APPID", WechatConstants.getAppId()).replace("REDIRECT_URI", redirect_url).replace("SCOPE", scope).replace("STATE", state);
                logger.info("生成从微信获取code的url，url=" + url);
            }
        } catch (Exception e) {
            logger.error("生成被微信回调的url：", e);
            return "";
        }
        return url;
    }

    /**
     * @param
     * @author: jinliang
     * @create: 2018/6/20 18:48
     * @desc: 获取微信授权信息
     **/
    public static JSONObject getWXAuthizedInfo(String code) {
        try {
            if (StringUtils.isNotBlank(code)) {
                String urlStr = WechatConstants.getAgreeAuthizedUrl();
                urlStr = urlStr.replace("APPID", WechatConstants.getAppId()).replace("CODE", code).replace("SECRET", WechatConstants.getAppSecret());
                logger.info("获取微信授权信息, url=" + urlStr);
                //获取用户微信授权信息
                String returnStr = connectionAuthized(urlStr);
                if (StringUtils.isNotBlank(returnStr)) {
                    logger.info("微信用户授权信息：【" + JSON.parseObject(returnStr) + "】");
                    return JSON.parseObject(returnStr);
                }
            }
        } catch (Exception e) {
            logger.error("同意授权登录，获取微信用户授权信息错误", e);
            return null;
        }
        return null;
    }

    /**
     * @param
     * @author: jinliang
     * @create: 2018/6/20 18:49
     * @desc: 微信通讯连接
     **/
    private static String connectionAuthized(String urlStr) {
        if (StringUtils.isNotBlank(urlStr)) {
            HttpURLConnection connection = null;
            StringBuilder sb = null;
            try {
                URL url = new URL(urlStr);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                // 取得输入流，并使用Reader读取
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                sb = new StringBuilder();
                String lines;
                //接受返回的数据
                while ((lines = bufferedReader.readLine()) != null) {
                    sb.append(lines);
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //断开连接
                connection.disconnect();
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * @param
     * @author: jinliang
     * @create: 2018/6/20 18:57
     * @desc: 获取openId
     **/
    public static String getOpenId(String code) {
        try {
            JSONObject jsonObject = getWXAuthizedInfo(code);
            if (null != jsonObject) {
                logger.info("微信获取用户openid：【" + jsonObject.getString("openid") + "】");
                return jsonObject.getString("openid");
            }
        } catch (Exception e) {
            logger.error("获取openId出错", e);
            return null;
        }
        return null;
    }

    /**
     * @param
     * @author: jinliang
     * @create: 2018/6/20 19:01
     * 获取wxUnionId
     * @desc: 第一步：通过openId获取WxAccessToken
     * 第二步：
     **/
    public static String getAccessToken() {
        String accessToken = "";
        String url = WechatConstants.getAccessTokenUrl();
        if (StringUtils.isNotBlank(url)) {
            url = url.replace("APPID", WechatConstants.getAppId()).replace("SECRET", WechatConstants.getAppSecret());
            logger.info("微信获取关注公众号的用户token, url=" + url);
            // 获取accessToken信息
            String returnStr = connectionAuthized(url);
            if (StringUtils.isNotBlank(returnStr)) {
                logger.info("微信获取关注公众号的用户token信息：【" + JSON.parseObject(returnStr) + "】");
                accessToken = JSON.parseObject(returnStr).getString("access_token");
                if (StringUtils.isNotBlank(accessToken)) {
                    WechatAccessToken.accessToken = JSON.parseObject(returnStr).getString("access_token");
                    WechatAccessToken.expiresIn = StringUtils.isNotBlank(JSON.parseObject(returnStr).getString("expires_in")) ? Long.valueOf(JSON.parseObject(returnStr).getString("expires_in")) : 0;
                    WechatAccessToken.time = DateFormartUtil.getFormartDate(DateFormartUtil.DATE_FORMART_YYYYMMDD_HHMMSS, new Date());
                }
            }
        }
        return accessToken;
    }

    /**
     * @param
     * @author: jinliang
     * @create: 2018/6/20 19:13
     * @desc: 根据code得到openID，WxAccessToken，得到url，后得到unionId
     **/
    public static WxUserInfo getUserInfo(String code) {
        String openId = getOpenId(code);
        if (StringUtils.isNotBlank(openId)) {
            String accessToken = getAccessToken();
            if (StringUtils.isNotBlank(accessToken)) {
                logger.info("获取关注公众号的用户信息 【accessToken=" + accessToken + ", openId=" + openId + "】");
                String url = WechatConstants.getUserInfoUrl();
                url = url.replace("'ACCESS_TOKEN", accessToken).replace("OPENID", openId);
                String returnStr = connectionAuthized(url);
                if (StringUtils.isNotBlank(returnStr)) {
                    WxUserInfo wxUserInfo = JSON.toJavaObject(JSON.parseObject(returnStr), WxUserInfo.class);
                    logger.info("微信获取关注公众号的用户信息：【" + wxUserInfo.toString() + "】");
                    return wxUserInfo;
                }
            }
        }
        return null;
    }

    /**
     * @param
     * @author: jinliang
     * @create: 2018/6/20 19:29
     * @desc: 获取accessToken
     **/
    public static String getWxAccessToken() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        String accessToken = "";
        try {
            long expiresIn = WechatAccessToken.expiresIn;
            String time = WechatAccessToken.time;
            if (StringUtils.isNotBlank(time) && expiresIn > 0 && StringUtils.isNotBlank(time)) {
                // 当前时间与上一次存储的时间差
                String timeDiff= DateFormartUtil.computationalTimeDiff(time);
                if (StringUtils.isNotBlank(timeDiff) && ((expiresIn - 200L)-Long.valueOf(timeDiff))<0){
                    // 已超时，去微信重新获取accessToken
                    accessToken = getAccessToken();
                }else {
                    // 未超时，直接从静态变量取出accessToken
                    accessToken = WechatAccessToken.accessToken;
                }
            }else {
                // WechatAccessToken对象中的accessToken为空，直接去微信获取accessToken
                accessToken = getAccessToken();
            }
        }
        catch (Exception e){
            logger.error("WechatUtil.getAccessToken() error!", e);
        }
        finally {
            lock.unlock();
        }
        return accessToken;
    }

}
