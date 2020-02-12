package com.ebix.easi.auto.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ebix.easi.auto.model.api.interceptor.BaseProjectInterceptor;

@Component
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	BaseProjectInterceptor baseProjectInterceptor;

	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
		      // LogInterceptor apply to all URLs.
		      registry.addInterceptor(baseProjectInterceptor);


		      //Examples

		      // Old Login url, no longer use.
		      // Use OldURLInterceptor to redirect to a new URL.
		/*
		 * registry.addInterceptor(new OldLoginInterceptor())//
		 * .addPathPatterns("/admin/oldLogin");
		 */

		      // This interceptor apply to URL like /admin/*
		      // Exclude /admin/oldLogin
		/*
		 * registry.addInterceptor(new AdminInterceptor())//
		 * .addPathPatterns("/admin/*")// .excludePathPatterns("/admin/oldLogin");
		 */

	   }

}
