package com.onlinevoting.OnlineVoting;

import com.onlinevoting.OnlineVoting.model.Admin;
import com.onlinevoting.OnlineVoting.model.Election;
import com.onlinevoting.OnlineVoting.model.Party;
import com.onlinevoting.OnlineVoting.model.User;
import com.onlinevoting.OnlineVoting.model.Vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class OnlineVotingApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OnlineVotingApplication.class, args);
	}

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(User.class);
		config.exposeIdsFor(Admin.class);
		config.exposeIdsFor(Vote.class);
		config.exposeIdsFor(Election.class);
		config.exposeIdsFor(Party.class);
    }
}
