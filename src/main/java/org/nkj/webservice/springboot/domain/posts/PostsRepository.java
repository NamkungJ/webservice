package org.nkj.webservice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // public interface JpaRepository<T, ID>
}
