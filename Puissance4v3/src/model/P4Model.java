package model;

public class P4Model {
	private GrilleJeu grille;
	private Joueur joueur1, joueur2;
	private static int tour = 0;


	/**
	 * constructeur
	 * @param joueur1
	 * @param joueur2
	 */
	public P4Model(String joueur1, String joueur2) {
		grille = new GrilleJeu();
		this.joueur1 = new Joueur(joueur1, 'r');
		this.joueur2 = new Joueur(joueur2,'j');
	}

	/**
	 * renvoi la valeur de tour
	 * @return
	 */
	public static int getTour() {
		return tour;
	}

	/**
	 * change la valeur de tour
	 * @param tour
	 */
	
	public static void setTour(int tour) {
		P4Model.tour = tour;
	}
	
	/**
	 * renvoi la grille
	 * @return grille
	 */
	public GrilleJeu getGrille() {
		return grille;
	}
	
	/**
	 * reinit a 0 et puis remets le tour a 0
	 */
	public void reinitialiser() {
		grille.initialise();
		tour = 0;
	}
	
	/**
	 * joueur commence en 1 puis joueur 2 
	 * @return le joueur qui doit joueur
	 */
	public Joueur getJoueurQuiJoue() {
		if (tour == 0)
			return this.joueur1;
		return this.joueur2;
	}

	/**
	 * 
	 * @param couleur a changer en fonction du serveur ou client choisis
	 * @param user serveur ou client
	 */
	public void setCouleurJoueur(char couleur, String user) {
		char couleurinv = 'a' ;
		switch(couleur){
		case 'r' : couleurinv = 'j'; // si on choisis r , l'autre adversaire a j
		break;
		case 'j' : couleurinv = 'r'; // inverse
		}

		 if(user.compareToIgnoreCase("Serv")==0){ 
			joueur1.setCouleur(couleur);
			joueur2.setCouleur(couleurinv);

		}else{
			joueur2.setCouleur(couleur);
			joueur1.setCouleur(couleurinv);
			}							
	}
}