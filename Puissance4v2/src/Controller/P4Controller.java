package Controller;

import java.io.IOException;
import model.P4Model;
import View.gagnantPartie;
import View.FenetreJoueurs;
import View.Fenetre;
import java.awt.event.ActionEvent;

public class P4Controller{
	private Fenetre view;
	private P4Model model;
	private FenetreJoueurs menu;
	private static int tourjoueur;
	private char couleur;
	
	private String user;

	public P4Controller(P4Model model) {
		this.model = model;
		this.view = new Fenetre(this);
		view.setVisible(true); 									//Affiche la 2e f
		this.menu = new FenetreJoueurs(this);
		menu.setVisible(true);
	}
	public void jouer(int colonne) throws IOException{
		int ligne = model.getGrille().prochaineCaseLibre(colonne);
		char couleur = model.getJoueurQuiJoue().getCouleur();
		view.placerPion(colonne, 6 - ligne, couleur);
		if(model.getGrille().coupJoueur(colonne, ligne, couleur)){
			if(model.getGrille().gagnantPartie(colonne, ligne)) {
				partieTermine(P4Model.getTour()+1);
				view.annoncerGagnant(P4Model.getTour()+1);
			}
			else if(!model.getGrille().estRemplie()){
				P4Model.setTour(1-P4Model.getTour());
				view.changerTour(P4Model.getTour()+1);
				userTour();	
			}else{
				gagnantPartie annonce = new gagnantPartie(0, this, "Match nul!");
				annonce.setAlwaysOnTop(true);
				annonce.setVisible(true);
				view.afficherBoutons(false);
			}
		}
	}
	//a completer ------------------------------------------------
	public void createUser(String user, char couleur,String ip){
		this.couleur = couleur;
		view.setVisible(true);
	}
		public void userTour(){
					if(!(model.getTour() == tourjoueur))
						view.afficherBoutons(true);
					else 
						view.afficherBoutons(true);
		}	
		
		public void activerBoutons() {
			if(user.compareToIgnoreCase("Client")== 0)
			view.afficherBoutons(false);
			else 
			view.afficherBoutons(true);
		}
		
		
		
		

		public Fenetre getView() {
			return view;
		}

		public void setView(Fenetre view) {
			this.view = view;
		}

		public P4Model getModel() {
			return model;
		}

		public FenetreJoueurs getMenu() {
			return menu;
		}
		public void setModel(P4Model model) {
			this.model = model;
		}		


	public void partieTermine(int joueur){
			model.aGagne(joueur);
	}

	public void reinitialiser() {		
		model.reinitialiser();
		view.reinitialiser();
			
	}

	public void fermer() {
		view.dispose();
	}
	
	public P4Model Getmodel(){
		return this.model;
	}
}
