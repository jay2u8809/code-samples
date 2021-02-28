package com.jay2u8809.codesamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing		// JPA Auditing 활성화
@SpringBootApplication(scanBasePackages = {"com.jay2u8809.codesamples"})
public class CodeSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeSamplesApplication.class, args);
	}

}
