package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.control.ProgettoControl;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EntraInProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private ProgettoControl control;

	public EntraInProgetto(String matricola) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Entra In Progetto");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodice.setBounds(0, 0, 144, 237);
		panel.add(lblCodice);
		
		textField = new JTextField();
		textField.setBounds(156, 105, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("Invio");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codice = textField.getText().trim();
				if(codice.isEmpty()) {
					JOptionPane.showMessageDialog(EntraInProgetto.this, "Inserisci un codice");
				}
				
				try {
					ProgettoControl control = new ProgettoControl();
					boolean entrato = control.entraInProgetto(matricola, codice);
					if(entrato) {
						JOptionPane.showMessageDialog(EntraInProgetto.this, "Sei entrato nel progetto");
						Homepage home = new Homepage(matricola);
						home.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(EntraInProgetto.this, "Il codice non è valido");
					}
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(EntraInProgetto.this, "C'è stato un errore: "+ ex.getMessage(), "Errore",JOptionPane.ERROR_MESSAGE);
					Homepage home = new Homepage(matricola);
					home.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(317, 208, 117, 29);
		panel.add(btnNewButton_1);

	}
}
