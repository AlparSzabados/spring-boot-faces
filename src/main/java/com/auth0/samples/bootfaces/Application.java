package com.auth0.samples.bootfaces;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.*;

@EnableAutoConfiguration
@ComponentScan({"com.auth0.samples.bootfaces"})
public class Application extends SpringBootServletInitializer {
	private static final EnumSet<DispatcherType> DISPATCHER_TYPES = EnumSet.of(FORWARD, REQUEST, ASYNC, ERROR);

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new FacesServlet(), "*.jsf");
	}

	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(DISPATCHER_TYPES);
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}
}
