package com.rays.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration class for the ORS application.
 * <p>
 * Configures HTTP security settings including CSRF protection, URL-based
 * authorization rules, stateless session management, and JWT token filtering.
 * Pre/post method-level security is enabled via
 * {@code @EnableGlobalMethodSecurity(prePostEnabled = true)}.
 * </p>
 * <p>
 * Public endpoints such as authentication and profile picture URLs are
 * permitted without authentication, while all other requests require a
 * valid JWT token.
 * </p>
 *
 * @author Krati Trivedi
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The JWT request filter that intercepts and validates JWT tokens
     * on every incoming HTTP request before processing by Spring Security.
     */
    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    /**
     * Configures HTTP security for the ORS application.
     * <p>
     * The following security rules are applied:
     * <ul>
     *   <li>CSRF protection is disabled for stateless REST API usage</li>
     *   <li>Requests to {@code /Auth/**}, {@code /User/profilePic/**}, and
     *       {@code /Jasper/report/**} are permitted without authentication</li>
     *   <li>All other requests require authentication</li>
     *   <li>Session management is set to {@link SessionCreationPolicy#STATELESS}
     *       as JWT tokens are used instead of HTTP sessions</li>
     *   <li>The {@link JWTRequestFilter} is added before
     *       {@link UsernamePasswordAuthenticationFilter} in the filter chain</li>
     *   <li>CORS is enabled to allow cross-origin requests</li>
     * </ul>
     * </p>
     *
     * @param http the {@link HttpSecurity} object used to configure web-based security
     * @throws Exception if an error occurs while configuring HTTP security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/Auth/**", "/User/profilePic/**", "/Jasper/report/**").permitAll()
                .anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors();
    }
}