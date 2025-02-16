package com.festival.tickets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.festival.tickets.entity.models.Banda;
import com.festival.tickets.entity.service.IBandaService;

@CrossOrigin(origins = "*")
@RestController
public class BandaController {

    @Autowired
    private IBandaService bandaService;


    @GetMapping("/bandas")
    public List<Banda> getAllBandas() {
        return bandaService.getAll();
    }


    @GetMapping("/bandas/{id}")
    public Banda getOne(@PathVariable(value = "id") long id) {
        return bandaService.get(id);
    }


    @PostMapping("/bandas")
    public ResponseEntity<Banda> post(@RequestBody Banda banda) {
        bandaService.post(banda);
        return ResponseEntity.status(HttpStatus.CREATED).body(banda);
    }


    @PutMapping("/bandas/{id}")
    public void put(@PathVariable(value = "id") long id, @RequestBody Banda banda) {
        bandaService.put(banda, id);
    }


    @DeleteMapping("/bandas/{id}")
    public void delete(@PathVariable(value = "id") long id) {
        bandaService.delete(id);
    }
}