package com.epf.mapper;

import com.epf.dto.MapDTO;
import com.epf.model.Map;

public class MapMapper {
    public static Map toModel(MapDTO dto) {
        return new Map(
                dto.getId_map(),
                dto.getLigne(),
                dto.getColonne(),
                dto.getChemin_image()
        );
    }

    public static MapDTO toDTO(Map map) {
        MapDTO dto = new MapDTO();
        dto.setId_map(map.getId_map());
        dto.setId_map(map.getId_map());
        dto.setLigne(map.getLigne());
        dto.setColonne(map.getColonne());
        dto.setChemin_image(map.getChemin_image());
        return dto;
    }
}
