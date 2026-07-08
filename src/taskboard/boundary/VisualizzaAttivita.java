package taskboard.boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;

public class VisualizzaAttivita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VisualizzaAttivita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("NOME ATTIVITà");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 428, 81);
		panel.add(scrollPane);
		
		JTextArea txtrDesc = new JTextArea();
		txtrDesc.setText("DESC");
		scrollPane.setViewportView(txtrDesc);
		
		JLabel lblNewLabel_1 = new JLabel("Scadenza: XX/YY/ZZ");
		lblNewLabel_1.setBounds(26, 99, 134, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Responsabili: A,B,C,...");
		lblNewLabel_2.setBounds(26, 127, 134, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stato: XXXXXX");
		lblNewLabel_3.setBounds(26, 158, 134, 16);
		panel.add(lblNewLabel_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Completata");
		chckbxNewCheckBox.setBounds(172, 154, 128, 23);
		panel.add(chckbxNewCheckBox);

	}
}
