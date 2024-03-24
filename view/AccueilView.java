package view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccueilView extends JPanel {

  // label contenu dans le panel infoPanel
  private JLabel nomHotel;
  private JLabel adresseHotel;
  private JLabel noteMoyenne;
  private JLabel backgroundLabel;

  // panneaux
  private JPanel infoPanel;
  private JPanel overlayPanel;

  // Style
  private Font font;
  private Color couleurText;

  public AccueilView(model.Hotel hotel) {

    setLayout(new BorderLayout());

    // Création du panneau pour l'image d'arrière-plan
    backgroundLabel = new JLabel(
        new ImageIcon("C:/Users/Inès/Downloads/Forest_Robin_Muhammad/Projet_JAVA_Hotel/resources/DOZOTEL.jpg"));

    // Création du panneau pour les informations
    infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

    nomHotel = new JLabel("");
    font = new Font("Arial", Font.BOLD, 200);
    couleurText = new Color(255, 230, 105);
    nomHotel.setFont(font);
    nomHotel.setForeground(couleurText);
    nomHotel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

    adresseHotel = new JLabel("");
    font = new Font("Arial", Font.BOLD, 50);
    adresseHotel.setFont(font);
    couleurText = new Color(0, 5, 57);
    adresseHotel.setForeground(couleurText);
    adresseHotel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

    noteMoyenne = new JLabel("Note : ");
    font = new Font("Arial", Font.BOLD, 50);
    noteMoyenne.setFont(font);
    couleurText = new Color(0, 5, 57);
    noteMoyenne.setForeground(couleurText);
    noteMoyenne.setAlignmentX(JLabel.CENTER_ALIGNMENT);

    infoPanel.add(nomHotel);
    infoPanel.add(Box.createVerticalStrut(150));
    infoPanel.add(adresseHotel);
    infoPanel.add(noteMoyenne);

    infoPanel.setOpaque(false);

    // Superposition des informations sur l'image d'arrière-plan
    overlayPanel = new JPanel(new GridBagLayout());
    overlayPanel.setOpaque(false);
    overlayPanel.add(infoPanel);

    // Ajout du panneau de superposition sur le label d'arrière-plan
    backgroundLabel.setLayout(new BorderLayout());
    backgroundLabel.add(overlayPanel, BorderLayout.CENTER);

    // Redimensionnement de l'image dans le label d'arrière-plan
    backgroundLabel.setHorizontalAlignment(JLabel.CENTER);
    backgroundLabel.setVerticalAlignment(JLabel.CENTER);
    backgroundLabel.setIcon(new ImageIcon(((ImageIcon) backgroundLabel.getIcon()).getImage().getScaledInstance(
        1920, 1080, Image.SCALE_SMOOTH)));

    // Ajout du label d'arrière-plan à la vue AccueilView
    add(backgroundLabel);

    new controller.AccueilController(hotel, this);
  }

  public JLabel getNomHotel() {
    return nomHotel;
  }

  public JLabel getAdresseHotel() {
    return adresseHotel;
  }

  public JLabel getNoteMoyenne() {
    return noteMoyenne;
  }
}
