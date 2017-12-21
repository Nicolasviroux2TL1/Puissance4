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
import javax.swing.JTextPane;
import javax.swing.JButton;

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

	
	public FenetreJoueurs(P4Controller controller) {
		this.controller = controller ; 
		setResizable(false);
		setTitle("Menu");
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
		
		JTextPane text = new JTextPane();
		text.setText("Projet par Viroux Nicolas & Filipp Shatskiy");
		panel.add(text);
		text.setEditable(false);
		
		JLabel couleur = new JLabel("Choisissez votre couleur :");
		GridBagConstraints gbc_couleur = new GridBagConstraints();
		gbc_couleur.insets = new Insets(0, 0, 5, 5);
		gbc_couleur.gridx = 1;
		gbc_couleur.gridy = 2;
		contentPane.add(couleur, gbc_couleur);
		
		btJaune = new JRadioButton("Jaune");
		btJaune.addActionListener(this);
		
		GridBagConstraints gbc_btJaune = new GridBagConstraints();
		gbc_btJaune.insets = new Insets(0, 0, 5, 5);
		gbc_btJaune.gridx = 3;
		gbc_btJaune.gridy = 2;
		contentPane.add(btJaune, gbc_btJaune);
		
		btRouge = new JRadioButton("Rouge");
		btRouge.addActionListener(this);
		
		GridBagConstraints gbc_btRouge = new GridBagConstraints();
		gbc_btRouge.insets = new Insets(0, 0, 5, 0);
		gbc_btRouge.gridx = 4;
		gbc_btRouge.gridy = 2;
		contentPane.add(btRouge, gbc_btRouge);
		
		JLabel choix = new JLabel("Vous etes :");
		GridBagConstraints gbc_choix = new GridBagConstraints();
		gbc_choix.insets = new Insets(0, 0, 5, 5);
		gbc_choix.gridx = 1;
		gbc_choix.gridy = 4;
		contentPane.add(choix, gbc_choix);
		
		btClient = new JRadioButton("Client");
		btClient.addActionListener(this);

		GridBagConstraints gbc_btClient = new GridBagConstraints();
		gbc_btClient.insets = new Insets(0, 0, 5, 5);
		gbc_btClient.gridx = 3;
		gbc_btClient.gridy = 4;
		contentPane.add(btClient, gbc_btClient);
		
		btServ = new JRadioButton("Serveur");
		btServ.addActionListener(this);

		GridBagConstraints gbc_btServ = new GridBagConstraints();
		gbc_btServ.insets = new Insets(0, 0, 5, 0);
		gbc_btServ.gridx = 4;
		gbc_btServ.gridy = 4;
		contentPane.add(btServ, gbc_btServ);
		
		btnSuivant = new JButton("Suivant");
		btnSuivant.addActionListener(this);
		GridBagConstraints gbc_btnSuivant = new GridBagConstraints();
		gbc_btnSuivant.insets = new Insets(0, 0, 5, 5);
		gbc_btnSuivant.gridx = 2;
		gbc_btnSuivant.gridy = 5;
		contentPane.add(btnSuivant, gbc_btnSuivant);
		
		ipServ = new JLabel("Localhost: ");
		ipServ.setEnabled(false);
		GridBagConstraints gbc_ipServ = new GridBagConstraints();
		gbc_ipServ.insets = new Insets(0, 0, 0, 5);
		gbc_ipServ.gridx = 1;
		gbc_ipServ.gridy = 6;
		contentPane.add(ipServ, gbc_ipServ);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 6;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);	
		
	}

	public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
				case "Suivant": getInfo();
				break;
				case "Client": btServ.setSelected(false);
				ipServ.setEnabled(true);
				textField.setText("127.0.0.1"); 
			    btRouge.setEnabled(false);
				btRouge.setSelected(false);
				btJaune.setEnabled(false);
				btJaune.setSelected(false);
				user = "Client";
				break;
				case "Serveur":btClient.setSelected(false);
				ipServ.setEnabled(false);
				textField.setText("127.0.0.1");
				textField.setEnabled(false);
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
	public void getInfo(){
		if(btServ.isSelected() &&( btJaune.isSelected() || btRouge.isSelected()) || btClient.isSelected()){
			controller.createUser(user,couleur,textField.getText());
			this.setVisible(false);	
		} else
			JOptionPane.showMessageDialog(this,"completez les champs !","attention", JOptionPane.WARNING_MESSAGE);
	}
}
