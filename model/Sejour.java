package model;

import java.time.LocalDate;
import java.util.Vector;

/**
 * 
 */
public class Sejour {

    /**
     * Default constructor
     */
    public Sejour(Reservation reservation) {
        this.date_de_debut = reservation.getDateDebut();
        this.date_de_fin = reservation.getDateFin();
        this.client = reservation.getClient();
        this.commodite = reservation.getCommodite();

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
    public int consommation;

    /**
     * 
     */
    public Client client;

    /**
     * 
     */
    public Commodite commodite;

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

    public LocalDate getDateDebut() {
        return date_de_debut;
    }

    public LocalDate getDateFin() {
        return date_de_fin;
    }

    public int getConsommation() {
        return consommation;
    }

    public void setConsommation(int consommation) {
        this.consommation = consommation;
    }

    public Client getClient() {
        return client;
    }

    public Commodite getCommodite() {
        return commodite;
    }
}