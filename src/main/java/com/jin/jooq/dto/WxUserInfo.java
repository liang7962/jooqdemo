package com.jin.jooq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: huangchuang
 * @Description: 微信个人用户信息
 * @Date: 2018/4/4 17:51
 * @Modified By: huangchuang
 */
public class WxUserInfo implements Serializable {

    private static final long serialVersionUID = -1586629803480325109L;
    private String nickname;
    private String unionid;
    private String openid;
    private String country;
    private String city;
    private String qr_scene;
    private String subscribe;
    private String subscribe_time;
    private String subscribe_scene;

    private String province;
    private List<Object> tagid_list;
    private String sex;
    private String groupid;
    private String language;
    private String remark;

    private String headimgurl;
    private String qr_scene_str;

    public WxUserInfo() {
    }

    public WxUserInfo(String nickname, String unionid, String openid, String country, String city, String qr_scene, String subscribe, String subscribe_time, String subscribe_scene, String province, List<Object> tagid_list, String sex, String groupid, String language, String remark, String headimgurl, String qr_scene_str) {
        this.nickname = nickname;
        this.unionid = unionid;
        this.openid = openid;
        this.country = country;
        this.city = city;
        this.qr_scene = qr_scene;
        this.subscribe = subscribe;
        this.subscribe_time = subscribe_time;
        this.subscribe_scene = subscribe_scene;
        this.province = province;
        this.tagid_list = tagid_list;
        this.sex = sex;
        this.groupid = groupid;
        this.language = language;
        this.remark = remark;
        this.headimgurl = headimgurl;
        this.qr_scene_str = qr_scene_str;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQr_scene() {
        return qr_scene;
    }

    public void setQr_scene(String qr_scene) {
        this.qr_scene = qr_scene;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getSubscribe_scene() {
        return subscribe_scene;
    }

    public void setSubscribe_scene(String subscribe_scene) {
        this.subscribe_scene = subscribe_scene;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<Object> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<Object> tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getQr_scene_str() {
        return qr_scene_str;
    }

    public void setQr_scene_str(String qr_scene_str) {
        this.qr_scene_str = qr_scene_str;
    }

    @Override
    public String toString() {
        return "WxUserInfo{" +
                "nickname='" + nickname + '\'' +
                ", unionid='" + unionid + '\'' +
                ", openid='" + openid + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", qr_scene='" + qr_scene + '\'' +
                ", subscribe='" + subscribe + '\'' +
                ", subscribe_time='" + subscribe_time + '\'' +
                ", subscribe_scene='" + subscribe_scene + '\'' +
                ", province='" + province + '\'' +
                ", tagid_list=" + tagid_list +
                ", sex='" + sex + '\'' +
                ", groupid='" + groupid + '\'' +
                ", language='" + language + '\'' +
                ", remark='" + remark + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", qr_scene_str='" + qr_scene_str + '\'' +
                '}';
    }
}
