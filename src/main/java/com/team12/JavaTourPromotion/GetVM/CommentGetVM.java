package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Comments;

public record CommentGetVM(Long id, String username, destiantions destination, String content, int rating) {

    public static CommentGetVM from(Comments comment){
        return new CommentGetVM(
                comment.getId(),
                comment.getUser().getUsername(),
                new destiantions(comment.getDestination().getId(),comment.getDestination().getName()),
                comment.getContent(),
                comment.getRating()
        );
    }

    public record destiantions(Long id, String name){}
}
