package com.example.Voiture.controllers;

import com.example.Voiture.entities.Voiture;
import com.example.Voiture.models.Client;
import com.example.Voiture.repositories.VoitureRepository;
import com.example.Voiture.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoitureController {
    
    @Autowired
    private VoitureRepository voitureRepository;
    
    @Autowired
    private ClientService clientService;

    @GetMapping("/voitures")
    public List<Voiture> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        voitures.forEach(voiture -> {
            voiture.setClient(clientService.clientById(voiture.getId_client()));
        });
        return voitures;
    }

    @GetMapping("/voiture/{id}")
    public Voiture findById(@PathVariable Long id) throws Exception {
        Voiture voiture = voitureRepository.findById(id)
            .orElseThrow(() -> new Exception("Voiture non trouvée"));
        voiture.setClient(clientService.clientById(voiture.getId_client()));
        return voiture;
    }

    @GetMapping("/voitures/client/{clientId}")
    public List<Voiture> findByClient(@PathVariable Long clientId) {
        List<Voiture> voitures = voitureRepository.findById_client(clientId);
        voitures.forEach(voiture -> {
            voiture.setClient(clientService.clientById(voiture.getId_client()));
        });
        return voitures;
    }
}
