package view;

import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import controller.P4Controller;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class Fenetre extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private static final int nbColonne = 7;
	private static final int nbLigne = 6;
	private JPanel contentPane;
	private JButton[] tabJButton = new JButton[nbColonne];
	private JLabel[][] tabJLabel = new JLabel[nbColonne][nbLigne + 1];
	private GridBagConstraints gbc;
	private JTextField TextEnvoi;
	private JTextArea textArea;
	private String chat ="";
	private int joueur;
	private P4Controller controller;


	 // Constructeur

	public Fenetre(P4Controller controller){
		setResizable(false);
		setTitle("Puissance 4"); //titre de la fenetre du jeu
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermeture de la fenetre au moment du click sur la crois
		setBounds(100, 100, 1220, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.addWindowListener(this);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 128));

		//chat + placement + style
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(65, 105, 225));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chat :", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 614, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		TextEnvoi = new JTextField();
		TextEnvoi.setForeground(new Color(0, 0, 0));
		TextEnvoi.setBackground(new Color(220, 220, 220));
		TextEnvoi.setColumns(10);
		//bouton "envoyer"
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setOpaque(false);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(TextEnvoi, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addGap(27))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(TextEnvoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		textArea = new JTextArea(20,30);
		textArea.setForeground(new Color(0, 191, 255));
		textArea.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(textArea);
		

		textArea.setDisabledTextColor(Color.BLACK);
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setLineWrap(true);
		panel_1.setLayout(gl_panel_1);
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		contentPane.setLayout(gl_contentPane);
		gbc = new GridBagConstraints();
		for (int i = 0; i < 7; i++) {
			tabJButton[i] = new JButton("" + i);
			tabJButton[i].addActionListener(this);
			gbc.gridx = i;
			gbc.gridy = 0;
			panel.add(tabJButton[i], gbc);
			for (int a = 1; a < 7; a++) {
				tabJLabel[i][a] = new JLabel(new ImageIcon("images/grille.jpg"));
				gbc.gridx = i;
				gbc.gridy = a;
				panel.add(tabJLabel[i][a], gbc);
			}
		}
	}
	
	/*
	 * actions lors de l'envoie du texte dans le chat
	 */
	public void actionPerformed(ActionEvent event) {
		int a;
		if (event.getActionCommand() == "Reinitialiser") {
			controller.reinitialiser();
		} else if (event.getActionCommand() == "Envoyer") {
				try {
					controller.netWrite("Chat/" + TextEnvoi.getText());
					controller.writeChat(TextEnvoi.getText(),"inconnu");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		} else {
				try {
					a = Integer.parseInt(event.getActionCommand());
					controller.jouer(a);
					controller.netWrite("col/" + a); 
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		}
		 // affiche les boutons de la grille

	public void afficherBoutons(boolean choix) {
		for (int i = 0; i < 7; i++) {
			tabJButton[i].setEnabled(choix);
		}
	}

	 //messages recus dans le chat commun

	public void setChat(String f, String user) {
		if(user.compareToIgnoreCase("Systeme")==0)
			chat = f + "\n";
		else if(user.compareToIgnoreCase("Serv")==0){
			chat = "joueur "+ 1 +" : " + f + "\n";			
		} else{
			chat = "joueur "+ 2 +" : " + f + "\n";		
		} 
		textArea.append(chat);
		textArea.setCaretPosition(textArea.getText().length()-1);		
	}

	 // placement des pions 

	public void placerPion(int x, int y, char couleur) {
		if (y < 1) 
			JOptionPane.showMessageDialog(null, "Cette colonne est remplie");
		
			tabJLabel[x][y].setIcon(new ImageIcon("images/" + couleur + ".jpg"));
		
	}
	

	//reinitialise la grille en cours

	public void reinitialiser() {
		for (int i = 0; i < 7; i++) {
			for (int a = 1; a < 7; a++) {
				tabJLabel[i][a].setIcon(new ImageIcon("images/grille.jpg"));
			}
		}
	}
	

	// annonce le gagnant

	public void annoncerGagnant(int joueur) {
		gagnantPartie annonce = new gagnantPartie(joueur, controller, "partie terminée!");
		annonce.setAlwaysOnTop(true);
		annonce.setVisible(true);
		afficherBoutons(false);
	}


	public void windowClosing (WindowEvent e) {
		if(controller.getConnectOK())
	           try {
				controller.netWrite("Col/" + 7);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    }
	

	public int getJoueur() {
		return joueur;
	}


	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}
	

	public void changerTour(int joueur) {
		this.setJoueur(joueur);
	}
	

	public P4Controller getControl(){
		return controller;
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}