package com.epf.dao;

import com.epf.model.Plante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlanteDAOImpl implements PlanteDAO {
    private final JdbcTemplate jdbcTemplate;

    public PlanteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Plante> planteRowMapper = (rs, rowNum) ->
            new Plante( rs.getInt("id_plante"), rs.getString("nom"), rs.getInt("point_de_vie"), rs.getDouble("attaque_par_seconde"), rs.getInt("degat_attaque"), rs.getInt("cout"), rs.getDouble("soleil_par_seconde"), rs.getString("effet"), rs.getString("chemin_image"));

    @Override
    public Plante findById(int id_plante) {
        return jdbcTemplate.queryForObject("SELECT * FROM plante WHERE id_plante = ?", planteRowMapper, id_plante);
    }
    @Override
    public List<Plante> findAll() {
        return jdbcTemplate.query("SELECT * FROM plante", planteRowMapper);
    }

    @Override
    public void save(Plante plante) {
            String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, plante.getNom());
                ps.setInt(2, plante.getPoint_de_vie());
                ps.setDouble(3, plante.getAttaque_par_seconde());
                ps.setInt(4, plante.getDegat_attaque());
                ps.setDouble(5, plante.getCout());
                ps.setDouble(6, plante.getSoleil_par_seconde());
                ps.setString(7, plante.getEffet());
                ps.setString(8, plante.getChemin_image());
                return ps;
            }, keyHolder);

            Number key = keyHolder.getKey();
            if (key != null) {
                plante.setId_plante(key.intValue());
            }
    }
    @Override
    public void delete(int id_plante) {
        jdbcTemplate.update("DELETE FROM plante WHERE id_plante = ?", id_plante);
    }
    @Override
    public void update(Plante plante) {
        jdbcTemplate.update("UPDATE plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?", plante.getNom(), plante.getPoint_de_vie(), plante.getAttaque_par_seconde(), plante.getDegat_attaque(), plante.getCout(), plante.getSoleil_par_seconde(), plante.getEffet(), plante.getChemin_image(), plante.getId_plante());
    }
}
