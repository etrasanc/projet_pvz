package com.epf.dao;

import com.epf.model.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MapDAOImpl implements MapDAO {
    private final JdbcTemplate jdbcTemplate;

    public MapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Map> MapRowMapper = (rs, rowNum) ->
            new Map( rs.getInt("id_map"), rs.getInt("ligne"), rs.getInt("colonne"), rs.getString("chemin_image"));

    @Override
    public Map findById(int id_Map) {
        return jdbcTemplate.queryForObject("SELECT * FROM Map WHERE id_Map = ?", MapRowMapper, id_Map);
    }
    @Override
    public List<Map> findAll() {
        return jdbcTemplate.query("SELECT * FROM Map", MapRowMapper);
    }
    @Override
    public void save(Map Map) {
        String sql = "INSERT INTO Map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Map.getLigne());
            ps.setInt(2, Map.getColonne());
            ps.setString(3, Map.getChemin_image());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            Map.setId_map(key.intValue());
        }
    }
    @Override
    public void delete(int id_Map) {
        String deleteZombieSql = "DELETE FROM zombie WHERE id_Map = ?";
        jdbcTemplate.update(deleteZombieSql, id_Map);
        jdbcTemplate.update("DELETE FROM Map WHERE id_Map = ?", id_Map);
    }
    @Override
    public void update(Map Map) {
        jdbcTemplate.update("UPDATE Map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_Map = ?", Map.getLigne(), Map.getColonne(), Map.getChemin_image(), Map.getId_map());}
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM map WHERE id_map = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
