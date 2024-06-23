package com.team12.JavaTourPromotion.controller;

import com.team12.JavaTourPromotion.model.Destinations;
import com.team12.JavaTourPromotion.model.DestinationImages;
import com.team12.JavaTourPromotion.service.ProviceService;
import com.team12.JavaTourPromotion.service.CityService;
import com.team12.JavaTourPromotion.service.DistrictsOrWardsService;
import com.team12.JavaTourPromotion.service.CategoryService;
import com.team12.JavaTourPromotion.service.DestinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class DestinationController {

}
