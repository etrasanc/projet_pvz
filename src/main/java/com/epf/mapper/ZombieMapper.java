package com.epf.mapper;

import com.epf.dto.ZombieDTO;
import com.epf.model.Zombie;

public class ZombieMapper {

    public static Zombie toModel(ZombieDTO dto) {
        return new Zombie(
                dto.getId_zombie(),
                dto.getNom(),
                dto.getPoint_de_vie(),
                dto.getAttaque_par_seconde(),
                dto.getDegat_attaque(),
                dto.getVitesse_de_deplacement(),
                dto.getId_map(),
                dto.getChemin_image()
        );
    }

    public static ZombieDTO toDTO(Zombie zombie) {
        ZombieDTO dto = new ZombieDTO();
        dto.setId_zombie(zombie.getId_zombie());
        dto.setNom(zombie.getNom());
        dto.setPoint_de_vie(zombie.getPoint_de_vie());
        dto.setAttaque_par_seconde(zombie.getAttaque_par_seconde());
        dto.setDegat_attaque(zombie.getDegat_attaque());
        dto.setVitesse_de_deplacement(zombie.getVitesse_de_deplacement());
        dto.setId_map(zombie.getId_map());
        dto.setChemin_image(zombie.getChemin_image());
        return dto;
    }
}

