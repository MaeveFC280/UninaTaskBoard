package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.control.ProgettoControl;
import taskboard.entity.Progetto;

public class Homepage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private String matricola;
    private ProgettoControl progettoControl;
    private DefaultListModel<Progetto> modello;
    private List<Progetto> progetti;
    private JList<Progetto> listaProgetti;

    public Homepage(String matricola) {
        this.matricola = matricola;
        this.progettoControl = new ProgettoControl();
        this.modello = new DefaultListModel<>();

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
        btnEntra.addActionListener(e -> {
            EntraInProgetto entra = new EntraInProgetto(matricola);
            entra.setVisible(true);
            entra.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent ev) {
                    caricaProgetti();
                }
            });
        });

        JButton btnCrea = new JButton("Crea");
        panel.add(btnCrea);
        btnCrea.addActionListener(e -> {
            CreaProgetto crea = new CreaProgetto(matricola);
            crea.setVisible(true);
            crea.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent ev) {
                    caricaProgetti();
                }
            });
        });

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        listaProgetti = new JList<>();
        listaProgetti.setModel(modello);
        scrollPane.setViewportView(listaProgetti);

        listaProgetti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent ev) {
                if (ev.getClickCount() == 2) {
                    Progetto selezionato = listaProgetti.getSelectedValue();
                    if (selezionato != null) {
                        VisualizzaProgetto vp = new VisualizzaProgetto(matricola, selezionato);
                        vp.setVisible(true);
                        dispose();
                    }
                }
            }
        });

        caricaProgetti();
    }

    private void caricaProgetti() {
        try {
            progetti = progettoControl.getProgettiUtente(matricola);
            modello.clear();
            for (Progetto p : progetti) {
                modello.addElement(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Errore nel caricamento dei progetti",
                "Errore", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}