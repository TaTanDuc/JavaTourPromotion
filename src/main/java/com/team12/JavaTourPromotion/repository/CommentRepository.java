package com.team12.JavaTourPromotion.repository;

import com.team12.JavaTourPromotion.model.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentRepository extends JpaRepository<Comments, Long>{
}
