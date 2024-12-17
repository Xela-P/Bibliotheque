import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DocumentManager {
    public static void ajouterDocument(String titre, String datePublication, int stock, double prix, int idEditeur) {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "INSERT INTO Document (Titre, DatePublication, Stock, Prix, idEditeur) VALUES ('" + titre + "', '" + datePublication + "', " + stock + ", " + prix + ", " + idEditeur + ")";
            stmt.executeUpdate(sql);
            System.out.println("Document ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void afficherDocuments() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Document";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idDoc") + ", Titre: " + rs.getString("Titre"));
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ajouterDocument("Notre-Dame de Paris", "1831-01-01", 5, 12.99, 1);
        afficherDocuments();
    }
}