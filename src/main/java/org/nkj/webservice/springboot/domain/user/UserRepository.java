package org.nkj.webservice.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // 이미 생성된 사용자인지, 처음 가입하는 사용자 인지 확인
    Optional<User> findByEmail(String email);

}
