package taskboard.boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import taskboard.entity.Progetto;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;

public class CreaAttivita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldNome;
	private JLabel lblTipo;
	private JComboBox comboBoxTipo;
	private JLabel lblScadenza;
	private final JPanel panel_1 = new JPanel();
	private JButton btnCrea;
	private JLabel lblDescrizione;
	private JTextField textFieldDescrizione;
	private JDateChooser dateChooser;
	private JLabel lblResponsabili;
	private JList list;

	public CreaAttivita(Progetto progetto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Crea Attività");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 10, 0));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNome);
		
		textFieldNome = new JTextField();
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDescrizione);
		
		textFieldDescrizione = new JTextField();
		panel.add(textFieldDescrizione);
		textFieldDescrizione.setColumns(10);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTipo);
		
		comboBoxTipo = new JComboBox();
		panel.add(comboBoxTipo);
		
		lblScadenza = new JLabel("Scadenza");
		lblScadenza.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblScadenza);
		
		dateChooser = new JDateChooser();
		panel.add(dateChooser);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		dateChooser.setDate(new java.util.Date());
		
		lblResponsabili = new JLabel("Responsabile/i");
		lblResponsabili.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblResponsabili);
		
		list = new JList();
		panel.add(list);
		
		btnCrea = new JButton("Crea");
		panel_1.add(btnCrea);

	}

}
