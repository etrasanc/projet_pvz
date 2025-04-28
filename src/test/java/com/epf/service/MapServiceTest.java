package com.epf.service;

import com.epf.dao.MapDAO;
import com.epf.dto.MapDTO;
import com.epf.model.Map;
import com.epf.service.MapServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapServiceTest {

    @Mock
    private MapDAO mapDAO;

    @InjectMocks
    private MapServiceImpl mapService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private MapDTO getSampleDTO() {
        MapDTO dto = new MapDTO();
        dto.setLigne(10);
        dto.setColonne(15);
        dto.setChemin_image("/img/map.png");
        return dto;
    }

    @Test
    public void testFindById_ShouldReturnDTO() {
        Map map = new Map(10, 15, "/img/map.png");
        map.setId_map(1);
        when(mapDAO.findById(1)).thenReturn(map);

        MapDTO dto = mapService.findById(1);

        assertNotNull(dto);
        assertEquals(10, dto.getLigne());
        assertEquals(15, dto.getColonne());
        assertEquals("/img/map.png", dto.getChemin_image());
    }

    @Test
    public void testFindAll_ShouldReturnListOfDTOs() {
        Map map1 = new Map(10, 15, "/img/map1.png");
        map1.setId_map(1);
        Map map2 = new Map(20, 25, "/img/map2.png");
        map2.setId_map(2);

        when(mapDAO.findAll()).thenReturn(Arrays.asList(map1, map2));

        List<MapDTO> result = mapService.findAll();

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getLigne());
        assertEquals("/img/map2.png", result.get(1).getChemin_image());
    }

    @Test
    public void testSave_ShouldCallDAOSave() {
        MapDTO dto = getSampleDTO();

        mapService.save(dto);

        verify(mapDAO, times(1)).save(any(Map.class));
    }

    @Test
    public void testUpdate_ShouldCallDAOUpdate() {
        MapDTO dto = getSampleDTO();

        mapService.update(dto);

        verify(mapDAO, times(1)).update(any(Map.class));
    }

    @Test
    public void testDelete_ShouldCallDAODelete() {
        mapService.delete(3);

        verify(mapDAO, times(1)).delete(3);
    }
}
