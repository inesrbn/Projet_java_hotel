package model;

/**
 * 
 */
public class Produit {

    /**
     * Default constructor
     */
    public Produit(String nom, double prix) {
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
    public double prix;

    /**
     * 
     */
    public Hotel hotel;

    /**
     * 
     */
    public Sejour sejour;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}