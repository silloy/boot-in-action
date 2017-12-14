package com.zj;

import com.zj.condition.JdbcTemplateCondition;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
@Conditional(JdbcTemplateCondition.class)
@CommonsLog
public class BootApplication extends SpringBootServletInitializer {



	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootApplication.class);
	}
}
