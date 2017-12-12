package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Controller.P4Controller;

//import Controller.Puissance4Controller;


public class FenetreJoueurs extends JFrame {
		

	private JLabel label1, label2;
	private JButton Suivant;
	private JRadioButton jaune, rouge, client, serveur;
	private JPanel contentPane;
	private char couleur = 'v';
	
	public FenetreJoueurs(P4Controller controller) {
		super("Puissance 4 ");
		//this.controller = controller ; 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JTextPane txtpnPuissance = new JTextPane();
		txtpnPuissance.setText("Projet réalisé par Viroux & Shatskiy");
		panel.add(txtpnPuissance);
		txtpnPuissance.setEditable(false);
		
		
		//initialisation de JLabel 1
		JLabel label1 = new JLabel("Couleur :");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 5, 5);
		gbc_label1.gridx = 1;
		gbc_label1.gridy = 2;
		contentPane.add(label1, gbc_label1);
		
		//initialisation des RadioBoutons
		jaune = new JRadioButton("Jaune");
		jaune.addActionListener(null);

		GridBagConstraints gbc_jaune = new GridBagConstraints();
		gbc_jaune.insets = new Insets(0, 0, 5, 5);
		gbc_jaune.gridx = 3;
		gbc_jaune.gridy = 2;
		contentPane.add(jaune, gbc_jaune);

		rouge = new JRadioButton("Rouge");
	//	rouge.addActionListener(this);
		
		GridBagConstraints gbc_rouge = new GridBagConstraints();
		gbc_rouge.insets = new Insets(0, 0, 5, 0);
		gbc_rouge.gridx = 4;
		gbc_rouge.gridy = 2;
		contentPane.add(rouge, gbc_rouge);
		
		//Regroupement des RadioButtons
		ButtonGroup group1 = new ButtonGroup();
		group1.add(jaune);
		group1.add(rouge);
		
		JLabel lblVoustes = new JLabel("Vous etes :");
		GridBagConstraints gbc_lblVoustes = new GridBagConstraints();
		gbc_lblVoustes.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoustes.gridx = 1;
		gbc_lblVoustes.gridy = 4;
		contentPane.add(lblVoustes, gbc_lblVoustes);
		client = new JRadioButton("Client");

		GridBagConstraints gbc_Client = new GridBagConstraints();
		gbc_Client.insets = new Insets(0, 0, 5, 5);
		gbc_Client.gridx = 3;
		gbc_Client.gridy = 4;
		contentPane.add(client, gbc_Client);
		
		serveur = new JRadioButton("Serveur");
		serveur.addActionListener(null);
		
		GridBagConstraints gbc_serveur = new GridBagConstraints();
		gbc_serveur.insets = new Insets(0, 0, 5, 0);
		gbc_serveur.gridx = 4;
		gbc_serveur.gridy = 4;
		contentPane.add(serveur, gbc_serveur);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(client);
		group2.add(serveur);
	

	
		
		//Ecouteur
				ActionListener aL = new ActionListener() {
					public void actionPerformed(ActionEvent event)
					{
						//on cache la fenetre courante
						setVisible(false);
						
						//Ouverture de la fenetre principale du jeu 
						
						
						switch(event.getActionCommand()) {
						case "Suivant" : getErreur();	
						break;
						case "Jaune": rouge.setSelected(false);
						couleur = 'j';
						break;
						case "Rouge": jaune.setSelected(false);
						couleur = 'r';
						break;
					
						}		
						JFrame fenetrePrincipale = new Fenetre(controller);
						fenetrePrincipale.setVisible(true);
					}
				};
				
		
			//init Button Suivant
		Suivant = new JButton("Suivant");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.insets = new Insets(0, 0, 5, 5);
		gbc_btnOK.gridx = 3;
		gbc_btnOK.gridy = 5;
		contentPane.add(Suivant, gbc_btnOK);
		Suivant.addActionListener(aL);
					
	}

	//private void proprFenetreJoueurs(){
		

public void getErreur(){
	if(serveur.isSelected() &&( jaune.isSelected() || rouge.isSelected()) || client.isSelected()){
		//controller.createUser(user,couleur,textField.getText());
		this.setVisible(false);	
	} else
		JOptionPane.showMessageDialog(this,"completez les champs !","attention", JOptionPane.WARNING_MESSAGE);
	}
}

