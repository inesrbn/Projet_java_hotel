package model;

/**
 * 
 */
public class Option {

    /**
     * Default constructor
     */
    public Option(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }

    /**
     * 
     */
    public String nom;

    /**
     * 
     */
    public int prix;

    /**
     * 
     */
    public Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

}