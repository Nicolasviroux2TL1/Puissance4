package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import view.Deconnection;
import controller.P4Controller;

public class Client extends Socket  implements Runnable  {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private P4Controller controller;
	private String message ;
	private volatile boolean running = true; //rend visible a tous les threads
	public static final int numPort = 2345;
	
	/**
	 * Constructeur de la classe Client
	 * @param controller
	 * @param local
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Client(P4Controller controller, String local) throws UnknownHostException, IOException {
		super(local,numPort);
		this.controller = controller;
		output = new ObjectOutputStream(this.getOutputStream()); //conversion d'un objet en un tableau d'octets pour pouvoir l'envoyer sur le reseau
		input = new ObjectInputStream(this.getInputStream());    //a travers le socket
		Thread t = new Thread(this);
		t.start(); 
	}
	
	/**
	 * lecture de ce que le client envoie
	 * @return la reponse du client
	 */
	private String readClient() {
		try {
			message = (String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * ecriture du message
	 * @param colonne message envoye
	 * @throws IOException exception rejetee par la methode writeClient
	 */
	public void writeClient(String message) throws IOException{ 
		output.writeObject(message);
	}
	
	/**
	 * le thread est pret a  travailler en appelant start()
	 */
	public void run() {
		int col = 0;
		try {
				while(!this.isClosed() && running){
					String[] read = readClient().split("/");
					if(read[0].compareToIgnoreCase("col")== 0){
						col = Integer.parseInt(read[1]);
						if(col == 7){
							running = false;
							controller.setConnectOK(false);
							controller.reinitialiser();
							Deconnection deco= new Deconnection(controller);
							deco.setVisible(true);
						} else 
							controller.jouer(col);
					}else if(read[0].compareToIgnoreCase("Systeme")==0){
						controller.getModel().setCouleurJoueur(read[1].charAt(0), "Client");
					} else {
						controller.writeChat(read[1],"Serv");
					}
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	}
}