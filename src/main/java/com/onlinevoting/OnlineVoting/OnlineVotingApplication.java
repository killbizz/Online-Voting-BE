package com.onlinevoting.OnlineVoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineVotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineVotingApplication.class, args);
	}

	// @Override
    // public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    //     config.exposeIdsFor(Synthesizer.class);
	// 	config.exposeIdsFor(User.class);
    // }
}
