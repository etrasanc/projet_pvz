package com.epf.service;

import com.epf.dto.ZombieDTO;
import java.util.List;

public interface ZombieService {
    ZombieDTO findById(int id_zombie);
    List<ZombieDTO> findAll();
    List<ZombieDTO> findByMap(int id_map);
    void save(ZombieDTO zombieDTO);
    void update(ZombieDTO zombieDTO);
    void delete(int id_zombie);
}


