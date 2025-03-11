import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
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
        private static final long serialVersionUID = 1L;

        public FenetreBienvenue() {
            setTitle("Bienvenue dans la Bibliothèque");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Bienvenue dans l'application Bibliothèque", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

            // Interface
            JButton btnAjout = new JButton("Ajouter un document");
            JButton btnRecherche = new JButton("Rechercher un document");
            JButton btnLister = new JButton("Lister les documents");
            JButton btnAjoutEditeur = new JButton("Ajouter un éditeur");
            JButton btnModifierEditeur = new JButton("Modifier un éditeur");
            JButton btnSupprimerEditeur = new JButton("Supprimer un éditeur");
            JButton btnAjoutUtilisateur = new JButton("Ajouter un utilisateur");
            JButton btnModifierUtilisateur = new JButton("Modifier un utilisateur");
            JButton btnSupprimerUtilisateur = new JButton("Supprimer un utilisateur");

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(300, 60);
            btnAjout.setPreferredSize(buttonSize);
            btnRecherche.setPreferredSize(buttonSize);
            btnLister.setPreferredSize(buttonSize);
            btnAjoutEditeur.setPreferredSize(buttonSize);
            btnModifierEditeur.setPreferredSize(buttonSize);
            btnSupprimerEditeur.setPreferredSize(buttonSize);
            btnAjoutUtilisateur.setPreferredSize(buttonSize);
            btnModifierUtilisateur.setPreferredSize(buttonSize);
            btnSupprimerUtilisateur.setPreferredSize(buttonSize);

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
            btnAjoutUtilisateur.addActionListener(e -> {
                new FenetreAjoutUtilisateur().setVisible(true);
                dispose();
            });

            // Panel pour centrer les boutons
            JPanel panelButtons = new JPanel();
            panelButtons.setLayout(new GridBagLayout());
            panelButtons.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Marge intérieure
            panelButtons.setBackground(fondGris); // Fond gris pour le panel des boutons
            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les boutons
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelButtons.add(btnAjout, gbc);
            gbc.gridx = 1;
            panelButtons.add(btnRecherche, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelButtons.add(btnLister, gbc);
            gbc.gridx = 1;
            panelButtons.add(btnAjoutEditeur, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelButtons.add(btnModifierEditeur, gbc);
            gbc.gridx = 1;
            panelButtons.add(btnSupprimerEditeur, gbc);
            gbc.gridx = 5;
            panelButtons.add(btnAjoutUtilisateur, gbc);

            add(panelButtons, BorderLayout.CENTER);

            // Fond gris pour la fenêtre principale
            getContentPane().setBackground(fondGris);
        }
    }

    // Fenêtre pour ajouter un document
    static class FenetreAjoutDocument extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreAjoutDocument() {
            setTitle("Ajouter un document");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Ajouter un document", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

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

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnAjouter.setPreferredSize(buttonSize);
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            // Actions
            btnAjouter.addActionListener(e -> {
                try {
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
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout : " + ex.getMessage());
                }
            });

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            // Layout
            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);
            JPanel panelForm = new JPanel();
            panelForm.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelForm.add(lblTitre, gbc);
            gbc.gridx = 1;
            panelForm.add(txtTitre, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelForm.add(lblDate, gbc);
            gbc.gridx = 1;
            panelForm.add(txtDate, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelForm.add(lblStock, gbc);
            gbc.gridx = 1;
            panelForm.add(txtStock, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panelForm.add(lblPrix, gbc);
            gbc.gridx = 1;
            panelForm.add(txtPrix, gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panelForm.add(lblEditeur, gbc);
            gbc.gridx = 1;
            panelForm.add(txtEditeur, gbc);
            gbc.gridx = 0;
            gbc.gridy = 5;
            panelForm.add(btnAjouter, gbc);
            gbc.gridx = 1;
            panelForm.add(btnRetour, gbc);
            add(panelForm, BorderLayout.CENTER);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    // Fenêtre pour rechercher un document
    static class FenetreRechercheDocument extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreRechercheDocument() {
            setTitle("Rechercher un document");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Rechercher un document", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

            // Interface
            JLabel lblTitre = new JLabel("Titre:");
            JTextField txtTitre = new JTextField(20);
            JButton btnRechercher = new JButton("Rechercher");
            JButton btnRetour = new JButton("Retour");
            JTextArea txtResultat = new JTextArea();
            txtResultat.setEditable(false);

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnRechercher.setPreferredSize(buttonSize);
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            btnRechercher.addActionListener(e -> {
                String titre = txtTitre.getText();
                List<String> result = DocumentManager.rechercherDocumentParTitre(titre);
                txtResultat.setText(result.toString());
            });

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            // Layout
            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);
            JPanel panelRecherche = new JPanel();
            panelRecherche.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelRecherche.add(lblTitre, gbc);
            gbc.gridx = 1;
            panelRecherche.add(txtTitre, gbc);
            gbc.gridx = 2;
            panelRecherche.add(btnRechercher, gbc);
            add(panelRecherche, BorderLayout.NORTH);
            add(new JScrollPane(txtResultat), BorderLayout.CENTER);
            add(btnRetour, BorderLayout.SOUTH);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    // Fenêtre pour lister tous les documents
    static class FenetreListeDocuments extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreListeDocuments() {
            setTitle("Liste des documents");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Liste des documents", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

            // Interface
            JTextArea txtResultat = new JTextArea();
            txtResultat.setEditable(false);
            JButton btnRetour = new JButton("Retour");

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            // Charger les documents
            String query = "SELECT * FROM Document";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

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
            add(panelBandeau, BorderLayout.NORTH);
            add(new JScrollPane(txtResultat), BorderLayout.CENTER);
            add(btnRetour, BorderLayout.SOUTH);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    static class FenetreAjoutEditeur extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreAjoutEditeur() {
            setTitle("Ajouter un éditeur");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Ajouter un éditeur", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

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

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnAjouter.setPreferredSize(buttonSize);
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            btnAjouter.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nom = txtNom.getText();
                    String adresse = txtAdresse.getText();
                    String pays = txtPays.getText();

                    EditeurManager.ajouterEditeur(id, adresse, pays, nom);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.");
                }
            });
            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);
            JPanel panelForm = new JPanel();
            panelForm.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelForm.add(lblId, gbc);
            gbc.gridx = 1;
            panelForm.add(txtId, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelForm.add(lblNom, gbc);
            gbc.gridx = 1;
            panelForm.add(txtNom, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelForm.add(lblAdresse, gbc);
            gbc.gridx = 1;
            panelForm.add(txtAdresse, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panelForm.add(lblPays, gbc);
            gbc.gridx = 1;
            panelForm.add(txtPays, gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panelForm.add(btnAjouter, gbc);
            gbc.gridx = 1;
            panelForm.add(btnRetour, gbc);
            add(panelForm, BorderLayout.CENTER);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    static class FenetreModifierEditeur extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreModifierEditeur() {
            setTitle("Modifier un éditeur");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Modifier un éditeur", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

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

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnModifier.setPreferredSize(buttonSize);
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            btnModifier.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nom = txtNom.getText();
                    String adresse = txtAdresse.getText();
                    String pays = txtPays.getText();

                    EditeurManager.modifierEditeur(id, adresse, pays, nom);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.");
                }
            });

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);
            JPanel panelForm = new JPanel();
            panelForm.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelForm.add(lblId, gbc);
            gbc.gridx = 1;
            panelForm.add(txtId, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelForm.add(lblNom, gbc);
            gbc.gridx = 1;
            panelForm.add(txtNom, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelForm.add(lblAdresse, gbc);
            gbc.gridx = 1;
            panelForm.add(txtAdresse, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panelForm.add(lblPays, gbc);
            gbc.gridx = 1;
            panelForm.add(txtPays, gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panelForm.add(btnModifier, gbc);
            gbc.gridx = 1;
            panelForm.add(btnRetour, gbc);
            add(panelForm, BorderLayout.CENTER);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    static class FenetreSupprimerEditeur extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreSupprimerEditeur() {
            setTitle("Supprimer un éditeur");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Supprimer un éditeur", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

            JLabel lblId = new JLabel("ID Éditeur:");
            JTextField txtId = new JTextField(10);
            JButton btnSupprimer = new JButton("Supprimer");
            JButton btnRetour = new JButton("Retour");

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnSupprimer.setPreferredSize(buttonSize);
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            btnSupprimer.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    EditeurManager.supprimerEditeur(id);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.");
                }
            });

            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);
            JPanel panelForm = new JPanel();
            panelForm.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelForm.add(lblId, gbc);
            gbc.gridx = 1;
            panelForm.add(txtId, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelForm.add(btnSupprimer, gbc);
            gbc.gridx = 1;
            panelForm.add(btnRetour, gbc);
            add(panelForm, BorderLayout.CENTER);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    static class FenetreAjoutUtilisateur extends JFrame {
        private static final long serialVersionUID = 1L;

        public FenetreAjoutUtilisateur() {
            setTitle("Ajouter un utilisateur");
            setSize(800, 600); // Taille uniforme pour toutes les fenêtres
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Couleurs
            Color fondGris = Color.decode("#36393F"); // Couleur de fond gris similaire à Discord
            Color bandeauTurquoise = Color.decode("#1ABC9C"); // Couleur du bandeau bleu turquoise

            // Bandeau supérieur
            JPanel panelBandeau = new JPanel();
            panelBandeau.setBackground(bandeauTurquoise);
            panelBandeau.setPreferredSize(new Dimension(getWidth(), 60));
            JLabel bienvenue = new JLabel("Ajouter un utilisateur", JLabel.CENTER);
            bienvenue.setForeground(Color.WHITE);
            bienvenue.setFont(new Font("Arial", Font.BOLD, 20));
            panelBandeau.add(bienvenue);

            JLabel lblId = new JLabel("ID utilisateur:");
            JTextField txtId = new JTextField(10);

            JLabel lblNom = new JLabel("Nom:");
            JTextField txtNom = new JTextField(50);

            JLabel lblPrenom = new JLabel("Prenom:");
            JTextField txtPrenom = new JTextField(50);

            JLabel lblAge = new JLabel("Age:");
            JTextField txtAge = new JTextField(3);

            JLabel lblAdresse = new JLabel("Adresse:");
            JTextField txtAdresse = new JTextField(100);

            JLabel lblMail = new JLabel("Adresse Mail:");
            JTextField txtMail = new JTextField(50);

            JLabel lblTel = new JLabel("Telephone:");
            JTextField txtTel = new JTextField(15);

            JButton btnAjouter = new JButton("Ajouter");
            JButton btnRetour = new JButton("Retour");

            // Réduire la taille des boutons
            Dimension buttonSize = new Dimension(75, 15); // 2 fois plus petit
            btnAjouter.setPreferredSize(buttonSize);
            btnRetour.setPreferredSize(buttonSize);
            btnRetour.setBackground(bandeauTurquoise); // Couleur turquoise pour le bouton "Retour"

            btnAjouter.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nom = txtNom.getText();
                    String prenom = txtPrenom.getText();
                    String categorie = "Mineur";
                    int age = Integer.parseInt(txtAge.getText());
                    String adresse = txtAdresse.getText();
                    String mail = txtMail.getText();
                    String tel = txtTel.getText();
                    if (age >= 18) {
                        categorie = "Majeur";
                    }

                    UtilisateurManager.ajouterUtilisateur(id, nom, prenom, adresse, categorie, age, mail, tel);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout : " + ex.getMessage());
                }
            });
            btnRetour.addActionListener(e -> {
                new FenetreBienvenue().setVisible(true);
                dispose();
            });

            setLayout(new BorderLayout());
            add(panelBandeau, BorderLayout.NORTH);
            JPanel panelForm = new JPanel();
            panelForm.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espacement entre les composants
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelForm.add(lblId, gbc);
            gbc.gridx = 1;
            panelForm.add(txtId, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelForm.add(lblNom, gbc);
            gbc.gridx = 1;
            panelForm.add(txtNom, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panelForm.add(lblAdresse, gbc);
            gbc.gridx = 1;
            panelForm.add(txtAdresse, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panelForm.add(lblTel, gbc);
            gbc.gridx = 1;
            panelForm.add(txtTel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panelForm.add(lblMail, gbc);
            gbc.gridx = 1;
            panelForm.add(txtMail, gbc);
            gbc.gridx = 0;
            gbc.gridy = 5;
            panelForm.add(lblTel, gbc);
            gbc.gridx = 1;
            panelForm.add(txtTel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 6;
            panelForm.add(lblPrenom, gbc);
            gbc.gridx = 1;
            panelForm.add(txtPrenom, gbc);
            gbc.gridx = 0;
            gbc.gridy = 7;
            panelForm.add(lblAge, gbc);
            gbc.gridx = 1;
            panelForm.add(txtAge, gbc);
            gbc.gridx = 0;
            gbc.gridy = 8;
            panelForm.add(btnAjouter, gbc);
            gbc.gridx = 1;
            panelForm.add(btnRetour, gbc);
            add(panelForm, BorderLayout.CENTER);

            // Fond gris pour la fenêtre
            getContentPane().setBackground(fondGris);
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
