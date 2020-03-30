package com.easys.course.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easys.course.core.model.Course;
import com.easys.course.endpoint.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/admin/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage course")
public class CourseController {
	private final CourseService courseService;

	@GetMapping
	@ApiOperation(value = "List all available courses", response = Course[].class)
	public ResponseEntity<Iterable<Course>> list(Pageable pageable) {
		return new ResponseEntity<>(courseService.list(pageable), HttpStatus.OK);
	}

}
