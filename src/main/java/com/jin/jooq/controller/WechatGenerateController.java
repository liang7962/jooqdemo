package com.jin.jooq.controller;

import com.jin.jooq.util.WechatUtil;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
