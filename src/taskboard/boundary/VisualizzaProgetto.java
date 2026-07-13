package taskboard.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JButton;

import taskboard.entity.Progetto;

public class VisualizzaProgetto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String matricola;
    private Progetto progetto;

    public VisualizzaProgetto(String matricola, Progetto progetto) {
        this.matricola = matricola;
        this.progetto = progetto;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNomeProgetto = new JLabel(progetto.getNome());
        lblNomeProgetto.setBounds(5, 0, 440, 16);
        lblNomeProgetto.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNomeProgetto);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 33, 439, 36);
        contentPane.add(scrollPane);

        JTextPane descrizionePane = new JTextPane();
        descrizionePane.setText(progetto.getDescrizione());
        descrizionePane.setEditable(false);
        scrollPane.setViewportView(descrizionePane);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(5, 81, 439, 137);
        contentPane.add(scrollPane_1);

        JList<Object> listaAttivita = new JList<>();
        scrollPane_1.setViewportView(listaAttivita);

        JButton btnCerca = new JButton("Cerca");
        btnCerca.setBounds(5, 6, 117, 29);
        contentPane.add(btnCerca);

        JButton btnHome = new JButton("Home");
        btnHome.setBounds(5, 237, 117, 29);
        contentPane.add(btnHome);
        btnHome.addActionListener(e -> {
            Homepage home = new Homepage(matricola);
            home.setVisible(true);
            dispose();
        });

        JButton btnAggiungi = new JButton("Aggiungi attività");
        btnAggiungi.setBounds(292, 230, 153, 36);
        contentPane.add(btnAggiungi);

        JButton btnReport = new JButton("REPORT");
        btnReport.setBounds(327, 6, 117, 29);
        contentPane.add(btnReport);
    }
}
