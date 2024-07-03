package com.cocoon.cop;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.main.Category;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.repository.account.AccountRepository;
import com.cocoon.cop.repository.category.CategoryRepository;
import com.cocoon.cop.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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

import java.time.LocalDateTime;


@SpringBootApplication
@EnableJpaAuditing // @CreatedDate 사용을 위한 추가
@RequiredArgsConstructor // PostConstruct 를 위한 추가
public class CopApplication implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CopApplication.class);

	private final MemberRepository memberRepository;
	private final AccountRepository accountRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final CategoryRepository categoryRepository;

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
	@Transactional
	public void init() {
		Member admin = Member.builder()
				.email("jongwon3340@gmail.com")
				.password(passwordEncoder.encode("dnflwlq1408"))
				.role("ROLE_ADMIN")
				.name("CHOI JONGWON")
				.ruby("チェ　チョンウォン")
				.phoneNumber("010-3333-4444")
				.build();

		Member user = Member.builder()
				.email("testuser@gmail.com")
				.password(passwordEncoder.encode("testuser"))
				.role("ROLE_USER")
				.name("TEST USER")
				.ruby("テスト　ユーザー")
				.phoneNumber("010-1111-2222")
				.build();


		// Member 가 연관관계의 주인임
		Account adminAccount = new Account(admin, "5627693", 850000);
		admin.setAccount(adminAccount);
		adminAccount.setMember(admin);

		Account userAccount = new Account(admin, "4583295", 2000000);
		user.setAccount(userAccount);
		userAccount.setMember(user);

		memberRepository.save(admin);
		memberRepository.save(user);



	}
}
