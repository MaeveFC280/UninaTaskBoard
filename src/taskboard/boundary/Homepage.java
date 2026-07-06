package taskboard.boundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Homepage extends JFrame {

    private JPanel contentPane;

    public Homepage() {

        setTitle("Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Homepage");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Menlo", Font.BOLD | Font.ITALIC, 26));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        JPanel infoLogin = new JPanel();
        contentPane.add(infoLogin, BorderLayout.CENTER);

        GridBagLayout gbl_infoLogin = new GridBagLayout();
        gbl_infoLogin.columnWidths = new int[] {180};
        gbl_infoLogin.rowHeights = new int[] {30, 30, 30, 0};
        gbl_infoLogin.columnWeights = new double[] {0.0};
        gbl_infoLogin.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0};
        infoLogin.setLayout(gbl_infoLogin);

        JButton btnNewButton = new JButton("Entra");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 2;
        infoLogin.add(btnNewButton, gbc_btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Crea");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.gridx = 0;
        gbc_btnNewButton_1.gridy = 3;
        infoLogin.add(btnNewButton_1, gbc_btnNewButton_1);
    }
}