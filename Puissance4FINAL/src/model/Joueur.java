package model;

public class Joueur  {
	private String nomJoueur;
	private char couleur;
	private int joueurGagnant;
	
	/**
	 * cree un joueur
	 * @param nomJoueur nom du joueur
	 * @param couleur du joueur
	 */
	public Joueur (String nomJoueur , char couleur){
		this.nomJoueur = nomJoueur;
		this.couleur = couleur;
	}
	/**
	 * @return string renvoi le nom du joueur
	 */
	public String getNomJoueur() { // getter
		return nomJoueur;
	}
	/**
	 * @param nomJoueur ,change le nom du joueur
	 */
	public void setNomJoueur(String nomJoueur) { // setter 
		this.nomJoueur = nomJoueur;
	}
	/**
	 * @return la couleur
	 */
	public char getCouleur() { // getter
		return couleur;
	}
	
	/**
	 * @param couleur, change la couleur du joueur
	 */
	public void setCouleur(char couleur) { // setter
		this.couleur = couleur;
	}
	
	/**
	 * @return  le nom du joueur gagnant
	 */
	public int getJoueurGagnant() { // getter
		return joueurGagnant;
	}

	/**
	 * @param joueurGagnant change le nom du gagnant de la partie
	 */
	public void setJoueurGagnant(int joueurGagnant) { // setter
		this.joueurGagnant = joueurGagnant;
	}
}