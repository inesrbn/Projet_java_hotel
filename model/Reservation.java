package model;

import java.time.LocalDate;
import java.util.Vector;

/**
 * 
 */
public class Reservation {

    /**
     * Default constructor
     */
    public Reservation(LocalDate date_de_debut, LocalDate date_de_fin) {

        if (verifReservation(date_de_debut, date_de_fin)) {
            this.date_de_debut = date_de_debut;
            this.date_de_fin = date_de_fin;
        }
    }

    /**
     * 
     */
    public LocalDate date_de_debut;

    /**
     * 
     */
    public LocalDate date_de_fin;

    /**
     * 
     */
    public Sejour sejour;

    /**
     * 
     */
    public Commodite commodite;

    /**
     * 
     */
    public Client client;

    /**
     * 
     */
    public Vector<Option> listOption = new Vector<Option>();

    public void addOption(Option option) {
        listOption.add(option);
    }

    public Vector<Option> getOptions() {
        return listOption;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public void setCommodite(Commodite commodite) {
        this.commodite = commodite;
    }

    public Commodite getCommodite() {
        return commodite;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDateDebut() {
        return date_de_debut;
    }

    public void setDateDebut(LocalDate date_de_debut) {
        this.date_de_debut = date_de_debut;
    }

    public LocalDate getDateFin() {
        return date_de_fin;
    }

    public void setDateFin(LocalDate date_de_fin) {
        this.date_de_fin = date_de_fin;
    }

    public Boolean verifReservation(LocalDate date_de_debut, LocalDate date_de_fin) {
        return (true);
    }
}