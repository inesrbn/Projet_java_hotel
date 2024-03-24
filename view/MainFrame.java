package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

    // utilitaire controller
    private Vector<model.Client> clients;
    private Vector<model.Commodite> commodites;

    // éléments contenus dans MainFrame
    private JFrame frame;
    public JPanel mainPanel;
    private CardLayout cardLayout;
    private model.Hotel hotel;
    private Color couleurFond;

    public MainFrame() {

        // lancement de la "bdd"
        model.bdd bdd = new model.bdd();
        hotel = bdd.hotel;
        clients = hotel.getClients();
        commodites = hotel.getCommodite();

        frame = new JFrame("Ma fenêtre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du panel principal avec le CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Création des panneaux pour chaque page
        view.AccueilView accueilView = new AccueilView(hotel);
        JPanel accueilPanel = accueilView;
        JPanel reservationPanel = new ReservationView(hotel, commodites);
        view.SejourView sejourView = new SejourView(hotel);
        JPanel sejourPanel = sejourView;
        JPanel clientsPanel = new ClientsView(hotel, clients);

        // Ajout des panneaux au conteneur principal
        mainPanel.add(accueilPanel, "Accueil");
        mainPanel.add(reservationPanel, "Reservation");
        mainPanel.add(sejourPanel, "Sejour");
        mainPanel.add(clientsPanel, "Clients");

        // Création des boutons de navigation
        JButton buttonAccueil = new JButton("Accueil");
        JButton buttonReservation = new JButton("Réservation");
        JButton buttonSejour = new JButton("Séjour");
        JButton buttonClients = new JButton("Clients");

        // Ajout des listeners pour les boutons
        buttonAccueil.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(mainPanel, "Accueil");
                        new controller.AccueilController(hotel, accueilView);
                    }
                });

        buttonReservation.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(mainPanel, "Reservation");
                    }
                });

        buttonSejour.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(mainPanel, "Sejour");
                        new controller.SejourController(hotel, sejourView);
                    }
                });

        buttonClients.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(mainPanel, "Clients");
                    }
                });

        // Ajout des boutons à la fenêtre
        JPanel buttonPanel = new JPanel();
        couleurFond = new Color(237, 237, 237);

        buttonPanel.add(buttonAccueil);
        buttonPanel.add(buttonReservation);
        buttonPanel.add(buttonSejour);
        buttonPanel.add(buttonClients);
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        buttonPanel.setBackground(couleurFond);

        // Ajout du panel principal à la fenêtre
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Affichage de la fenêtre
        frame.pack();

        // setLocationRelativeTo(null);

        // récuperer la dimension de l'écran
        // Dimension monitorDims = Toolkit.getDefaultToolkit().getScreenSize();
        // frame.setSize(monitorDims.width, monitorDims.height);
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
