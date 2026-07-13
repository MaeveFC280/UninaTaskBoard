package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;  // NB: vedi nota sotto, è JButton
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.entity.Progetto;

public class VisualizzaProgetto extends JFrame {

    private static final long serialVersionUID = 1L;
    private String matricola;
    private Progetto progetto;

    public VisualizzaProgetto(String matricola, Progetto progetto) {
        this.matricola = matricola;
        this.progetto = progetto;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 450);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // --- ZONA ALTA: titolo + descrizione ---
        JPanel pannelloAlto = new JPanel(new BorderLayout(0, 5));

        JLabel lblNome = new JLabel(progetto.getNome());
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        lblNome.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        pannelloAlto.add(lblNome, BorderLayout.NORTH);

        JTextArea areaDescrizione = new JTextArea(progetto.getDescrizione());
        areaDescrizione.setEditable(false);
        areaDescrizione.setLineWrap(true);           // va a capo da solo
        areaDescrizione.setWrapStyleWord(true);      // va a capo sulle parole intere
        areaDescrizione.setOpaque(false);            // niente sfondo bianco da campo
        areaDescrizione.setBorder(null);             // niente bordo da campo
        areaDescrizione.setFocusable(false);         // niente cursore/selezione
        pannelloAlto.add(areaDescrizione, BorderLayout.CENTER);

        contentPane.add(pannelloAlto, BorderLayout.NORTH);

        // --- ZONA CENTRALE: lista attività (si espande) ---
        JList<Object> listaAttivita = new JList<>();
        contentPane.add(new JScrollPane(listaAttivita), BorderLayout.CENTER);

        // --- ZONA BASSA: bottoni ---
        JPanel pannelloBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnHome = new JButton("Home");
        JButton btnCerca = new JButton("Cerca");
        JButton btnAggiungi = new JButton("Aggiungi attività");
        JButton btnReport = new JButton("Report");
        pannelloBottoni.add(btnHome);
        pannelloBottoni.add(btnCerca);
        pannelloBottoni.add(btnAggiungi);
        pannelloBottoni.add(btnReport);
        contentPane.add(pannelloBottoni, BorderLayout.SOUTH);

        // --- LOGICA ---
        btnHome.addActionListener(e -> {
            Homepage home = new Homepage(matricola);
            home.setVisible(true);
            dispose();
        });
    }
}

