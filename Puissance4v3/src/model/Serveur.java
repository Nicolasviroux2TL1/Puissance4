package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.P4Controller;

public class Serveur extends ServerSocket  implements Runnable   {
	private Socket client ;
	private ObjectInputStream input;
	private ObjectOutputStream output ;
	private P4Controller controller;
	private volatile boolean running = true;
	public static final int numPort = 4;

	public Serveur(P4Controller controller) throws IOException{
		super(numPort);
		this.controller = controller;
		Thread t = new Thread(this);
		t.start(); 
	}


	public void writeServ(String colonne) throws IOException {
		output.writeObject(colonne);
	}

	public String readServ() {
		 String Colrecu = "";
		 try {
			Colrecu =(String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return Colrecu;
	}

	public void run(){
		int col = 0;
		try {
			client = this.accept();
			input = new ObjectInputStream(client.getInputStream());
			output = new ObjectOutputStream(client.getOutputStream());
			controller.setConnectOK(true);
			while(!this.isClosed() && running){
				String[] read = readServ().split("/");
				if(read[0].compareToIgnoreCase("Col")==0){
					col = Integer.parseInt(read[1]);
					if(col == 7){
						running = false;
						controller.setConnectOK(false);
						controller.reinitialiser();
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
