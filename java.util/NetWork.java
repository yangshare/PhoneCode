package com.yangcheng.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;

/**
 * @author 杨成 E-mail:yangcheng@wiswit.com
 * @version 创建时间：2017年3月21日 上午11:30:01 该类实现网络接口的一个通讯
 */

public class NetWork {

	/*
	 * POST方法提交
	 * 
	 */
	public static String post(String url, Map<String, String> maps) {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);// post提交
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");// 在头文件中设置转码
		try {
			if (maps.size() > 0)
				post.setRequestBody(map2NameValuePair(maps, "UTF-8"));

			client.executeMethod(post);

			// 读取post返回信息
			InputStream is = post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String str;
			while ((str = br.readLine()) != null)
				sb.append(str);

			String result = sb.toString();
			
			return result;
		} catch (HttpException e) {
			return "网络异常";
		} catch (IOException e) {
			return "IO异常";
		} catch (Exception e) {
			return "异常";
		} finally {
			post.releaseConnection();
		}

	}
	
	public static String post(String url) {
		return post(url, null);
	}

	/**
	 * MAP类型数组转换成NameValuePair类型
	 * 
	 * @param map
	 *            MAP类型数组
	 * @return NameValuePair类型数组
	 */
	private static NameValuePair[] map2NameValuePair(Map<String, String> map, String charset) throws Exception {
		NameValuePair[] nameValuePair = new NameValuePair[map.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			nameValuePair[i++] = new NameValuePair(entry.getKey(), URLEncoder.encode(entry.getValue(), charset));
		}
		return nameValuePair;
	}
}
