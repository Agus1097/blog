package com.prueba.blog.repositories;

import com.prueba.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser_Email(String email);

    @Query(value = "SELECT * FROM posts p " +
            "WHERE p.exposure_id = :exposureId AND p.expiration_date > :dateNow " +
            "ORDER BY created_at DESC", nativeQuery = true)
    List<Post> getPublicPosts(@Param("exposureId") Long exposureId, @Param("dateNow") Instant dateNow);

    Post findByPostId(String postId);
}
