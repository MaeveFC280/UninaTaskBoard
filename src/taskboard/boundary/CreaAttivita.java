package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import taskboard.control.AttivitaControl;
import taskboard.entity.Progetto;
import taskboard.entity.Studente;

public class CreaAttivita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldDesc;

	public CreaAttivita(Progetto progetto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel Label = new JLabel("Crea Atività");
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		Label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(Label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 10, 10));
		
		JLabel lbNome = new JLabel("Nome");
		panel.add(lbNome);
		
		textFieldNome = new JTextField();
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblDesc = new JLabel("Descrizione");
		panel.add(lblDesc);
		
		textFieldDesc = new JTextField();
		panel.add(textFieldDesc);
		textFieldDesc.setColumns(10);
		
		JLabel lbTipo = new JLabel("Tipo");
		panel.add(lbTipo);
		
		JComboBox comboBoxTipo = new JComboBox();
		panel.add(comboBoxTipo);
		
		JLabel lbScadenza = new JLabel("Scadenza");
		panel.add(lbScadenza);
		
		JDateChooser dateChooser = new JDateChooser();
		panel.add(dateChooser);
		
		JLabel lbResp = new JLabel("Responsabile/i");
		panel.add(lbResp);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JList<Studente> list = new JList<>();
		scrollPane.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnNewButton = new JButton("Crea");
		panel_1.add(btnNewButton);
		
		btnNewButton.addActionListener(e -> {
			String nome = textFieldNome.getText().trim();
			String descrizione = textFieldDesc.getText().trim();
			String tipo = (String) comboBoxTipo.getSelectedItem();
			Date d = (Date) dateChooser.getDate();
			List<Studente> responsabili = list.getSelectedValuesList();
			try {
				AttivitaControl AttControl = new AttivitaControl();
				AttControl.creaAttivita(nome, descrizione, d, tipo, progetto.getCodiceInvito(), responsabili);
				
			}catch (IllegalArgumentException ex){
				JOptionPane.showMessageDialog(this, ex.getMessage(),"Dati non validi", JOptionPane.ERROR_MESSAGE);
				
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(),"Errore nella creazione dell'attività", JOptionPane.ERROR_MESSAGE);
			}
            
        });

	}

}
