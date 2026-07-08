package taskboard.boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CreaProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaProgetto frame = new CreaProgetto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreaProgetto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Crea Progetto");
		lblTitolo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setBounds(6, 6, 438, 27);
		contentPane.add(lblTitolo);
		
		JTextArea Nome = new JTextArea();
		Nome.setBounds(176, 73, 117, 16);
		contentPane.add(Nome);
		
		JTextArea Descrizione = new JTextArea();
		Descrizione.setBounds(6, 126, 438, 99);
		contentPane.add(Descrizione);
		
		JButton btnCrea = new JButton("Crea");
		btnCrea.setBounds(327, 237, 117, 29);
		contentPane.add(btnCrea);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(6, 237, 117, 29);
		contentPane.add(btnIndietro);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(16, 45, 428, 16);
		contentPane.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrizione.setBounds(6, 101, 440, 16);
		contentPane.add(lblDescrizione);

	}
}
