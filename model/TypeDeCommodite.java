package model;

/**
 * 
 */
public class TypeDeCommodite {

    /**
     * Default constructor
     */
    public TypeDeCommodite(String nom, int prix) {
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

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }
}
