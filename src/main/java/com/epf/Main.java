package com.epf;

import com.epf.config.DataSourceConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        DataSourceConfig dsc = new DataSourceConfig();

        try (Connection connection = dsc.DataSource().getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Map");

            while (resultSet.next()) {
                String nom = resultSet.getString("id_map");
                System.out.println("Plante : " + nom);
            }
        }catch(Exception e){
            System.err.println("Erreur de connexion : "+e.getMessage());
        }
    }
}
