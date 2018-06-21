package com.jin.jooq.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: CookieUtil.java
 * @Description: Cookie工具类
 * @author: huangchuang
 * @version: v1.0
 * @create at: 2017年8月2日 下午5:13:27
 * @reviewer:
 * @review at:
 */
public class CookieUtil
{
	/**
	 * @Title: getCookieByName
	 * @Description: 从cookie获取值
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request, String cookieName)
	{
		String value = "";
		Cookie[] cookies = request.getCookies();
		if (null != cookies)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName()))
				{
					value = cookie.getValue();
				}
			}
		}
		return value;
	}

	/**
	 * @Title: setCookie
	 * @Description: 新增cookie
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param exp
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int exp)
	{
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(exp);
		response.addCookie(cookie);
	}

	/**
	 * @Title: delCookie
	 * @Description: 删除cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String cookieName)
	{
		Cookie[] cookies = request.getCookies();
		if (null != cookies)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName()))
				{
					cookie.setValue(null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
}
