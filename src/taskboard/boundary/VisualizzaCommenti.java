package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.control.CommentoControl;
import taskboard.entity.Attivita;
import taskboard.entity.Commento;

public class VisualizzaCommenti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCommento;

	public VisualizzaCommenti(String matricola, Attivita attivita) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCommenti = new JLabel("Commenti");
		lblCommenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommenti.setFont(new Font("Lao MN", Font.PLAIN, 20));
		contentPane.add(lblCommenti, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JList<Commento> listCommenti = new JList<>();
		scrollPane.setViewportView(listCommenti);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		textFieldCommento = new JTextField();
		panel.add(textFieldCommento, BorderLayout.NORTH);
		textFieldCommento.setColumns(10);
		
		JButton btnInvia = new JButton("Invia");
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CommentoControl control = new CommentoControl();
					control.creaCommento(textFieldCommento.getText(), attivita.getId(), matricola);
					textFieldCommento.setText("");
					List<Commento> commenti = control.getCommenti(attivita.getId());
					listCommenti.setListData(commenti.toArray(new Commento[0]));
				}catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(VisualizzaCommenti.this, ex.getMessage(), "Dati invalidi", JOptionPane.WARNING_MESSAGE);
				}catch (SQLException ex) {
					JOptionPane.showMessageDialog(VisualizzaCommenti.this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnInvia, BorderLayout.EAST);

		try {
			CommentoControl control = new CommentoControl();
			List<Commento> commenti = control.getCommenti(attivita.getId());
			listCommenti.setListData(commenti.toArray(new Commento[0]));
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),"Errore nel caricamento dei commenti", JOptionPane.ERROR_MESSAGE);
		}
	}

}
