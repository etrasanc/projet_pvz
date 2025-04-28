package com.epf.service;

import com.epf.dto.PlanteDTO;

import java.util.List;

public interface PlanteService {
    PlanteDTO findById(int id_plante);
    List<PlanteDTO> findAll();
    void save(PlanteDTO planteDTO);
    void update(PlanteDTO planteDTO);
    void delete(int id_plante);
}
