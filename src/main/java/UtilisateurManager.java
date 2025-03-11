import java.sql.*;

public class UtilisateurManager {

    public UtilisateurManager() {

    }

    // Vérifier si un utilisateur existe
    public static boolean utilisateurExiste(int idUtilisateur) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Utilisateur WHERE idUtilisateur = ?";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUtilisateur);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Vérifier si un document existe et est en stock
    public static boolean documentDisponible(int idDoc) throws SQLException {
        String sql = "SELECT Stock FROM Document WHERE idDoc = ?";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDoc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Emprunter un document
    public static void emprunterDocument(int idUtilisateur, int idDoc) throws SQLException {
        if (utilisateurExiste(idUtilisateur) && documentDisponible(idDoc)) {
            String sqlEmprunt = "INSERT INTO Emprunt (DateEmprunt, DateRetourPrevu, idUtilisateur, idDoc) VALUES (CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY), ?, ?)";
            String sqlUpdateStock = "UPDATE Document SET Stock = Stock - 1 WHERE idDoc = ?";
            try (Connection conn = DatabaseManager.getConnection();
                    PreparedStatement stmtEmprunt = conn.prepareStatement(sqlEmprunt);
            	PreparedStatement stmtUpdateStock = conn.prepareStatement(sqlUpdateStock)) {
                stmtEmprunt.setInt(1, idUtilisateur);
                stmtEmprunt.setInt(2, idDoc);
                stmtEmprunt.executeUpdate();
                stmtUpdateStock.setInt(1, idDoc);
                stmtUpdateStock.executeUpdate();
            }
        }
    }

    // Remettre un document emprunté
    public static void remettreDocument(int idUtilisateur, int idDoc) throws SQLException {
        String sqlRetour = "DELETE FROM Emprunt WHERE idUtilisateur = ? AND idDoc = ?";
        String sqlUpdateStock = "UPDATE Document SET Stock = Stock + 1 WHERE idDoc = ?";
        try (Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmtRetour = conn.prepareStatement(sqlRetour);
        	PreparedStatement stmtUpdateStock = conn.prepareStatement(sqlUpdateStock)) {
            stmtRetour.setInt(1, idUtilisateur);
            stmtRetour.setInt(2, idDoc);
            stmtRetour.executeUpdate();
            stmtUpdateStock.setInt(1, idDoc);
            stmtUpdateStock.executeUpdate();
        }
    }

    // Ajouter un utilisateur s'il n'existe pas déjà
    public static void ajouterUtilisateur(int id, String nom, String prenom, String adresse, String categorie, int age, String mail, String tel) throws SQLException {
        if (!utilisateurExiste(id)) {
            String sql = "INSERT INTO Utilisateur (idUtilisateur, Nom, Prenom, Adresse, Categorie, Age, Mail, Tel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql))  {
                stmt.setInt(1, id);
                stmt.setString(2, nom);
                stmt.setString(3, prenom);
                stmt.setString(4, adresse);
                stmt.setString(5, categorie);
                stmt.setInt(6, age);
                stmt.setString(7, mail);
                stmt.setString(8, tel);
                stmt.executeUpdate();
            }
        }
    }

    // Modifier un utilisateur existant
    public static void modifierUtilisateur(int id, String nom, String prenom, String adresse, String categorie, int age, String mail, String tel) throws SQLException {
        if (utilisateurExiste(id)) {
            String sql = "UPDATE Utilisateur SET Nom = ?, Prenom = ?, Adresse = ?, Categorie = ?, Age = ?, Mail = ?, Tel = ? WHERE idUtilisateur = ?";
            try (Connection conn = DatabaseManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql))  {
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setString(3, adresse);
                stmt.setString(4, categorie);
                stmt.setInt(5, age);
                stmt.setString(6, mail);
                stmt.setString(7, tel);
                stmt.setInt(8, id);
                stmt.executeUpdate();
            }
        }
    }

    // Supprimer un utilisateur s'il existe
    public static void supprimerUtilisateur(int id) throws SQLException {
        if (utilisateurExiste(id)) {
            String sql = "DELETE FROM Utilisateur WHERE idUtilisateur = ?";
            try (Connection conn = DatabaseManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql))  {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
    }
}
