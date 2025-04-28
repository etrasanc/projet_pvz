package com.epf.service;

import com.epf.dto.MapDTO;
import java.util.List;

public interface MapService {
    MapDTO findById(int id_map);
    List<MapDTO> findAll();
    void save(MapDTO mapDTO);
    void update(MapDTO mapDTO);
    void delete(int id_map);
}
