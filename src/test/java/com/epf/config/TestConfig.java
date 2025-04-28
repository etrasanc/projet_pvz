package com.epf.config;

import com.epf.config.DataSourceConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConfig {
        public static void main(String[] args) {
            DataSourceConfig dsc = new DataSourceConfig();

            try (Connection connection = dsc.DataSource().getConnection()) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Map");

                while (resultSet.next()) {
                    String id = resultSet.getString("id_map");
                    System.out.println("identifiant : " + id);
                }
            }catch(Exception e){
                System.err.println("Erreur de connexion : "+e.getMessage());
            }
        }

}
