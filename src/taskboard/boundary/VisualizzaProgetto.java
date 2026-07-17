package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;  // NB: vedi nota sotto, è JButton
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizzaProgetto extends JFrame {

    private static final long serialVersionUID = 1L;
    private String matricola;
    private Progetto progetto;
    private JLabel codiceInvito;
    private AttivitaControl control = new AttivitaControl();

    public VisualizzaProgetto(String matricola, Progetto progetto) {
        this.matricola = matricola;
        this.progetto = progetto;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 450);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);


        JPanel pannelloAlto = new JPanel(new BorderLayout(0, 5));

        JLabel lblNome = new JLabel(progetto.getNome());
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        lblNome.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        pannelloAlto.add(lblNome, BorderLayout.NORTH);

        JTextArea areaDescrizione = new JTextArea(progetto.getDescrizione());
        areaDescrizione.setEditable(false);
        areaDescrizione.setLineWrap(true);       
        areaDescrizione.setWrapStyleWord(true);     
        areaDescrizione.setOpaque(false);           
        areaDescrizione.setBorder(null);             
        areaDescrizione.setFocusable(false);         
        pannelloAlto.add(areaDescrizione, BorderLayout.CENTER);

        contentPane.add(pannelloAlto, BorderLayout.NORTH);
        
        codiceInvito = new JLabel(progetto.getCodiceInvito());
        pannelloAlto.add(codiceInvito, BorderLayout.EAST);

 
        JList<Attivita> list = new JList<>();
        contentPane.add(new JScrollPane(list), BorderLayout.CENTER);
        
        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    Attivita attivitaSelezionata =
                            list.getSelectedValue();
                    if (attivitaSelezionata != null) {
                        VisualizzaAttivita finestra =new VisualizzaAttivita(matricola, progetto, attivitaSelezionata);
                        finestra.setVisible(true);
                    }
                }
            }
        });
        
        
        try {
			List<Attivita> listaAttivita = control.getAttivita(progetto.getCodiceInvito());
			list.setListData(listaAttivita.toArray(new Attivita[0]));
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),"Errore nel caricamento delle attività", JOptionPane.ERROR_MESSAGE);
		}
        

 
        JPanel pannelloBottoni = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnHome = new JButton("Home");
        JButton btnCerca = new JButton("Cerca");
        JButton btnAggiungi = new JButton("Aggiungi attività");
        JButton btnReport = new JButton("Report");
        btnReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Report report =new Report(progetto);
        		report.setVisible(true);
        	}
        });
        
        pannelloBottoni.add(btnHome);
        pannelloBottoni.add(btnCerca);
        pannelloBottoni.add(btnAggiungi);
        pannelloBottoni.add(btnReport);
        contentPane.add(pannelloBottoni, BorderLayout.SOUTH);

    
        btnHome.addActionListener(e -> {
            Homepage home = new Homepage(matricola);
            home.setVisible(true);
            dispose();
        });
        
        btnAggiungi.addActionListener(e -> {
            CreaAttivita creaAttivita = new CreaAttivita(progetto, matricola);
            creaAttivita.setVisible(true);
            dispose();
        });
    }
}

