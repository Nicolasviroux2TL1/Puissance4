package test;

import model.P4Model;
import controller.P4Controller;


/**
 * classe principale qui va afficher la fenetre
 * @author Nicolas Viroux & Filipp Shatskiy 2TL1
 * 2TL1 
 * Projet Puissance 4
 */
public class Principale {

	@SuppressWarnings("unused") // L'annotation suppressWarnings est uniquement utilisee pour donner des informations
								//complementaires au compilateur et ainsi eviter de signaler au developpeur des choses dont il connait les risques.
	
	public static void main(String[] args) {

		
		P4Model model = new P4Model("joueur1", "joueur2");
		P4Controller controller = new P4Controller(model);
	}
}