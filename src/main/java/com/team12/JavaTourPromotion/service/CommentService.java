package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.repository.CommentRepository;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
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
    private final DestinationRepository destinationsRepository;
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comments> getCommentById(Long id) {
        return commentRepository.findById(id);
    }
    // Add a new product to the database
    @Transactional
    public Comments addComment(Comments comment) {
        Comments savedComment = commentRepository.save(comment);
        updateDestinationScore(savedComment.getDestination().getId());
        return savedComment;
    }
    // Update an existing product

    // Delete a product by its id
    public void deleteCommentById(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalStateException("Province with ID " + id + " does not exist.");
        }
        commentRepository.deleteById(id);
    }
    private void updateDestinationScore(Long destinationId) {
        Optional<Destinations> destinationOpt = destinationsRepository.findById(destinationId);
        if (destinationOpt.isPresent()) {
            Destinations destination = destinationOpt.get();
            destination.calculateAverageScore();
            destinationsRepository.save(destination);
        }
    }
}
