import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditeurManager {

    // Ajouter un éditeur
    public static void ajouterEditeur(int idEditeur, String adresse, String pays, String nom) {
        String query = "INSERT INTO Editeur (idEditeur, Adresse, Pays, Nom) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idEditeur);
            stmt.setString(2, adresse);
            stmt.setString(3, pays);
            stmt.setString(4, nom);

            stmt.executeUpdate();
            System.out.println("Éditeur ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rechercher un éditeur par nom
    public static List<String> rechercherEditeurParNom(String nom) {
        String query = "SELECT idEditeur, Adresse, Pays, Nom FROM Editeur WHERE Nom LIKE ?";
        List<String> editeurs = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + nom + "%"); // Recherche partielle sur le nom
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                editeurs.add("ID: " + rs.getInt("idEditeur") +
                        ", Nom: " + rs.getString("Nom") +
                        ", Adresse: " + rs.getString("Adresse") +
                        ", Pays: " + rs.getString("Pays"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editeurs;
    }

    // Modifier un éditeur
    public static void modifierEditeur(int idEditeur, String nouvelleAdresse, String nouveauPays, String nouveauNom) {
        String query = "UPDATE Editeur SET Adresse = ?, Pays = ?, Nom = ? WHERE idEditeur = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nouvelleAdresse);
            stmt.setString(2, nouveauPays);
            stmt.setString(3, nouveauNom);
            stmt.setInt(4, idEditeur);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Éditeur modifié avec succès.");
            } else {
                System.out.println("Aucun éditeur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un éditeur
    public static void supprimerEditeur(int idEditeur) {
        String query = "DELETE FROM Editeur WHERE idEditeur = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idEditeur);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Éditeur supprimé avec succès.");
            } else {
                System.out.println("Aucun éditeur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
