package com.charles.mocktrial.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.charles.mocktrial.model.Team;
import com.charles.mocktrial.model.TeamMembership;
import com.charles.mocktrial.model.TeamRole;
import com.charles.mocktrial.model.User;
import com.charles.mocktrial.repository.TeamMembershipRepository;
import com.charles.mocktrial.repository.TeamRepository;
import com.charles.mocktrial.repository.UserRepository;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initializeData(
        UserRepository userRepository,
        TeamRepository teamRepository,
        TeamMembershipRepository teamMembershipRepository) {
            return args -> {
                User user = new User(
                    "Charles",
                    "crnewhouse22@gmail.com"
                );

                userRepository.save(user);

                Team team = new Team(
                    "Mock Trial Team"
                );

                teamRepository.save(team);

                TeamMembership membership = new TeamMembership(
                    user,
                    team,
                    TeamRole.CAPTAIN
                );

                teamMembershipRepository.save(membership);
            };
        }
}