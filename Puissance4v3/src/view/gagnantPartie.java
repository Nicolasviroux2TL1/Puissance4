package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.P4Controller;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gagnantPartie extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
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
		JLabel label = new JLabel();
		label.setBounds(75, 18, 128, 16);
		contentPane.add(label);
		if (gagnantPartie == "Match nul!") {
			label.setText("Match nul!");
		} else {
			label.setText("Le joueur " + joueur + " a gagne!");
		}

		JButton stopPartie = new JButton("Arreter");
		stopPartie.addActionListener(this);
		stopPartie.setBounds(19, 46, 117, 29);
		contentPane.add(stopPartie);

		JButton rejouer = new JButton("Rejouer");
		rejouer.addActionListener(this);
		rejouer.setBounds(132, 46, 117, 29);
		contentPane.add(rejouer);

		this.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
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