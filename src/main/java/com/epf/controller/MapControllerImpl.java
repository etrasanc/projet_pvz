package com.epf.controller;

import com.epf.dto.MapDTO;
import com.epf.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapControllerImpl implements MapController {
    private final MapService mapService;

    @Autowired
    public MapControllerImpl(MapService mapService) {
        this.mapService = mapService;
    }

    @Override
    public MapDTO getById(@PathVariable int id) {
        return mapService.findById(id);
    }

    @Override
    public List<MapDTO> getAll() {
        return mapService.findAll();
    }


    @Override
    public void create(@RequestBody MapDTO mapDTO) {
        mapService.save(mapDTO);
    }

    @Override
    public void update(@RequestBody MapDTO mapDTO) {
        mapService.update(mapDTO);
    }

    @Override
    public void delete(@PathVariable int id) {
        mapService.delete(id);
    }
}
