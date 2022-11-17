package com.lcwd.boot.controller;

import java.io.WriteAbortedException;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Server;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lcwd.boot.controller.model.User;

@RestController
public class UserController {

	// add user

	// json->javaobject->deserialization
	// java->json=> serialization
//	@RequestMapping("/add-user")
//	public String addUser( @RequestBody Map<String, Object> user ) {
//		
//		System.out.println("add user method works");
//		System.out.println(user);
//		
//		return "user is added";
//	}

//	@RequestMapping("/add-user")
//	public User addUser(   @RequestBody User user) {
//
//		System.out.println("add user method works");
//		System.out.println(user);
//
//		return user;
//	}

	//@RequestMapping(value="/add-user",method = RequestMethod.POST)
	@PostMapping("/add-user")
	public String addUser(@RequestParam("userId") int id, @RequestParam int userToken

	) {

		System.out.println("add user method works");
		System.out.println("user id " + id);
		System.out.println("user token " + userToken);

		return "done";
	}

	//@RequestMapping(value="/list-data",method = RequestMethod.GET)
	@GetMapping("/list-data")
	public String listTest(@RequestBody List<String> list) {
		System.out.println(list);
		return "Testing list";
	}

	// example of query parameters

	//@RequestMapping(value="/query", method = RequestMethod.DELETE)
	@DeleteMapping("/query")
	public String queryParamters(@RequestParam("userId") int id,
			@RequestParam(value = "postId", required = false, defaultValue = "11") int pId) {

		System.out.println("User id : " + id);
		System.out.println("Post id : " + pId);
		return "params done";
	}

	// example of path variable
	@RequestMapping("/delete-user/{userId}/post/{postId}")
	public String deleteUser(@PathVariable("userId") int uid, @PathVariable int postId) {

		System.out.println("user id " + uid);
		System.out.println("post id : " + postId);
		return "User deleted";
	}

	// upload file
	@RequestMapping("/upload-file")
	public String uploadFile(@RequestParam("image") MultipartFile file) {

		System.out.println("File name : " + file.getOriginalFilename());
		System.out.println("File Type : " + file.getContentType());
		if (file.getContentType().equals("application/pdf")) {

//			  write on Server 
			return "uploaded";
		} else {
			return "File not allowed : upload only pdf";
		}

//		file write
//		System.out.println("File size " + file.getSize());
//		return file.getOriginalFilename();
	}

}
