package com.jin.jooq.controller;

import com.jin.jooq.util.CookieUtil;
import com.jin.jooq.util.WechatUtil;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*测试调度*/
@Controller
public class WechatGenerateController {
    private static Logger logger= LoggerFactory.getLogger(WechatGenerateController.class);

    @Value("${cookie.unionid}")
    private String wxUnionId;
    @Value("${cookie.exp}")
    private int exp;

    /**
     * 注册页面, 成功微信code
     * @return
     */
    @RequestMapping(value = "/register_view", method = RequestMethod.GET)
    public String registerGetWechatUnionId(HttpServletResponse response){
        logger.info("第一步，生成code");
        String authizedUrl = WechatUtil.generateAuthizedLink("/authizedregist");
        if (StringUtils.isNotBlank(authizedUrl))
        {
            // 在微信生成code成功，重定向到 “/authizedregist” 获取unionId
            return "redirect:" + authizedUrl;
        }
        // 在微信生成code获取失败，直接进入注册页面
        return "framework/register";
    }

    /**
     * 根据在微信生成的code, 获取unionId，保存到cookie
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/authizedregist")
    public String registerWechatAuthized(HttpServletRequest request, HttpServletResponse response){
        logger.info("第二步，获取code");
        String code = request.getParameter("code");
        logger.info("第三步，获取unionId");
        String unionId = WechatUtil.getUnionId(code);

        if (StringUtils.isNotBlank(unionId)) {
            logger.info("用户注册获取unionId成功>>>>>>>【unionId = " + unionId + "】");
            // 保存到cookie, 默认保存七天
            CookieUtil.setCookie(response, wxUnionId, unionId, exp);
        }
        return "framework/register";
    }


    /**
     * @author: jinliang
     * @create: 2018/6/20 18:37
     * @desc: 生成从微信获取code的url，通过生成的url调用微信
     * @param
     **/
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(ModelMap modelMap){
        String authizedUrl = WechatUtil.generateAuthizedLink("/authizedindex");
        if (StringUtils.isNotBlank(authizedUrl)){
            return "redirect:" + authizedUrl;
        }
        return "view/memberCenter";
    }

    /**
     * @author: jinliang
     * @create: 2018/6/20 18:37
     * @desc: 通过微信回调，直接获取code
     * @param
     **/
    @RequestMapping(value = "/authizedindex")
    public String wechatAuthized(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        logger.info("第一步，获取code");
        String code=request.getParameter("code");
        return code;
    }

}
