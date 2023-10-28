package likelion.underdog.songgotmae.config;

import likelion.underdog.songgotmae.domain.member.MemberRole;
import likelion.underdog.songgotmae.util.jwt.JwtAuthenticationFilter;
import likelion.underdog.songgotmae.util.jwt.JwtAuthorizationFilter;
import likelion.underdog.songgotmae.util.formatter.CustomResponseFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static likelion.underdog.songgotmae.util.constant.URL_PATTERNS.*;


@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.debug("DEBUG : BCryptPasswordEncoder 빈 등록");
        return new BCryptPasswordEncoder();
    }

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(new JwtAuthenticationFilter(authManager));
            builder.addFilter(new JwtAuthorizationFilter(authManager));
            super.configure(builder);
        }
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session id를 서버에서 관리 x (jwt 사용에정)
                .and()
                .apply(new CustomSecurityFilterManager()); // 커스텀 필터 (JWT 필터 등) 적용

        /*
        * 시큐리티 컨텍스트 내 예외 핸들링
        **/
        http
                .exceptionHandling()
                    .authenticationEntryPoint(((request, response, authException) -> {
                        CustomResponseFormatter.fail(response, "로그인을 진행해 주세요.", authException, HttpStatus.UNAUTHORIZED);
                    }))
                .and()
                    .exceptionHandling().accessDeniedHandler(((request, response, accessDeniedException) -> {
                        CustomResponseFormatter.fail(response, "관리자 권한이 없습니다.", accessDeniedException, HttpStatus.FORBIDDEN);
                    }));


        http
                .authorizeHttpRequests() // 5.6 버전 이후 authorizeRequests 보다 authorizeHttpRequests 권장
                    .antMatchers(SWAGGER_URL_PATTERNS).permitAll()
                    .antMatchers(H2_URL_PATTERNS).permitAll()
                    .antMatchers(NEED_LOGIN_URL_PATTERNS).authenticated() // post api는 로그인 필요
                    .antMatchers(ADMIN_PAGE_URL_PATTERNS).hasRole(String.valueOf(MemberRole.ADMIN)) // admin api는 권한 필요
                    .anyRequest().permitAll()
        ;

        return http.build();
    }


    /* CORS configuration */
    public CorsConfigurationSource configurationSource() {
        log.debug("DEBUG : CorsConfigurationSource CORS 설정이 filterchain에 등록");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 -> TODO: 항후 프론트엔트 IP만 허용해야함
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /*
        * 모든 주소 요청에 대하여 위 설정을 적용하겠다.
        **/
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
