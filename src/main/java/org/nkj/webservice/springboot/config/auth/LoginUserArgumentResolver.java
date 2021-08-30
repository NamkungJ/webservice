package org.nkj.webservice.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor    // 조건에 맞는 메서드가 있으면, HandlerMethodArgumentResolver구현체(LoginUserArgumentResolver)가 지정한 값을 해당 메서드에 넘길 수 있음
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override   // 컨트롤러 메서드의 특정 파라미터를 지원하는지
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;  // 파라미터에 @LoginUser 가 있는지
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());   // 파라미터 클래스 타입이 SessionUser.class 인지
        return isLoginUserAnnotation && isUserClass;
    }

    @Override   // 파라미터로 전달한 객체를 지정
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
