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
        jdbcTemplate.execute("CREATE TABLE zombie (" +
                "id_zombie INT AUTO_INCREMENT PRIMARY KEY, " +
                "nom VARCHAR(255), " +
                "point_de_vie INT, " +
                "attaque_par_seconde DOUBLE, " +
                "degat_attaque INT, " +
                "vitesse_de_deplacement DOUBLE, " +
                "id_map INT, " +
                "chemin_image VARCHAR(255))");

        zombieDAO = new ZombieDAOImpl(jdbcTemplate);
    }

    @Test
    public void testSaveAndFindById() {
        Zombie zombie = new Zombie("ZombieTest", 100, 1.5, 10, 2.0, 1, "/path/to/image.png");

        zombieDAO.save(zombie);

        assertNotNull(zombie.getId_zombie());

        Zombie foundZombie = zombieDAO.findById(zombie.getId_zombie());
        assertNotNull(foundZombie);
        assertEquals("ZombieTest", foundZombie.getNom());
    }

    @Test
    public void testFindAll() {
        Zombie zombie1 = new Zombie("Zombie1", 100, 1.5, 10, 2.0, 1, "/path/to/image1.png");
        Zombie zombie2 = new Zombie("Zombie2", 150, 2.0, 15, 2.5, 1, "/path/to/image2.png");

        zombieDAO.save(zombie1);
        zombieDAO.save(zombie2);

        List<Zombie> zombies = zombieDAO.findAll();
        assertEquals(2, zombies.size());
    }

    @Test
    public void testFindByMap() {
        Zombie zombie1 = new Zombie("ZombieMap1", 100, 1.5, 10, 2.0, 1, "/path/to/image1.png");
        Zombie zombie2 = new Zombie("ZombieMap2", 150, 2.0, 15, 2.5, 2, "/path/to/image2.png");

        zombieDAO.save(zombie1);
        zombieDAO.save(zombie2);

        List<Zombie> zombiesMap1 = zombieDAO.findByMap(1);
        assertEquals(1, zombiesMap1.size());
        assertEquals("ZombieMap1", zombiesMap1.get(0).getNom());
    }

    @Test
    public void testUpdate() {
        Zombie zombie = new Zombie("ZombieUpdate", 100, 1.5, 10, 2.0, 1, "/path/to/image.png");
        zombieDAO.save(zombie);

        zombie.setNom("ZombieUpdated");
        zombieDAO.update(zombie);

        Zombie updatedZombie = zombieDAO.findById(zombie.getId_zombie());
        assertEquals("ZombieUpdated", updatedZombie.getNom());
    }

    @Test
    public void testDelete() {
        Zombie zombie = new Zombie("ZombieToDelete", 100, 1.5, 10, 2.0, 1, "/path/to/image.png");
        zombieDAO.save(zombie);

        List<Zombie> zombiesBefore = zombieDAO.findAll();
        int countBefore = zombiesBefore.size();

        zombieDAO.delete(zombie.getId_zombie());

        List<Zombie> zombiesAfter = zombieDAO.findAll();
        int countAfter = zombiesAfter.size();

        assertEquals(countBefore - 1, countAfter);
    }
}
