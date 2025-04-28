package com.epf.service;

import com.epf.dao.MapDAO;
import com.epf.dao.ZombieDAO;
import com.epf.dto.ZombieDTO;
import com.epf.exception.InvalidMapException;
import com.epf.mapper.ZombieMapper;
import com.epf.model.Zombie;
import com.epf.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZombieServiceImpl implements ZombieService {

    private final ZombieDAO zombieDAO;
    private final MapDAO mapDAO;

    @Autowired
    public ZombieServiceImpl(ZombieDAO zombieDAO, MapDAO mapDAO) {
        this.zombieDAO = zombieDAO;
        this.mapDAO = mapDAO;
    }

    @Override
    public ZombieDTO findById(int id_zombie) {
        return ZombieMapper.toDTO(zombieDAO.findById(id_zombie));
    }

    @Override
    public List<ZombieDTO> findAll() {
        return zombieDAO.findAll().stream()
                .map(ZombieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ZombieDTO> findByMap(int id_map) {
        return zombieDAO.findByMap(id_map).stream()
                .map(ZombieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void save(ZombieDTO zombieDTO) {
        if (!mapDAO.existsById(zombieDTO.getId_map())) {
            throw new InvalidMapException("La map avec l'id " + zombieDTO.getId_map() + " n'existe pas.");
        }
        Zombie zombie = ZombieMapper.toModel(zombieDTO);


        int generatedId = zombieDAO.save(zombie);
        zombieDTO.setId_zombie(generatedId);
    }

    @Override
    public void update(ZombieDTO zombieDTO) {
        Zombie zombie = new Zombie(
                zombieDTO.getId_zombie(),
                zombieDTO.getNom(),
                zombieDTO.getPoint_de_vie(),
                zombieDTO.getAttaque_par_seconde(),
                zombieDTO.getDegat_attaque(),
                zombieDTO.getVitesse_de_deplacement(),
                zombieDTO.getId_map(),
                zombieDTO.getChemin_image()
        );
        zombieDAO.update(zombie);
    }

    @Override
    public void delete(int id_zombie) {
        zombieDAO.delete(id_zombie);
    }
}
