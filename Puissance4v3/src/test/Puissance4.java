package test;
import java.util.Scanner;
/**
 * Classe principale
 */
class Puissance4 {
	protected static Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {
    System.out.println("Entrez votre nom: ");
    String nom = scanner.nextLine();
    System.out.println("--");

    Partie p = new Partie(new Ordinateur(Jeu.JAUNE), new Humain(nom, Jeu.ROUGE));
    p.joue();
  }
}

class Partie {

  private Joueur[] joueurs = new Joueur[2];
  private Jeu jeu;

  public Partie(Joueur joueur1, Joueur joueur2) {
    joueurs[0] = joueur1;
    joueurs[1] = joueur2;
    jeu = new Jeu();
  }

  public void joue() {
    int vainqueur = -1;
    int cJoueur = 0;

    while (vainqueur==-1 && !jeu.estPlein()) {
      joueurs[cJoueur].joue(jeu);
      if (jeu.estPlein()) {
        vainqueur = -1;
      }

      // Si 4 pions sont alignÃ©s, on a un vainqueur
      // (mÃªme si le jeu est plein!)

      if (jeu.cherche4()) {
	vainqueur = cJoueur;
      }

      // On change de joueur pour l'itÃ©ration suivante
      cJoueur++;
      cJoueur %= 2;
    }

    System.out.println("La partie est finie.");
    jeu.afficher();
    if (vainqueur == -1) {
      System.out.println("Match nul.");
    } else {
      System.out.println("Le vainqueur est " + joueurs[vainqueur].getNom());
    }
  }
}

class Jeu {

  public final static int VIDE = 0;
  public final static int JAUNE = 1;
  public final static int ROUGE = 2;

  private int taille;
  private int[][] grille;	// 0 = vide, 1 = joueur JAUNE, 2 = joueur rouge

  public Jeu(int taille) {
    initJeu(taille);
  }

  public Jeu() {
    initJeu(8);
  }

  private void initJeu(int taille) {
    this.taille = taille;
    grille = new int[taille][taille];
    for (int col = 0; col < taille ; col++) {
      for (int row = 0; row < taille; row++) {
        grille[col][row] = VIDE;
      }
    }
  }

  public boolean joueCoup(int col, int joueur) {
    if ((col < 0) || (col >= taille)) {
      return false;
    }

    // Trouve la premiÃ¨re place vide dans la colonne
    for (int ligne = 0; ligne < taille; ligne++) {
      if (grille[col][ligne] == VIDE) {
        grille[col][ligne] = joueur;
        return true;
      }
    }

    // La colonne est pleine
    return false;
  }

  /**
   * Cette mÃ©thode vÃ©rifie toutes les lignes, colonnes et diagonales pour une sÃ©rie de 4 pions
   * de la mÃªme couleur. Si une telle sÃ©rie existe, retourne true.
   * 
   * @return true si le jeu contient 4 pions alignÃ©s
   */
  public boolean cherche4() {
    // VÃ©rifie les horizontales ( - )
    for (int ligne = 0; ligne < taille; ligne++) {
      if (cherche4alignes(0, ligne, 1, 0)) {
        return true;
      }
    }

    // VÃ©rifie les verticales ( Â¦ )
    for (int col = 0; col < taille; col++) {
      if (cherche4alignes(col, 0, 0, 1)) {
        return true;
      }
    }

    // Diagonales (cherche depuis la ligne du bas)
    for (int col = 0; col < taille; col++) {
      // PremiÃ¨re diagonale ( / )
      if (cherche4alignes(col, 0, 1, 1)) {
        return true;
      }
      // DeuxiÃ¨me diagonale ( \ )
      if (cherche4alignes(col, 0, -1, 1)) {
        return true;
      }
    }

    // Diagonales (cherche depuis les colonnes gauches et droites)
    for (int ligne = 0; ligne < taille; ligne++) {
      // PremiÃ¨re diagonale ( / )
      if (cherche4alignes(0, ligne, 1, 1)) {
        return true;
      }
      // DeuxiÃ¨me diagonale ( \ )
      if (cherche4alignes(taille - 1, ligne, -1, 1)) {
        return true;
      }
    }

    // On n'a rien trouvÃ©
    return false;
  }

