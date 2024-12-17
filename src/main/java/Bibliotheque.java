import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;


public class Bibliotheque extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bibliotheque() {
		// Créer la fenêtre principale
		JFrame frame = new JFrame("Application de Bibliothèque");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());

		// Ajouter la barre de menus
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		itemQuitter.addActionListener(e -> System.exit(0));
		menuFichier.add(itemQuitter);
		menuBar.add(menuFichier);

		JMenu menuAide = new JMenu("Aide");
		JMenuItem itemAPropos = new JMenuItem("À propos");
		itemAPropos.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Application de Bibliothèque - Version 1.0"));
		menuAide.add(itemAPropos);
		menuBar.add(menuAide);

		frame.setJMenuBar(menuBar);

		// Ajouter des onglets
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panelAccueil = createAccueilPanel();
		JPanel panelLivres = createDocumentPanel();
		JPanel panelFormulaire = createFormulairePanel();
		JPanel panelRecherche = createRecherchePanel();

		tabbedPane.addTab("Accueil", panelAccueil);
		tabbedPane.addTab("Liste des Documents", panelLivres);
		tabbedPane.addTab("Ajouter un Document", panelFormulaire);
		tabbedPane.addTab("Rechercher un document", panelRecherche);

		frame.add(tabbedPane, BorderLayout.CENTER);

		// Afficher la fenêtre
		frame.setVisible(true);
	}

	// Méthode pour créer le panneau d'accueil
	private JPanel createAccueilPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Bienvenue", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 25));
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

	// Méthode pour créer le panneau des livres (tableau)
	private JPanel createDocumentPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		String[] columnNames = {"Titre", "Stock", "ID", "Prix", "Date de publication"};
		Object[][] data = {
				{"1", "1984", "George Orwell", "1949"},
				{"2", "Le Seigneur des Anneaux", "J.R.R. Tolkien", "1954"},
				{"3", "Harry Potter", "J.K. Rowling", "1997"}
		};

		JTable table = new JTable(new DefaultTableModel(data, columnNames));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}
	
	private JPanel createRecherchePanel() {
		JPanel panel = new JPanel(new BorderLayout());

        // Titre principal
        JLabel lblTitre = new JLabel("Bibliothèque municipale", SwingConstants.CENTER);
        lblTitre.setBounds(200, 10, 500, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitre.setForeground(Color.WHITE);

        // En-tête
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 900, 50);
        header.setBackground(new Color(70, 150, 140));
        header.add(lblTitre);

        // Champs de recherche
        JLabel lblRecherche = new JLabel("Recherche");
        lblRecherche.setBounds(50, 70, 100, 25);
        JTextField txtRecherche = new JTextField();
        txtRecherche.setBounds(150, 70, 150, 25);

        JLabel lblType = new JLabel("Type (dvd,cd,livre)");
        lblType.setBounds(350, 70, 150, 25);
        JComboBox<String> cbType = new JComboBox<>(new String[]{"Livre", "CD", "DVD"});
        cbType.setBounds(500, 70, 150, 25);

        JLabel lblGenre = new JLabel("Genre");
        lblGenre.setBounds(50, 110, 150, 25);
        JComboBox<String> cbGenre = new JComboBox<>(new String[]{"Roman", "Animation", "Aventure"});
        cbGenre.setBounds(150, 110, 150, 25);

        JLabel lblAge = new JLabel("Âge (adulte, enfant)");
        lblAge.setBounds(350, 110, 150, 25);
        JComboBox<String> cbAge = new JComboBox<>(new String[]{"Adulte", "Enfant"});
        cbAge.setBounds(500, 110, 150, 25);

        // Informations supplémentaires
        JLabel lblStock = new JLabel("En stock");
        lblStock.setBounds(50, 170, 100, 25);
        JTextField txtStock = new JTextField();
        txtStock.setBounds(150, 170, 150, 25);

        JLabel lblAgeTexte = new JLabel("Âge");
        lblAgeTexte.setBounds(350, 170, 100, 25);
        JTextField txtAge = new JTextField();
        txtAge.setBounds(500, 170, 150, 25);

        JLabel lblIDUsager = new JLabel("Id usager");
        lblIDUsager.setBounds(50, 210, 100, 25);
        JTextField txtIDUsager = new JTextField();
        txtIDUsager.setBounds(150, 210, 150, 25);

        JLabel lblDateDebut = new JLabel("Date début prêt");
        lblDateDebut.setBounds(350, 210, 100, 25);
        JTextField txtDateDebut = new JTextField();
        txtDateDebut.setBounds(500, 210, 150, 25);

        JLabel lblIDLivre = new JLabel("ID Livre");
        lblIDLivre.setBounds(50, 250, 100, 25);
        JTextField txtIDLivre = new JTextField();
        txtIDLivre.setBounds(150, 250, 150, 25);

        JLabel lblDateFin = new JLabel("Date fin prêt");
        lblDateFin.setBounds(350, 250, 100, 25);
        JTextField txtDateFin = new JTextField();
        txtDateFin.setBounds(500, 250, 150, 25);

        // Bouton prêt
        JButton btnPret = new JButton("Prêt");
        btnPret.setBounds(350, 300, 100, 30);
        btnPret.setBackground(new Color(70, 150, 140));
        btnPret.setForeground(Color.WHITE);

        // Image du livre
        JLabel lblImage = new JLabel();
        lblImage.setBounds(700, 70, 150, 200);
        ImageIcon image = new ImageIcon("martine.jpg"); // Ajoute ton image ici
        lblImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));

        // Ajouter les composants à la fenêtre
        panel.add(header);
        panel.add(lblRecherche);
        panel.add(txtRecherche);
        panel.add(lblType);
        panel.add(cbType);
        panel.add(lblGenre);
        panel.add(cbGenre);
        panel.add(lblAge);
        panel.add(cbAge);
        panel.add(lblStock);
        panel.add(txtStock);
        panel.add(lblAgeTexte);
        panel.add(txtAge);
        panel.add(lblIDUsager);
        panel.add(txtIDUsager);
        panel.add(lblDateDebut);
        panel.add(txtDateDebut);
        panel.add(lblIDLivre);
        panel.add(txtIDLivre);
        panel.add(lblDateFin);
        panel.add(txtDateFin);
        panel.add(btnPret);
        panel.add(lblImage);

        // Style du fond
        panel.setVisible(true);
    
		return panel;
	}

	private JPanel createFormulairePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 10, 10));

		JLabel lblTitre = new JLabel("Titre:");
		JTextField txtTitre = new JTextField();

		JLabel lblAnnee = new JLabel("Année:");
		JTextField txtAnnee = new JTextField();
		
		JLabel stock = new JLabel("Stock:");
		JTextField txtStock = new JTextField();

		JLabel lblAuteur = new JLabel("Auteur:");
		JTextField txtAuteur = new JTextField();

		JLabel price = new JLabel("Prix:");
		JTextField txtPrice = new JTextField();
		
		JLabel editeur = new JLabel("Editeur:");
		JTextField txtEditeur = new JTextField();

		JButton btnAjouter = new JButton("Ajouter");
		JButton btnAnnuler = new JButton("Annuler");

		// Ajouter les champs et boutons au panneau
		panel.add(lblTitre);
		panel.add(txtTitre);
		panel.add(lblAuteur);
		panel.add(txtAuteur);
		panel.add(lblAnnee);
		panel.add(txtAnnee);
		panel.add(stock);
		panel.add(txtStock);
		panel.add(price);
		panel.add(txtPrice);
		panel.add(editeur);
		panel.add(txtEditeur);
		panel.add(btnAjouter);
		panel.add(btnAnnuler);

		
		//DocumentManager.ajouterDocument(lblTitre, lblAnnee, stock, price, editeur);
		// Ajouter des écouteurs d'événements pour les boutons
		btnAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titre = txtTitre.getText();
				String auteur = txtAuteur.getText();
				String annee = txtAnnee.getText();

				if (!titre.isEmpty() && !auteur.isEmpty() && !annee.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Livre ajouté :\n" +
							"Titre: " + titre + "\nAuteur: " + auteur + "\nAnnée: " + annee);
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAnnuler.addActionListener(e -> {
			txtTitre.setText("");
			txtAuteur.setText("");
			txtAnnee.setText("");
		});

		return panel;
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bibliotheque app = new Bibliotheque();
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setVisible(true);
		});
	}

}
