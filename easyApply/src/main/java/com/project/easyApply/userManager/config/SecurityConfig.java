package com.project.easyApply.userManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService getUserDetailsServices(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsServices());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return daoAuthenticationProvider;
    }

    protected  void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.authenticationProvider(getDaoAuthProvider());
    }

    @Bean
    @SuppressWarnings("removal")
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/user/**").authenticated()
                .requestMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
                .defaultSuccessUrl("/user/");
        return http.build();
    }

}

//        http.csrf().disable()
//                .authorizeRequests(authorize ->
//                        authorize
//                                .requestMatchers("/FirstPage", "/signup", "/signin").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/dashboard")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll()
//                );
//        return http.build();
