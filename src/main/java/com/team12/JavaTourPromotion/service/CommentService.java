package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.GetVM.CommentGetVM;
import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.repository.CommentRepository;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
import com.team12.JavaTourPromotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;

    public List<CommentGetVM> getAllComments(){
        return commentRepository.findAll()
                .stream().map(CommentGetVM::from).toList();
    }

    public Comments addComment(Long id ,String username, Comments comment){
        comment.setUser(userRepository.findByUsername(username));
        comment.setDestination(destinationRepository.getDesById(id));
        return commentRepository.save(comment);
    }

    @Transactional
    public void hideComment(Long id) {
        Comments comments = commentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Comment not found"));
        comments.setStatus(true);
        commentRepository.save(comments);
    }

    @Transactional
    public void unhideComment(Long id) {
        Comments comments = commentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Comment not found"));
        comments.setStatus(false);
        commentRepository.save(comments);
    }
}
