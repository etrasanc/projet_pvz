package com.epf.dao;

import com.epf.model.Plante;

import java.util.List;

public interface PlanteDAO {
    Plante findById(int id_plante);
    List<Plante> findAll();
    void save(Plante plante);
    void update(Plante plante);
    void delete(int id_plante);
}
