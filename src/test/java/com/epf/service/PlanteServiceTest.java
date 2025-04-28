package com.epf.service;

import com.epf.dao.PlanteDAO;
import com.epf.dto.PlanteDTO;
import com.epf.model.Plante;
import com.epf.service.PlanteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlanteServiceTest {

    @Mock
    private PlanteDAO planteDAO;

    @InjectMocks
    private PlanteServiceImpl planteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private PlanteDTO getSampleDTO() {
        PlanteDTO dto = new PlanteDTO();
        dto.setNom("Tournesol");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(0.0);
        dto.setDegat_attaque(0);
        dto.setCout(50);
        dto.setSoleil_par_seconde(25.0);
        dto.setEffet("produit du soleil");
        dto.setChemin_image("/img/tournesol.png");
        return dto;
    }

    @Test
    public void testFindById_ShouldReturnDTO() {
        Plante plante = new Plante("Tournesol", 100, 0.0, 0, 50, 25.0, "produit du soleil", "/img/tournesol.png");
        plante.setId_plante(1);
        when(planteDAO.findById(1)).thenReturn(plante);

        PlanteDTO dto = planteService.findById(1);

        assertNotNull(dto);
        assertEquals("Tournesol", dto.getNom());
        assertEquals(100, dto.getPoint_de_vie());
        assertEquals("/img/tournesol.png", dto.getChemin_image());
    }

    @Test
    public void testFindAll_ShouldReturnListOfDTOs() {
        Plante p1 = new Plante("P1", 100, 1.0, 10, 100, 0.0, "tir", "/img/p1.png");
        p1.setId_plante(1);
        Plante p2 = new Plante("P2", 150, 0.0, 0, 50, 25.0, "soleil", "/img/p2.png");
        p2.setId_plante(2);

        when(planteDAO.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PlanteDTO> result = planteService.findAll();

        assertEquals(2, result.size());
        assertEquals("P1", result.get(0).getNom());
        assertEquals("/img/p2.png", result.get(1).getChemin_image());
    }

    @Test
    public void testSave_ShouldCallDAOSave() {
        PlanteDTO dto = getSampleDTO();

        planteService.save(dto);

        verify(planteDAO, times(1)).save(any(Plante.class));
    }

    @Test
    public void testUpdate_ShouldCallDAOUpdate() {
        PlanteDTO dto = getSampleDTO();
        dto.setId_plante(5);
        dto.setNom("PlanteModifiée");

        planteService.update(dto);

        verify(planteDAO, times(1)).update(any(Plante.class));
    }

    @Test
    public void testDelete_ShouldCallDAODelete() {
        planteService.delete(7);

        verify(planteDAO, times(1)).delete(7);
    }
}
