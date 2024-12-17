public class Utilisateur {
    private int idUtilisateur;
    private String Nom;
    private String Prenom;
    private String Adresse;
    private String Categorie;
    private int Age;
    private String Mail;
    private String Tel;


    public Utilisateur() {
    	
    	
    }


	/**
	 * @return the idUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return Nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		Nom = nom;
	}


	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return Prenom;
	}


	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}


	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return Adresse;
	}


	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}


	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return Categorie;
	}


	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		Categorie = categorie;
	}


	/**
	 * @return the age
	 */
	public int getAge() {
		return Age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		Age = age;
	}


	/**
	 * @return the mail
	 */
	public String getMail() {
		return Mail;
	}


	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		Mail = mail;
	}


	/**
	 * @return the tel
	 */
	public String getTel() {
		return Tel;
	}


	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		Tel = tel;
	}
    
}