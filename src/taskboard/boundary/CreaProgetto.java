package taskboard.boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import taskboard.control.ProgettoControl;

import java.awt.GridBagLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class CreaProgetto extends JFrame {

	private JPanel contentPane;

	public CreaProgetto(String matricola) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblTitolo = new JLabel("Crea Progetto");
		panel.add(lblTitolo);
		lblTitolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnCrea = new JButton("Crea");
		panel_1.add(btnCrea);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNome = new JLabel("Nome");
		panel_2.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextArea Nome = new JTextArea();
		panel_2.add(Nome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		panel_2.add(lblDescrizione);
		lblDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		JTextArea Descrizione = new JTextArea();
		scrollPane.setViewportView(Descrizione);
		Descrizione.setLineWrap(true);
		Descrizione.setWrapStyleWord(true);
		btnCrea.addActionListener(e -> {
    		String nome = Nome.getText();
    		String desc = Descrizione.getText();
    		
    		if(nome.isEmpty() || desc.isEmpty()) {
    			JOptionPane.showMessageDialog(this, "Compila tutti i campi","Errore di login",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		
    		try {
    			ProgettoControl progettoControl = new ProgettoControl();
				progettoControl.creaProgetto(matricola, nome, desc);
				 
    		} catch (SQLException ex) {
    			JOptionPane.showConfirmDialog(this, ex,"Errore di login",JOptionPane.ERROR_MESSAGE);
    			ex.printStackTrace();
    		}
    });

	}
}
