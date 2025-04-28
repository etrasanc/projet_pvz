package com.epf.dao;

import com.epf.model.Plante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlanteDAOTest {

    private PlanteDAOImpl planteDAO;
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

        // Créer la table plante
        jdbcTemplate.execute("DROP TABLE IF EXISTS plante");
        jdbcTemplate.execute("CREATE TABLE plante (" +
                "id_plante INT AUTO_INCREMENT PRIMARY KEY, " +
                "nom VARCHAR(255), " +
                "point_de_vie INT, " +
                "attaque_par_seconde DOUBLE, " +
                "degat_attaque INT, " +
                "cout DOUBLE, " +
                "soleil_par_seconde DOUBLE, " +
                "effet VARCHAR(255), " +
                "chemin_image VARCHAR(255))");

        // Initialisation du DAO avec JdbcTemplate
        planteDAO = new PlanteDAOImpl(jdbcTemplate);
    }

    @Test
    public void testSaveAndFindById() {
        // Création d'une nouvelle Plante
        Plante plante = new Plante("PlanteTest", 100, 1.5, 10, 50, 2.0, "EffetTest", "/path/to/image.png");

        // Sauvegarde de la plante
        planteDAO.save(plante);

        // Vérification que l'id a bien été généré
        assertNotNull(plante.getId_plante());

        // Recherche de la plante par son id
        Plante foundPlante = planteDAO.findById(plante.getId_plante());
        assertNotNull(foundPlante);
        assertEquals("PlanteTest", foundPlante.getNom());
    }

    @Test
    public void testFindAll() {
        // Création et insertion de 2 plantes
        Plante plante1 = new Plante("Plante1", 100, 1.5, 10, 50, 2.0, "Effet1", "/path/to/image1.png");
        Plante plante2 = new Plante("Plante2", 150, 2.0, 15, 60, 2.5, "Effet2", "/path/to/image2.png");

        planteDAO.save(plante1);
        planteDAO.save(plante2);

        // Vérification de la taille de la liste
        List<Plante> plantes = planteDAO.findAll();
        assertEquals(2, plantes.size());
    }

    @Test
    public void testUpdate() {
        // Création et insertion d'une plante
        Plante plante = new Plante("PlanteUpdate", 100, 1.5, 10, 50, 2.0, "EffetUpdate", "/path/to/image.png");
        planteDAO.save(plante);

        // Modification du nom de la plante
        plante.setNom("PlanteUpdated");
        planteDAO.update(plante);

        // Récupération de la plante mise à jour
        Plante updatedPlante = planteDAO.findById(plante.getId_plante());
        assertEquals("PlanteUpdated", updatedPlante.getNom());
    }

    @Test
    public void testDelete() {
        // Création et insertion d'une plante
        Plante plante = new Plante("PlanteToDelete", 100, 1.5, 10, 50, 2.0, "EffetToDelete", "/path/to/image.png");
        planteDAO.save(plante);

        // Vérification que la plante a été ajoutée
        List<Plante> plantesBefore = planteDAO.findAll();
        int countBefore = plantesBefore.size();

        // Suppression de la plante
        planteDAO.delete(plante.getId_plante());

        // Vérification qu'il y a une plante en moins
        List<Plante> plantesAfter = planteDAO.findAll();
        int countAfter = plantesAfter.size();

        assertEquals(countBefore - 1, countAfter);
    }
}
