package com.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.util.Length;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.webank.cfpd.tool.common.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 功能 ：测试http请求的servlet
 * @author zwwang  2016-11-28 下午8:09:42
 */
public class HttpResponseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 9172592498145332149L;

	/**
	 * 处理以get方式提交的申请
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		doPost(request, response);
	}
	
	/**
	 * 功能 : 处理post方式的请求
	 * 开发：zwwang 2016-11-28 下午8:09:58
	 * @param request
	 * @param response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		//原生ajax跨域访问时允许返回
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		
		//得到path,诸如：http://localhost:8080/ch01/ShowBaby.action
//		String path = request.getServletPath();
		
		System.out.println("requestContentType:" + request.getContentType());
		String requestContentType = request.getContentType();
		
		String name = null;
		String address = null;
		String attachments = null;
		if (requestContentType != null && requestContentType != "" && requestContentType.toLowerCase().startsWith("application/json")) {
			String jsonParameter = readJsonString(request);
			
			//获取 json化格式请求参数
			
			//json字符串转换为json对象
			JSONObject jsonObject = JSONObject.fromObject(jsonParameter);
			
			name = jsonObject.getString("name");
			address = jsonObject.getString("address");
			
		} else {
			
			name = request.getParameter("name");
			address = request.getParameter("address");
			attachments = request.getParameter("attachments");
		}
		
		System.out.println("get param name:" + name);
		System.out.println("get param address:" + address);
		
		//multipart/form-data 方式提交文件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}

		Map<String, Object> requestParams = new HashMap<String, Object>();
		for (FileItem fileItem : items) {
			
			byte[] byteArray = new byte[1024 * 1024];
			int length = 0;
			if (fileItem.isFormField()) {
				System.out.println(fileItem.getFieldName() + ":" + fileItem.getString());
			} else {
				BufferedOutputStream bufferedOutputStream = null;
				try {
					bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("F:\\" + fileItem.getName()));
					InputStream inputStream = fileItem.getInputStream();
					while ((length = inputStream.read(byteArray)) != -1) {
						
						bufferedOutputStream.write(byteArray, 0, length);
					}
					bufferedOutputStream.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					
					try {
						bufferedOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		
		//httpClient post方式 文件被编码成字符串提交  接收后解码成字节数组
		/*List<Map<String, String>> attachmentList = JsonUtil.format(attachments, List.class);
		for (Map<String, String> map : attachmentList) {
			
			byte[] fileByte = null;
			FileOutputStream outputStream = null;
			try {
				fileByte = new BASE64Decoder().decodeBuffer(map.get("Data"));
				outputStream = new FileOutputStream("F:\\" + map.get("Name"));
				outputStream.write(fileByte);
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}*/
		
		
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//返回字符串类型结果
		out.write("post Response success! name=" + name + ",address=" + address);		
		//返回json化的数据
//		out.write("{success:true}");
		
	}
	
	/**
	 * 功能 : 接收post方式的http请求 json格式的参数
	 * 开发：zwwang 2016-11-25 下午5:38:14
	 * @param request
	 * @return
	 */
	public String readJsonString(HttpServletRequest request) {
		
		StringBuffer json = new StringBuffer();
		String line = null;
		
		try {
			
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("jsonParameter:" + json.toString());
		
		return json.toString();
		
	}

}
