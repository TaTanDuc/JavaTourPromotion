package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class DestinationService {

    private final DestinationRepository destinationRepository;
    // Retrieve all products from the database
    public List<Destinations> getAllDestination(int x, int y, String s) {
        return destinationRepository.findAll();
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
