package com.epf.controller;

import com.epf.dto.MapDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MapController {
    @GetMapping("/{id}")
    MapDTO getById(@PathVariable("id") int id);

    @GetMapping
    List<MapDTO> getAll();

    @PostMapping
    void create(@RequestBody MapDTO mapDTO);

    @PutMapping("/{id}")
    void update(@RequestBody MapDTO mapDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id);
}
