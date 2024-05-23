package com.cocoon.cop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate 사용을 위한 추가
public class CopApplication implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CopApplication.class);

	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(CopApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info(" ");
		LOGGER.info("APPLICATION STARTED WITH USERNAME: {} AND PASSWORD: {}", username, password);
		LOGGER.info(" ");
	}
}
