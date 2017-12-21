package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import view.Deconnection;

import controller.P4Controller;

public class Serveur extends ServerSocket  implements Runnable   {
	private Socket client ;
	private ObjectInputStream input;
	private ObjectOutputStream output ;
	private P4Controller controller;
	private volatile boolean running = true; // volatile le rend visible a  tous les threads
	public static final int numPort = 2345;

	/**
	 * Constructeur de la classe Serveur
	 * @param controller 	
	 * @throws IOException exception rejetee par le constructeur Serveur
	 */
	public Serveur(P4Controller controller) throws IOException{
		super(numPort);
		this.controller = controller;
		Thread t = new Thread(this);
		t.start(); 
	}

	/**
	 * Ecrire le message
	 * @param colonne message du serveur dans le chat
	 * @throws IOException
	 */
	public void writeServ(String message) throws IOException {
		output.writeObject(message);
	}

	/**
	 * lecture du serveur 
	 * @return message recu
	 */
	public String readServ() {
		 String message = "";
		 try {
			 message =(String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * le thread est pret a  travailler en appelant start()
	 */
	public void run(){ //instancier socket
		int col = 0;
		try {
			client = this.accept();
			input = new ObjectInputStream(client.getInputStream());
			output = new ObjectOutputStream(client.getOutputStream());
			controller.setConnectOK(true);
			while(!this.isClosed() && running){
				String[] read = readServ().split("/");
				if(read[0].compareToIgnoreCase("col")==0){
					col = Integer.parseInt(read[1]);
					if(col == 7){
						running = false;
						controller.setConnectOK(false);
						controller.reinitialiser();
						Deconnection deco= new Deconnection(controller);
						deco.setVisible(true);
						this.close();
					}else 
						controller.jouer(col);
				} else {
					controller.writeChat(read[1],"Client");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}