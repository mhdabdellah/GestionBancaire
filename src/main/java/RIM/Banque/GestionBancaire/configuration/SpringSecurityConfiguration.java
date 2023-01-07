package RIM.Banque.GestionBancaire.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import RIM.Banque.GestionBancaire.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // @Autowired
    // BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable().cors().disable()
<<<<<<< Updated upstream
                .authorizeHttpRequests()
                // .requestMatchers("/users/getAllUsers").permitAll()
                .requestMatchers("/users/login",
                         "/users/registerNewUser",
                        "/comptes","/ouvertureCompte","/searchcompte/{codecompte}").permitAll()
                .anyRequest().authenticated()
                // .anyRequest().permitAll()
                .and().httpBasic();
=======
        
            .authorizeHttpRequests()
            .requestMatchers("/users/login").permitAll()
            // .pathMatchers("/bars/pk").permitAll()
            // .anyRequest().permitAll()
            .anyRequest().authenticated()
            .and().httpBasic();
>>>>>>> Stashed changes

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder()).and().build();

    }

}
