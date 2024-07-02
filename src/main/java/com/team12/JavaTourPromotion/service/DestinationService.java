package com.team12.JavaTourPromotion.service;

import com.team12.JavaTourPromotion.GetVM.DestinationDetailGetVM;
import com.team12.JavaTourPromotion.model.Categories;
import com.team12.JavaTourPromotion.model.Comments;
import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.Users;
import com.team12.JavaTourPromotion.repository.DestinationRepository;
import com.team12.JavaTourPromotion.GetVM.DestinationGetVM;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Transactional
    public void avgScore() {
        List<Destinations> destination = destinationRepository.findAll();
        destination.forEach(des -> {
            double avgScore = des.getComments().stream().mapToDouble(Comments::getRating).average().orElse(0.0);
            des.setScore((float) avgScore);
            destinationRepository.save(des);
        });
    }

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
                                (string == null || des.getName().matches("(?i)(.*)"+string+"(.*)(?i)"))
                )
                .map(DestinationGetVM::from)
                .collect(Collectors.toList());
    }

    public Optional<DestinationGetVM> getDestinationById(Long id) {
        return destinationRepository.findById(id).map(DestinationGetVM::from);
    }

    public Optional<DestinationDetailGetVM> getDestinationDetailId(Long id) {
        return destinationRepository.findById(id).map(DestinationDetailGetVM::from);
    }

    public Destinations addDestination(Destinations destination) {
        return destinationRepository.save(destination);
    }

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

    public void deleteDestinationById(Long id) {
        if (!destinationRepository.existsById(id)) {
            throw new IllegalStateException("Destination with ID " + id + " does not exist.");
        }
        destinationRepository.deleteById(id);
    }
    public Optional<Destinations> getDestinationById11(Long id) {
        return destinationRepository.findById(id);
    }
}
