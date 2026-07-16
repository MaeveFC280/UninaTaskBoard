package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import taskboard.control.AttivitaControl;
import taskboard.entity.Attivita;
import taskboard.entity.Progetto;
import taskboard.entity.Studente;

public class VisualizzaAttivita extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private AttivitaControl control = new AttivitaControl();
    private Attivita attivita;

    private JButton btnStato;
    private JLabel txtStato;

    public VisualizzaAttivita(String matricola, Progetto progetto, Attivita attivita) {
        this.attivita = attivita;

        setTitle("Dettaglio attività");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 350);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblNomeAttivita = new JLabel(attivita.getNome());
        lblNomeAttivita.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNomeAttivita.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNomeAttivita, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        contentPane.add(panel, BorderLayout.CENTER);

        JLabel lblDescrizione = new JLabel("Descrizione:");
        panel.add(lblDescrizione);

        JScrollPane scrollPaneDescrizione = new JScrollPane();
        panel.add(scrollPaneDescrizione);

        JTextArea textAreaDescrizione = new JTextArea();
        textAreaDescrizione.setEditable(false);
        textAreaDescrizione.setLineWrap(true);
        textAreaDescrizione.setWrapStyleWord(true);

        if (attivita.getDescrizione() != null) {
            textAreaDescrizione.setText(attivita.getDescrizione());
        } else {
            textAreaDescrizione.setText("");
        }

        scrollPaneDescrizione.setViewportView(textAreaDescrizione);

        JLabel lblDataCreazione = new JLabel("Data creazione:");
        panel.add(lblDataCreazione);

        JLabel txtDataCreazione = new JLabel();

        if (attivita.getDataCreazione() != null) {
            txtDataCreazione.setText(attivita.getDataCreazione().toString());
        } else {
            txtDataCreazione.setText("Non disponibile");
        }

        panel.add(txtDataCreazione);

        JLabel lblDataScadenza = new JLabel("Data scadenza:");
        panel.add(lblDataScadenza);

        JLabel txtDataScadenza = new JLabel();

        if (attivita.getDataScadenza() != null) {
            txtDataScadenza.setText(attivita.getDataScadenza().toString());
        } else {
            txtDataScadenza.setText("Nessuna");
        }

        panel.add(txtDataScadenza);

        JLabel lblStato = new JLabel("Stato:");
        panel.add(lblStato);

        txtStato = new JLabel(attivita.getStato());
        panel.add(txtStato);

        JLabel lblResponsabili = new JLabel("Responsabili:");
        panel.add(lblResponsabili);

        JScrollPane scrollPaneResponsabili = new JScrollPane();
        panel.add(scrollPaneResponsabili);

        JList<Studente> listResponsabili = new JList<>();
        scrollPaneResponsabili.setViewportView(listResponsabili);

        try {
            List<Studente> responsabili = control.getResponsabili(attivita.getId());
            listResponsabili.setListData(responsabili.toArray(new Studente[0]));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Errore nel caricamento dei responsabili",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        JPanel pannelloBottoni = new JPanel();
        pannelloBottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane.add(pannelloBottoni, BorderLayout.SOUTH);

        JButton btnChiudi = new JButton("Chiudi");
        pannelloBottoni.add(btnChiudi);

        btnChiudi.addActionListener(e -> {
            dispose();
        });

        JButton btnCommenti = new JButton("Commenti");
        pannelloBottoni.add(btnCommenti);

        btnStato = new JButton();
        pannelloBottoni.add(btnStato);

        btnStato.addActionListener(e -> {
            cambiaStatoAttivita();
        });

        aggiornaPulsanteStato();
    }

    private void aggiornaPulsanteStato() {
        String stato = attivita.getStato();

        txtStato.setText(stato);

        if ("NON INIZIATO".equals(stato)) {
            btnStato.setText("Avvia attività");
            btnStato.setEnabled(true);
        } else if ("IN CORSO".equals(stato)) {
            btnStato.setText("Completa attività");
            btnStato.setEnabled(true);
        } else if ("COMPLETATO".equals(stato)) {
            btnStato.setText("Attività completata");
            btnStato.setEnabled(false);
        } else if ("SCADUTO".equals(stato)) {
            btnStato.setText("Attività scaduta");
            btnStato.setEnabled(false);
        } else {
            btnStato.setText("Stato non riconosciuto");
            btnStato.setEnabled(false);
        }
    }

    private void cambiaStatoAttivita() {
        try {
            String statoCorrente = attivita.getStato();

            if ("NON INIZIATO".equals(statoCorrente)) {
                control.svolgimentoAttivita(attivita.getId());
                attivita.setStato("IN CORSO");

                JOptionPane.showMessageDialog(
                        this,
                        "Attività avviata."
                );

            } else if ("IN CORSO".equals(statoCorrente)) {
                int scelta = JOptionPane.showConfirmDialog(
                        this,
                        "Confermi il completamento dell'attività?",
                        "Completa attività",
                        JOptionPane.YES_NO_OPTION
                );

                if (scelta == JOptionPane.YES_OPTION) {
                    control.completaAttivita(attivita.getId());
                    attivita.setStato("COMPLETATO");

                    JOptionPane.showMessageDialog(
                            this,
                            "Attività completata."
                    );
                }
            }

            aggiornaPulsanteStato();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Errore nell'aggiornamento dell'attività",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}