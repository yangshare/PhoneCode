package com.wiswit.core.org.action;

import java.util.HashMap;
import java.util.Map;

import com.yangcheng.utils.NetWork;

/**
 * @author 杨成 E-mail:yangcheng@wiswit.com
 * @version 创建时间：2017年3月27日 上午12:17:44 类说明
 */

public class test {

	/**
	 * 测试类：如下示例进行短信的访问和验证码的验证
	 * @param args
	 */
	public static void main(String[] args) {
		// 短信平台访问-发送短信
		Map<String, String> maps1 = new HashMap<String, String>();
		maps1.put("phoneNum", "157********");// 手机号
		maps1.put("tokenid", "******");// 唯一标识
		String flag1 = NetWork.post("http://127.0.0.1:8080/PhoneCode/phonecode/base!sendCode.action", maps1);
		System.out.println("反馈信息==" + flag1);
		// 短信平台验证-验证验证码
		Map<String, String> maps2 = new HashMap<String, String>();
		maps2.put("phoneNum", "157********");// 手机号
		maps2.put("code", "660163");// 验证码
		String flag2 = NetWork.post("http://127.0.0.1:8080/PhoneCode/phonecode/base!valida.action", maps2);
		System.out.println("反馈信息==" + flag2);

	}

}
