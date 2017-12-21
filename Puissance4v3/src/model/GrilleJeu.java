package model;

public class GrilleJeu {
	private CaseJeu [][] grille;
	private int nbCasesVides = nbLigne * nbColonne;
	private static final int nbLigne = 6; 
	private static final int nbColonne = 7; 
	
	/**
	 * c'est le constructeur avec informations des cases du jeu (ligne et colonne)
	 */
	public GrilleJeu () {
		grille = new CaseJeu [nbColonne][nbLigne] ;
		initialise();
	}
	
	/**
	 * modifie unecase dans le tableau
	 * @param x colonne a remplir
	 * @param y ligne a remplir
	 * @param couleur remplis une couleur
	 * @return false ou true --> si la case est bien remplis dans la grille 
	 */
	public boolean remplirCase(int x, int y, char couleur) {
		if (x >= nbColonne || y >= nbLigne)
			return false;
		grille[x][y].setCouleur(couleur);
		this.nbCasesVides--;
		return true;
	}
	
	/**
	 * @return la grille
	 */
	public CaseJeu[][] getGrille() {
		return this.grille;
	}
	/**
	 * @param i , la case a remplir dans une colonne
	 * @param j , case a remplir dans une ligne
	 * on parcourt depuis le bas de la colonne jusqu'a une case vide
	 * @param couleur a remplir dans une case
	 * @return true ou false pour determiner si la case s'est rempli ou pas
	 */
	public boolean coupJoueur(int i , int j, char couleur){
		if(i>= nbColonne || j >= nbLigne){ // si la colonne ou ligne pleine alors le coup n'est pas valide
			return false;
			}
			else {
			grille[i][j].setCouleur(couleur);
			this.nbCasesVides--;
			return true;
			}
		}
	/**
	 * Renvoi le numero de la prochaine case libre dans une colonne donneee en parametre
	 * regarde si une case est remplie
	 * @param colonne a verifier
	 * @return , numero de la prochaine case vide dans une colonne
	 */
	public int prochaineCaseLibre(int colonne) {
		int cpt = 0;
		for (int i = 0; i < nbLigne; i++) {
			if (grille[colonne][i].getCouleur() == 'V')
				return cpt;
			cpt++;
		}
		return cpt; 
	}
	
	/**
	 * @param grille
	 * @param i considere comme ligne
	 * @param j considere comme colonne
	 * mets toutes la grille a 0 
	 */
	public void initialise() {

		for (int i = 0; i < nbColonne; i++) {
			for (int j = 0; j < nbLigne; j++) {
				grille[i][j] = new CaseJeu();
			}
		}
		this.nbCasesVides = nbLigne * nbColonne;
	}
	/**
	 * compte pour arriver a 4 cases de la meme couleur pour un gagnant
	 * @param colonne 
	 * @param ligne
	 * @param dirLigne debut (direction) de la ligne
	 * @param dirColonne debut (direction) de la colonne
	 * @param  jaune ou rouge
	 * @return le nombre de cases de la meme couleur
	 */
	public int compter(int colonne, int position, int dirLigne, int dirColonne, char couleur) {
		if (colonne >= nbColonne || position >= nbLigne || colonne < 0 || position < 0)
			return 0;
		if (grille[colonne][position].getCouleur() == couleur)
			return compter(colonne + dirLigne, position + dirColonne, dirLigne, dirColonne, couleur) + 1;
		return 0;
	}
	
	/**
	 * si la grille est rempli 
	 * @return vrai ou faux
	 */
	public boolean estRemplie() {
		return this.nbCasesVides == 0;
	}
		   
	/** verifie si une colonne contient 4 cases de la meme couleur a  partir d'une ligne passe en parametre
	  * qui determinera la couleur a  verifierr
	  * @param colonne : colonne a  examiner (+1)
	  * @param ligne : ligne ou se trouve le pion (0)
	  * @return vrai si la colonne et gagnante
	  */
	public boolean colonneGagnante(int colonne, int position) {
		return compter(colonne, position, 0, 1, grille[colonne][position].getCouleur())
				+ compter(colonne, position, 0, -1, grille[colonne][position].getCouleur()) > 4;
	}
	
	/**
	 * @param grille
	 * @param ligne : ligne Ã  examiner (0)
	 * @param colonne ou le pion se trouve (+1)
	 * @param couleur du pion
	 * @return true si ligne (horizontalement, vers la droite) gagnante
	 */
	public boolean ligneGagnante(int colonne, int position) {
		return compter(colonne, position, 1, 0, grille[colonne][position].getCouleur())
				+ compter(colonne, position, -1, 0, grille[colonne][position].getCouleur()) > 4;
	}

	/**
	 * @param grille
	 * @param ligne -1
	 * @param colonne +1
	 * @param couleur du pion
	 * @return true si diagonale vers haut et la droite est gagnante 
	 */
	public boolean diagonaleHautDroiteGagnante(int colonne, int position) {
		return compter(colonne, position, 1, 1, grille[colonne][position].getCouleur())
				+ compter(colonne, position, -1, -1, grille[colonne][position].getCouleur()) > 4;
	}
	
	/**
	 * @param grille
	 * @param ligne +1
	 * @param colonne +1
	 * @param couleur
	 * @return true si diagonale vers bas et la droite est gagnante 
	 */
	public boolean diagonaleBasDroiteGagnante(int colonne, int position) {
		return compter(colonne, position, -1, 1, grille[colonne][position].getCouleur())
				+ compter(colonne, position, 1, -1, grille[colonne][position].getCouleur()) > 4;
	}
	 
	 /**
	  * Methode permettant de determner si la ligne gagnante est trouvee 
	  * @param ligne a laquelle le pion est / ou pas
	  * @param colonne a laquelle le pion est/ ou pas
	  * @return true si une des trois methodes est true
	  */
	 
	 public boolean gagnantPartie(int ligne, int colonne) {
		 if(diagonaleHautDroiteGagnante(ligne, colonne)
			|| diagonaleBasDroiteGagnante(ligne, colonne) 
			|| ligneGagnante(ligne, colonne) 
			|| colonneGagnante(ligne, colonne)) {
			 return true;
		 }return false;
	 }
}