package com.team12.JavaTourPromotion.viewmodel;

import com.team12.JavaTourPromotion.model.*;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record DestinationGetVM(Long id, String name, float score, String content, String image, List<destinationsImages> imagesList, List<comments> commentsList, String provinceName, String cityName, String DoWName, List<categories> categoriesList){
    public static DestinationGetVM from(Destinations destination){

        List<destinationsImages> simpleImagesList = destination.getImages().stream()
                .map(img -> new destinationsImages(img.getPath())).toList();

        List<comments> simpleCommentsList = destination.getComments().stream()
                .map(coms -> new comments(coms.getId(),coms.getUser().getUsername(), coms.getContent(),coms.getRating(),coms.isStatus()))
                .toList();

        List<categories> simpleCategoriesList = destination.getCategories().stream()
                .map(cats -> new categories(cats.getId(),cats.getName()))
                .toList();

        return new DestinationGetVM(
                destination.getId(),
                destination.getName(),
                destination.getScore(),
                destination.getContent(),
                destination.getImageUrl(),
                simpleImagesList,
                simpleCommentsList,
                destination.getProvince().getName(),
                destination.getCity().getName(),
                destination.getDoW().getName(),
                simpleCategoriesList
        );
    }

    public record destinationsImages(String Path){}
    public record comments(Long id, String Username, String content, int rating, boolean status) {}
    public record categories(Long id, String name){}
}
