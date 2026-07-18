package taskboard.boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import taskboard.control.ReportControl;
import taskboard.entity.Progetto;

public class Report extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelNumeri;
	private JPanel panelGrafici;

	public Report(Progetto progetto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("REPORT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		panelNumeri = new JPanel();
		panelNumeri.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(panelNumeri, BorderLayout.WEST);
		panelNumeri.setLayout(new GridLayout(6, 1, 0, 5));
		
		panelGrafici = new JPanel();
		contentPane.add(panelGrafici, BorderLayout.CENTER);
		panelGrafici.setLayout(new GridLayout(1, 2, 0, 0));

		
		try {
			ReportControl control = new ReportControl();
			
			int totale = control.contaAttivita(progetto);
			int NONIniziati = control.contaNONIniziati(progetto);
			int completati = control.contaCompletati(progetto);
			int inCorso = control.contaInCorso(progetto);
			int scaduti = totale-NONIniziati-completati-inCorso;
			HashMap<String, Integer> completatoStudente = control.statisticheStudente(progetto);
			int sviluppo = control.contaSviluppo(progetto);
			
			panelNumeri.add(new JLabel("Attività totali: "+ totale));
			panelNumeri.add(new JLabel("Attività completate: "+ completati));
			panelNumeri.add(new JLabel("Attività in corso: "+ inCorso));
			panelNumeri.add(new JLabel("Attività non iniziate: "+ NONIniziati));
			panelNumeri.add(new JLabel("Attività scadute: "+ scaduti));
			panelNumeri.add(new JLabel("Attività di sviluppo: "+ sviluppo));
			
			//stat attivita
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.addValue(completati, "Attività", "Completate");
			dataset.addValue(inCorso, "Attività", "In corso");
			dataset.addValue(NONIniziati, "Attività", "Non iniziate");
			dataset.addValue(scaduti, "Attività", "Scadute");
			
			JFreeChart chart = ChartFactory.createBarChart(
				    "Attività",
				    "Stato",
				    "Numero",
				    dataset);
			
			panelGrafici.add(new ChartPanel(chart));
			
			//stat studenti
			DefaultCategoryDataset dataStudenti = new DefaultCategoryDataset();
			

			for (String nominativo : completatoStudente.keySet()) {
			    int numeroCompletamenti = completatoStudente.get(nominativo);
			    dataStudenti.addValue(numeroCompletamenti, "Attività completate", nominativo);
			}

			JFreeChart chartStudenti = ChartFactory.createBarChart(
			        "Attività completate per studente",
			        "Studente",
			        "Numero attività",
			        dataStudenti
			);

			panelGrafici.add(new ChartPanel(chartStudenti));
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore nella generazione del report", JOptionPane.ERROR_MESSAGE);
		}

	}

}
