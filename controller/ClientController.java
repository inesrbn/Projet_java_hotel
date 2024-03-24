package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClientController {

  private model.Hotel hotel;
  private model.Client client;
  private view.ClientsView view;
  private Vector<model.Client> clients;

  public ClientController(model.Hotel hotel, view.ClientsView view) {
    this.hotel = hotel;
    this.view = view;
    this.view.addClientListener(new addClientListener());
    this.view.SupprClientListener(new supprClientListener());
    this.view.addNoteListener(new addNoteListener());
  }

  class addClientListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      // vérifie qu'aucun champs n'est vide
      if (!(view.getNomField().getText().isEmpty()) &&
          !(view.getPrenomField().getText().isEmpty()) &&
          !(view.getEmailField().getText().isEmpty())) {

        // récupération des information contenus dans les champs
        String nom = view.getNomField().getText();
        String prenom = view.getPrenomField().getText();
        String email = view.getEmailField().getText();

        if (getClientByEmail(email) == null) {
          // réinisitalisation des champs
          view.getNomField().setText("");
          view.getPrenomField().setText("");
          view.getEmailField().setText("");
          view.getrepEmailLabel().setText("");

          // mise a jour du model
          client = new model.Client(nom, prenom, email, hotel.getProduits().size());
          hotel.addClient(client);

          // mise a jour de la view
          clients = hotel.getClients();
          view.updateClientList(clients);

        } else {
          view.getrepEmailLabel().setText("email non valide");
        }

      } else {
        view.getrepEmailLabel().setText("champs non remplis");
      }
    }
  }

  class addNoteListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

      if (!view.getEmailNoteField().getText().isEmpty()) {

        int note = Integer.parseInt(view.getNoteBox().getSelectedItem().toString());
        String email = view.getEmailNoteField().getText();

        client = getClientByEmail(email);
        if (client != null) {
          client.setNote(note);
          view.updateClientList(clients);
          view.getEmailNoteField().setText("");
          view.getrepEmailLabel1().setText("");
        } else {
          view.getrepEmailLabel1().setText("email non valide");
        }
      } else {
        view.getrepEmailLabel1().setText("champs non remplis");
      }
    }
  }

  class supprClientListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

      if (!view.getSupprEmailField().getText().isEmpty()) {

        String email = view.getSupprEmailField().getText();

        client = getClientByEmail(email);
        if (client != null) {
          hotel.supprClient(client);
          clients = hotel.getClients();
          view.updateClientList(clients);
          view.getSupprEmailField().setText("");
          view.getrepEmailLabel2().setText("");
        } else {
          view.getrepEmailLabel2().setText("email non valide");
        }
      } else {
        view.getrepEmailLabel2().setText("champs non remplis");
      }
    }
  }

  public model.Client getClientByEmail(String email) {

    if (!(email.isEmpty())) {

      clients = hotel.getClients();
      for (model.Client client : clients) {

        if (client.getEmail().equals(email)) {
          return client;
        }
      }
    } else {
      return null;
    }
    return null;
  }
}
