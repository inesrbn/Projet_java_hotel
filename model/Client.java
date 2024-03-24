package model;

import java.util.Vector;

/**
 *
 */
public class Client {

    /**
     * Default constructor
     */
    public Client(String nom, String prenom, String email, int nbProduit) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;

        for (int i = 0; i < nbProduit; i++) {
            listQuantityProduct.add(0);
        }
    }

    /**
     *
     */
    public String nom;

    /**
     *
     */
    public String prenom;

    /**
     *
     */
    public String email;

    /**
     *
     */
    public int note = 0;

    /**
     *
     */
    public Hotel hotel;

    /**
     *
     */
    public Vector<Reservation> listReservation = new Vector<Reservation>();

    /**
     *
     */
    public Vector<Integer> listQuantityProduct = new Vector<Integer>();

    public Integer getListQuantityProduct(int index) {
        return listQuantityProduct.get(index);
    }

    public void setListQuantityProduct(int value, int index) {
        listQuantityProduct.set(index, value);
    }

    public void addReservation(Reservation reservation) {
        listReservation.add(reservation);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
