package com.epf.controller;

import com.epf.dto.ZombieDTO;
import com.epf.service.ZombieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    @InjectMocks
    private ZombieControllerImpl zombieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ZombieDTO getSampleZombieDTO() {
        ZombieDTO dto = new ZombieDTO();
        dto.setId_zombie(1);
        dto.setNom("TestZombie");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(1.5);
        dto.setDegat_attaque(20);
        dto.setVitesse_de_deplacement(2.0);
        dto.setId_map(1);
        dto.setChemin_image("/img/zombie.png");
        return dto;
    }

    @Test
    public void testGetById_ShouldReturnZombieDTO() {
        ZombieDTO expected = getSampleZombieDTO();
        when(zombieService.findById(1)).thenReturn(expected);

        ZombieDTO result = zombieController.getById(1);

        assertNotNull(result);
        assertEquals("TestZombie", result.getNom());
        verify(zombieService).findById(1);
    }

    @Test
    public void testGetAll_ShouldReturnListOfZombies() {
        ZombieDTO z1 = getSampleZombieDTO();
        ZombieDTO z2 = getSampleZombieDTO();
        z2.setId_zombie(2);
        z2.setNom("AnotherZombie");

        when(zombieService.findAll()).thenReturn(Arrays.asList(z1, z2));

        List<ZombieDTO> result = zombieController.getAll();

        assertEquals(2, result.size());
        verify(zombieService).findAll();
    }

    @Test
    public void testGetByMap_ShouldReturnZombiesForMap() {
        ZombieDTO zombie = getSampleZombieDTO();
        when(zombieService.findByMap(1)).thenReturn(List.of(zombie));

        List<ZombieDTO> result = zombieController.getByMap(1);

        assertEquals(1, result.size());
        assertEquals("TestZombie", result.get(0).getNom());
        verify(zombieService).findByMap(1);
    }

    @Test
    public void testCreate_ShouldCallServiceSave() {
        ZombieDTO dto = getSampleZombieDTO();

        zombieController.create(dto);

        verify(zombieService).save(dto);
    }

    @Test
    public void testUpdate_ShouldCallServiceUpdate() {
        ZombieDTO dto = getSampleZombieDTO();
        dto.setNom("UpdatedZombie");

        zombieController.update(dto);

        verify(zombieService).update(dto);
    }

    @Test
    public void testDelete_ShouldCallServiceDelete() {
        zombieController.delete(3);

        verify(zombieService).delete(3);
    }
}

