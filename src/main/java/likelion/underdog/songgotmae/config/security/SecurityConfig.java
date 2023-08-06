package likelion.underdog.songgotmae.config.security;

import likelion.underdog.songgotmae.domain.member.UserType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static likelion.underdog.songgotmae.constant.StaticValue.SwaggerUrlPatterns;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final FilterConfig filterConfig;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.debug("DEBUG : BCryptPasswordEncoder 빈 등록");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("DEBUG : filterchain 빈 등록");
        http
                .headers().frameOptions().disable() // h2-console 화면 사용 위해 disable
                .and()
                .httpBasic().disable() // 브라우저가 지 멋대로 팝업창을 이용하여 사용자 인증을 하려고 하는 기능 -> disabled
                .cors().configurationSource(configurationSource())
                .and()
                .csrf().disable() // enable하면 postman 동작 x
                .formLogin().disable() // 기본 로그인 방식 사용 x
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // session id를 서버에서 관리 x (jwt 사용에정)

        /*
        * Exception 가로채기
        **/
//        http
//                .exceptionHandling().authenticationEntryPoint((request, response, authException) ->
//                {
//                    CustomResponseUtil.unAuthentication(response, "로그인을 진행해 주세요");
//                });
        http
                .authorizeRequests()
                .antMatchers(SwaggerUrlPatterns)
                .permitAll()
                .antMatchers("/api/v1/**").authenticated() // public api는 인증 필요
                .antMatchers("/admin/v1/**").hasRole(String.valueOf(UserType.ADMIN)) // admin api는 권한 필요
                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
        ;

        return http.build();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }


    /* CORS configuration */
    public CorsConfigurationSource configurationSource() {
        log.debug("DEBUG : CorsConfigurationSource CORS 설정이 filterchain에 등록");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (항후 프론트엔트 IP만 허용해야함)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /*
        * 모든 주소 요청에 대하여 위 설정을 적용하겠다.
        **/
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
