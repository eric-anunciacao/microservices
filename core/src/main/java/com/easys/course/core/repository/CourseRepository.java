package com.easys.course.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.easys.course.core.model.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
