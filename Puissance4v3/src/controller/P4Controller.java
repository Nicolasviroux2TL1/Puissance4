package controller;

import java.io.IOException;

import model.Client;
import model.P4Model;
import model.Serveur;
import view.Fenetre;
import view.FenetreJoueurs;
import view.gagnantPartie;

public class P4Controller{
	private Fenetre view;
	private P4Model model;
	private FenetreJoueurs menu;
	private static int tourjoueur;
	private char couleur;
	private boolean connection = false;
	private String user;
	private Serveur serv;
	private Client client;

	public P4Controller(P4Model model) {
		this.model = model;
		this.view = new Fenetre(this);
		view.setVisible(false); 		// pr pas afficher la Fenetre en même temps
		this.menu = new FenetreJoueurs(this);
		menu.setVisible(true);
	}
	public void jouer(int colonne) throws IOException{
		int ligne = model.getGrille().prochaineCaseLibre(colonne);
		char couleur = model.getJoueurQuiJoue().getCouleur();
		view.placerPion(colonne, 6 - ligne, couleur);
		
		if(model.getGrille().coupJoueur(colonne, ligne, couleur)){
			
			if(model.getGrille().gagnantPartie(colonne, ligne)) {
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
	//------------------------------------------------
	public void createUser(String user, char couleur,String ip){
		this.couleur = couleur;
		view.setVisible(true);
		view.afficherBoutons(false);	
		this.user = user;
		if(user.compareToIgnoreCase("Client")==0){
			try {
				 client = new Client(this,ip);
				 tourjoueur = 1; // tour pour lequel le client pourra jouer
				 connection = true; 
			}  catch (IOException e) {
				e.printStackTrace();
			}
		}else if(user.compareToIgnoreCase("Serv")==0){
			try {
				model.setCouleurJoueur(couleur,user); // attribue la couleur au serveur
				serv = new Serveur(this);
				 view.setChat("En attente du Client pour pouvoir joueur !", "Systeme");
				tourjoueur = 0; // tour pour lequel le serveur pourra jouer
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
		
		public void netWrite(String colonne) throws IOException{
			if(user.compareToIgnoreCase("Serv")==0)
				serv.writeServ(colonne);
			else 
				client.writeClient(colonne);		
		}
	
		public void activerBoutons() {
			if(user.compareToIgnoreCase("Client")== 0)
				view.afficherBoutons(false);
			else 
				view.afficherBoutons(true);
		}
	
		//Ecrire dans le chat
		public void writeChat(String f,String user) {
			if(user.compareToIgnoreCase("inconnu")==0)
				user = this.user;
			view.setChat(f,user);
		}
		public void userTour(){
					if(!(P4Model.getTour() == tourjoueur))
						view.afficherBoutons(false); // pour pas pouvoir joueur 2 d'affilé
					else 
						view.afficherBoutons(true);
		}
		
		public void setConnectOK(boolean connect) throws IOException{ //seul le serveur l'utilise 
			connection = connect;
			if(connect == true){
				view.afficherBoutons(true);
				view.setChat("Connection ! ","Systeme");
				if(couleur == 'r')
					netWrite("Systeme/j"); // envoi de la couleur au client
				else
					netWrite("Systeme/r");				
			}
		}
		
		public void reinitialiser() {		
			model.reinitialiser();
			view.reinitialiser();
				
		}

		public void fermer() {
			view.dispose();
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
		
		public boolean getConnectOK(){
		return connection;
		}
}
