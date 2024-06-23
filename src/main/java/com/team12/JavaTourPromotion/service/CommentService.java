package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Provinces;
import com.team12.JavaTourPromotion.repository.CommentRepository;
import com.team12.JavaTourPromotion.repository.ProvinceRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class CommentService {
    private final CommentRepository commentRepository;
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comments> getCommentById(Long id) {
        return commentRepository.findById(id);
    }
    // Add a new product to the database
    public Comments addComment(Comments provinces) {
        return commentRepository.save(provinces);
    }
    // Update an existing product

    // Delete a product by its id
    public void deleteCommentById(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalStateException("Province with ID " + id + " does not exist.");
        }
        commentRepository.deleteById(id);
    }
}
