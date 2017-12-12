package View;

import Controller.P4Controller;
import model.P4Model;

/**
 * classe principale qui va afficher la fenetre
 * @author Nicolas Viroux & Filipp Shatskiy 2TL1
 *
 */
public class Principale {

	public static void main(String[] args) {
		
		P4Model model = new P4Model("joueur1", "joueur2");
		P4Controller control = new P4Controller(model);
		
		
		
	}

}
