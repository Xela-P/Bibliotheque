import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class Bibliotheque {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreBienvenue().setVisible(true));
    }

    // Fenêtre de bienvenue
    static class FenetreBienvenue extends JFrame {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FenetreBienvenue() {
            setTitle("Bienvenue dans la Bibliothèque");
            setSize(1280, 720);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Interface
            JLabel bienvenue = new JLabel("Bienvenue dans l'application Bibliothèque", JLabel.CENTER);
            JButton btnAjout = new JButton("Ajouter un document");
            JButton btnRecherche = new JButton("Rechercher un document");
            JButton btnLister = new JButton("Lister les documents");
            JButton btnAjoutEditeur = new JButton("Ajouter un éditeur");
            JButton btnModifierEditeur = new JButton("Modifier un éditeur");
            JButton btnSupprimerEditeur = new JButton("Supprimer un éditeur");

            // Listeners
            btnAjout.addActionListener(e -> {
                new FenetreAjoutDocument().setVisible(true);
                dispose();
            });
            btnRecherche.addActionListener(e -> {
                new FenetreRechercheDocument().setVisible(true);
                dispose();
            });
            btnLister.addActionListener(e -> {
                new FenetreListeDocuments().setVisible(true);
                dispose();
            });
            btnAjoutEditeur.addActionListener(e -> {
                new FenetreAjoutEditeur().setVisible(true);
                dispose();
            });
            btnModifierEditeur.addActionListener(e -> {
                new FenetreModifierEditeur().setVisible(true);
                dispose();
            });
            btnSupprimerEditeur.addActionListener(e -> {
                new FenetreSupprimerEditeur().setVisible(true);
                dispose();
            });

            // Layout
            setLayout(new BorderLayout());
            add(bienvenue, BorderLayout.CENTER);

            JPanel panelButtons = new JPanel();
            panelButtons.add(btnAjout);
            panelButtons.add(btnRecherche);
            panelButtons.add(btnLister);
            panelButtons.add(btnAjoutEditeur);
            panelButtons.add(btnModifierEditeur);
            panelButtons.add(btnSupprimerEditeur);
            add(panelButtons, BorderLayout.SOUTH);
        }
    }

    // Fenêtre pour ajouter un document
    static class FenetreAjoutDocument extends JFrame {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FenetreAjoutDocument() {
            setTitle("Ajouter un document");
            setSize(400, 300);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Interface
            JLabel lblTitre = new JLabel("Titre:");
            JTextField txtTitre = new JTextField(20);

            JLabel lblDate = new JLabel("Date de publication (YYYY-MM-DD):");
            JTextField txtDate = new JTextField(10);

            JLabel lblStock = new JLabel("Stock:");
            JTextField txtStock = new JTextField(5);

            JLabel lblPrix = new JLabel("Prix:");
            JTextField txtPrix = new JTextField(10);

            JLabel lblEditeur = new JLabel("ID Editeur:");
            JTextField txtEditeur = new JTextField(5);

            JButton btnAjouter = new JButton("Ajouter");
            JButton btnRetour = new JButton("Retour");

            // Actions
            btnAjouter.addActionListener(e -> {
                String titre = txtTitre.getText();
                String datePublication = txtDate.getText();
                int stock = Integer.parseInt(txtStock.getText());
                double prix = Double.parseDouble(txtPrix.getText());
                int idEditeur = Integer.parseInt(txtEditeur.getText());

                String query = "INSERT INTO Document (Titre, DatePublication, Stock, Prix, idEditeur) VALUES (?, ?, ?, ?, ?)";
                try (Connection conn = DatabaseManager.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, titre);
                    stmt.setDate(2, Date.valueOf(datePublication));
                    stmt.setInt(3, stock);
                    stmt.setDouble(4, prix);
                    stmt.setInt(5, idEditeur);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Document ajouté avec succès !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout : " + ex.getMessage());
                }
            });

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            // Layout
            setLayout(new GridLayout(6, 2));
            add(lblTitre); add(txtTitre);
            add(lblDate); add(txtDate);
            add(lblStock); add(txtStock);
            add(lblPrix); add(txtPrix);
            add(lblEditeur); add(txtEditeur);
            add(btnAjouter); add(btnRetour);
        }
    }

    // Fenêtre pour rechercher un document
    static class FenetreRechercheDocument extends JFrame {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FenetreRechercheDocument() {
            setTitle("Rechercher un document");
            setSize(600, 400);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Interface
            JLabel lblTitre = new JLabel("Titre:");
            JTextField txtTitre = new JTextField(20);
            JButton btnRechercher = new JButton("Rechercher");
            JButton btnRetour = new JButton("Retour");
            JTextArea txtResultat = new JTextArea();
            txtResultat.setEditable(false);

            btnRechercher.addActionListener(e -> {
                String titre = txtTitre.getText();
                List<String > result = DocumentManager.rechercherDocumentParTitre(titre);
                txtResultat.setText(result.toString());
            });

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            // Layout
            setLayout(new BorderLayout());
            JPanel panelRecherche = new JPanel();
            panelRecherche.add(lblTitre); panelRecherche.add(txtTitre); panelRecherche.add(btnRechercher);
            add(panelRecherche, BorderLayout.NORTH);
            add(new JScrollPane(txtResultat), BorderLayout.CENTER);
            add(btnRetour, BorderLayout.SOUTH);
        }
    }

    // Fenêtre pour lister tous les documents
    static class FenetreListeDocuments extends JFrame {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FenetreListeDocuments() {
            setTitle("Liste des documents");
            setSize(600, 400);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Interface
            JTextArea txtResultat = new JTextArea();
            txtResultat.setEditable(false);
            JButton btnRetour = new JButton("Retour");

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            // Charger les documents
            String query = "SELECT * FROM Document";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    txtResultat.append("ID: " + rs.getInt("idDoc") + ", Titre: " + rs.getString("Titre") +
                            ", Date: " + rs.getDate("DatePublication") + ", Stock: " + rs.getInt("Stock") +
                            ", Prix: " + rs.getDouble("Prix") + "\n");
                }
            } catch (SQLException ex) {
                txtResultat.setText("Erreur : " + ex.getMessage());
            }

            // Layout
            setLayout(new BorderLayout());
            add(new JScrollPane(txtResultat), BorderLayout.CENTER);
            add(btnRetour, BorderLayout.SOUTH);
        }
    }
		
		static class FenetreAjoutEditeur extends JFrame {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public FenetreAjoutEditeur() {
	            setTitle("Ajouter un éditeur");
	            setSize(400, 300);
	            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	            setLocationRelativeTo(null);

	            JLabel lblId = new JLabel("ID Éditeur:");
	            JTextField txtId = new JTextField(10);

	            JLabel lblNom = new JLabel("Nom:");
	            JTextField txtNom = new JTextField(20);

	            JLabel lblAdresse = new JLabel("Adresse:");
	            JTextField txtAdresse = new JTextField(20);

	            JLabel lblPays = new JLabel("Pays:");
	            JTextField txtPays = new JTextField(20);

	            JButton btnAjouter = new JButton("Ajouter");
	            JButton btnRetour = new JButton("Retour");

	            btnAjouter.addActionListener(e -> {
	                int id = Integer.parseInt(txtId.getText());
	                String nom = txtNom.getText();
	                String adresse = txtAdresse.getText();
	                String pays = txtPays.getText();

	               EditeurManager.ajouterEditeur(id, adresse, pays, nom);
	            });
	            btnRetour.addActionListener(e -> {
	                new FenetreBienvenue().setVisible(true);
	                dispose();
	            });

	            setLayout(new GridLayout(5, 2));
	            add(lblId); add(txtId);
	            add(lblNom); add(txtNom);
	            add(lblAdresse); add(txtAdresse);
	            add(lblPays); add(txtPays);
	            add(btnAjouter); add(btnRetour);
	        }
	    }

	    static class FenetreModifierEditeur extends JFrame {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public FenetreModifierEditeur() {
	            setTitle("Modifier un éditeur");
	            setSize(400, 300);
	            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	            setLocationRelativeTo(null);

	            JLabel lblId = new JLabel("ID Éditeur:");
	            JTextField txtId = new JTextField(10);

	            JLabel lblNom = new JLabel("Nouveau nom:");
	            JTextField txtNom = new JTextField(20);

	            JLabel lblAdresse = new JLabel("Nouvelle adresse:");
	            JTextField txtAdresse = new JTextField(20);

	            JLabel lblPays = new JLabel("Nouveau pays:");
	            JTextField txtPays = new JTextField(20);

	            JButton btnModifier = new JButton("Modifier");
	            JButton btnRetour = new JButton("Retour");

	            btnModifier.addActionListener(e -> {
	                int id = Integer.parseInt(txtId.getText());
	                String nom = txtNom.getText();
	                String adresse = txtAdresse.getText();
	                String pays = txtPays.getText();

	                EditeurManager.modifierEditeur(id, adresse, pays, nom);
	            });

	            btnRetour.addActionListener(e -> {
	                new FenetreBienvenue().setVisible(true);
	                dispose();
	            });

	            setLayout(new GridLayout(5, 2));
	            add(lblId); add(txtId);
	            add(lblNom); add(txtNom);
	            add(lblAdresse); add(txtAdresse);
	            add(lblPays); add(txtPays);
	            add(btnModifier); add(btnRetour);
	        }
	    }

	    static class FenetreSupprimerEditeur extends JFrame {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public FenetreSupprimerEditeur() {
	            setTitle("Supprimer un éditeur");
	            setSize(400, 200);
	            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	            setLocationRelativeTo(null);

	            JLabel lblId = new JLabel("ID Éditeur:");
	            JTextField txtId = new JTextField(10);
	            JButton btnSupprimer = new JButton("Supprimer");
	            JButton btnRetour = new JButton("Retour");

	            btnSupprimer.addActionListener(e -> {
	                int id = Integer.parseInt(txtId.getText());
	                EditeurManager.supprimerEditeur(id);
	            });

	            btnRetour.addActionListener(e -> {
	                new FenetreBienvenue().setVisible(true);
	                dispose();
	            });

	            setLayout(new GridLayout(2, 2));
	            add(lblId); add(txtId);
	            add(btnSupprimer); add(btnRetour);
	        }
	    }
		
		@Override
		public String toString() {
			return "";
			
		}
    }

