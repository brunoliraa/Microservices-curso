package microservices.course.auth.security.config;

import lombok.RequiredArgsConstructor;
import microservices.course.auth.security.filter.JwtUsernameAndPasswordAuthenticationFilter;
import microservices.course.core.property.JwtConfiguration;
import microservices.course.security.config.SecurityTokenConfig;
import microservices.course.security.token.creator.TokenCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

    private final UserDetailsService userDetailsService;
    private final TokenCreator tokenCreator;
 //   private final TokenConverter tokenConverter;


    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration,
                                     @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     TokenCreator tokenCreator) {
        super(jwtConfiguration);
        this.userDetailsService = userDetailsService;
        this.tokenCreator = tokenCreator;
//        this.tokenConverter = tokenConverter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration, tokenCreator));
                //.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
