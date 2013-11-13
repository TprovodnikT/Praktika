package Print;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.ImageView;

import Data.DBPerson;
import Data.Values;
import Interfaces.PrintInterface;

@SuppressWarnings("serial")
public class PrintForms extends JFrame implements PrintInterface {
	private ArrayList<JEditorPane> pane;
	private ArrayList<JLabel> pageNumbers;
	private JButton btnPrint;
	private HTMLEditorKit kit;
	private int counterBR = 0;
	private String htmlToPage = "";
	private ArrayList<String> htmlString = new ArrayList<String>();

	private ArrayList<DBPerson> pList;
	private String date;
	private Values values;
	private double IIN;
	private double sumOfDividents;
	// Ja razotne ir 4, tad juridicalPerson=true
	private boolean juridicalPerson = false;
	private double allStocks = 0;
	String numberInWords = "";
	NumbersToWords numbersToWords = new NumbersToWords();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<DBPerson> getpList() {
		return pList;
	}

	public void setpList(ArrayList<DBPerson> pList) {
		this.pList = pList;
	}

	public PrintForms(String date, ArrayList<DBPerson> pList) {
		values = new Values();
		values = values.getValuesFromFile();
		IIN = values.getIIN();
		sumOfDividents = values.getSumOfDividents();
		this.setDate(date);
		this.setpList(pList);
		pane = new ArrayList<JEditorPane>();
		pageNumbers = new ArrayList<JLabel>();
		btnPrint = new JButton("Print");
		kit = new HTMLEditorKit() {
			public ViewFactory getViewFactory() {
				return new HTMLFactory() {
					public View create(javax.swing.text.Element elem) {
						View view = super.create(elem);
						if (view instanceof ImageView) {
							((ImageView) view).setLoadsSynchronously(true);
						}
						return view;
					}
				};
			}
		};
		ArrayList<DBPerson> listOffPerson = new ArrayList<DBPerson>();
		for (int i = 0; i < 7; i++) {
			numbersToWords.clearAll();
			counterBR = 0;
			htmlToPage = style;
			htmlString.removeAll(htmlString);
			listOffPerson.removeAll(listOffPerson);
			allStocks = 0;
			switch (i) {
			case 0:

				for (DBPerson p : pList) {
					if (p.getFactory() == 1 && !p.getBankCode().isEmpty()) {
						listOffPerson.add(p);
						allStocks += p.getAllStocks();
					}
				}
				if (!listOffPerson.isEmpty()) {
					pane.add(new JEditorPane());					
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents - (IIN * allStocks
							* sumOfDividents / 100))));
					dividentList(listOffPerson, "1. Jaudas strādājošie");
				}
				break;
			case 1:
				allStocks = 0;
				for (DBPerson p : pList) {
					if (p.getFactory() == 1 && p.getBankCode().isEmpty()) {
						listOffPerson.add(p);
						allStocks += p.getAllStocks();
					}
				}
				if (!listOffPerson.isEmpty()) {
					htmlToPage = style;
					pane.add(new JEditorPane());
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents - (IIN * allStocks
							* sumOfDividents / 100))));
					dividentList(listOffPerson,
							"1. Jaudas strādājošie, skaidrā nauda");					
				}
				break;
			case 2:
				allStocks = 0;
				for (DBPerson p : pList) {
					if (p.getFactory() == 2) {
						listOffPerson.add(p);
						allStocks += p.getAllStocks();
					}
				}
				if (!listOffPerson.isEmpty()) {
					htmlToPage = style;
					pane.add(new JEditorPane());					
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents - (IIN * allStocks
							* sumOfDividents / 100))));
					dividentList(listOffPerson, "2. Jaudas administrācija");					
				}

				break;
			case 3:
				allStocks = 0;
				for (DBPerson p : pList) {
					if (p.getFactory() == 3 && !p.getBankCode().isEmpty()) {
						allStocks += p.getAllStocks();
						listOffPerson.add(p);
					}
				}
				if (!listOffPerson.isEmpty()) {
					htmlToPage = style;
					pane.add(new JEditorPane());					
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents - (IIN * allStocks
							* sumOfDividents / 100))));
					dividentList(listOffPerson, "3. Jaudas atbrīvotie");					
				}
				break;
			case 4:
				allStocks = 0;
				for (DBPerson p : pList) {
					if (p.getFactory() == 3 && p.getBankCode().isEmpty()) {
						allStocks += p.getAllStocks();
						listOffPerson.add(p);
					}
				}
				if (!listOffPerson.isEmpty()) {
					htmlToPage = style;
					pane.add(new JEditorPane());					
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents - (IIN * allStocks
							* sumOfDividents / 100))));
					dividentList(listOffPerson,
							"3. Jaudas atbrīvotie, skaidrā nauda");					
				}
				break;
			case 5:
				allStocks = 0;
				for (DBPerson p : pList) {
					if (p.getFactory() == 4) {
						allStocks += p.getAllStocks();
						listOffPerson.add(p);
					}
				}
				if (!listOffPerson.isEmpty()) {
					htmlToPage = style;
					pane.add(new JEditorPane());					
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents - (IIN * allStocks
							* sumOfDividents / 100))));
					dividentList(listOffPerson, "4. Jaudas ITD");
					
				}
				break;
			case 6:
				allStocks = 0;
				for (DBPerson p : pList) {
					if (p.getFactory() == 5) {
						allStocks += p.getAllStocks();
						listOffPerson.add(p);
						juridicalPerson = true;
					}
				}
				if (!listOffPerson.isEmpty()) {
					htmlToPage = style;
					pane.add(new JEditorPane());					
					numberInWords = numbersToWords.getWord(((int) (allStocks
							* sumOfDividents)));
					dividentList(listOffPerson, "5. Juridiskas personas");
					
				}
				break;
			}

		}

		JPanel mainPanel = new JPanel();
		JScrollPane mainPanelScroll = new JScrollPane(mainPanel);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel.setLayout(gbl);
		for (int i = 0; i < pane.size(); i++) {
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 1.0;
			gbc.anchor = GridBagConstraints.WEST;
			gbl.setConstraints(pane.get(i), gbc);
			pane.get(i).setEditable(false);
			pane.get(i).setPreferredSize(new Dimension(480, 670));
			int k = i + 1;
			pageNumbers.add(new JLabel(String.valueOf(k)));
			pageNumbers.get(i).setBounds(230, 625, 10, 10);
			pane.get(i).add(pageNumbers.get(i));
			mainPanel.add(pane.get(i));
		}
		add(mainPanelScroll);
		pack();
		setSize(600, 480);
		setVisible(true);
		add(btnPrint, BorderLayout.SOUTH);
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new PrinterClass().printForm(pane);
			}
		});
	}

	public void dividentList(ArrayList<DBPerson> listOfPersons, String pageName) {
		String strYearOfDividents = date.substring(date.length() - 4);
		int intYearOfDividents = Integer.parseInt(strYearOfDividents);
		intYearOfDividents--;
		
		if (juridicalPerson) {
			IIN = 0;
		} else {
			IIN = values.getIIN();
		}
		double allDividents=allStocks * sumOfDividents - (IIN * allStocks
				* sumOfDividents / 100);
//		System.out.println(numberInWords);
		htmlString
				.add("<table class=header width=100%><tr><td><h4 align=center>A/S Energofirma &#8220;Jauda&#8221; Reģ.Nr.40003012798</h4>"
						+ "<br/>Sapulces&nbsp;datums: "
						+ date
						+ "<br/>"
						+ "<br/>Vadītājs:  ___________________________"
						+ "<br/> Galv.&nbsp;grāmatvedis___________________________"
						+ "<br/>Sarakstu&nbsp;sastadīja: I.Rombaha"
						+ "<br/><div align=center><b>Dividenžu maksājumi kopā, kas aprēķināti</b></div>"
						+ "</br><div align=center>"
						+ pageName
						+ " "
						+ intYearOfDividents + ". gadu</div></td></tr></table>");
		for (int i = 0; i < listOfPersons.size(); i++) {
			// int x = i++;
			htmlString
					.add("<table border=0 id=insertingTable>"
							+ "<tr><td>"
							+ "<table class=body border=1 style='border-style: solid; border-color: red;' cellspacing=0 cellpadding=5>"
							+ "<tr>" + "<td rowspan=2><b>Nr.</b></td>"
							+ "<td rowspan=2><b>Kods</b></td> "
							+ "<td rowspan=2><b>Uzvārds, vārds</b></td>"
							+ "<td rowspan=2><b>Pers.kods</b></td>"
							+ "<td colspan=2><b>Kopā akcijas</b></td>"
							+ "<td rowspan=2><b>IIN " + IIN + "%</b></td>"
							+ "<td rowspan=2><b>Izmaksai<br/>Summa</b></td>"
							+ "<td rowspan=2><b>Bankas Kods</b></td>" + "</tr>"
							+ "<tr>" + "<td><b>Daudz.</b></td>"
							+ "<td><b>Summa</b></td>" + "</tr>");
			for (DBPerson p : listOfPersons) {
				String tmpRow;
				tmpRow = "<tr><td>" + i++ + "</td>";
				tmpRow += "<td>" + p.getId() + "</td>";
				tmpRow += "<td>" + p.getName() + " " + p.getSurname() + "</td>";
				tmpRow += "<td>" + p.getPersonalCodeOrRegistraionNumber() + "</td>";
				tmpRow += "<td>" + String.valueOf(p.getAllStocks()) + "</td>";
				tmpRow += "<td>"
						+ String.valueOf(p.getAllStocks() * sumOfDividents)
						+ "</td>";
				tmpRow += "<td>"
						+ String.valueOf(p.getAllStocks() * sumOfDividents
								* IIN / 100) + "</td>";
				tmpRow += "<td>"
						+ String.valueOf(p.getAllStocks() * sumOfDividents
								- p.getAllStocks() * sumOfDividents * IIN / 100)
						+ "</td>";
				tmpRow += "<td>" + p.getBankCode() + "</td></tr>";
				htmlString.add(tmpRow);
			}
			// setHTML("");
		}
		htmlString.add("<tr><td></td><td></td><td></td><td></td><td>"
				+ allStocks
				+ "</td><td>"
				+ allStocks
				* sumOfDividents
				+ "</td><td>"
				+ IIN
				* allStocks
				* sumOfDividents
				/ 100
				+ "</td><td>"
				+ (allStocks * sumOfDividents - (IIN * allStocks
						* sumOfDividents / 100))
				+ "</td><td></td></tr></table>");
		htmlString.add("<p>Summa izmaksai: "
				+ (allStocks * sumOfDividents - (IIN * allStocks
						* sumOfDividents / 100)));
		htmlString.add("<p>" + numberInWords +" Ls "+(int)((allDividents-(int)allDividents)*100)+" s");
		for (String s : htmlString) {
			setHTML(s);
		}
		setHTML("");
	}

	public void setHTML(String htmlStringLocal) {
		if (!pane.isEmpty()) {
			int in = pane.size() - 1;
			pane.get(in).setEditorKit(kit);
			HTMLDocument doc = (HTMLDocument) pane.get(in).getDocument();
			if (counterBR > lineOnPages) {
				pane.get(in).setText("");
				htmlToPage += "</td></tr></table>";
				pane.get(in).setText(htmlToPage);
				if (!htmlStringLocal.equals("")) {
					if(htmlStringLocal.indexOf("Summa izmaksai:")!=-1||htmlStringLocal.indexOf("Ls")!=-1){
						pane.add(new JEditorPane());
						in++;
						counterBR = 0;
						htmlToPage = style + htmlStringLocal;
					}
					else{
						pane.add(new JEditorPane());
						in++;
						counterBR = 0;
						htmlToPage = style + htmlString.get(1) + htmlStringLocal;
					}					
				}
			} else if (htmlStringLocal.equals("")) {
				pane.get(in).setText("");
				htmlToPage += "</td></tr></table>";
				pane.get(in).setText(htmlToPage);
				pane.get(in).setDocument(doc);
			} else {
				for (String breaker : lineBreakers) {
					int indexOfBreaker = 0;
					String htmlCopy = htmlStringLocal;
					while (indexOfBreaker != -1) {
						indexOfBreaker = htmlCopy.indexOf(breaker);
						if (indexOfBreaker != -1) {
							if (indexOfBreaker != htmlCopy.length()) {
								htmlCopy = htmlCopy
										.substring(indexOfBreaker + 1);
							} else {
								htmlCopy = "";
							}
							counterBR++;
						}
					}
				}
				htmlToPage += htmlStringLocal;
				pane.get(in).setDocument(doc);
			}
		}
	}
}
