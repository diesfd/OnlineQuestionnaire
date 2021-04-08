package com.zyx.OnlineQuestionnaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyx.OnlineQuestionnaire.dao")
public class OnlineQuestionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuestionnaireApplication.class, args);
	}

}
