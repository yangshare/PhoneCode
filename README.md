# PhoneCode
手机短信验证接口

###   java-API
java.util目录有NetWork.java和test.java。test.java类依赖于NetWork.java类，当然NetWork.java主要负责网络通讯完全可以自己去定义。使用示例如下：

```
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
```

###   web-API
web.util目录有test.html作为示例代码，本示例默认使用ajax通讯，使用者当然也可以根据需要选择自己的通讯方式，甚至可以直接拼接url在浏览器直接访问api。使用示例如下：

```
<!DOCTYPE HTML>
<html>

	<head>
		<meta charset="utf-8">
		
		<title>短信验证web-API</title>
		
	</head>

	<body>
		<div class="header"></div>
		<div class="loginWraper">
			<div id="loginform" class="loginBox">
				<div class="form form-horizontal">
					<div class="row cl">
						<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
						<div class="formControls col-xs-8">
							<input id="account" type="text" placeholder="手机号" class="input-text size-L">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
						<div class="formControls col-xs-8">
							<input id="pwd" name="" type="password" placeholder="密码" class="input-text size-L">
						</div>
					</div>
					<div class="row cl">
						<div class="formControls col-xs-8 col-xs-offset-3">
							<input id="code" class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
							<input id="getCode" type="button" class="btn btn-success radius size-L" value="&nbsp;获取验证码&nbsp;">
						</div>
					</div>
					
					<div class="row cl">
						<div class="formControls col-xs-8 col-xs-offset-3">
							<input id="regist" type="button" class="btn btn-default radius size-L" value="&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	


	
		
		<script>
			//点击注册按钮
			$("#regist").click(function() {
				
						type: 'post',
						url:'http://127.0.0.1:8080/PhoneCode/phonecode/base!valida.action',
						data: {
							phoneNum:"157********",// 手机号
							code:"******"//验证码
						},
						
						dataType: 'json',
						
						success: function(data) {
							console.log(JSON.stringify(data));
							
						},
						error: function() {
							toastr.warning('网络异常');
						}
					});
				
		
			//点击获取验证码按钮
			$("#getCode").click(function() {
			
					$.ajax({
						type: 'post',
						url:'http://127.0.0.1:8080/PhoneCode/phonecode/base!sendCode.action',
						data: {
							phoneNum:"157********",// 手机号
							tokenid:"******"//唯一标识
						},
						
						dataType: 'json',
						
						success: function(data) {
							
							console.log(JSON.stringify(data));
						},
						error: function() {
							toastr.warning('网络异常');
						}
					});
				
			});
		</script>
	</body>

</html>
```
