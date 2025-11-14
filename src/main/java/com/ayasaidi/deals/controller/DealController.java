package com.ayasaidi.deals.controller;

import com.ayasaidi.deals.dto.DealDto;
import com.ayasaidi.deals.entity.Deal;
import com.ayasaidi.deals.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DealController {

    @Autowired
    private DealService dealService;

    // Endpoint pour sauvegarder un array de deals
    @PostMapping("/adddeals")
    public ResponseEntity<String> addDeals(@RequestBody List<DealDto> deals) {
        try {
            dealService.saveDeals(deals);
            return ResponseEntity.ok("Deals enregistrés avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
    @PostMapping("/addDealDto")
    public ResponseEntity<String> addDealDto(@RequestBody DealDto deal) throws Exception {

        try {
            dealService.saveDealFromDTO(deal);

            return ResponseEntity.ok("Deals enregistrés avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }

    @GetMapping("/dtodeals")
    public List<DealDto> getDtoDeals() {
        return dealService.getAllDtoDeals();
    }




}
