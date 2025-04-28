package com.epf.service;

import com.epf.dao.MapDAO;
import com.epf.dao.ZombieDAO;
import com.epf.dto.ZombieDTO;
import com.epf.exception.InvalidMapException;
import com.epf.model.Zombie;
import com.epf.service.ZombieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZombieServiceTest {

    @Mock
    private ZombieDAO zombieDAO;

    @Mock
    private MapDAO mapDAO;

    @InjectMocks
    private ZombieServiceImpl zombieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ZombieDTO getSampleDTO() {
        ZombieDTO dto = new ZombieDTO();
        dto.setNom("ZombieTest");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(1.2);
        dto.setDegat_attaque(15);
        dto.setVitesse_de_deplacement(2.5);
        dto.setId_map(1);
        dto.setChemin_image("/img/zombie.png");
        return dto;
    }

    @Test
    public void testSave_WhenMapExists_ShouldSaveZombie() {
        ZombieDTO dto = getSampleDTO();
        when(mapDAO.existsById(dto.getId_map())).thenReturn(true);
        when(zombieDAO.save(any())).thenReturn(42);

        zombieService.save(dto);

        verify(zombieDAO, times(1)).save(any(Zombie.class));
        assertEquals(42, dto.getId_zombie());
    }

    @Test
    public void testSave_WhenMapDoesNotExist_ShouldThrowException() {
        ZombieDTO dto = getSampleDTO();
        when(mapDAO.existsById(dto.getId_map())).thenReturn(false);

        assertThrows(InvalidMapException.class, () -> zombieService.save(dto));
        verify(zombieDAO, never()).save(any());
    }

    @Test
    public void testFindById_ShouldReturnDTO() {
        Zombie zombie = new Zombie("ZombieX", 100, 1.5, 10, 2.0, 1, "/img.png");
        zombie.setId_zombie(1);
        when(zombieDAO.findById(1)).thenReturn(zombie);

        ZombieDTO dto = zombieService.findById(1);

        assertEquals("ZombieX", dto.getNom());
        assertEquals(1, dto.getId_map());
    }

    @Test
    public void testFindAll_ShouldReturnAllDTOs() {
        Zombie z1 = new Zombie("Z1", 100, 1.5, 10, 2.0, 1, "/img1.png");
        z1.setId_zombie(1);
        Zombie z2 = new Zombie("Z2", 150, 2.0, 20, 2.5, 2, "/img2.png");
        z2.setId_zombie(2);

        when(zombieDAO.findAll()).thenReturn(Arrays.asList(z1, z2));

        List<ZombieDTO> result = zombieService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByMap_ShouldReturnDTOs() {
        Zombie z = new Zombie("ZMap", 120, 1.8, 12, 2.2, 1, "/zmap.png");
        z.setId_zombie(10);

        when(zombieDAO.findByMap(1)).thenReturn(List.of(z));

        List<ZombieDTO> zombies = zombieService.findByMap(1);

        assertEquals(1, zombies.size());
        assertEquals("ZMap", zombies.get(0).getNom());
    }

    @Test
    public void testUpdate_ShouldCallDAOUpdate() {
        ZombieDTO dto = getSampleDTO();
        dto.setId_zombie(99);
        dto.setNom("UpdatedZombie");

        zombieService.update(dto);

        verify(zombieDAO, times(1)).update(any(Zombie.class));
    }

    @Test
    public void testDelete_ShouldCallDAODelete() {
        zombieService.delete(5);
        verify(zombieDAO).delete(5);
    }
}

