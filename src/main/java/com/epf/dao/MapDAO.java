package com.epf.dao;

import com.epf.model.Map;

import java.util.List;

public interface MapDAO {
    Map findById(int id_map);
    List<Map> findAll();
    void save(Map entity);
    void update(Map entity);
    void delete(int id_map);
    boolean existsById(int id);
}
