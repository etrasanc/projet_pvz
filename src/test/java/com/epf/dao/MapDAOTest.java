package com.epf.dao;

import com.epf.model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.util.List;

public class MapDAOTest {

    private MapDAOImpl mapDAO;
    private ZombieDAOImpl zombieDAO;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Configuration de la base de données H2 en mémoire
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("DROP TABLE IF EXISTS Map");
        jdbcTemplate.execute("DROP TABLE IF EXISTS zombie");  // Ajoute cette ligne pour supprimer la table zombie s'il y en a une
        jdbcTemplate.execute("CREATE TABLE Map (" +
                "id_map INT AUTO_INCREMENT PRIMARY KEY, " +
                "ligne INT, " +
                "colonne INT, " +
                "chemin_image VARCHAR(255))");

        jdbcTemplate.execute("CREATE TABLE zombie (" +
                "id_zombie INT AUTO_INCREMENT PRIMARY KEY, " +
                "nom VARCHAR(255), " +
                "point_de_vie INT, " +
                "attaque_par_seconde DOUBLE, " +
                "degat_attaque INT, " +
                "vitesse_de_deplacement DOUBLE, " +
                "id_map INT, " +
                "chemin_image VARCHAR(255))");

        mapDAO = new MapDAOImpl(jdbcTemplate);
        zombieDAO = new ZombieDAOImpl(jdbcTemplate);
    }


    @Test
    public void testSaveAndFindById() {
        Map map = new Map(5, 10, "path/to/image.png");

        mapDAO.save(map);

        assertNotNull(map.getId_map());

        Map foundMap = mapDAO.findById(map.getId_map());
        assertNotNull(foundMap);
        assertEquals(5, foundMap.getLigne());
        assertEquals(10, foundMap.getColonne());
        assertEquals("path/to/image.png", foundMap.getChemin_image());
    }

    @Test
    public void testFindAll() {
        Map map1 = new Map(5, 10, "path/to/image1.png");
        Map map2 = new Map(6, 12, "path/to/image2.png");

        mapDAO.save(map1);
        mapDAO.save(map2);

        List<Map> maps = mapDAO.findAll();
        assertEquals(2, maps.size());
    }

    @Test
    public void testUpdate() {
        Map map = new Map(5, 10, "path/to/image.png");
        mapDAO.save(map);

        map.setLigne(15);
        map.setColonne(20);
        map.setChemin_image("new/path/to/image.jpg");
        mapDAO.update(map);

        Map updatedMap = mapDAO.findById(map.getId_map());
        assertEquals(15, updatedMap.getLigne());
        assertEquals(20, updatedMap.getColonne());
        assertEquals("new/path/to/image.jpg", updatedMap.getChemin_image());
    }

    @Test
    public void testDelete() {
        Map map = new Map(5, 10, "path/to/image.png");
        mapDAO.save(map);

        List<Map> mapsBefore = mapDAO.findAll();
        int countBefore = mapsBefore.size();

        mapDAO.delete(map.getId_map());

        List<Map> mapsAfter = mapDAO.findAll();
        int countAfter = mapsAfter.size();

        assertEquals(countBefore - 1, countAfter);
    }

    @Test
    public void testExistsById() {
        Map map = new Map(5, 10, "path/to/image.png");
        mapDAO.save(map);

        assertTrue(mapDAO.existsById(map.getId_map()));

        // Vérification d'une map qui n'existe pas
        assertFalse(mapDAO.existsById(999));  // Id qui n'existe pas
    }
}
