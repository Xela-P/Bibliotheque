import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentManager {

    // Ajouter un document
    public static void ajouterDocument(int idDoc, String titre, Date datePublication, int stock, double prix, int idEditeur) {
        String query = "INSERT INTO Document (idDoc, Titre, DatePublication, Stock, Prix, idEditeur) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idDoc);
            stmt.setString(2, titre);
            stmt.setDate(3, datePublication);
            stmt.setInt(4, stock);
            stmt.setDouble(5, prix);
            stmt.setInt(6, idEditeur);

            stmt.executeUpdate();
            System.out.println("Document ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rechercher des documents par titre
    public static List<String> rechercherDocumentParTitre(String titre) {
        String query = "SELECT * FROM Document WHERE Titre LIKE ?";
        List<String> documents = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + titre + "%"); // Recherche partielle
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                documents.add("ID: " + rs.getInt("idDoc") + ", Titre: " + rs.getString("Titre") +
                        ", Date: " + rs.getDate("DatePublication") + ", Stock: " + rs.getInt("Stock") +
                        ", Prix: " + rs.getDouble("Prix") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    // Modifier un document
    public static void modifierDocument(int idDoc, String nouveauTitre, int nouveauStock, double nouveauPrix) {
        String query = "UPDATE Document SET Titre = ?, Stock = ?, Prix = ? WHERE idDoc = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nouveauTitre);
            stmt.setInt(2, nouveauStock);
            stmt.setDouble(3, nouveauPrix);
            stmt.setInt(4, idDoc);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Document modifié avec succès.");
            } else {
                System.out.println("Aucun document trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un document
    public static void supprimerDocument(int idDoc) {
        String query = "DELETE FROM Document WHERE idDoc = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idDoc);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Document supprimé avec succès.");
            } else {
                System.out.println("Aucun document trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
