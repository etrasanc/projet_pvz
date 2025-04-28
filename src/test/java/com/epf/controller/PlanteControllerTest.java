package com.epf.controller;

import com.epf.dto.PlanteDTO;
import com.epf.service.PlanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    @InjectMocks
    private PlanteControllerImpl planteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private PlanteDTO getSamplePlanteDTO() {
        PlanteDTO dto = new PlanteDTO();
        dto.setId_plante(1);
        dto.setNom("Tournesol");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(0);
        dto.setDegat_attaque(0);
        dto.setCout(50);
        dto.setSoleil_par_seconde(5.0);
        dto.setEffet("génère du soleil");
        dto.setChemin_image("/img/tournesol.png");
        return dto;
    }

    @Test
    public void testGetById_ShouldReturnPlanteDTO() {
        PlanteDTO expected = getSamplePlanteDTO();
        when(planteService.findById(1)).thenReturn(expected);

        PlanteDTO result = planteController.getById(1);

        assertNotNull(result);
        assertEquals("Tournesol", result.getNom());
        assertEquals(5.0, result.getSoleil_par_seconde());
        verify(planteService).findById(1);
    }

    @Test
    public void testGetAll_ShouldReturnListOfPlantes() {
        PlanteDTO p1 = getSamplePlanteDTO();
        PlanteDTO p2 = getSamplePlanteDTO();
        p2.setId_plante(2);
        p2.setNom("Pisto-pois");
        p2.setDegat_attaque(20);

        when(planteService.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PlanteDTO> result = planteController.getAll();

        assertEquals(2, result.size());
        assertEquals("Tournesol", result.get(0).getNom());
        assertEquals("Pisto-pois", result.get(1).getNom());
        verify(planteService).findAll();
    }

    @Test
    public void testCreate_ShouldCallServiceSave() {
        PlanteDTO dto = getSamplePlanteDTO();

        planteController.create(dto);

        verify(planteService).save(dto);
    }

    @Test
    public void testUpdate_ShouldCallServiceUpdate() {
        PlanteDTO dto = getSamplePlanteDTO();
        dto.setNom("Tournesol Boosté");

        planteController.update(dto);

        verify(planteService).update(dto);
    }

    @Test
    public void testDelete_ShouldCallServiceDelete() {
        planteController.delete(3);

        verify(planteService).delete(3);
    }
}
