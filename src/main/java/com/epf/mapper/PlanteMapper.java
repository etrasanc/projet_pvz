package com.epf.mapper;

import com.epf.dto.PlanteDTO;
import com.epf.model.Plante;

public class PlanteMapper {
    public static Plante toModel(PlanteDTO dto) {
        return new Plante(
                dto.getId_plante(),
                dto.getNom(),
                dto.getPoint_de_vie(),
                dto.getAttaque_par_seconde(),
                dto.getDegat_attaque(),
                dto.getCout(),
                dto.getSoleil_par_seconde(),
                dto.getEffet(),
                dto.getChemin_image()
        );
    }

    public static PlanteDTO toDTO(Plante plante) {
        PlanteDTO dto = new PlanteDTO();
        dto.setId_plante(plante.getId_plante());
        dto.setNom(plante.getNom());
        dto.setPoint_de_vie(plante.getPoint_de_vie());
        dto.setAttaque_par_seconde(plante.getAttaque_par_seconde());
        dto.setDegat_attaque(plante.getDegat_attaque());
        dto.setCout(plante.getCout());
        dto.setSoleil_par_seconde(plante.getSoleil_par_seconde());
        dto.setEffet(plante.getEffet());
        dto.setChemin_image(plante.getChemin_image());
        return dto;
    }
}
