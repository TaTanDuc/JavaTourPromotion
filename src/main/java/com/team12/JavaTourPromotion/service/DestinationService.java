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
    public List<Destination> getAllDestination() {
        return destinationRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Destination> getDestinationById(Long id) {
        return destinationRepository.findById(id);
    }
    // Add a new product to the database
    public Destination addDestination(Destination destination) {
        return destinationRepository.save(destination);
    }
    // Update an existing destination
    public Destination updateDestination(@NotNull Destination destination) {
        Destination existingDestination = destinationRepository.findById(destination.ge())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        destination.getId() + " does not exist."));
        existingDestination.setName(product.getName());
        existingDestination.setPrice(product.getPrice());
        existingDestination.setDescription(product.getDescription());
        existingDestination.setCategory(product.getCategory());
        return     public Optional<Destination> getDestinationById(Long id) {
            return destinationRepository.findById(id);
        }
.save(existingDestination);
    }
    // Delete a product by its id
    public void deleteDestinationById(Long id) {
        if (!destinationRepository.existsById(id)) {
            throw new IllegalStateException("Destination with ID " + id + " does not exist.");
        }
        destinationRepository.deleteById(id);
    }

}
