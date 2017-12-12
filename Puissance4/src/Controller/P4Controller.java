package Controller;

import java.io.IOException;
import model.P4Model;
import View.FenetreJoueurs;
import View.Fenetre;

public class P4Controller{
	private Fenetre view;
	private P4Model model;
	private FenetreJoueurs menu;
	private static int tourjoueur;
	private char couleur;

	public P4Controller(P4Model model) {
		this.model = model;
		this.view = new Fenetre(this);
		view.setVisible(false);
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
				//view.annoncerGagnant(P4Model.getTour()+1);
			}
			else if(!model.getGrille().estRemplie()){
				P4Model.setTour(1-P4Model.getTour());
				view.changerTour(P4Model.getTour()+1);
				userTour();	
			}else{
				view.afficherBoutons(false);
			}
		}
	}
		public void userTour(){
					if(!(model.getTour() == tourjoueur))
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
