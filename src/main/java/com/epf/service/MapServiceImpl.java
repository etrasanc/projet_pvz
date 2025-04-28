package com.epf.service;

import com.epf.dao.MapDAO;
import com.epf.dto.MapDTO;
import com.epf.mapper.MapMapper;
import com.epf.model.Map;
import com.epf.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    private final MapDAO MapDAO;

    @Autowired
    public MapServiceImpl(MapDAO MapDAO) {
        this.MapDAO = MapDAO;
    }

    @Override
    public MapDTO findById(int id_Map) {
        return MapMapper.toDTO(MapDAO.findById(id_Map));
    }

    @Override
    public List<MapDTO> findAll() {
        return MapDAO.findAll().stream()
                .map(MapMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(MapDTO MapDTO) {
        Map Map = MapMapper.toModel(MapDTO);
        MapDAO.save(Map);
    }

    @Override
    public void update(MapDTO MapDTO) {
        Map Map = new Map(
                MapDTO.getId_map(),
                MapDTO.getLigne(),
                MapDTO.getColonne(),
                MapDTO.getChemin_image()
        );
        MapDAO.update(Map);
    }

    @Override
    public void delete(int id_Map) {
        MapDAO.delete(id_Map);
    }
}