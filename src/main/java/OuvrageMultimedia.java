import java.sql.Time;

public class OuvrageMultimedia extends Document {
    private String type;
    private Time dureeTotale;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the dureeTotale
	 */
	public Time getDureeTotale() {
		return dureeTotale;
	}
	/**
	 * @param dureeTotale the dureeTotale to set
	 */
	public void setDureeTotale(Time dureeTotale) {
		this.dureeTotale = dureeTotale;
	}

    
}