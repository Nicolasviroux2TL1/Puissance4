package model;

public class P4Model {
	private GrilleJeu grille;
	private Joueur joueur1, joueur2;
	private static int tour = 0;

	
	public Joueur getJoueurQuiJoue() {
		if (tour == 0)
			return this.joueur1;
		return this.joueur2;
	}

	//Constructeur
	public P4Model(String joueur1, String joueur2) {
		grille = new GrilleJeu();
		this.joueur1 = new Joueur(joueur1, 'r');
		this.joueur2 = new Joueur(joueur2,'j');
	}

	public static int getTour() {
		return tour;
	}

	public static void setTour(int tour) {
		P4Model.tour = tour;
	}
	
	public GrilleJeu getGrille() {
		return grille;
	}

	public Joueur getJoueur1() {
		return this.joueur1;
	}

	public Joueur getJoueur2() {
		return this.joueur2;
	}

	public void reinitialiser() {
		grille.initialise();
		tour = 0;
	}
	/**
	 * selectionne le joueur qui a gagne
	 * @param joueur
	 */
	public void aGagne(int joueur) {
		if (joueur == 1)
			joueur1.setJoueurGagnant(joueur1.getJoueurGagnant() + 1);
		else
			joueur2.setJoueurGagnant(joueur2.getJoueurGagnant() + 1);
	}

		// Si joueur1 choisis une couleur , joueur2 obtiens l'inverse 
	public void setCouleurJoueur(char couleur, String user) {
		char couleurinv = 'a' ;
		switch(couleur){
		case 'r' : couleurinv = 'j'; 
		break;
		case 'j' : couleurinv = 'r'; 
		}

		 if(user.compareToIgnoreCase("Serv")==0){ // 
			joueur1.setCouleur(couleur);
			joueur2.setCouleur(couleurinv);

		}else{
			joueur2.setCouleur(couleur);
			joueur1.setCouleur(couleurinv);
			}							
	}
}