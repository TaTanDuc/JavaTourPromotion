package com.team12.JavaTourPromotion.GetVM;

import com.team12.JavaTourPromotion.model.*;
import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DestinationGetVM(Long id, String name, float score, String content, String image, long commentCount, String provinceName, String cityName, String DoWName, List<categories> categoriesList){
    public static DestinationGetVM from(Destinations destination){

        List<destinationsImages> simpleImagesList = destination.getImages().stream()
                .map(img -> new destinationsImages(img.getPath())).toList();

        List<categories> simpleCategoriesList = destination.getCategories().stream()
                .map(cats -> new categories(cats.getId(),cats.getName()))
                .toList();

        return new DestinationGetVM(
                destination.getId(),
                destination.getName(),
                destination.getScore(),
                destination.getContent(),
                destination.getImageUrl(),
                destination.getComments().size(),
                destination.getProvince().getName(),
                destination.getCity().getName(),
                destination.getDoW().getName(),
                simpleCategoriesList
        );
    }

    public record destinationsImages(String Path){}
    public record categories(Long id, String name){}
}
