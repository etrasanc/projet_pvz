package com.epf.service;

import com.epf.dao.PlanteDAO;
import com.epf.dto.PlanteDTO;
import com.epf.mapper.PlanteMapper;
import com.epf.model.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanteServiceImpl implements PlanteService {
    private final PlanteDAO planteDAO;

    @Autowired
    public PlanteServiceImpl(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }

    @Override
    public PlanteDTO findById(int id_plante) {
        return PlanteMapper.toDTO(planteDAO.findById(id_plante));
    }

    @Override
    public List<PlanteDTO> findAll() {
        return planteDAO.findAll().stream()
                .map(PlanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PlanteDTO planteDTO) {
        Plante plante = PlanteMapper.toModel(planteDTO);
        planteDAO.save(plante);
    }

    @Override
    public void update(PlanteDTO planteDTO) {
        Plante plante = new Plante(
                planteDTO.getId_plante(),
                planteDTO.getNom(),
                planteDTO.getPoint_de_vie(),
                planteDTO.getAttaque_par_seconde(),
                planteDTO.getDegat_attaque(),
                planteDTO.getCout(),
                planteDTO.getSoleil_par_seconde(),
                planteDTO.getEffet(),
                planteDTO.getChemin_image()
        );
        planteDAO.update(plante);
    }

    @Override
    public void delete(int id_plante) {
        planteDAO.delete(id_plante);
    }
}
