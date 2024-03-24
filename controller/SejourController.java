package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JSpinner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class SejourController {
    private model.Hotel hotel;
    private view.SejourView view;
    private Vector<model.Sejour> listSejour;
    private model.Sejour selectedSejour;

    public SejourController(model.Hotel hotel, view.SejourView view) {
        this.hotel = hotel;
        this.view = view;

        view.updateViewInit(getAllSejour(), hotel.getProduits());
        view.finSejourListener(new finSejourListener());
    }

    class finSejourListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            getCommoditeBySejour().setSejour(null);

            view.getRightPanel().setVisible(false);
            view.getMiddlePanel().setVisible(false);
            view.updateView(getAllSejour(), hotel.getProduits());
        }
    }

    private Vector<model.Sejour> getAllSejour() {
        listSejour = new Vector<model.Sejour>();
        for (model.Commodite commodite : hotel.getCommodite()) {
            if (commodite.getSejour() != null) {
                listSejour.add(commodite.getSejour());
            }
        }
        return listSejour;
    }

    private model.Commodite getCommoditeBySejour() {
        Vector<model.Commodite> listCommodite = hotel.getCommodite();
        for (model.Commodite commodite : listCommodite) {
            if (commodite.getSejour() == selectedSejour) {
                return commodite;
            }
        }
        return null;
    }

    public void selectSejourListener(model.Sejour sejour) {
        selectedSejour = sejour;

        LocalDate dateD = selectedSejour.getDateDebut();
        LocalDate dateF = selectedSejour.getDateFin();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String sDateD = dateD.format(formatter);
        String sDateF = dateF.format(formatter);

        view.getDebutSejourLabel().setText(sDateD);
        view.getFinSejourLabel().setText(sDateF);

        String nom = selectedSejour.getClient().getNom();
        String prenom = selectedSejour.getClient().getPrenom();
        String email = selectedSejour.getClient().getEmail();

        view.getInfoClientLabel().setText(nom + "  ");
        view.getInfoClientLabel1().setText(prenom + "  ");
        view.getInfoClientLabel2().setText(email + "  ");

        view.getRightPanel().setVisible(true);
        view.getMiddlePanel().setVisible(true);

        if (facturation() != -1) {
            view.getCoutTotalLabel().setText("Coût total :" + formatDecimalPrix(facturation()) + " €");
        }

        view.updateView();
        view.updateView(selectedSejour.listOption);
    }

    public void storeClientQuantity(int quantity, JSpinner s) {

        int i = 0;
        for (JSpinner spinner : view.getQuantitySpinners()) {
            if ((spinner == s) && (selectedSejour != null)) {
                selectedSejour.getClient().setListQuantityProduct(quantity, i);
                break;
            }
            i++;
        }
        if (facturation() != -1) {
            view.getCoutTotalLabel().setText("Coût total :" + formatDecimalPrix(facturation()) + " €");
        }
    }

    public void setQuantitySpinnersValue(Vector<JSpinner> spinners) {
        int i = 0;
        Vector<JSpinner> quantitySpinners = view.getQuantitySpinners();
        for (model.Produit produit : hotel.getProduits()) {
            Integer quantity = selectedSejour.getClient().getListQuantityProduct(i);
            quantitySpinners.get(i).setValue((Object) quantity);
            i++;
        }
    }

    private double facturation() {
        if (selectedSejour != null) {
            double coutChambre = selectedSejour.getCommodite().getType().getPrix();
            double coutOptions = 0;
            for (model.Option option : selectedSejour.getOptions()) {
                coutOptions += option.getPrix();
            }
            double coutProduits = 0;
            int i = 0;
            for (model.Produit produit : hotel.getProduits()) {

                coutProduits += Double.valueOf(selectedSejour.getClient().getListQuantityProduct(i))
                        * produit.getPrix();
                i++;
            }

            double coutTotal = coutChambre + coutOptions + coutProduits;
            return coutTotal;
        }
        return -1;
    }

    private String formatDecimalPrix(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }
}
