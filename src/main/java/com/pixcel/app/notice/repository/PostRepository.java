package com.pixcel.app.notice.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pixcel.app.notice.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
	@Query(value = "SELECT * FROM (" +
            "  SELECT * FROM POST " + 
            "  WHERE post_id LIKE :prefix || '%' " +
            "  ORDER BY post_id DESC" +
            ") WHERE ROWNUM <= 1", 
    nativeQuery = true)
	Optional<PostEntity> findLatestPostIdByPrefix(@Param("prefix") String prefix);
}
