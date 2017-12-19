package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.P4Controller;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gagnantPartie extends JFrame implements ActionListener {

	private JPanel contentPane;
	private P4Controller controller;

	/**
	 * Create the frame.
	 */
	public gagnantPartie(int joueur, P4Controller controller, String gagnantPartie) {
		this.controller = controller;
		setTitle(gagnantPartie);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 119);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblLeJoueurA = new JLabel();
		lblLeJoueurA.setBounds(75, 18, 128, 16);
		contentPane.add(lblLeJoueurA);
		if (gagnantPartie == "Match nul!") {
			lblLeJoueurA.setText("Match nul!");
		} else {
			lblLeJoueurA.setText("Le joueur " + joueur + " a gagnè!");
		}

		JButton btnArrter = new JButton("Arrêter");
		btnArrter.addActionListener(this);
		btnArrter.setBounds(19, 46, 117, 29);
		contentPane.add(btnArrter);

		JButton btnRejouer = new JButton("Rejouer");
		btnRejouer.addActionListener(this);
		btnRejouer.setBounds(132, 46, 117, 29);
		contentPane.add(btnRejouer);

		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Rejouer") {
			controller.reinitialiser();
			this.setVisible(false);
			controller.activerBoutons();
			dispose();
		} else {
			controller.fermer();
			this.setVisible(false);
			dispose();
			System.exit(0);
		}
	}
}
