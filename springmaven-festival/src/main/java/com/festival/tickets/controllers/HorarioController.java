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

import com.festival.tickets.entity.models.Horario;
import com.festival.tickets.entity.service.IHorarioService;

@CrossOrigin(origins = "*")
@RestController
public class HorarioController {

    @Autowired
    IHorarioService horarioService;

    @GetMapping("/horarios")
    public List<Horario> getAllHorarios() {
        return horarioService.getAll();
    }

    @GetMapping("/horarios/{id}")
    public Horario getOne(@PathVariable(value = "id") long id) {
        return horarioService.get(id);
    }

    @PostMapping("/horarios")
    public ResponseEntity<Horario> post(@RequestBody Horario horario) {
        horarioService.post(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(horario);
    }

    @PutMapping("/horarios/{id}")
    public void put(@PathVariable(value = "id") long id, @RequestBody Horario horario) {
        horarioService.put(horario, id);
    }

    @DeleteMapping("/horarios/{id}")
    public void delete(@PathVariable(value = "id") long id) {
        horarioService.delete(id);
    }
}