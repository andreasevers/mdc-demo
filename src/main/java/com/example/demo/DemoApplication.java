package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Configuration
class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	HandlerInterceptor yourInjectedInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				MDC.put("behaviour","chill");
				return true;
			}
		});
	}
}

@RestController
class DemoController {

	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/hello")
	public String getHello() {
		logger.info("test");
		return "hello";
	}
}

@Configuration
class Runner implements CommandLineRunner {

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("test system out log");

	}
}