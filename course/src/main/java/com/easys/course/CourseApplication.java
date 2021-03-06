package com.easys.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.easys.course.core.property.JwtConfiguration;

@SpringBootApplication
@EntityScan({ "com.easys.course.core.model" })
@EnableJpaRepositories({ "com.easys.course.core.repository" })
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("com.easys")
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
