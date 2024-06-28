package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DestinationController {
    @Autowired
    private final DestinationService destinationService;

//    @GetMapping("/destinations")
//    public ResponseEntity<List<DestinationGETVM>> getAllDestinations(Integer pageNo, Integer pageSize, String sortBy){
//        return  ResponseEntity.ok(destinationService.getAllDestination(
//                        pageNo == null ? 0 : pageNo,
//                        pageSize == null ? 20 : pageSize,
//                        sortBy == null ? "id" : sortBy)
//                .stream()
//                .map(DestinationGETVM::from)
//                .toList()
//        );
//    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<Void> deleteDestinationByID(@RequestParam Long id){
        destinationService.getDestinationById(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("destination/get/{id}")
//    public ResponseEntity<DestinationGETVM> getDestinationByID(@RequestParam Long id){
//        return ResponseEntity.ok(destinationService.getDestinationById(id)
//                .map(DestinationGETVM::from)
//                .orElse(null));
//    }
//
////    @GetMapping("destination/search")
////    public ResponseEntity<List<DestinationGETVM>> searchDestination(String keyword){
////        return ResponseEntity.ok(destinationService.searchDestination(keyword)
////                .stream()
////                .map(DestinationGETVM::from)
////                .toList()
////        );
////    }
}
