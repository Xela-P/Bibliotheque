import java.sql.Date;

public class Journal extends Document {
    private Date datePublicationSpecifique;

	/**
	 * @return the datePublicationSpecifique
	 */
	public Date getDatePublicationSpecifique() {
		return datePublicationSpecifique;
	}

	/**
	 * @param datePublicationSpecifique the datePublicationSpecifique to set
	 */
	public void setDatePublicationSpecifique(Date datePublicationSpecifique) {
		this.datePublicationSpecifique = datePublicationSpecifique;
	}

    
}