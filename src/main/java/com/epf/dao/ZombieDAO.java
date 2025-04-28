package com.epf.dao;

import com.epf.model.Zombie;

import java.util.List;

public interface ZombieDAO {
    Zombie findById(int id_zombie);
    List<Zombie> findAll();
    List<Zombie> findByMap(int id_map);
    int save(Zombie zombie);
    void update(Zombie zombie);
    void delete(int id_zombie);
}
