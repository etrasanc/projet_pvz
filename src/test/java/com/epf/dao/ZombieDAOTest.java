package com.epf.dao;

import com.epf.model.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.util.List;

public class ZombieDAOTest {

    private ZombieDAOImpl zombieDAO;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        jdbcTemplate = new JdbcTemplate(dataSource);
        zombieDAO = new ZombieDAOImpl(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE IF EXISTS zombie");
        // Création de la table zombie dans la base de données H2
        jdbcTemplate.execute("CREATE TABLE zombie (" +
                "id_zombie INT AUTO_INCREMENT PRIMARY KEY, " +
                "nom VARCHAR(255), " +
                "point_de_vie INT, " +
                "attaque_par_seconde DOUBLE, " +
                "degat_attaque INT, " +
                "vitesse_de_deplacement DOUBLE, " +
                "id_map INT, " +
                "chemin_image VARCHAR(255))");

        // Initialisation du DAO avec JdbcTemplate
        zombieDAO = new ZombieDAOImpl(jdbcTemplate);
    }

    @Test
    public void testSaveAndFindById() {
        // Création d'un nouveau Zombie
        Zombie zombie = new Zombie("ZombieTest", 100, 1.5, 10, 2.0, 1, "/path/to/image.png");

        // Sauvegarde du zombie
        zombieDAO.save(zombie);

        // Vérification que l'id a bien été généré
        assertNotNull(zombie.getId_zombie());

        // Recherche du zombie par son id
        Zombie foundZombie = zombieDAO.findById(zombie.getId_zombie());
        assertNotNull(foundZombie);
        assertEquals("ZombieTest", foundZombie.getNom());
    }

    @Test
    public void testFindAll() {
        // Création et insertion de 2 zombies
        Zombie zombie1 = new Zombie("Zombie1", 100, 1.5, 10, 2.0, 1, "/path/to/image1.png");
        Zombie zombie2 = new Zombie("Zombie2", 150, 2.0, 15, 2.5, 1, "/path/to/image2.png");

        zombieDAO.save(zombie1);
        zombieDAO.save(zombie2);

        // Vérification de la taille de la liste
        List<Zombie> zombies = zombieDAO.findAll();
        assertEquals(2, zombies.size());
    }

    @Test
    public void testFindByMap() {
        // Création et insertion de zombies
        Zombie zombie1 = new Zombie("ZombieMap1", 100, 1.5, 10, 2.0, 1, "/path/to/image1.png");
        Zombie zombie2 = new Zombie("ZombieMap2", 150, 2.0, 15, 2.5, 2, "/path/to/image2.png");

        zombieDAO.save(zombie1);
        zombieDAO.save(zombie2);

        // Recherche des zombies associés à la map 1
        List<Zombie> zombiesMap1 = zombieDAO.findByMap(1);
        assertEquals(1, zombiesMap1.size());
        assertEquals("ZombieMap1", zombiesMap1.get(0).getNom());
    }

    @Test
    public void testUpdate() {
        // Création et insertion d'un zombie
        Zombie zombie = new Zombie("ZombieUpdate", 100, 1.5, 10, 2.0, 1, "/path/to/image.png");
        zombieDAO.save(zombie);

        // Modification du nom du zombie
        zombie.setNom("ZombieUpdated");
        zombieDAO.update(zombie);

        // Récupération du zombie mis à jour
        Zombie updatedZombie = zombieDAO.findById(zombie.getId_zombie());
        assertEquals("ZombieUpdated", updatedZombie.getNom());
    }

    @Test
    public void testDelete() {
        // Création et insertion d'un zombie
        Zombie zombie = new Zombie("ZombieToDelete", 100, 1.5, 10, 2.0, 1, "/path/to/image.png");
        zombieDAO.save(zombie);

        // Vérification que le zombie a été ajouté
        List<Zombie> zombiesBefore = zombieDAO.findAll();
        int countBefore = zombiesBefore.size();

        // Suppression du zombie
        zombieDAO.delete(zombie.getId_zombie());

        // Vérification qu'il y a un zombie en moins
        List<Zombie> zombiesAfter = zombieDAO.findAll();
        int countAfter = zombiesAfter.size();

        assertEquals(countBefore - 1, countAfter);
    }
}
