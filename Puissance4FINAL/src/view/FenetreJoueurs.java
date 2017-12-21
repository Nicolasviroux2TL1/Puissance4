package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.P4Controller;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
//ip
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.Font;

public class FenetreJoueurs extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton btServ, btClient, btJaune, btRouge;
	private JLabel ipServ;
	private JButton btnSuivant;
	private char couleur ;
	private String user;
	private P4Controller controller;
    private String adresseIPLocale ;

	/*
	 * constructeur de la classe FenetreJoueurs, fenetre menu du jeu composee par windowsBuilder
	 * @param controller     
	 */
	public FenetreJoueurs(P4Controller controller) {
		this.controller = controller ;
		setTitle("Menu      |      Realise par Nicolas Viroux et Filipp Shatskiy"); //titre de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fenetre se ferme en appuyant sur la croix
		setBounds(100, 100, 500, 280);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
				//bouton Jaune
				btJaune = new JRadioButton("Jaune");
				btJaune.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btJaune.setBackground(new Color(65, 105, 225));
				btJaune.setForeground(new Color(255, 255, 0));
				btJaune.addActionListener(this);
				//style + placement du radio bouton
				JLabel couleur_1 = new JLabel("Choisissez votre couleur :");
				couleur_1.setForeground(new Color(255, 255, 255));
				couleur_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				GridBagConstraints gbc_couleur_1 = new GridBagConstraints();// gestionnaire de placement
				gbc_couleur_1.insets = new Insets(0, 0, 5, 5);
				gbc_couleur_1.gridx = 2;
				gbc_couleur_1.gridy = 1;
				contentPane.add(couleur_1, gbc_couleur_1);
				
				GridBagConstraints gbc_btJaune = new GridBagConstraints();
				gbc_btJaune.insets = new Insets(0, 0, 5, 5);
				gbc_btJaune.gridx = 2;
				gbc_btJaune.gridy = 2;
				contentPane.add(btJaune, gbc_btJaune);
				//bouton Rouge + style
				btRouge = new JRadioButton("Rouge");
				btRouge.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btRouge.setBackground(new Color(65, 105, 225));
				btRouge.setForeground(new Color(255, 0, 0));
				btRouge.addActionListener(this);
				//placement du bouton Rouge
				GridBagConstraints gbc_btRouge = new GridBagConstraints();
				gbc_btRouge.insets = new Insets(0, 0, 5, 5);
				gbc_btRouge.gridx = 4;
				gbc_btRouge.gridy = 2;
				contentPane.add(btRouge, gbc_btRouge);
						
		//recherche de l'adresse ip du pc
		try{
			 InetAddress inetadr = InetAddress.getLocalHost();
			 adresseIPLocale = (String) inetadr.getHostAddress();
		} catch (UnknownHostException e) {
             e.printStackTrace();
      }
				//bouton "Suivant"
				btnSuivant = new JButton("Suivant");
				btnSuivant.addActionListener(this);
						//Bouton "Client" style + placement
						btClient = new JRadioButton("Client");
						btClient.setForeground(new Color(255, 255, 255));
						btClient.setFont(new Font("Tahoma", Font.ITALIC, 13));
						btClient.setBackground(new Color(65, 105, 225));
						btClient.addActionListener(this);
						
								GridBagConstraints gbc_btClient = new GridBagConstraints();
								gbc_btClient.insets = new Insets(0, 0, 5, 5);
								gbc_btClient.gridx = 2;
								gbc_btClient.gridy = 4;
								contentPane.add(btClient, gbc_btClient);
				//Bouton "Serveur" style + placement
				btServ = new JRadioButton("Serveur");
				btServ.setForeground(new Color(255, 255, 255));
				btServ.setFont(new Font("Tahoma", Font.ITALIC, 13));
				btServ.setBackground(new Color(65, 105, 225));
				btServ.addActionListener(this);
				
						GridBagConstraints gbc_btServ = new GridBagConstraints();
						gbc_btServ.insets = new Insets(0, 0, 5, 5);
						gbc_btServ.gridx = 4;
						gbc_btServ.gridy = 4;
						contentPane.add(btServ, gbc_btServ);
				//affichage de l'adresse ip du pc dans le cadre "Localhost"
				ipServ = new JLabel("Localhost :");
				ipServ.setBackground(new Color(255, 255, 255));
				ipServ.setEnabled(false);
				GridBagConstraints gbc_ipServ = new GridBagConstraints();
				gbc_ipServ.insets = new Insets(0, 0, 0, 5);
				gbc_ipServ.gridx = 0;
				gbc_ipServ.gridy = 7;
				contentPane.add(ipServ, gbc_ipServ);
				
				textField = new JTextField();
				textField.setEnabled(false);
				textField.setEditable(false);
					GridBagConstraints gbc_textField = new GridBagConstraints();
					gbc_textField.insets = new Insets(0, 0, 0, 5);
					gbc_textField.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField.gridx = 2;
					gbc_textField.gridy = 7;
					contentPane.add(textField, gbc_textField);
					textField.setColumns(10);	
				GridBagConstraints gbc_btnSuivant = new GridBagConstraints();
				gbc_btnSuivant.gridx = 8;
				gbc_btnSuivant.gridy = 7;
				contentPane.add(btnSuivant, gbc_btnSuivant);
		
	}
	/*
	 * actions au moment des choix
	 */
	public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
				case "Suivant": getInfo();
				break;
				case "Client": btServ.setSelected(false);
				ipServ.setEnabled(true);
				textField.setText(adresseIPLocale); 
			    btRouge.setEnabled(true);
				btRouge.setSelected(false);
				btJaune.setEnabled(true);
				btJaune.setSelected(false);
				user = "Client";
				break;
				case "Serveur":btClient.setSelected(false);
				ipServ.setEnabled(true);
				textField.setText("127.0.0.1");
				textField.setEnabled(true);
				textField.setEditable(false);
				btRouge.setEnabled(true);
				btJaune.setEnabled(true);
				user = "Serv";
				break;
				case "Jaune": btRouge.setSelected(false);
				couleur = 'j';
				break;
				case "Rouge": btJaune.setSelected(false);
				couleur = 'r';
				break;
			}
	}
	/*
	 * message d'erreur si rien n'est selectionne
	 */
	public void getInfo(){
		if(btServ.isSelected() &&( btJaune.isSelected() || btRouge.isSelected()) || btClient.isSelected()){
			controller.createUser(user,couleur,textField.getText());
			this.setVisible(false);	
		} else
			JOptionPane.showMessageDialog(this,"completez les champs !","attention", JOptionPane.WARNING_MESSAGE);
	}
}
