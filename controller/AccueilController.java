package controller;

import java.text.DecimalFormat;
import java.util.Vector;

public class AccueilController {

    private Vector<model.Client> clients;

    public AccueilController(model.Hotel hotel, view.AccueilView view) {
        clients = hotel.getClients();

        String nomHotel = hotel.getNom();
        String adresseHotel = hotel.getAdresse();

        view.getNomHotel().setText(nomHotel);
        view.getAdresseHotel().setText("" + adresseHotel);
        view.getNoteMoyenne().setText("Note : " + String.valueOf(formatDecimal(CalculNoteMoyenne(clients)) + "/5"));
    }

    private double CalculNoteMoyenne(Vector<model.Client> clients) {

        double sommeNote = 0;
        int nbNote = 0;

        for (model.Client client : clients) {

            if (client.getNote() != 0) {

                sommeNote += client.getNote();
                nbNote++;
            }
        }

        if (nbNote > 0) {
            return sommeNote / nbNote;
        } else {
            return 0;
        }

    }

    private String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return decimalFormat.format(value);
    }
}