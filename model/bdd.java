package model;

import java.time.LocalDate;

public class bdd {

    public Hotel hotel;

    public bdd() {

        // création de l'hotel
        hotel = new Hotel("Doz Hotel", "102 Av. des Champs-Élysées, 75008 Paris");

        // Liste des produits du Minibar
        Produit produit1 = new Produit("Chips", 2.50);
        Produit produit2 = new Produit("Coca", 1.50);
        Produit produit3 = new Produit("Bonbons", 2);
        Produit produit4 = new Produit("Restaurant", 19.99);
        Produit produit5 = new Produit("Champagne", 30);
        Produit produit6 = new Produit("Vin", 39.99);
        hotel.addProduit(produit1);
        hotel.addProduit(produit2);
        hotel.addProduit(produit3);
        hotel.addProduit(produit4);
        hotel.addProduit(produit5);
        hotel.addProduit(produit6);

        // Création de clients
        int nombreDeProduits = hotel.getProduits().size();
        Client client1 = new Client("Thornton", "Patrick", "PatrickPThornton@dayrep.com", nombreDeProduits);
        Client client2 = new Client("Miyaz", "Abdul-Qadir", "MiyazAbdul-QadirTuma@jourrapide.com", nombreDeProduits);
        Client client3 = new Client("Ana Fernandes", "Julia", "AnaFernandes@gmail.es", nombreDeProduits);
        Client client4 = new Client("Güçlü", "Özinal ", "Özinal-Güçlü@outlook.com", nombreDeProduits);
        Client client5 = new Client("Rousset", "Jeanne", "J.rousset@hotmail.fr", nombreDeProduits);
        Client client6 = new Client("Scholtz", "Radmila ", "Scholtzradmila@hotmail.de", nombreDeProduits);
        Client client7 = new Client("Palmade", "Pierre", "pierrelevrai@outlook.com", nombreDeProduits);
        Client client8 = new Client("Costa", "Frederic", "Fred.c91@gmail.com", nombreDeProduits);
        Client client9 = new Client("Bassigno", "Jeremy", "Jerems77140@yahoo.fr", nombreDeProduits);
        Client client10 = new Client("Suhaute", "Kévin", "kevinelsoat@orange.fr", nombreDeProduits);
        Client client11 = new Client("a", "a", "a", nombreDeProduits);

        // Ajout des clients dans la liste des clients de l'hotel
        hotel.addClient(client1);
        hotel.addClient(client2);
        hotel.addClient(client3);
        hotel.addClient(client4);
        hotel.addClient(client5);
        hotel.addClient(client6);
        hotel.addClient(client7);
        hotel.addClient(client8);
        hotel.addClient(client9);
        hotel.addClient(client10);
        hotel.addClient(client11);

        // Ajout des notes pour chaque client
        client1.setNote(2);
        client2.setNote(5);
        client3.setNote(3);
        client4.setNote(4);
        client5.setNote(3);
        client6.setNote(5);
        client7.setNote(5);
        client8.setNote(2);
        client9.setNote(4);
        client10.setNote(5);

        // Type de commoditée
        TypeDeCommodite CS = new TypeDeCommodite("Chambre simple", 80);
        TypeDeCommodite CD = new TypeDeCommodite("Chambre double", 100);
        TypeDeCommodite SC = new TypeDeCommodite("Suite classique", 250);
        TypeDeCommodite SP = new TypeDeCommodite("Suite présidentielle", 1000);

        // Chambres de type simple, leurs prix, numéro de chambre et étage
        Commodite chambreS1 = new Commodite(CS, 101, 1);
        Commodite chambreS2 = new Commodite(CS, 102, 1);
        Commodite chambreS3 = new Commodite(CS, 103, 1);
        Commodite chambreS4 = new Commodite(CS, 104, 1);
        Commodite chambreS5 = new Commodite(CS, 201, 2);
        Commodite chambreS6 = new Commodite(CS, 202, 2);
        Commodite chambreS7 = new Commodite(CS, 203, 2);
        Commodite chambreS8 = new Commodite(CS, 204, 2);
        hotel.addCommodite(chambreS1);
        hotel.addCommodite(chambreS2);
        hotel.addCommodite(chambreS3);
        hotel.addCommodite(chambreS4);
        hotel.addCommodite(chambreS5);
        hotel.addCommodite(chambreS6);
        hotel.addCommodite(chambreS7);
        hotel.addCommodite(chambreS8);

        // Chambres de type double, leurs prix, numéro de chambre et étage
        Commodite chambreD1 = new Commodite(CD, 301, 3);
        Commodite chambreD2 = new Commodite(CD, 302, 3);
        Commodite chambreD3 = new Commodite(CD, 303, 3);
        Commodite chambreD4 = new Commodite(CD, 304, 3);
        Commodite chambreD5 = new Commodite(CD, 401, 4);
        Commodite chambreD6 = new Commodite(CD, 402, 4);
        Commodite chambreD7 = new Commodite(CD, 403, 4);
        Commodite chambreD8 = new Commodite(CD, 404, 4);
        hotel.addCommodite(chambreD1);
        hotel.addCommodite(chambreD2);
        hotel.addCommodite(chambreD3);
        hotel.addCommodite(chambreD4);
        hotel.addCommodite(chambreD5);
        hotel.addCommodite(chambreD6);
        hotel.addCommodite(chambreD7);
        hotel.addCommodite(chambreD8);

        // Suites de type clasique, leurs prix, numéro de chambre et étage
        Commodite suiteC1 = new Commodite(SC, 501, 4);
        Commodite suiteC2 = new Commodite(SC, 502, 4);
        Commodite suiteC3 = new Commodite(SC, 601, 5);
        Commodite suiteC4 = new Commodite(SC, 602, 5);
        hotel.addCommodite(suiteC1);
        hotel.addCommodite(suiteC2);
        hotel.addCommodite(suiteC3);
        hotel.addCommodite(suiteC4);

        // Suites de type Présidentielle, leurs prix, et l'étage qu'elles occupent
        Commodite suiteP1 = new Commodite(SP, 700, 6);
        Commodite suiteP2 = new Commodite(SP, 800, 7);
        hotel.addCommodite(suiteP1);
        hotel.addCommodite(suiteP2);

        // Liste des options proposées par l'Hotel
        Option option1 = new Option("Petit déjeuner", 20);
        Option option2 = new Option("wifi", 10);
        Option option3 = new Option("Piscine", 50);
        Option option4 = new Option("Voiture de location", 50);
        Option option5 = new Option("Guide touristique", 20);
        Option option6 = new Option("Laverie", 40);
        Option option7 = new Option("Salle de sport", 15);
        hotel.addOption(option1);
        hotel.addOption(option2);
        hotel.addOption(option3);
        hotel.addOption(option4);
        hotel.addOption(option5);
        hotel.addOption(option6);
        hotel.addOption(option7);

        // Listes de réservations et Séjours
        LocalDate date_de_debut1 = LocalDate.of(2022, 6, 1);
        LocalDate date_de_fin1 = LocalDate.of(2022, 8, 1);
        Reservation reservation1 = new Reservation(date_de_debut1, date_de_fin1);
        reservation1.setClient(client1);
        reservation1.setCommodite(chambreS2);
        chambreS2.addReservation(reservation1);

        LocalDate date_de_debut2 = LocalDate.of(2021, 12, 11);
        LocalDate date_de_fin2 = LocalDate.of(2021, 12, 30);
        Reservation reservation2 = new Reservation(date_de_debut2, date_de_fin2);
        reservation2.setClient(client3);
        reservation2.setCommodite(chambreS1);
        chambreS1.addReservation(reservation2);

        LocalDate date_de_debut3 = LocalDate.of(2023, 7, 1);
        LocalDate date_de_fin3 = LocalDate.of(2023, 7, 31);
        Reservation reservation3 = new Reservation(date_de_debut3, date_de_fin3);
        reservation3.setClient(client6);
        reservation3.setCommodite(chambreS1);
        Sejour sejour1 = new Sejour(reservation3);
        sejour1.addOption(option4);
        sejour1.addOption(option1);
        sejour1.addOption(option3);
        chambreS1.setSejour(sejour1);

        LocalDate date_de_debut4 = LocalDate.of(2023, 7, 14);
        LocalDate date_de_fin4 = LocalDate.of(2023, 7, 21);
        Reservation reservation4 = new Reservation(date_de_debut4, date_de_fin4);
        reservation4.setClient(client1);
        reservation4.setCommodite(suiteC1);
        Sejour sejour2 = new Sejour(reservation4);
        sejour2.addOption(option7);
        sejour2.addOption(option2);
        sejour2.addOption(option5);
        sejour2.addOption(option6);
        suiteC1.setSejour(sejour2);

    }

    public Hotel getHotel() {
        return hotel;
    }
}
