package org.nkj.webservice.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 옵션 disabl
                .and()
                    .authorizeRequests()    // URL별 권한 관리 설정, authorizeRequests를 선언해야 antMatchers(권한 관리 대상)가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()  // 전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // USER 권한을 가진 사람만 가능한 URL
                    .anyRequest().authenticated()   // 설정한 URL 외에 나머지 URL는 모두 인증된 사용자들만 가능
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  // 로그아웃 성공 시 /로 이동
                .and()
                    .oauth2Login()  // 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // 로그인 성공 후 사용자 정보를 가져올 때 설정들 담당
                            .userService(customOAuth2UserService);  // 로그인 성공 시 후속 조치를 진행할 OAuth2UserService 인터페이스 구현체를 등록 (사용자 정보를 가져온 이후 추가로 진행할 기능)
    }

}
