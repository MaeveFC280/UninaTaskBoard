package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.control.LoginControl;

public class LoginBoundary extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    public LoginBoundary() {
    	LoginControl loginControl = new LoginControl();

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Menlo", Font.BOLD | Font.ITALIC, 26));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        JPanel infoLogin = new JPanel();
        contentPane.add(infoLogin, BorderLayout.CENTER);

        GridBagLayout gbl_infoLogin = new GridBagLayout();
        gbl_infoLogin.columnWidths = new int[] {100, 180};
        gbl_infoLogin.rowHeights = new int[] {30, 30, 30};
        gbl_infoLogin.columnWeights = new double[] {0.0, 0.0};
        gbl_infoLogin.rowWeights = new double[] {0.0, 0.0, 0.0};
        infoLogin.setLayout(gbl_infoLogin);

        JLabel lblMatricola = new JLabel("Matricola");
        GridBagConstraints gbc_lblMatricola = new GridBagConstraints();
        gbc_lblMatricola.anchor = GridBagConstraints.EAST;
        gbc_lblMatricola.insets = new Insets(0, 0, 10, 10);
        gbc_lblMatricola.gridx = 0;
        gbc_lblMatricola.gridy = 0;
        infoLogin.add(lblMatricola, gbc_lblMatricola);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(0, 0, 10, 0);
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        infoLogin.add(textField, gbc_textField);
        textField.setColumns(15);

        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 10, 10);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 1;
        infoLogin.add(lblPassword, gbc_lblPassword);

        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(0, 0, 10, 0);
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 1;
        infoLogin.add(passwordField, gbc_passwordField);
        passwordField.setColumns(15);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.addActionListener(e -> {
        		String matricola = textField.getText();
        		String password = new String(passwordField.getPassword());
        		
        		if(matricola.isEmpty() || password.isEmpty()) {
        			JOptionPane.showMessageDialog(LoginBoundary.this, "Compila tutti i campi","Errore di login",JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		try {
        			boolean ok = loginControl.login(matricola, password);
        			if (ok==true) {
        				Homepage homepage = new Homepage(matricola);
        				homepage.setVisible(true);
                		dispose();
        			} else {
        				JOptionPane.showMessageDialog(LoginBoundary.this, "Matricola o password errate","Errore di login",JOptionPane.ERROR_MESSAGE);
        			}
        		} catch (SQLException ex) {
        			JOptionPane.showConfirmDialog(LoginBoundary.this, ex,"Errore di login",JOptionPane.ERROR_MESSAGE);
        		}
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 2;
        infoLogin.add(btnNewButton, gbc_btnNewButton);
    }
}