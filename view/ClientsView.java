package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ClientsView extends JPanel {

    // utilitaire controller
    public model.Hotel hotel;
    private Vector<model.Client> clientList;

    // panneaux
    private JPanel leftPanel;
    private JPanel rightPanel;

    // éléments contenus dans le leftPanel
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JTextField supprEmailField; // email a suppr
    private JTextField emailNoteField;
    private JButton addClientButton;
    private JButton supprClientButton;
    private JButton ajouterNoteButton;
    private JComboBox<String> noteBox;
    private Color couleurF;

    // élements contenus dans le rightPanel
    private JPanel tabClient;
    private JScrollPane tabClientScrollPane;
    private JLabel repEmailLabel;
    private JLabel repEmailLabel1;
    private JLabel repEmailLabel2;

    public ClientsView(model.Hotel hotel, Vector<model.Client> clientList) {
        this.hotel = hotel;
        this.clientList = clientList;

        // Crée un conteneur racine avec un GridBagLayout
        setLayout(new GridBagLayout());

        // Crée les 2 sous conteneur (leftPanel, rightPanel)
        sousPanel();

        // Crée le controlleur pour ajouter un client
        new controller.ClientController(hotel, this);
    }

    private void sousPanel() {
        // Création de la couleur de fond
        couleurF = new Color(237, 237, 237);
        // Crée un panneau pour le bouton
        leftPanel = new JPanel();
        leftPanel.setBackground(couleurF);

        // Ajoute le panneau du bouton à la grille
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.15;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        addClientPanel();
        add(leftPanel, c);

        // Ajoute un panneau en haut du panneau de droite
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.white);

        // Ajoute le panneau de droite à la grille
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.85;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        tabClientPanel();
        add(rightPanel, c);
    }

    private void addClientPanel() {
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 0)); // espace par rapport T L B R

        JLabel nomLabel = new JLabel("Nom : ");
        nomLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nomField = new JTextField(50);
        nomField.setMaximumSize(nomField.getPreferredSize());
        leftPanel.add(nomLabel);
        leftPanel.add(nomField);

        // a changer pour obtenir le visuel souhaité
        leftPanel.add(Box.createVerticalStrut(50)); // créer un espace vertical

        JLabel prenomLabel = new JLabel("Prenom : ");
        prenomField = new JTextField(50);
        prenomField.setMaximumSize(prenomField.getPreferredSize());
        leftPanel.add(prenomLabel);
        leftPanel.add(prenomField);

        // a changer pour obtenir le visuel souhaité
        leftPanel.add(Box.createVerticalStrut(50));

        JLabel emailLabel = new JLabel("email : ");
        emailField = new JTextField(50);
        emailField.setMaximumSize(emailField.getPreferredSize());
        leftPanel.add(emailLabel);
        leftPanel.add(emailField);

        // a changer pour obtenir le visuel souhaité
        leftPanel.add(Box.createVerticalStrut(50));

        addClientButton = new JButton("  Ajouter client  ");
        repEmailLabel = new JLabel("");
        leftPanel.add(addClientButton);
        leftPanel.add(repEmailLabel);

        leftPanel.add(Box.createVerticalStrut(150));

        JLabel noteLabel = new JLabel("note : ");
        String[] noteHotel = { "1", "2", "3", "4", "5" };
        noteBox = new JComboBox<>(noteHotel);
        noteBox.setMaximumSize(new Dimension(200, 20));
        leftPanel.add(noteLabel);
        leftPanel.add(noteBox);

        JLabel emailNoteLabel = new JLabel("email : ");
        emailNoteField = new JTextField(50);
        emailNoteField.setMaximumSize(emailNoteField.getPreferredSize());
        leftPanel.add(emailNoteLabel);
        leftPanel.add(emailNoteField);

        leftPanel.add(Box.createVerticalStrut(50));

        ajouterNoteButton = new JButton("Ajouter Note");
        leftPanel.add(ajouterNoteButton);
        repEmailLabel1 = new JLabel("");
        leftPanel.add(repEmailLabel1);

        leftPanel.add(Box.createVerticalStrut(150));

        JLabel supprEmailLabel = new JLabel("email : ");
        supprEmailField = new JTextField(50);
        supprEmailField.setMaximumSize(supprEmailField.getPreferredSize());
        leftPanel.add(supprEmailLabel);
        leftPanel.add(supprEmailField);

        leftPanel.add(Box.createVerticalStrut(50));

        supprClientButton = new JButton("Supprimer client");
        repEmailLabel2 = new JLabel("");
        leftPanel.add(supprClientButton);
        leftPanel.add(repEmailLabel2);
    }

    private void tabClientPanel() {
        // crée le tableau qui contient les clients
        int nbLine;
        if (clientList == null) {
            nbLine = 0;
        } else {
            nbLine = clientList.size();
        }
        tabClient = new JPanel(new GridLayout(0, 4/* , 20, 200 */));

        // entete du tableau
        JLabel colNom = new JLabel("NOM");
        colNom.setBackground(Color.gray);
        colNom.setOpaque(true);
        colNom.setPreferredSize(new Dimension(300, 20));

        JLabel colPrenom = new JLabel("PRENOM");
        colPrenom.setBackground(Color.gray);
        colPrenom.setOpaque(true);

        JLabel colEmail = new JLabel("EMAIL");
        colEmail.setBackground(Color.gray);
        colEmail.setOpaque(true);

        JLabel colNote = new JLabel("NOTE");
        colNote.setBackground(Color.gray);
        colNote.setOpaque(true);

        tabClient.add(colNom);
        tabClient.add(colPrenom);
        tabClient.add(colEmail);
        tabClient.add(colNote);

        // corps du tableau
        for (int i = 0; i < nbLine; i++) {
            JLabel clientNom = new JLabel(clientList.get(i).getNom());
            JLabel clientPrenom = new JLabel(clientList.get(i).getPrenom());
            JLabel clientEmail = new JLabel(clientList.get(i).getEmail());
            JLabel clientNote = new JLabel();
            int note = clientList.get(i).getNote();
            if (note == 0) {
                clientNote.setText("null");
            } else {
                clientNote.setText(String.valueOf(note));
            }

            if (i % 2 == 1) {
                clientNom.setBackground(Color.lightGray);
                clientNom.setOpaque(true);
                clientPrenom.setBackground(Color.lightGray);
                clientPrenom.setOpaque(true);
                clientEmail.setBackground(Color.lightGray);
                clientEmail.setOpaque(true);
                clientNote.setBackground(Color.lightGray);
                clientNote.setOpaque(true);
            }

            tabClient.add(clientNom);
            tabClient.add(clientPrenom);
            tabClient.add(clientEmail);
            tabClient.add(clientNote);
        }

        // ajout du tableau dans les containers
        tabClientScrollPane = new JScrollPane(tabClient);
        // tabClientScrollPane.setPreferredSize(new Dimension(1000, 500));
        // tabClientScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(tabClientScrollPane);
    }

    // listener
    public void addClientListener(ActionListener listeneur) {
        addClientButton.addActionListener(listeneur);
    }

    public void SupprClientListener(ActionListener listeneur) {
        supprClientButton.addActionListener(listeneur);
    }

    public void addNoteListener(ActionListener listeneur) {
        ajouterNoteButton.addActionListener(listeneur);
    }

    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrenomField() {
        return prenomField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getSupprEmailField() {
        return supprEmailField;
    }

    public JTextField getEmailNoteField() {
        return emailNoteField;
    }

    public JComboBox<String> getNoteBox() {
        return noteBox;
    }

    public JLabel getrepEmailLabel() {
        return repEmailLabel;
    }

    public JLabel getrepEmailLabel1() {
        return repEmailLabel1;
    }

    public JLabel getrepEmailLabel2() {
        return repEmailLabel2;
    }

    // update
    public void updateClientList(Vector<model.Client> clients) {
        clientList = clients;
        rightPanel.remove(tabClientScrollPane);
        tabClientPanel();
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}
