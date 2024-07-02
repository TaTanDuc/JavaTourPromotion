package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.Provinces;
import com.team12.JavaTourPromotion.model.Users;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserGetVM (Long id, String username, String name, String img,List<comments> commentsList, List<bookmark> bookmarksList){

    public static UserGetVM from(@NotNull Users user) {

        List<comments> simpleComments = user.getComments().stream()
                .map(comments -> new comments(
                        comments.getId(),comments.getUser().getUsername(),
                        new destinations(comments.getDestination().getId(),comments.getDestination().getName()),
                        comments.getRating())
                ).toList();

        List<bookmark> simpleBookmark = user.getBookmarks().stream()
                .map(bookmarks -> new bookmark(
                        new destinations(bookmarks.getDestination().getId(), bookmarks.getDestination().getName()))
                ).toList();

        return new UserGetVM(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getProfileImgPath(),
                simpleComments,
                simpleBookmark
        );
    }

    // Define a simplified views
    public record comments(Long id, String Username, destinations destination, int rating) {}
    public record destinations(Long id, String name) {}
    public record bookmark(destinations destination){}
}
