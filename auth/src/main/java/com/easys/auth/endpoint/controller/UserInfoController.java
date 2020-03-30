package com.easys.auth.endpoint.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easys.course.core.model.ApplicationUser;

@RestController
@RequestMapping("user")
public class UserInfoController {

	@GetMapping(path = "info")
	public ResponseEntity<ApplicationUser> getUserInfo(Principal principal) {
		ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal)
				.getPrincipal();
		return new ResponseEntity<>(applicationUser, HttpStatus.OK);
	}
}
