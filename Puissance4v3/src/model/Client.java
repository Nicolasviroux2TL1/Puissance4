package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.P4Controller;

public class Client extends Socket  implements Runnable  {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private P4Controller controller;
	private String recevoir ;
	private volatile boolean running = true;
	public static final int numPort = 4;

	public Client(P4Controller controller, String ip) throws UnknownHostException, IOException {
		super(ip,numPort);
		this.controller = controller;
		output = new ObjectOutputStream(this.getOutputStream());
		input = new ObjectInputStream(this.getInputStream());
		Thread t = new Thread(this);
		t.start(); 
	}

	private String readClient() {
		try {
			recevoir = (String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return recevoir;
	}
	
	public void writeClient(String colonne) throws IOException{
		output.writeObject(colonne);
	}
	
	public void run() {
		int col = 0;
		try {
				while(!this.isClosed() && running){
					String[] read = readClient().split("/");
					if(read[0].compareToIgnoreCase("Col")== 0){
						col = Integer.parseInt(read[1]);
						if(col == 7){
							running = false;
							controller.setConnectOK(false);
							controller.reinitialiser();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}