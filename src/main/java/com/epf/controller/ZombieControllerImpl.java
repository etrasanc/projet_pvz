package com.epf.controller;

import com.epf.controller.ZombieController;
import com.epf.dto.ZombieDTO;
import com.epf.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zombies")
public class ZombieControllerImpl implements ZombieController {

    private final ZombieService zombieService;

    @Autowired
    public ZombieControllerImpl(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @Override
    public ZombieDTO getById(@PathVariable int id) {
        return zombieService.findById(id);
    }

    @Override
    public List<ZombieDTO> getAll() {
        return zombieService.findAll();
    }

    @Override
    public List<ZombieDTO> getByMap(@PathVariable int id_map) {
        return zombieService.findByMap(id_map);
    }

    @Override
    public void create(@RequestBody ZombieDTO zombieDTO) {
        zombieService.save(zombieDTO);
    }

    @Override
    public void update(@RequestBody ZombieDTO zombieDTO) {
        zombieService.update(zombieDTO);
    }

    @Override
    public void delete(@PathVariable int id) {
        zombieService.delete(id);
    }
}


