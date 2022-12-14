package com.mindhub.homebanking.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()

                .antMatchers(HttpMethod.POST,
                        "/api/clients").permitAll()
                .antMatchers("/web/assets/**", "api/login").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/accounts",
                        "/api/clients/current/accounts",
                        "/api/loans",
                        "/api/clients/current/cards").hasAnyAuthority("CLIENT", "ADMIN")

                .antMatchers("/api/clients/current",
                        "/api/accounts/current",
                        "/api/loans",
                        "/web/**", "/api/cards/cardsInfo").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/h2-console/**","/rest/**",
                        "/api/clients/**",
                        "/api/loans", "/api/cards",
                        "/api/accounts/transactions",
                        "/api/accounts/").hasAuthority("ADMIN");

        httpSecurity.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");



        httpSecurity.logout().logoutUrl("/api/logout");

        // turn off checking for CSRF tokens

        httpSecurity.csrf().disable();



        //disabling frameOptions so h2-console can be accessed

        httpSecurity.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response

        httpSecurity.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        httpSecurity.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        httpSecurity.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        httpSecurity.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
}
}
