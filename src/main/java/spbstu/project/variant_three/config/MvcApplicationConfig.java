package spbstu.project.variant_three.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import spbstu.project.variant_three.entity.User;
import spbstu.project.variant_three.service.UserService;

import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MvcApplicationConfig extends WebSecurityConfigurerAdapter {
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/user").permitAll()
        .antMatchers(HttpMethod.POST, "/user").not().authenticated()
        .antMatchers("/css/**", "/js/**", "/**", "/img/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authProvider());
  }

  @Bean
  public AuthenticationProvider authProvider() {
    var provider = new DaoAuthenticationProvider();



    addPAPA("PAPA");

    addStudent("Student");

    System.err.println("USERNAME: student PASSWORD: asd");
    System.err.println("USERNAME: PAPA PASSWORD: 123 (ADMIN)");

    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userService);

    return provider;
  }

  private User addStudent(String name) {
    final User user = new User();

    user.setUsername(name);
    user.setPassword(passwordEncoder.encode("asd"));
    user.setRoles(Set.of("USER"));

    return userService.save(user);
  }

  private User addPAPA(String name) {
    final User admin = new User();

    admin.setUsername(name);
    admin.setPassword(passwordEncoder.encode("123"));
    admin.setRoles(Set.of("ADMIN"));

    return userService.save(admin);
  }
}