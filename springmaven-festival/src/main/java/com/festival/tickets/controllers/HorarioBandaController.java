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

import com.festival.tickets.entity.models.HorarioBanda;
import com.festival.tickets.entity.service.IHorarioBandaService;

@CrossOrigin(origins = "*")
@RestController
public class HorarioBandaController {

    @Autowired
    IHorarioBandaService horarioBandaService;

    @GetMapping("/horarios-bandas")
    public List<HorarioBanda> getAllHorarioBandas() {
        return horarioBandaService.getAll();
    }

    @GetMapping("/horarios-bandas/{id}")
    public HorarioBanda getOne(@PathVariable(value = "id") long id) {
        return horarioBandaService.get(id);
    }

    @PostMapping("/horarios-bandas")
    public ResponseEntity<?> post(@RequestBody HorarioBanda horarioBanda) {
        try {
            horarioBandaService.post(horarioBanda);
            return ResponseEntity.status(HttpStatus.CREATED).body(horarioBanda);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/horarios-bandas/{id}")
    public ResponseEntity<?> put(@PathVariable(value = "id") long id, @RequestBody HorarioBanda horarioBanda) {
        try {
            horarioBandaService.put(horarioBanda, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/horarios-bandas/{id}")
    public void delete(@PathVariable(value = "id") long id) {
        horarioBandaService.delete(id);
    }
}