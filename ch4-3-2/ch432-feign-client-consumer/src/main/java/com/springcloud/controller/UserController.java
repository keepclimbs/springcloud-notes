package com.springcloud.controller;

import com.springcloud.model.User;
import com.springcloud.service.FileUploadFeignService;
import com.springcloud.service.UserFeignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserFeignService userFeignService;

	@Autowired
	private FileUploadFeignService fileUploadFeignService;

	/**
	 * 用于演示Feign的Get请求多参数传递
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@RequestBody @ApiParam(name="用户",value="传入json格式",required=true) User user){
		return userFeignService.addUser(user);
	}

	/**
	 * 用于演示Feign的Post请求多参数传递
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser( @RequestBody @ApiParam(name="用户",value="传入json格式",required=true) User user){
		return userFeignService.updateUser(user);
	}

	/**
	 * 用于演示Feign的文件上传
	 * @return
	 */
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "文件上传", notes = "请选择文件上传" )
	public String imageUpload(@ApiParam(value="文件上传",required = true) MultipartFile file ) {
		return fileUploadFeignService.fileUpload(file);
	}

}