  /**
   * Cette mÃ©thode cherche 4 pions alignÃ©s sur une ligne. Cette ligne est dÃ©finie par
   * le point de dÃ©part, ou origine de coordonnÃ©es (oCol,oLigne), et par le dÃ©placement
   * delta (dCol,dLigne). En utilisant des valeurs appropriÃ©es pour dCol et dLigne
   * on peut vÃ©rifier toutes les directions:
   * - horizontale:    dCol = 0, dLigne = 1
   * - vÃ©rticale:      dCol = 1, dLigne = 0
   * - 1Ã¨re diagonale: dCol = 1, dLigne = 1
   * - 2Ã¨me diagonale: dCol = 1, dLigne = -1
   *
   * @param oCol   Colonne d'origine de la recherche
   * @param oLigne Ligne d'origine de la recherche
   * @param dCol   Delta de dÃ©placement sur une colonne
   * @param dLigne Delta de dÃ©placement sur une ligne
   * @return true si on trouve un alignement
   */
  private boolean cherche4alignes(int oCol, int oLigne, int dCol, int dLigne) {
    int couleur = VIDE;
    int compteur = 0;

    int curCol = oCol;
    int curRow = oLigne;

    while ((curCol >= 0) && (curCol < taille) && (curRow >= 0) && (curRow < taille)) {
      if (grille[curRow][curCol] != couleur) {
        // Si la couleur change, on rÃ©initialise le compteur
        couleur = grille[curRow][curCol];
        compteur = 1;
      } else {
        // Sinon on l'incrÃ©mente
        compteur++;
      }

      // On sort lorsque le compteur atteint 4
      if ((couleur != VIDE) && (compteur == 4)) {
        return true;
        
      }

      // On passe Ã  l'itÃ©ration suivante
      curCol += dCol;
      curRow += dLigne;
    }

    // Aucun alignement n'a Ã©tÃ© trouvÃ©
    return false;
  }

  /**
   * VÃ©rifie s'il est encore possible de placer des pions
   * @return true si le tableau est plein
   */

  
  public boolean estPlein() {
    // On cherche une case vide. S'il n'y en a aucune, le tableau est plein
    for (int col = 0; col < taille; col++) {
      for (int ligne = 0; ligne < taille; ligne++) {
        if (grille[col][ligne] == VIDE) {
          return false;
        }
      }
    }

    return true;
  }

  public int getTaille() {
    return taille;
  }

  public void afficher() {
    for (int ligne = taille - 1; ligne >= 0; --ligne) {
      for (int col = 0; col < taille; col++) {
        switch (grille[col][ligne]) {
        case VIDE:
          System.out.print(' ');
          break;
        case ROUGE:
          System.out.print('R');
          break;
        case JAUNE:
          System.out.print('J');
          break;
        }
      }
      System.out.println();
    }

    for (int i = 0; i < taille; ++i) {
      System.out.print('-');
    }
    System.out.println();
    for (int i = 1; i <= taille; ++i) {
      System.out.print(i);
    }
    System.out.println();
  }
}

class Joueur {
  private String nom;
  private int couleur;

  public Joueur(String nom, int couleur) {
    this.nom = nom;
    this.couleur = couleur;
  }

  public String getNom() {
    return nom;
  }

  public int getCouleur() {
    return couleur;
  }

  /**
   * Cette mÃ©thode joue un coup avec le tableau reçu en paramÃ¨tre.
   * La mÃ©thode est vide car les sous-classes doivent l'implÃ©menter.
   * (Vous verrez prochainement comment gÃ©rer ce genre de cas plus proprement)
   * @param jeu Le Jeu avec lequel jouer.
   */
  public void joue(Jeu jeu) {}

}

class Humain extends Joueur {

  public Humain(String nom,int couleur) {
    super(nom, couleur);
  }

  public void joue(Jeu jeu) {
    jeu.afficher();

    boolean valide;
    do {
      System.out.println("Joueur " + this.getNom() + ", entrez un numero de colonne" +
               "  (entre 1 et " + jeu.getTaille() + ") : ");

      int col = Puissance4.scanner.nextInt(); // on pourrait faire ici la validation de la lecture
      col--;                		 	      // remet entre 0 et taille-1 (indice Ã  la Java)

      valide = jeu.joueCoup(col, this.getCouleur());
      if (valide == false) {
        System.out.println("-> Coup NON valide.");
      }
    } while (valide == false);
  }
}


class Ordinateur extends Joueur {

  public Ordinateur(int couleur) {
    super("Le programme", couleur);
  }

  public void joue(Jeu jeu) {
    for (int col = 0; col < jeu.getTaille(); col++) {
      if (jeu.joueCoup(col, this.getCouleur())) {
        System.out.println(this.getNom() + " a joue en " + (col + 1));
        return;
      }
    }
  }
}