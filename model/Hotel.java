package model;

import java.util.Vector;

/**
 *
 */
public class Hotel {

    /**
     * Default constructor
     */
    public Hotel(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    /**
     *
     */
    public String nom;

    /**
     *
     */
    public String adresse;

    /**
     *
     */
    public int noteMoyenne;

    /**
     *
     */
    public Vector<Commodite> listCommodite = new Vector<Commodite>();

    /**
     *
     */
    public Vector<Client> listClient = new Vector<Client>();

    /**
     *
     */
    public Vector<Produit> listProduit = new Vector<Produit>();

    /**
     *
     */
    public Vector<Option> listOptions = new Vector<Option>();

    public void addClient(Client client) {
        listClient.add(client);
    }

    public void supprClient(Client client) {
        listClient.remove(client);
    }

    public void addCommodite(Commodite commodite) {
        listCommodite.add(commodite);
    }

    public void supprCommodite(Commodite commodite) {
        listCommodite.remove(commodite);
    }

    public void addProduit(Produit produit) {
        listProduit.add(produit);
    }

    public void addOption(Option option) {
        listOptions.add(option);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Vector<Client> getClients() {
        return listClient;
    }

    public Vector<Commodite> getCommodite() {
        return listCommodite;
    }

    public Vector<Option> getOptions() {
        return listOptions;
    }

    public Vector<Produit> getProduits() {
        return listProduit;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNote() {
        return noteMoyenne;
    }

    public void setNote(int noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }
}
