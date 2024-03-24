package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import model.Sejour;

public class ReservationController {

    private model.Hotel hotel;
    private view.ReservationView view;
    private Vector<model.Commodite> listCommodites;
    private model.Reservation reservation;

    public ReservationController(model.Hotel hotel, view.ReservationView view) {
        this.hotel = hotel;
        this.view = view;
        this.view.addReservationListener(new addReservationListener());
        view.updateView(hotel.getOptions());
    }

    class addReservationListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            // verifie qu'aucun champ n'est vide
            if (!(view.getDateDebutField().getText().isEmpty()) &&
                    !(view.getDateFinField().getText().isEmpty()) &&
                    !(view.getClientEmailField().getText().isEmpty())) {

                // récuperation des informations contenus dans les champs
                String clientEmail = view.getClientEmailField().getText();

                // vérification de l'existance du client
                model.Client client = getClientByEmail(clientEmail);

                if (client != null) {

                    LocalDate dateDebut = null;
                    LocalDate dateFin = null;

                    // récuperation des informations contenus dans les champs
                    String sDateDebut = view.getDateDebutField().getText();
                    String sDateFin = view.getDateFinField().getText();

                    try {
                        // traitement des informations
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        dateDebut = LocalDate.parse(sDateDebut, formatter);
                        dateFin = LocalDate.parse(sDateFin, formatter);

                    } catch (DateTimeParseException exception) {
                        view.getReponseLabel().setText("date non reconnu");
                    }

                    if (dateDebut != null && dateFin != null) {
                        String selectedType = (String) view.getTypeComboBox().getSelectedItem();
                        // vérification de la disponibilité des commodités
                        listCommodites = hotel.getCommodite();
                        for (model.Commodite commodite : listCommodites) {

                            if (estDisponible(dateDebut, dateFin, commodite.getListReservation())
                                    && (selectedType == commodite.getType().getNom())) {

                                // réinitialisation des champs
                                view.getDateDebutField().setText("");
                                view.getDateFinField().setText("");
                                view.getClientEmailField().setText("");

                                // mise a jour du model
                                reservation = new model.Reservation(dateDebut, dateFin);
                                reservation.setClient(client);
                                reservation.setCommodite(commodite);
                                addOptionsToReservation(reservation);
                                client.addReservation(reservation);
                                commodite.addReservation(reservation);

                                // mise a jour de la view
                                view.updateView();
                                view.getReponseLabel().setText("réservation validé");
                                break;

                            } else {

                                view.getReponseLabel().setText("aucune chambre dispo à cette période");
                            }
                        }
                    }
                } else {

                    view.getReponseLabel().setText("ce client n'existe pas");
                }
            } else {
                view.getReponseLabel().setText("champs non remplis");
            }
        }

        public model.Client getClientByEmail(String email) {
            Vector<model.Client> clients = hotel.getClients();
            for (model.Client client : clients) {

                if (client.getEmail().equals(email)) {
                    return client;
                }
            }
            return null;
        }

        public boolean estDisponible(LocalDate dateDebut, LocalDate dateFin,
                Vector<model.Reservation> VectorReservation) {
            for (model.Reservation reservation : VectorReservation) {
                if (seChevauchent(dateDebut, dateFin, reservation.getDateDebut(), reservation.getDateFin())) {
                    return false;
                }
            }
            return true;
        }

        public boolean seChevauchent(LocalDate dateDebut, LocalDate dateFin, LocalDate autreDateDebut,
                LocalDate autreDateFin) {
            return !dateDebut.isAfter(autreDateFin) && !dateFin.isBefore(autreDateDebut);
        }

        public void addOptionsToReservation(model.Reservation reservation) {
            int i = 0;
            for (JCheckBox checkbox : view.getListCheckBoxs()) {
                if (checkbox.isSelected()) {
                    reservation.addOption(hotel.getOptions().get(i));
                    checkbox.setSelected(false);
                }
                i++;
            }
        }
    }

    public void annulerReservationListener(model.Reservation reservation) {
        reservation.getCommodite().getListReservation().remove(reservation);
        view.updateView();
    }

    public void validerReservationListener(model.Reservation reservation) {
        model.Commodite commodite = reservation.getCommodite();
        if (commodite.getSejour() != null) {
            JOptionPane.showMessageDialog(null, "Il y a deja un séjour en cours pour cette chambre !");
        } else {
            commodite.getListReservation().remove(reservation);
            model.Sejour sejour = new Sejour(reservation);
            sejour.listOption = reservation.listOption;
            commodite.setSejour(sejour);
        }
        view.updateView();
    }
}
