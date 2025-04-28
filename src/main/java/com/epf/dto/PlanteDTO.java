package com.epf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanteDTO {
    private int id_plante;
    private String nom;
    private int point_de_vie;
    private double attaque_par_seconde;
    private int degat_attaque;
    private int cout;
    private double soleil_par_seconde;
    private String effet;
    private String chemin_image;

    // Getters & Setters
    public int getId_plante() { return id_plante; }
    public void setId_plante(Integer id_plante) { this.id_plante = id_plante; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPoint_de_vie() { return point_de_vie; }
    public void setPoint_de_vie(int point_de_vie) { this.point_de_vie = point_de_vie; }

    public double getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(double attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public int getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(int degat_attaque) { this.degat_attaque = degat_attaque; }

    public int getCout() { return cout; }
    public void setCout(int cout) { this.cout = cout; }

    public double getSoleil_par_seconde() { return soleil_par_seconde; }
    public void setSoleil_par_seconde(double soleil_par_seconde) { this.soleil_par_seconde = soleil_par_seconde; }

    public String getEffet() { return effet; }
    public void setEffet(String effet) {this.effet = effet; }

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) { this.chemin_image = chemin_image; }
}
