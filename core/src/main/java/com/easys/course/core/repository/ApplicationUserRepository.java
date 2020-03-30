package com.easys.course.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.easys.course.core.model.ApplicationUser;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

	ApplicationUser findByUsername(String username);
}
