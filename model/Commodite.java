package model;

import java.util.Vector;

/**
 * 
 */
public class Commodite {

    /**
     * Default constructor
     */
    public Commodite(TypeDeCommodite type, int numero, int etage) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
    }

    /**
     * 
     */
    public int numero;

    /**
     * 
     */
    public int etage;

    /**
     * 
     */
    public TypeDeCommodite type;

    /**
     * 
     */
    public Hotel hotel;

    /**
     * 
     */
    public Sejour sejour;

    /**
     * 
     */
    public Vector<Reservation> listReservation = new Vector<Reservation>();

    public void addReservation(Reservation reservation) {
        listReservation.add(reservation);
    }

    public void supprReservation(Reservation reservation) {
        listReservation.remove(reservation);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public Sejour getSejour() {
        return sejour;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public TypeDeCommodite getType() {
        return type;
    }

    public Vector<Reservation> getListReservation() {
        return listReservation;
    }
}