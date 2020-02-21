package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class loadController {
	/**
	 * MultipartFile类用来接收 上传的文件
	 */
	@RequestMapping("/upload")
	public String upload(@RequestParam("username")String username, @RequestParam("photo")MultipartFile photo) {
		System.out.println("username is " + username);
		//上传文件的名
		System.out.println("文件名： " + photo.getOriginalFilename());
		//photo.getName()是获取input：file标签的name，即photo
		if (!photo.isEmpty()) {
			try {
				photo.transferTo(new File("d:\\" + photo.getOriginalFilename()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/upload.jsp";
	}
	
	//ResponseEntity类可以操作响应头，响应行，响应体。
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpSession session) {
		ServletContext servletContext = session.getServletContext();
		//读取要下载的文件
		InputStream is = servletContext.getResourceAsStream("/file/jsp练习一.jsp");
		//将输入流中的数据，转化为字节数组
		byte[] body = null;
		try {
			//使用IO工具类转化流为字节数组
			body = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//告诉客户端，收到数据后如何处理
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "attachment;filename=jsp练习一.jsp");
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
		return responseEntity;
	}
}
