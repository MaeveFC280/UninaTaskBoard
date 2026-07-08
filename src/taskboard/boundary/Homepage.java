package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.control.ProgettoControl;
import taskboard.entity.Progetto;

public class Homepage extends JFrame {

    private JPanel contentPane;
    
    private String matricola;
    private ProgettoControl progettoControl;
    private DefaultListModel<Progetto> modello;

    public Homepage(String matricola) {
    	this.matricola=matricola;
    	this.progettoControl=new ProgettoControl();
    	this.modello=new DefaultListModel(); 

        setTitle("Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("Homepage");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Menlo", Font.BOLD | Font.ITALIC, 26));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton btnEntra = new JButton("Entra");
        panel.add(btnEntra);
        
        JButton btnCrea = new JButton("Crea");
        panel.add(btnCrea);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JList listaProgetti = new JList();
        listaProgetti.setModel(modello);
        scrollPane.setViewportView(listaProgetti);
    }
}