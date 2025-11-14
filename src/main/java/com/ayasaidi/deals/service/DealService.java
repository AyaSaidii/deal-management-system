package com.ayasaidi.deals.service;

import com.ayasaidi.deals.dto.DealDto;
import com.ayasaidi.deals.entity.Deal;
import com.ayasaidi.deals.repository.DealRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    // Liste des paires valides extraites du CSV
    private Set<String> validPairs = new HashSet<>();
    private Set<DealDto> nonvalidPairs = new HashSet<>();

    // Appelé automatiquement au démarrage
    @PostConstruct
    public void init() {
        loadValidPairs();
    }

    /**
     * Charger les paires valides depuis le fichier deals.csv situé dans resources/
     */
    public void loadValidPairs() {


        InputStream filePath = getClass().getResourceAsStream("/deals.csv");

        if (filePath == null) {
            System.out.println("⚠ Fichier CSV introuvable !");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePath))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                // Ignorer l'en-tête
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] columns = line.split(",");

                if (columns.length >= 2) {
                    String pair = columns[0].trim() + "-" + columns[1].trim();
                    validPairs.add(pair);
                }
            }

            System.out.println("✔️ Paires valides chargées : " + validPairs.size());

        } catch (Exception e) {
            System.out.println("❌ Erreur lors du chargement du fichier CSV : " + e.getMessage());
        }
    }

    /**
     * Vérification si un deal correspond à une paire de devises valide
     */
    public boolean isValidDeal(Deal deal) {
        String pair = deal.getFromCurrency().trim() + "-" + deal.getToCurrency().trim();
        return validPairs.contains(pair);
    }
    public  boolean isValidDealDto(DealDto dealDto){
        String pair = dealDto.getFromCurrency().trim() + "-" + dealDto.getToCurrency().trim();
        return validPairs.contains(pair);
    }

    /**
     * Sauvegarde d'un deal unique
     */
    public Deal saveDeal(Deal deal) throws Exception {

        if (!isValidDeal(deal)) {

            throw new Exception("Paire invalide : " + deal.getFromCurrency() + "-" + deal.getToCurrency());

        }

        if (dealRepository.existsById(deal.getDealUniqueId())) {
            throw new Exception("Ce deal existe déjà : " + deal.getDealUniqueId());
        }

        return dealRepository.save(deal);
    }

    public Deal saveDealFromDTO(DealDto dealDTO) throws Exception {
        // Vérifier si la paire est valide
        if (!isValidDealDto(dealDTO)) {
            nonvalidPairs.add(dealDTO);

            throw new Exception("Paire invalide : " + dealDTO.getFromCurrency() + "-" + dealDTO.getToCurrency());

        }

        // Créer l'entité Deal à partir du DTO
        Deal deal = new Deal();
        deal.setDealUniqueId(dealDTO.getDealUniqueId()); // génère un ID unique
        deal.setFromCurrency(dealDTO.getFromCurrency());
        deal.setToCurrency(dealDTO.getToCurrency());
        deal.setDealAmount(dealDTO.getDealAmount());
        deal.setDealTimestamp(LocalDateTime.now());

        return dealRepository.save(deal);
    }



    public void saveDeals(List<DealDto> dealDtos) throws Exception {
        List<Deal> deals = dealDtos.stream().map(dto -> {
            Deal deal = new Deal();
            deal.setDealUniqueId(dto.getDealUniqueId());
            deal.setFromCurrency(dto.getFromCurrency());
            deal.setToCurrency(dto.getToCurrency());
            deal.setDealAmount(dto.getDealAmount());
            deal.setDealTimestamp(LocalDateTime.now());
            return deal;
        }).collect(Collectors.toList());

        // Vérifier les paires avant de sauvegarder
        for (Deal deal : deals) {
            if (!isValidDeal(deal)) {
                throw new Exception("Paire invalide : " + deal.getFromCurrency() + "-" + deal.getToCurrency());
            }
        }

        dealRepository.saveAll(deals);
    }

    /**
     * Récupération de tous les deals
     */
    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }
    public List<DealDto> getAllDtoDeals() {
        List<Deal> deals = dealRepository.findAll();

        return deals.stream().map(deal -> {
            DealDto dto = new DealDto();
            dto.setDealUniqueId(deal.getDealUniqueId());
            dto.setFromCurrency(deal.getFromCurrency());
            dto.setToCurrency(deal.getToCurrency());
            dto.setDealAmount(deal.getDealAmount());
            dto.setDealTimestamp(deal.getDealTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }



    public boolean existsDeal(String dealUniqueId) {
        return dealRepository.existsById(dealUniqueId);
    }
}
