package taskboard.boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JButton;

public class VisualizzaProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VisualizzaProgetto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOME PROGETTO");
		lblNewLabel.setBounds(5, 0, 440, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 33, 439, 36);
		contentPane.add(scrollPane);
		
		JTextPane txtpnDescDescDesc = new JTextPane();
		txtpnDescDescDesc.setText("DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC ");
		scrollPane.setViewportView(txtpnDescDescDesc);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 81, 439, 137);
		contentPane.add(scrollPane_1);
		
		JList listaAttivita = new JList();
		scrollPane_1.setViewportView(listaAttivita);
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.setBounds(5, 6, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.setBounds(5, 237, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Aggiungi\nattività");
		btnNewButton_2.setBounds(292, 230, 153, 36);
		contentPane.add(btnNewButton_2);
		
		JButton btnReport = new JButton("REPORT");
		btnReport.setBounds(327, 6, 117, 29);
		contentPane.add(btnReport);

	}
}
