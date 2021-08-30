package org.nkj.webservice.springboot.config.auth.dto;

import lombok.Getter;
import org.nkj.webservice.springboot.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {  // User를 사용하지 않고, 직렬화 기능을 가진 세션 Dto 추가 생성해서 사용
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
