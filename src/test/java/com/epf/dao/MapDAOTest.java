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

        // Créer les tables nécessaires
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
                "chemin_image VARCHAR(255))");  // Crée la table zombie si elle n'existe pas

        // Initialisation du DAO avec JdbcTemplate
        mapDAO = new MapDAOImpl(jdbcTemplate);
        zombieDAO = new ZombieDAOImpl(jdbcTemplate);
    }


    @Test
    public void testSaveAndFindById() {
        // Création d'une nouvelle Map
        Map map = new Map(5, 10, "path/to/image.png");

        // Sauvegarde de la map
        mapDAO.save(map);

        // Vérification que l'id a bien été généré
        assertNotNull(map.getId_map());

        // Recherche de la map par son id
        Map foundMap = mapDAO.findById(map.getId_map());
        assertNotNull(foundMap);
        assertEquals(5, foundMap.getLigne());
        assertEquals(10, foundMap.getColonne());
        assertEquals("path/to/image.png", foundMap.getChemin_image());
    }

    @Test
    public void testFindAll() {
        // Création et insertion de 2 maps
        Map map1 = new Map(5, 10, "path/to/image1.png");
        Map map2 = new Map(6, 12, "path/to/image2.png");

        mapDAO.save(map1);
        mapDAO.save(map2);

        // Vérification de la taille de la liste
        List<Map> maps = mapDAO.findAll();
        assertEquals(2, maps.size());
    }

    @Test
    public void testUpdate() {
        // Création et insertion d'une map
        Map map = new Map(5, 10, "path/to/image.png");
        mapDAO.save(map);

        // Modification des attributs de la map
        map.setLigne(15);
        map.setColonne(20);
        map.setChemin_image("new/path/to/image.jpg");
        mapDAO.update(map);

        // Récupération de la map mise à jour
        Map updatedMap = mapDAO.findById(map.getId_map());
        assertEquals(15, updatedMap.getLigne());
        assertEquals(20, updatedMap.getColonne());
        assertEquals("new/path/to/image.jpg", updatedMap.getChemin_image());
    }

    @Test
    public void testDelete() {
        // Création et insertion d'une map
        Map map = new Map(5, 10, "path/to/image.png");
        mapDAO.save(map);

        // Vérification que la map a été ajoutée
        List<Map> mapsBefore = mapDAO.findAll();
        int countBefore = mapsBefore.size();

        // Suppression de la map
        mapDAO.delete(map.getId_map());

        // Vérification qu'il y a une map en moins
        List<Map> mapsAfter = mapDAO.findAll();
        int countAfter = mapsAfter.size();

        assertEquals(countBefore - 1, countAfter);
    }

    @Test
    public void testExistsById() {
        // Création et insertion d'une map
        Map map = new Map(5, 10, "path/to/image.png");
        mapDAO.save(map);

        // Vérification que la map existe
        assertTrue(mapDAO.existsById(map.getId_map()));

        // Vérification d'une map qui n'existe pas
        assertFalse(mapDAO.existsById(999));  // Id qui n'existe pas
    }
}
