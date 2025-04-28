package com.epf.controller;

import com.epf.dto.ZombieDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ZombieController {

    @GetMapping("/{id}")
    ZombieDTO getById(@PathVariable("id") int id);

    @GetMapping
    List<ZombieDTO> getAll();

    @GetMapping("/map/{id_map}")
    List<ZombieDTO> getByMap(@PathVariable("id_map") int id_map);

    @PostMapping
    void create(@RequestBody ZombieDTO zombieDTO);

    @PutMapping("/{id}")
    void update(@RequestBody ZombieDTO zombieDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id);
}
