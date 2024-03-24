package view;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class SejourView extends JPanel {

    // utilitaire controller
    private controller.SejourController rc;
    public model.Hotel hotel;
    private Vector<model.Sejour> listSejour;
    private Vector<model.Produit> listProduit;
    private Vector<model.Option> listOption;

    // panel left
    private JPanel leftPanel;
    private JScrollPane listSejourScrollPane;
    private JButton selectionnerButton;

    // panel milieu page
    private JPanel middlePanel;
    private JPanel tabOptionPanel;
    private JLabel dateDebutSejourLabel;
    private JLabel dateFinSejourLabel;
    private JLabel infoClientLabel;
    private JLabel infoClientLabel1;
    private JLabel infoClientLabel2;
    private JLabel coutTotalLabel;
    private JButton finSejourButton;
    private JScrollPane scrollPaneOption;

    // panel right
    private JPanel rightPanel;
    private JPanel tableauProduitPanel;
    private JScrollPane scrollPaneProduit;
    private JComboBox<String> produitBox;
    private Vector<JSpinner> quantitySpinners = new Vector<JSpinner>();
    private Color couleurF;

    private Font changeFont;

    public SejourView(model.Hotel hotel) {
        this.hotel = hotel;

        // Création de la couleur de fond
        couleurF = new Color(237, 237, 237);

        // Création du layout et de ses contraintes
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // panel gauche
        leftPanel = new JPanel();
        leftPanel.setBackground(couleurF);
        tabListSejour();
        // Ajout du Panel au container avec les contraintes
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        add(leftPanel, c);

        // panel milieu
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setPreferredSize(new Dimension(300, 0));
        middlePanel.setBackground(couleurF);
        milieuPage();
        // Ajout du Panel au container avec les contraintes
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        add(middlePanel, c);
        middlePanel.setVisible(false);

        // panel droite
        rightPanel = new JPanel();
        rightPanel.setBackground(couleurF);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        // Création du Panel contenant le tableau
        tableauProduitPanel = new JPanel();
        rightPanel.add(tableauProduitPanel);
        tabListProduit();
        // Ajout du Panel au container avec les contraintes
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        add(rightPanel, c);
        rightPanel.setVisible(false);

        // Création du controller
        rc = new controller.SejourController(hotel, this);
    }

    private void milieuPage() {
        // Ajout espace
        middlePanel.add(Box.createVerticalStrut(25));

        // Les dates du séjour
        // Création Panel date de début
        JPanel dateDebutPanel = new JPanel();
        dateDebutPanel.setLayout(new BoxLayout(dateDebutPanel, BoxLayout.X_AXIS));
        // Création des labels
        JLabel debutSejourLabel = new JLabel("Date de début : ");
        dateDebutSejourLabel = new JLabel("");
        dateDebutPanel.add(debutSejourLabel);
        dateDebutPanel.add(dateDebutSejourLabel);
        // Ajout du Panel au container
        middlePanel.add(dateDebutPanel);

        // Ajout espace
        middlePanel.add(Box.createVerticalStrut(25));

        // Création Panel date de fin
        JPanel dateFinPanel = new JPanel();
        dateFinPanel.setLayout(new BoxLayout(dateFinPanel, BoxLayout.X_AXIS));
        // Création des labels
        JLabel finSejourLabel = new JLabel("Date de Fin : ");
        dateFinSejourLabel = new JLabel("");
        dateFinPanel.add(finSejourLabel);
        dateFinPanel.add(dateFinSejourLabel);
        // Ajout du Panel au container
        middlePanel.add(dateFinPanel);

        // Ajout espace
        middlePanel.add(Box.createVerticalStrut(25));

        // Les informations sur le client
        // Création Panel Nom
        JPanel nomClientPanel = new JPanel();
        nomClientPanel.setLayout(new BoxLayout(nomClientPanel, BoxLayout.X_AXIS));
        // Création des labels
        JLabel nomClientLabel = new JLabel("Nom : ");
        infoClientLabel = new JLabel("");
        nomClientPanel.add(nomClientLabel);
        // Ajout du Panel au container
        nomClientPanel.add(infoClientLabel);
        middlePanel.add(nomClientPanel);

        // Ajout espace
        middlePanel.add(Box.createVerticalStrut(25));

        // Création Panel Prénom
        JPanel prenomClientPanel = new JPanel();
        prenomClientPanel.setLayout(new BoxLayout(prenomClientPanel, BoxLayout.X_AXIS));
        // Création des labels
        JLabel prenomClientLabel = new JLabel("Prénom : ");
        infoClientLabel1 = new JLabel("");
        prenomClientPanel.add(prenomClientLabel);
        prenomClientPanel.add(infoClientLabel1);
        // Ajout du Panel au container
        middlePanel.add(prenomClientPanel);

        // Ajout espace
        middlePanel.add(Box.createVerticalStrut(25));

        // Création Panel Email
        JPanel emailClientPanel = new JPanel();
        emailClientPanel.setLayout(new BoxLayout(emailClientPanel, BoxLayout.X_AXIS));
        // Création des labels
        JLabel emailClientLabel = new JLabel("Email : ");
        infoClientLabel2 = new JLabel("");
        emailClientPanel.add(emailClientLabel);
        emailClientPanel.add(infoClientLabel2);
        // Ajout du Panel au container
        middlePanel.add(emailClientPanel);

        // Ajout espace
        middlePanel.add(Box.createVerticalStrut(100));

        tabOptionPanel = new JPanel();
        tabOptionPanel.setAlignmentX(CENTER_ALIGNMENT);
        // Scroll pane contenant options
        tabListOption();
        // ajout du Panel au container
        middlePanel.add(tabOptionPanel);

        // Affichage du coût total des produits commandés
        // Création Panel coup total
        JPanel coutTotalPanel = new JPanel();
        coutTotalPanel.setLayout(new BoxLayout(coutTotalPanel, BoxLayout.Y_AXIS));
        // Création du label cout
        coutTotalLabel = new JLabel("Coût : ");
        coutTotalPanel.add(coutTotalLabel);
        coutTotalLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // Ajout d'un espace
        coutTotalPanel.add(Box.createVerticalStrut(50));
        // Création du bouton de cloture du séjour
        finSejourButton = new JButton("Clore le séjour");
        finSejourButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        coutTotalPanel.add(finSejourButton);
        // Ajout d'un espace
        coutTotalPanel.add(Box.createVerticalStrut(150));
        // Ajout du Panel au container
        middlePanel.add(coutTotalPanel);
    }

    private void tabListSejour() {
        // création du tableau des séjours
        int nbLine;
        if (listSejour == null) {
            nbLine = 0;
        } else {
            nbLine = listSejour.size();
        }
        JPanel tabSejour = new JPanel(new GridLayout(0, 5));

        // entete du tableau
        changeFont = new Font("Arial Black", Font.BOLD, 15);

        JLabel colClient = new JLabel("Client");
        colClient.setBackground(Color.gray);
        colClient.setOpaque(true);
        colClient.setPreferredSize(new Dimension(150, 26));
        colClient.setHorizontalAlignment(JLabel.CENTER);
        colClient.setFont(changeFont);

        JLabel colChambre = new JLabel("Chambre");
        colChambre.setBackground(Color.gray);
        colChambre.setOpaque(true);
        colChambre.setHorizontalAlignment(JLabel.CENTER);
        colChambre.setFont(changeFont);

        JLabel colDateDeb = new JLabel("Date de début");
        colDateDeb.setBackground(Color.gray);
        colDateDeb.setOpaque(true);
        colDateDeb.setHorizontalAlignment(JLabel.CENTER);
        colDateDeb.setFont(changeFont);

        JLabel colDateFin = new JLabel("Date de fin");
        colDateFin.setBackground(Color.gray);
        colDateFin.setOpaque(true);
        colDateFin.setHorizontalAlignment(JLabel.CENTER);
        colDateFin.setFont(changeFont);

        JLabel selectionner = new JLabel("Sélectionner");
        selectionner.setBackground(Color.gray);
        selectionner.setOpaque(true);
        selectionner.setHorizontalAlignment(JLabel.CENTER);
        selectionner.setFont(changeFont);

        tabSejour.add(colClient);
        tabSejour.add(colChambre);
        tabSejour.add(colDateDeb);
        tabSejour.add(colDateFin);
        tabSejour.add(selectionner);

        // corps du tableau
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < nbLine; i++) {

            JLabel nomClient = new JLabel(listSejour.get(i).getClient().getNom());
            nomClient.setHorizontalAlignment(JLabel.CENTER);

            JLabel numeroChambre = new JLabel(String.valueOf(listSejour.get(i).getCommodite().getNumero()));
            numeroChambre.setHorizontalAlignment(JLabel.CENTER);

            JLabel dateDebut = new JLabel(listSejour.get(i).getDateDebut().format(format).toString());
            dateDebut.setHorizontalAlignment(JLabel.CENTER);

            JLabel dateFin = new JLabel(listSejour.get(i).getDateFin().format(format).toString());
            dateFin.setHorizontalAlignment(JLabel.CENTER);

            selectionnerButton = new JButton("Sélectionner");
            selectionnerButton.setHorizontalAlignment(JLabel.CENTER);
            addSelectionnerButtonActionListener(selectionnerButton, i, listSejour);

            if (i % 2 == 1) {
                nomClient.setBackground(Color.lightGray);
                nomClient.setOpaque(true);
                numeroChambre.setBackground(Color.lightGray);
                numeroChambre.setOpaque(true);
                dateDebut.setBackground(Color.lightGray);
                dateDebut.setOpaque(true);
                dateFin.setBackground(Color.lightGray);
                dateFin.setOpaque(true);
                selectionnerButton.setBackground(Color.lightGray);
                selectionnerButton.setOpaque(true);
            } else {
                selectionnerButton.setBackground(Color.white);
                selectionnerButton.setOpaque(true);
            }

            selectionner.setFont(changeFont);

            tabSejour.add(nomClient);
            tabSejour.add(numeroChambre);
            tabSejour.add(dateDebut);
            tabSejour.add(dateFin);
            tabSejour.add(selectionnerButton);
        }

        listSejourScrollPane = new JScrollPane(tabSejour);
        // listSejourScrollPane.setPreferredSize(new Dimension(500, 900));
        leftPanel.add(listSejourScrollPane, BorderLayout.CENTER);
    }

    private void tabListProduit() {
        // création du tableau des options
        int nbLine;
        if (listProduit == null) {
            nbLine = 0;
        } else {
            nbLine = listProduit.size();
        }
        JPanel tabProduit = new JPanel(new GridLayout(0, 3));

        // entete du tableau
        changeFont = new Font("Arial Black", Font.BOLD, 15);

        JLabel colNom = new JLabel("Produit");
        colNom.setBackground(Color.gray);
        colNom.setOpaque(true);
        colNom.setPreferredSize(new Dimension(150, 26));
        colNom.setHorizontalAlignment(JLabel.CENTER);
        colNom.setFont(changeFont);

        JLabel colPrix = new JLabel("Prix");
        colPrix.setBackground(Color.gray);
        colPrix.setOpaque(true);
        colPrix.setHorizontalAlignment(JLabel.CENTER);
        colPrix.setFont(changeFont);

        JLabel selection = new JLabel("Quantité");
        selection.setBackground(Color.gray);
        selection.setOpaque(true);
        selection.setHorizontalAlignment(JLabel.CENTER);
        selection.setFont(changeFont);

        tabProduit.add(colNom);
        tabProduit.add(colPrix);
        tabProduit.add(selection);

        // corps du tableau
        for (int i = 0; i < nbLine; i++) {

            JLabel nomProduit = new JLabel(listProduit.get(i).getNom());
            nomProduit.setHorizontalAlignment(JLabel.CENTER);

            JLabel prixProduit = new JLabel(String.valueOf(formatDecimalPrix(listProduit.get(i).getPrix())) + " €");
            prixProduit.setHorizontalAlignment(JLabel.CENTER);

            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 999999, 1);
            JSpinner quantitySpinner = new JSpinner(spinnerModel);
            quantitySpinner.addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent e) {
                    rc.storeClientQuantity((int) quantitySpinner.getValue(), quantitySpinner);
                }

            });
            quantitySpinners.add(quantitySpinner);
            // addAnnulerButtonActionListener(selecteur, i, listOption);

            if (i % 2 == 1) {
                nomProduit.setBackground(Color.lightGray);
                nomProduit.setOpaque(true);
                prixProduit.setBackground(Color.lightGray);
                prixProduit.setOpaque(true);
                quantitySpinner.setBackground(Color.lightGray);
                quantitySpinner.setOpaque(true);
            }

            tabProduit.add(nomProduit);
            tabProduit.add(prixProduit);
            tabProduit.add(quantitySpinner);
        }

        scrollPaneProduit = new JScrollPane(tabProduit);
        scrollPaneProduit.setMaximumSize(scrollPaneProduit.getPreferredSize());
        // scrollPaneProduit.setPreferredSize(new Dimension(500, 700));
        tableauProduitPanel.add(scrollPaneProduit);
    }

    private void tabListOption() {
        // création du tableau des options
        int nbLine;
        if (listOption == null) {
            nbLine = 0;
        } else {
            nbLine = listOption.size();
        }
        JPanel tabOption = new JPanel(new GridLayout(0, 2));

        // entete du tableau
        changeFont = new Font("Arial Black", Font.BOLD, 15);

        JLabel colNom = new JLabel("Option");
        colNom.setBackground(Color.gray);
        colNom.setOpaque(true);
        colNom.setPreferredSize(new Dimension(150, 26));
        colNom.setHorizontalAlignment(JLabel.CENTER);
        colNom.setFont(changeFont);

        JLabel colPrix = new JLabel("Prix");
        colPrix.setBackground(Color.gray);
        colPrix.setOpaque(true);
        colPrix.setHorizontalAlignment(JLabel.CENTER);
        colPrix.setFont(changeFont);

        tabOption.add(colNom);
        tabOption.add(colPrix);

        // corps du tableau
        for (int i = 0; i < nbLine; i++) {

            JLabel nomOptionLabel = new JLabel(listOption.get(i).getNom());
            nomOptionLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel prixOptionLabel = new JLabel(String.valueOf(formatDecimalPrix(listOption.get(i).getPrix())) + " €");
            prixOptionLabel.setHorizontalAlignment(JLabel.CENTER);
            // addAnnulerButtonActionListener(selecteur, i, listOption);

            if (i % 2 == 1) {
                nomOptionLabel.setBackground(Color.lightGray);
                nomOptionLabel.setOpaque(true);
                prixOptionLabel.setBackground(Color.lightGray);
                prixOptionLabel.setOpaque(true);
            }

            tabOption.add(nomOptionLabel);
            tabOption.add(prixOptionLabel);
        }

        scrollPaneOption = new JScrollPane(tabOption);
        scrollPaneOption.setMaximumSize(scrollPaneOption.getPreferredSize());
        // scrollPaneProduit.setPreferredSize(new Dimension(500, 700));
        tabOptionPanel.add(scrollPaneOption);
    }

    // listener
    private void addSelectionnerButtonActionListener(JButton selectionnerButton, int index,
            Vector<model.Sejour> listSejour) {

        selectionnerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.Sejour sejour = listSejour.get(index);
                rc.selectSejourListener(sejour);
            }
        });
    }

    public void finSejourListener(ActionListener listener) {
        finSejourButton.addActionListener(listener);
    }

    // format decimal
    private String formatDecimalPrix(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }

    // getteurs
    public JLabel getCoutTotalLabel() {
        return coutTotalLabel;
    }

    public JLabel getDebutSejourLabel() {
        return dateDebutSejourLabel;
    }

    public JLabel getFinSejourLabel() {
        return dateFinSejourLabel;
    }

    public JLabel getInfoClientLabel() {
        return infoClientLabel;

    }

    public JLabel getInfoClientLabel1() {
        return infoClientLabel1;

    }

    public JLabel getInfoClientLabel2() {
        return infoClientLabel2;

    }

    public JComboBox<String> getProduitBox() {
        return produitBox;
    }

    public Vector<JSpinner> getQuantitySpinners() {
        return quantitySpinners;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JPanel getMiddlePanel() {
        return middlePanel;
    }

    // updateurs de la view
    public void updateView() {
        tableauProduitPanel.remove(scrollPaneProduit);
        quantitySpinners.removeAllElements();
        tabListProduit();
        rc.setQuantitySpinnersValue(quantitySpinners);
        tableauProduitPanel.revalidate();
        tableauProduitPanel.repaint();
    }

    public void updateViewInit(Vector<model.Sejour> listSejour, Vector<model.Produit> listProduit) {
        this.listSejour = listSejour;
        this.listProduit = listProduit;

        tableauProduitPanel.remove(scrollPaneProduit);
        quantitySpinners.removeAllElements();
        tabListProduit();
        tableauProduitPanel.revalidate();
        tableauProduitPanel.repaint();

        leftPanel.remove(listSejourScrollPane);
        tabListSejour();
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public void updateView(Vector<model.Option> listOption) {
        this.listOption = listOption;

        tabOptionPanel.remove(scrollPaneOption);
        tabListOption();
        tabOptionPanel.revalidate();
        tabOptionPanel.repaint();
    }

    public void updateView(Vector<model.Sejour> listSejour, Vector<model.Produit> listProduit) {
        this.listSejour = listSejour;
        this.listProduit = listProduit;

        tableauProduitPanel.remove(scrollPaneProduit);
        quantitySpinners.removeAllElements();
        tabListProduit();
        rc.setQuantitySpinnersValue(quantitySpinners);
        tableauProduitPanel.revalidate();
        tableauProduitPanel.repaint();

        leftPanel.remove(listSejourScrollPane);
        tabListSejour();
        leftPanel.revalidate();
        leftPanel.repaint();
    }
}