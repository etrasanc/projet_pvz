package com.epf.dao;

import com.epf.model.Zombie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ZombieDAOImpl implements ZombieDAO {
    private final JdbcTemplate jdbcTemplate;

    public ZombieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Zombie> zombieRowMapper = (rs, rowNum) ->
            new Zombie( rs.getInt("id_zombie"),rs.getString("nom"), rs.getInt("point_de_vie"), rs.getDouble("attaque_par_seconde"), rs.getInt("degat_attaque"), rs.getDouble("vitesse_de_deplacement"), rs.getInt("id_map"), rs.getString("chemin_image"));

    @Override
    public Zombie findById(int id_zombie) {
        return jdbcTemplate.queryForObject("SELECT * FROM zombie WHERE id_zombie = ?", zombieRowMapper, id_zombie);
    }
    @Override
    public List<Zombie> findAll() {
        return jdbcTemplate.query("SELECT * FROM zombie", zombieRowMapper);
    }
    @Override
    public List<Zombie> findByMap(int id_map) {
        return jdbcTemplate.query("SELECT * FROM zombie WHERE id_map = ?", zombieRowMapper, id_map);
    }
    @Override
    public int save(Zombie zombie) {

        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, id_map, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, zombie.getNom());
            ps.setInt(2, zombie.getPoint_de_vie());
            ps.setDouble(3, zombie.getAttaque_par_seconde());
            ps.setInt(4, zombie.getDegat_attaque());
            ps.setDouble(5, zombie.getVitesse_de_deplacement());
            ps.setInt(6, zombie.getId_map());
            ps.setString(7, zombie.getChemin_image());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            zombie.setId_zombie(key.intValue());
        }
        return keyHolder.getKey().intValue();
    }
    @Override
    public void delete(int id_zombie) {
        jdbcTemplate.update("DELETE FROM zombie WHERE id_zombie = ?", id_zombie);
    }
    @Override
    public void update(Zombie zombie) {
        jdbcTemplate.update("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, id_map = ?, chemin_image = ? WHERE id_zombie = ?", zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse_de_deplacement(), zombie.getId_map(), zombie.getChemin_image(), zombie.getId_zombie());}
    }



