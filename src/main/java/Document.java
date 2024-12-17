import java.math.BigDecimal;
import java.sql.Date;

public class Document {
    private int idDoc;
    private String titre;
    private Date datePublication;
    private int stock;
    private BigDecimal prix;
    private Editeur editeur;
    
    
	/**
	 * @return the idDoc
	 */
	public int getIdDoc() {
		return idDoc;
	}
	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * @return the datePublication
	 */
	public Date getDatePublication() {
		return datePublication;
	}
	/**
	 * @param datePublication the datePublication to set
	 */
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}
	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
	 * @return the prix
	 */
	public BigDecimal getPrix() {
		return prix;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	/**
	 * @return the editeur
	 */
	public Editeur getEditeur() {
		return editeur;
	}
	/**
	 * @param editeur the editeur to set
	 */
	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

    // Getters and setters
    
    
}