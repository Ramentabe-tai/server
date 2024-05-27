package com.cocoon.cop;

import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate 사용을 위한 추가
@RequiredArgsConstructor // PostConstruct 를 위한 추가
public class CopApplication implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CopApplication.class);

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

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

	@PostConstruct
	public void init() {
		Member admin = new Member().builder()
				.email("jongwon3340@gmail.com")
				.password(passwordEncoder.encode("dnflwlq1408"))
				.role("ROLE_ADMIN")
				.name("CHOI JONGWON")
				.phoneNumber("010-3333-4444")
				.build();
		memberRepository.save(admin);

		Member user = new Member().builder()
				.email("testuser@gmail.com")
				.password(passwordEncoder.encode("testuser"))
				.role("ROLE_USER")
				.name("TEST USER")
				.phoneNumber("010-1111-2222")
				.build();
		memberRepository.save(user);
	}
}
