<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="static/jquery/jquery-1.11.3.js" type="text/javascript"></script>
	<title>ajax示例</title>
	
	<script type="text/javascript">

		$(function(){
			$('input[id=ajaxUpLoadFile2]').change(function() {
				$("#fileName").val(document.getElementById("ajaxUpLoadFile2").files[0].name);
			});
			
		});

	</script>
	<script type="text/javascript">

	var url = "http://localhost:8080/Servlet/demo/httpRequest";
	var params = {};
	params["name"] = "wa&ng";
	params["address"] = "anhuisuzhou";

	//ajaxRequest(url, "POST", params);
	function uploadFile() {

		var url = "http://localhost:8080/Servlet/demo/httpRequest";
		var formData = new FormData();
		formData.append("name", "liucheng");
		formData.append("address", "jiangsu");

		//获取文件对象
		var fileObj = document.getElementById("ajaxUpLoadFile").files[0];
		var fileObj3 = document.getElementById("ajaxUpLoadFile3").files[0];
		formData.append("fileName", fileObj.name);
		formData.append("name", fileObj);
		formData.append("name", fileObj3);
		
		ajaxRequest(url, "POST", formData);
	}
	
	function ajaxRequest(url, method, params) {


		var ajaxObject = getAjaxObject();
		if (ajaxObject == null) {

			alert("Your browser does not support XMLHTTP.");
			return;
		}
		
		ajaxObject.onreadystatechange = callback;
		if (method == "GET") {

			url += ("?" + formParams(params)); 
			ajaxObject.open(method, url);
			ajaxObject.send(null);
		} else if (method == "POST") {

			ajaxObject.open(method, url);
			//设置请求消息头为如下的值：
            //Content-Type:application/x-www-form-urlencoded
           // ajaxObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			//ajaxObject.send(formParams(params));
			
			ajaxObject.send(params);
		}
		
		
	}

	//获取ajax对象  经验证  在chrome和IE下xmlHttpRequest = new XMLHttpRequest();都不会产生异常
	function getAjaxObject() {

		var xmlHttpRequest = null;
		
		if (window.XMLHttpRequest) {
			// code for all new browsers
			xmlHttpRequest=new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			// code for IE5 and IE6
		    xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			//alert("Your browser does not support XMLHTTP.");
		}
	    
	    return xmlHttpRequest;

	};

	//格式化参数  参数格式为map结构  var params = {}; params["name"] = "wang";
	function formParams(data) {

		var array = [];
		for (var key in data) {

			array.push(encodeURIComponent(key) + "=" + encodeURIComponent(data[key]));
		}
		console.log(array.join("&"));
		return array.join("&");
	};

	//回调函数
	function callback() {

		if(this.readyState == 4) {
	        
            if(this.status == 200) {

               // alert("响应头部:" + this.getAllResponseHeaders());
                alert("响应内容:" + this.responseText);
            } else {

	            alert("HTTP请求错误！错误码："+this.status);
            }
        } else {

			//alert("readyState不等于4");
        }
	};
	</script>
	
	
	
	<!-- js 原生ajax请求 简单版 -->
	<!-- //原生ajax跨域访问时允许返回 response.addHeader("Access-Control-Allow-Origin", "http://localhost:8081");-->
	<script type="text/javascript">

		//获取ajax对象  经验证  在chrome和IE下xmlHttpRequest = new XMLHttpRequest();都不会产生异常
		function getAjaxObject() {

			var xmlHttpRequest = null;
			
			if (window.XMLHttpRequest) {
				// code for all new browsers
				xmlHttpRequest=new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				// code for IE5 and IE6
			    xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
			} else {
				//alert("Your browser does not support XMLHTTP.");
			}
		    
		    return xmlHttpRequest;

		};
		//ajax();
		//ajax post请求
		function ajax() {

			var url = "http://localhost:8080/Servlet/demo/httpRequest";
			var formData = new FormData();
			formData.append("name", "liucheng");
			formData.append("address", "jiangsu");
			
			var ajaxObject = getAjaxObject();
			if (ajaxObject == null) {

				alert("Your browser does not support XMLHTTP.");
				return;
			}
			ajaxObject.open("post", url);
//			ajaxObject.onreadystatechange = callback;
			ajaxObject.onreadystatechange = function () {
		        if(ajaxObject.readyState == 4) {
			        
		            if(ajaxObject.status == 200) {

		               // alert("响应头部1:" + ajaxObject.getAllResponseHeaders());
		                //alert("响应内容:" + ajaxObject.responseText);
		            } else {

			            alert("HTTP请求错误！错误码："+ajaxObject.status);
		            }
		        } else {

					//alert("readyState不等于4");
		        }
		    };
			ajaxObject.send(formData);

		};

		//回调函数
		function callback() {

			if(this.readyState == 4) {
		        
	            if(this.status == 200) {

	                alert("响应头部:" + this.getAllResponseHeaders());
	                alert("响应内容:" + this.responseText);
	            } else {

		            alert("HTTP请求错误！错误码："+this.status);
	            }
	        } else {

				//alert("readyState不等于4");
	        }
		}
	</script>
</head>
<body>
	
	<input type="button" id="originalAjax" value="原生ajax请求" />
	
	<input type="file" id="ajaxUpLoadFile" />
	<input type="file" id="ajaxUpLoadFile3" />
	<input type="button" value="xmlHttpRequestFormData上传文件" onclick="uploadFile();">
	
	<label>读取文件</label>
	<input type="file" id="ajaxUpLoadFile2" style="display: none;" />
	<input id="fileName" type="text" readonly="readonly" >
	<a onclick="$('input[id=ajaxUpLoadFile2]').click();">浏览...</a>
	<div>
	</div>
</body>
</html>