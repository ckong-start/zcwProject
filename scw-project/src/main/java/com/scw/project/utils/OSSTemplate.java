package com.scw.project.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@ConfigurationProperties(prefix = "oss")
@ApiModel("文件上传下载的模板类")
@Slf4j
public class OSSTemplate {
	// scheme、endpoint、accessKeyId、accessKeySecret、bucketName、path
	@ApiModelProperty("访问协议")
	private String scheme;
	@ApiModelProperty("访问路径")
	private String endpoint;
	@ApiModelProperty("keyId")
	private String accessKeyId;
	@ApiModelProperty("KeySecret")
	private String accessKeySecret;
	@ApiModelProperty("桶名")
	private String bucketName;
	@ApiModelProperty("文件夹名")
	private String path;

	// 文件上传的方法
	public String uploadFile(MultipartFile file) {

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(scheme + endpoint, accessKeyId, accessKeySecret);

		// 上传文件流。
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileName = System.currentTimeMillis() + "_"
				+ UUID.randomUUID().toString().replace("_", "").substring(0, 6)
				+ "_" + file.getOriginalFilename();
		ossClient.putObject(bucketName, path + fileName, is);

		// 关闭OSSClient。
		ossClient.shutdown();
		// 返回保存的图片的url地址
		// 协议  bucketName.endPoint/保存图片的路径/文件名
		//例如：http://scw-sh-java-191010.oss-cn-shanghai.aliyuncs.com/scw-project/111.gif
		String uploadFilePath = scheme + bucketName + "." + endpoint + "/" + path + fileName;
		log.info("图片上传的地址：{}", uploadFilePath);
		return uploadFilePath;
	}
}
