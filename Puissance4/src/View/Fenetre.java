package View;

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
import Controller.P4Controller;


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
import java.awt.SystemColor;

public class Fenetre extends JFrame implements ActionListener, WindowListener {

	private static final int nbColonne = 7;
	private static final int nbLigne = 6;
	private JPanel contentPane;
	private JButton[] tabJButton = new JButton[nbColonne];
	private JLabel[][] tabJLabel = new JLabel[nbColonne][nbLigne + 1];
	private GridBagConstraints gbc;
	private JTextField TextEnvoi;
	private String chat ="";
	private JTextArea textArea;
	private int joueur;
	private P4Controller controller;


	public Fenetre(P4Controller controller){
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.addWindowListener(this);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Puissance 4",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

	
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chat :",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
					.addContainerGap())
		);

		TextEnvoi = new JTextField();
		TextEnvoi.setColumns(10);

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

	public void afficherBoutons(boolean choix) {
		for (int i = 0; i < 7; i++) {
			tabJButton[i].setEnabled(choix);
		}
	}

	public void reinitialiser() {
		for (int i = 0; i < 7; i++) {
			for (int a = 1; a < 7; a++) {
				tabJLabel[i][a].setIcon(new ImageIcon("images/grille.jpg"));
			}
		}
	}
	
	public void changerTour(int joueur) {
		this.joueur = joueur;
	}
	
	public P4Controller getControl(){
		return controller;
	}
	
	public void placerPion(int x, int y, char couleur) {
		if (y < 1) 
			JOptionPane.showMessageDialog(null, "Cette colonne est remplie");
		else 
			tabJLabel[x][y].setIcon(new ImageIcon("images/" + couleur + ".jpg"));
		
	}


	public void windowClosing (WindowEvent e) {
		
    }

	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
