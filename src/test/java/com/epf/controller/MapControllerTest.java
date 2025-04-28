package com.epf.controller;

import com.epf.dto.MapDTO;
import com.epf.service.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapControllerTest {

    @Mock
    private MapService mapService;

    @InjectMocks
    private MapControllerImpl mapController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private MapDTO getSampleMapDTO() {
        MapDTO dto = new MapDTO();
        dto.setId_map(1);
        dto.setLigne(10);
        dto.setColonne(15);
        dto.setChemin_image("/img/map.png");
        return dto;
    }

    @Test
    public void testGetById_ShouldReturnMapDTO() {
        MapDTO expected = getSampleMapDTO();
        when(mapService.findById(1)).thenReturn(expected);

        MapDTO result = mapController.getById(1);

        assertNotNull(result);
        assertEquals(10, result.getLigne());
        assertEquals("/img/map.png", result.getChemin_image());
        verify(mapService).findById(1);
    }

    @Test
    public void testGetAll_ShouldReturnListOfMaps() {
        MapDTO m1 = getSampleMapDTO();
        MapDTO m2 = getSampleMapDTO();
        m2.setId_map(2);
        m2.setLigne(20);
        m2.setColonne(25);
        m2.setChemin_image("/img/map2.png");

        when(mapService.findAll()).thenReturn(Arrays.asList(m1, m2));

        List<MapDTO> result = mapController.getAll();

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getLigne());
        assertEquals("/img/map2.png", result.get(1).getChemin_image());
        verify(mapService).findAll();
    }

    @Test
    public void testCreate_ShouldCallServiceSave() {
        MapDTO dto = getSampleMapDTO();

        mapController.create(dto);

        verify(mapService).save(dto);
    }

    @Test
    public void testUpdate_ShouldCallServiceUpdate() {
        MapDTO dto = getSampleMapDTO();
        dto.setColonne(99);

        mapController.update(dto);

        verify(mapService).update(dto);
    }

    @Test
    public void testDelete_ShouldCallServiceDelete() {
        mapController.delete(3);

        verify(mapService).delete(3);
    }
}
