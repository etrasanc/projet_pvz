package com.epf.controller;

import com.epf.dto.PlanteDTO;
import com.epf.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plantes")
public class PlanteControllerImpl implements PlanteController {

    private final PlanteService planteService;

    @Autowired
    public PlanteControllerImpl(PlanteService planteService) {
        this.planteService = planteService;
    }

    @Override
    public PlanteDTO getById(@PathVariable int id) {
        return planteService.findById(id);
    }

    @Override
    public List<PlanteDTO> getAll() {
        return planteService.findAll();
    }

    @Override
    public void create(@RequestBody PlanteDTO planteDTO) {
        planteService.save(planteDTO);
    }

    @Override
    public void update(@RequestBody PlanteDTO planteDTO) {
        planteService.update(planteDTO);
    }

    @Override
    public void delete(@PathVariable int id) {
        planteService.delete(id);
    }
}