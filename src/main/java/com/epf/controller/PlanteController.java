package com.epf.controller;

import com.epf.dto.PlanteDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PlanteController {
    @GetMapping("/{id}")
    PlanteDTO getById(@PathVariable("id") int id);

    @GetMapping
    List<PlanteDTO> getAll();

    @PostMapping
    void create(@RequestBody PlanteDTO planteDTO);

    @PutMapping("/{id}")
    void update(@RequestBody PlanteDTO planteDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id);
}
