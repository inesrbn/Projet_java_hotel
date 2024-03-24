package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ReservationView extends JPanel {

  // utilitaire controller
  private controller.ReservationController rc;
  public model.Hotel hotel;
  private Vector<model.Commodite> listCommodites;
  private Vector<model.Option> listOption;
  private Vector<JCheckBox> listCheckBoxs = new Vector<JCheckBox>();

  // panneaux
  private JPanel reservationPanel;
  private JPanel reservationPanel1;
  private JPanel optionReservationPanel;
  private JPanel gestionReservationPanel;

  // elements dans la fonction addReservation()
  private JTextField dateDebutField;
  private JTextField dateFinField;
  private JTextField clientEmailField;
  private JComboBox<String> typeComboBox;
  private JButton addReservationButton;
  private JLabel reponseLabel;

  // autre elements
  private JTextField supprReservationField;
  private JLabel reponseValidationLabel;

  private JScrollPane listReservationScrollPane;
  private JScrollPane scrollPaneOptions;
  private GridBagConstraints c;

  private Font changeFont;

  public ReservationView(model.Hotel hotel, Vector<model.Commodite> listCommodites) {

    this.hotel = hotel;
    this.listCommodites = listCommodites;

    setLayout(new GridBagLayout());

    sousPanel();

    rc = new controller.ReservationController(hotel, this);
  }

  private void sousPanel() {

    c = new GridBagConstraints();

    // création du panel de réservation comprenant un champ de réponse
    gestionReservationPanel = new JPanel();
    gestionReservationPanel.setBackground(Color.WHITE);
    c.gridx = 2;
    c.gridy = 0;
    c.gridheight = 2;
    c.weightx = 0.70;
    c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    add(gestionReservationPanel, c);

    reservationPanel = new JPanel();
    reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

    // panel contenant l'ajout des réservations
    reservationPanel1 = new JPanel();
    reservationPanel1.setBackground(Color.lightGray);
    reservationPanel1.setPreferredSize(new Dimension(0, 10));
    reservationPanel.add(reservationPanel1);

    // panel pour la liste des options
    optionReservationPanel = new JPanel();
    optionReservationPanel.setBackground(Color.lightGray);
    reservationPanel.add(optionReservationPanel);
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 2;
    c.weightx = 0.30;
    c.weighty = 0.9;
    c.fill = GridBagConstraints.BOTH;

    add(reservationPanel, c);

    addReservation();
    tabListOption();
    tabListReservation();

  }

  private void addReservation() {
    JPanel champsPanel = new JPanel();
    champsPanel.setLayout(new GridLayout(0, 1));

    // Date de fin de la réservation souhaitée
    JPanel clientEmailPanel = new JPanel();
    JLabel clientEmailLabel = new JLabel("Email du client : ");
    clientEmailField = new JTextField(20);
    clientEmailPanel.add(clientEmailLabel);
    clientEmailPanel.add(clientEmailField);
    champsPanel.add(clientEmailPanel);

    // Date de début de la réservation souhaitée
    JPanel datedebutPanel = new JPanel();
    JLabel datedebutLabel = new JLabel("Date de début (dd/mm/yyyy) : ");
    dateDebutField = new JTextField(10);
    datedebutPanel.add(datedebutLabel);
    datedebutPanel.add(dateDebutField);
    champsPanel.add(datedebutPanel);

    // Date de fin de la réservation souhaitée
    JPanel datefinPanel = new JPanel();
    JLabel dateFinLabel = new JLabel("Date de Fin (dd/mm/yyyy) : ");
    dateFinField = new JTextField(10);
    datefinPanel.add(dateFinLabel);
    datefinPanel.add(dateFinField);
    champsPanel.add(datefinPanel);

    // Ajout d'un JComboBox
    JLabel typeCommodite = new JLabel("Type de commodite :");
    String[] typeChambre = { "Chambre simple", "Chambre double", "Suite classique", "Suite présidentielle" };
    typeComboBox = new JComboBox<>(typeChambre);
    champsPanel.add(typeCommodite);
    champsPanel.add(typeComboBox);

    champsPanel.add(Box.createVerticalStrut(5)); // creation d'un espace

    // panel de validation
    // button de validation
    JPanel validationPanel = new JPanel(new FlowLayout());
    addReservationButton = new JButton("Valider");
    // champ de reponse
    reponseLabel = new JLabel("");
    reponseLabel.setPreferredSize(new Dimension(220, 20));
    // ajout des elements au panel de validation
    validationPanel.add(addReservationButton);
    validationPanel.add(reponseLabel);
    champsPanel.add(validationPanel);

    reservationPanel1.add(champsPanel);
  }

  private void tabListOption() {
    // création du tableau des options
    int nbLine;
    if (listOption == null) {
      nbLine = 0;
    } else {
      nbLine = listOption.size();
    }
    JPanel tabOption = new JPanel(new GridLayout(0, 3));

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

    JLabel selection = new JLabel("Selection");
    selection.setBackground(Color.gray);
    selection.setOpaque(true);
    selection.setHorizontalAlignment(JLabel.CENTER);
    selection.setFont(changeFont);

    tabOption.add(colNom);
    tabOption.add(colPrix);
    tabOption.add(selection);

    // corps du tableau
    for (int i = 0; i < nbLine; i++) {

      JLabel nomOption = new JLabel(listOption.get(i).getNom());
      nomOption.setHorizontalAlignment(JLabel.CENTER);

      JLabel prixOption = new JLabel(String.valueOf(listOption.get(i).getPrix()));
      prixOption.setHorizontalAlignment(JLabel.CENTER);

      JCheckBox selecteur = new JCheckBox("");
      selecteur.setHorizontalAlignment(JLabel.CENTER);
      listCheckBoxs.add(selecteur);

      if (i % 2 == 1) {
        nomOption.setBackground(Color.lightGray);
        nomOption.setOpaque(true);
        prixOption.setBackground(Color.lightGray);
        prixOption.setOpaque(true);
        selecteur.setBackground(Color.lightGray);
        selecteur.setOpaque(true);
      }

      tabOption.add(nomOption);
      tabOption.add(prixOption);
      tabOption.add(selecteur);
    }

    scrollPaneOptions = new JScrollPane(tabOption);
    scrollPaneOptions.setMaximumSize(scrollPaneOptions.getPreferredSize());
    // scrollPaneOptions.setPreferredSize(new Dimension(500, 700));
    optionReservationPanel.add(scrollPaneOptions);
  }

  private void tabListReservation() {
    // création du tableau des réservations
    Vector<model.Reservation> listAllReservations = getAllReservations();
    int nbLine;
    if (listAllReservations == null) {
      nbLine = 0;
    } else {
      nbLine = listAllReservations.size();
    }
    JPanel tabReservation = new JPanel(new GridLayout(0, 6));

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

    JLabel annuler = new JLabel("Annuler");
    annuler.setBackground(Color.gray);
    annuler.setOpaque(true);
    annuler.setHorizontalAlignment(JLabel.CENTER);
    annuler.setFont(changeFont);

    JLabel valider = new JLabel("Valider");
    valider.setBackground(Color.gray);
    valider.setOpaque(true);
    valider.setHorizontalAlignment(JLabel.CENTER);
    valider.setFont(changeFont);

    tabReservation.add(colClient);
    tabReservation.add(colChambre);
    tabReservation.add(colDateDeb);
    tabReservation.add(colDateFin);
    tabReservation.add(annuler);
    tabReservation.add(valider);

    // corps du tableau
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    for (int i = 0; i < nbLine; i++) {

      JLabel nomClient = new JLabel(listAllReservations.get(i).getClient().getNom());
      nomClient.setHorizontalAlignment(JLabel.CENTER);

      JLabel numeroChambre = new JLabel(String.valueOf(listAllReservations.get(i).getCommodite().getNumero()));
      numeroChambre.setHorizontalAlignment(JLabel.CENTER);

      JLabel dateDebut = new JLabel(listAllReservations.get(i).getDateDebut().format(format).toString());
      dateDebut.setHorizontalAlignment(JLabel.CENTER);

      JLabel dateFin = new JLabel(listAllReservations.get(i).getDateFin().format(format).toString());
      dateFin.setHorizontalAlignment(JLabel.CENTER);

      JButton annulerButton = new JButton("Annuler");
      annulerButton.setHorizontalAlignment(JLabel.CENTER);
      addAnnulerButtonActionListener(annulerButton, i, listAllReservations);

      JButton validerButton = new JButton("Valider");
      validerButton.setHorizontalAlignment(JLabel.CENTER);
      addValiderButtonActionListener(validerButton, i, listAllReservations);

      if (i % 2 == 1) {
        nomClient.setBackground(Color.lightGray);
        nomClient.setOpaque(true);
        numeroChambre.setBackground(Color.lightGray);
        numeroChambre.setOpaque(true);
        dateDebut.setBackground(Color.lightGray);
        dateDebut.setOpaque(true);
        dateFin.setBackground(Color.lightGray);
        dateFin.setOpaque(true);
        annulerButton.setBackground(Color.lightGray);
        annulerButton.setOpaque(true);
        validerButton.setBackground(Color.lightGray);
        validerButton.setOpaque(true);
      } else {
        annulerButton.setBackground(Color.white);
        annulerButton.setOpaque(true);
        validerButton.setBackground(Color.white);
        validerButton.setOpaque(true);
      }

      tabReservation.add(nomClient);
      tabReservation.add(numeroChambre);
      tabReservation.add(dateDebut);
      tabReservation.add(dateFin);
      tabReservation.add(annulerButton);
      tabReservation.add(validerButton);
    }

    listReservationScrollPane = new JScrollPane(tabReservation);
    listReservationScrollPane.setMaximumSize(listReservationScrollPane.getPreferredSize());
    // listReservationScrollPane.setPreferredSize(new Dimension(500, 900));
    gestionReservationPanel.add(listReservationScrollPane);
  }

  // listener
  private Vector<model.Reservation> getAllReservations() {
    Vector<model.Reservation> listAllReservations = new Vector<model.Reservation>();

    for (model.Commodite commodite : listCommodites) {

      for (model.Reservation reservation : commodite.getListReservation()) {

        listAllReservations.add(reservation);
      }
    }
    return listAllReservations;
  }

  public void addReservationListener(ActionListener listener) {
    addReservationButton.addActionListener(listener);
  }

  private void addAnnulerButtonActionListener(JButton annulerButton, int index,
      Vector<model.Reservation> listAllReservations) {

    annulerButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        model.Reservation reservation = listAllReservations.get(index);
        rc.annulerReservationListener(reservation);
      }
    });
  }

  private void addValiderButtonActionListener(JButton validerButton, int index,
      Vector<model.Reservation> listAllReservations) {

    validerButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        model.Reservation reservation = listAllReservations.get(index);
        rc.validerReservationListener(reservation);
      }
    });
  }

  public JLabel getReponseValidationLabel() {
    return reponseValidationLabel;
  }

  public JTextField getDateDebutField() {
    return dateDebutField;

  }

  public JTextField getDateFinField() {
    return dateFinField;
  }

  public JTextField getClientEmailField() {
    return clientEmailField;
  }

  public JLabel getReponseLabel() {
    return reponseLabel;
  }

  public JTextField getSupprReservationField() {
    return supprReservationField;
  }

  public Vector<JCheckBox> getListCheckBoxs() {
    return listCheckBoxs;
  }

  public JComboBox<String> getTypeComboBox() {
    return typeComboBox;
  }

  // update
  public void updateView(Vector<model.Option> listOptions) {
    this.listOption = listOptions;
    optionReservationPanel.remove(scrollPaneOptions);
    tabListOption();
    optionReservationPanel.revalidate();
    optionReservationPanel.repaint();
  }

  public void updateView() {
    gestionReservationPanel.remove(listReservationScrollPane);
    tabListReservation();
    gestionReservationPanel.revalidate();
    gestionReservationPanel.repaint();
  }
}