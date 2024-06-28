package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
import com.team12.JavaTourPromotion.viewmodel.DestinationGetVM;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DestinationService {

    private final DestinationRepository destinationRepository;

    // Retrieve all products from the database
    public List<DestinationGetVM> getAllDestination() {
        return destinationRepository.findAll()
                .stream()
                .map(DestinationGetVM::from)
                .toList();
    }

    public List<DestinationGetVM> getListByRequirements(Long provinceId, Long cityId, Long dowId, List<Long> categoriesId, String string){

        return destinationRepository.findAll()
                .stream()
                .filter(des ->
                        (provinceId == null || des.getProvince().getId().equals(provinceId)) &&
                                (cityId == null || des.getCity().getId().equals(cityId)) &&
                                (dowId == null || des.getDoW().getId().equals(dowId)) &&
                                (categoriesId == null || categoriesId.isEmpty() || des.getCategories().stream().map(Categories::getId).toList().containsAll(categoriesId)) &&
                                (string == null || des.getName().contains(string))
                )
                .map(DestinationGetVM::from)
                .collect(Collectors.toList());
    }

    // Retrieve a product by its id
    public Optional<Destinations> getDestinationById(Long id) {
        return destinationRepository.findById(id);
    }

    // Add a new product to the database
    public Destinations addDestination(Destinations destination) {
        return destinationRepository.save(destination);
    }

    // Update an existing destination
    public Destinations updateDestination(@NotNull Destinations destination) {
        Destinations existingDestination = destinationRepository.findById(destination.getId())
                .orElseThrow(() -> new IllegalStateException("Destination with ID " +
                        destination.getId() + " does not exist."));
        existingDestination.setName(destination.getName());
        existingDestination.setContent(destination.getContent());
        existingDestination.setImageUrl(destination.getImageUrl());
        existingDestination.setImages(destination.getImages());
        existingDestination.setCity(destination.getCity());
        existingDestination.setCategories(destination.getCategories());
        existingDestination.setDoW(destination.getDoW());
        existingDestination.setProvince(destination.getProvince());
        return destinationRepository.save(existingDestination);
    }

    // Delete a product by its id
    public void deleteProductById(Long id) {
        if (!destinationRepository.existsById(id)) {
            throw new IllegalStateException("Destination with ID " + id + " does not exist.");
        }
        destinationRepository.deleteById(id);
    }
}
