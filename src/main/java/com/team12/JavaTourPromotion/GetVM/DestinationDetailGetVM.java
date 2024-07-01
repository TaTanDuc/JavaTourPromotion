package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Destinations;
import lombok.Builder;

import java.util.List;

@Builder
public record DestinationDetailGetVM(Long id, String name, float score, String content, String image, List<comments> commentsList, List<destinationsImages> destinationsImagesList,String provinceName, String cityName, String DoWName, List<categories> categoriesList){
    public static DestinationDetailGetVM from(Destinations destination){

        List<destinationsImages> simpleImagesList = destination.getImages().stream()
                .map(img -> new destinationsImages(img.getPath())).toList();

        List<categories> simpleCategoriesList = destination.getCategories().stream()
                .map(cats -> new categories(cats.getId(),cats.getName()))
                .toList();

        List<comments> simpleCommentsList = destination.getComments().stream()
                .map(comments -> new comments(comments.getId(), comments.getUser().getUsername()
                                            ,comments.getContent(), comments.getRating()))
                .toList();

        return new DestinationDetailGetVM(
                destination.getId(),
                destination.getName(),
                destination.getScore(),
                destination.getContent(),
                destination.getImageUrl(),
                simpleCommentsList,
                simpleImagesList,
                destination.getProvince().getName(),
                destination.getCity().getName(),
                destination.getDoW().getName(),
                simpleCategoriesList
        );
    }

    public record comments(Long id, String username, String content, int rating){}
    public record destinationsImages(String Path){}
    public record categories(Long id, String name){}
}
