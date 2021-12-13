package com.springsecurity.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Como configurar sprig security?
     * Estenda classe WebSecurityConfigurerAdapter
     * Anote com @EnableWebSecurity - avisa que ela configura web security
     * Anote com @EnableGlobalMethodSecurity para configurar marcacao de paginas
     * Sobrescreva os metodos de configuraçao
     * configure(AuthenticationManagerBuilder auth)
     *  define forma de autenticacao
     * configure(HttpSecurity http)
     *  define quais paginas precisam de autenticacao
     * configure(WebSecurity web)
     *  define autenticacao de paginas estaticas
     */
    //private final UserDetailsService userDetailsService;

    //public SecurityConfig(UserDetailsService userDetailsService) {
    //    this.userDetailsService = userDetailsService;
    //}


    public void getParaTestes(ParaTestes paraTestes) {
        paraTestes.teste1();
        paraTestes.teste3();
        paraTestes.teste4();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .hasRole("ADMIN")
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin"));
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN").and()
                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER");

        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);


    }

}
