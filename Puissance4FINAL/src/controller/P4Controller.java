package controller;

import java.io.IOException;

import model.Client;
import model.P4Model;
import model.Serveur;
import view.Fenetre;
import view.FenetreJoueurs;
import view.gagnantPartie;

public class P4Controller{
	private Fenetre view; //fenetre principale
	private P4Model model;
	private FenetreJoueurs menu; //fenetre du menu
	private static int tourjoueur;
	private char couleur;
	private boolean connection = false;
	private String user;
	private Serveur serv;
	private Client client;

	/*
	 * Constructeur affiche / cache les fenetres du jeu
	 * @param model
	 */
	public P4Controller(P4Model model) {
		this.model = model;
		this.view = new Fenetre(this);
		view.setVisible(false); 		// pr pas afficher la Fenetre en meme temps
		this.menu = new FenetreJoueurs(this);
		menu.setVisible(true);
	}
	
	/*
	 * Permet de placer un pion en verifiant avant si la case est vide ou pas
	 * @param colonne verification par colonne 
	 * @throws IOException exception traitee par la methode "jouer"
	 */
	public void jouer(int colonne) throws IOException{
		int ligne = model.getGrille().prochaineCaseLibre(colonne); 
		char couleur = model.getJoueurQuiJoue().getCouleur();
		view.placerPion(colonne, 6 - ligne, couleur);
		//si le joueur place son pion, verification s'il a gagne et si la colonne est remplie
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

	/*
	 * choix de l'utilisateur 
	 * @param user utilisateur qui choisit sa couleur et client | serveur
	 * @param couleur couleur du joueur
	 * @param local 
	 */
	public void createUser(String user, char couleur,String local){
		this.couleur = couleur;
		view.setVisible(true);
		view.afficherBoutons(false);	
		this.user = user;
		if(user.compareToIgnoreCase("Client")==0){
			try {
				 client = new Client(this,local);
				 tourjoueur = 1; // tour pour lequel le client pourra jouer
				 connection = true; 
			}  catch (IOException e) {
				e.printStackTrace();
			}
		}else if(user.compareToIgnoreCase("Serv")==0){
			try {
				model.setCouleurJoueur(couleur,user); // attribue la couleur au serveur
				serv = new Serveur(this);
				 view.setChat("En attente du Client pour pouvoir jouer !", "Systeme");
				tourjoueur = 0; // tour pour lequel le serveur pourra jouer
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
		/*
		 * 
		 * @param colonne
		 * @throws IOException
		 */
		public void netWrite(String colonne) throws IOException{
			if(user.compareToIgnoreCase("Serv")==0)
				serv.writeServ(colonne);
			else 
				client.writeClient(colonne);		
		}
		/*
		 * Active le bouton pour celui a qui est le tour, desactive sinon
		 */
		public void activerBoutons() {
			if(user.compareToIgnoreCase("Client")== 0)
				view.afficherBoutons(false);
			else 
				view.afficherBoutons(true);
		}
	
		/*
		 * Permet d'ecrire dans le chat
		 * @param message envoyer le message
		 * @param user utilisateur qui parle
		 */
		public void writeChat(String message,String user) {
			user = this.user;
			view.setChat(message, user);
		}
		
		/*
		 * affiche/cache boutons a l'utilisateur qui joue/ joue pas
		 */
		public void userTour(){
					if(!(P4Model.getTour() == tourjoueur))
						view.afficherBoutons(false); // pour pas pouvoir joueur 2 d'affile
					else 
						view.afficherBoutons(true);
		}
		/*
		 * Affiche a l'ecran si l'utilisateur est connecte ou pas
		 * @param connect connection 
		 * @throws IOException exception traitee par setConnectOK
		 */
		public void setConnectOK(boolean connect) throws IOException{ //seul le serveur l'utilise 
			connection = connect;
			if(connect == true){
				view.afficherBoutons(true);
				view.setChat("Connection ! ","Systeme");
				view.setChat("Le Serveur commence en 1 ! ","Systeme");
				if(couleur == 'r')
					netWrite("Systeme/j"); // envoi de la couleur au client
				else
					netWrite("Systeme/r");	
			}
		}
		
		/*
		 * reinitialise la grille
		 */
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
