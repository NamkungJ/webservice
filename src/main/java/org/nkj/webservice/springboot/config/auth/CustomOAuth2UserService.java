package org.nkj.webservice.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.config.auth.dto.OAuthAttributes;
import org.nkj.webservice.springboot.config.auth.dto.SessionUser;
import org.nkj.webservice.springboot.domain.user.User;
import org.nkj.webservice.springboot.domain.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();    // 현대 로그인 진행 중인 서비스 (구글/네이버..)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails() // OAuth 로그인 진행 시 키가 되는 필드
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); // OAuth2UserService로 가져온 OAuth2User의 attribute를 담을 클래스

        User user = saveOrUpdate(attributes);   // 기가입 확인 -> 수정/가입
        httpSession.setAttribute("user", new SessionUser(user));    // 세션에 사용자 정보를 저장

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())   // 이메일로 이미 가입했는지 확인
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))    // 회원 정보 수정
                .orElse(attributes.toEntity()); // 회원 가입 public User toEntity()

        return userRepository.save(user);
    }

}
