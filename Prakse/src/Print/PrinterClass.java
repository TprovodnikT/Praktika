package Print;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JTable;

import Data.DBPerson;

public class PrinterClass implements Printable {
	ArrayList<DBPerson> pList;
	JTable table;
	ArrayList<JEditorPane> panel;

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		// JFrame dividentsFormFrame = new JFrame();
		// for(int i=0;i<panel.size();i++){
		// panel.get(i).setBackground(Color.white);
		// dividentsFormFrame.add(panel.get(i));
		// }

		// dividentsFormFrame.pack();
		// dividentsFormFrame.setVisible(false);
		// dividentsFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int numberOfPages = panel.size() - 1;
		if (page > numberOfPages) {
			return NO_SUCH_PAGE;
		}
		// numberOfPages=(int)(panel.getSize().getHeight()/pf.getImageableHeight());
		// if(numberOfPages>0){
		// for(int i=0; i<numberOfPages;i++){
		// }
		// }else{
		//
		// }
		// if (page > 0) {
		// return NO_SUCH_PAGE;
		// }
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		panel.get(page).print(g);

		return PAGE_EXISTS;
	}

	/* tell the caller that this page is part of the printed document */

	public void printForm(ArrayList<JEditorPane> pane) {
		this.panel = pane;
		prints();
	}

	// public void printForm(ArrayList<DBPerson> pList) {
	// this.pList = pList;
	// // for(DBPerson p:pList){
	// // System.out.println(p.name);
	// // }
	// panel = new JPanel();
	// GridBagLayout gbl = new GridBagLayout();
	// GridBagConstraints gbc = new GridBagConstraints();
	// panel.setLayout(gbl);
	// Date dNow = new Date();
	// String sDate = new SimpleDateFormat("dd/MM/yyyy").format(dNow);
	// JLabel lblHeader = new JLabel("A/S Energofirma "
	// + String.valueOf(Character.toChars(34)) + "Jauda"
	// + String.valueOf(Character.toChars(34))+"Reģ.Nr.123465789");
	// lblHeader.setFont(new Font("Arial", Font.BOLD, 12));
	// JLabel lblFooter = new JLabel(sDate + "  ");
	// int sum = 640;
	// DefaultTableModel model = new DefaultTableModel();
	// String[] columnNames = { "<html>Kods", "<html>Vārds,<br/>Uzvārds",
	// "<html>Personas&nbsp;kods", "<html>Kopā<br/>akcijas",
	// "<html>Summa", "<html>IIN<br/> 10%", "<html>Summa<br/>izmaksai",
	// "<html>Bankas&nbsp;kods" };
	// model.setColumnIdentifiers(columnNames);
	// int x =0;
	// for (DBPerson p : pList) {
	// x ++;
	// model.addRow(new String[] { String.valueOf(p.code),
	// p.name + " " + p.surname, p.personalCode,
	// String.valueOf(p.getAllStocks()), String.valueOf(sum),
	// String.valueOf(0.1 * sum), String.valueOf(sum - 0.1 * sum),
	// p.bankCode });
	// }
	// if(x==1){
	// x=0;
	// }
	// else{
	// x=x*12;
	// }
	//
	// table = new JTable(model);
	// table.setBackground(Color.white);
	// // table.setForeground(Color.white);
	// table.getTableHeader().setBackground(Color.white);
	// table.getTableHeader().setPreferredSize(new Dimension(460, 50));
	// table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 10));
	// table.setFont(new Font("Arial", Font.PLAIN, 9));
	// JScrollPane pane = new JScrollPane(table);
	// pane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
	// pane.setPreferredSize(new Dimension(460, 70 + x));
	// pane.setBackground(Color.white);
	// pane.setForeground(Color.white);
	// table.getColumnModel().getColumn(0).setPreferredWidth(40);
	// table.getColumnModel().getColumn(1).setPreferredWidth(100);
	// table.getColumnModel().getColumn(2).setPreferredWidth(130);
	// table.getColumnModel().getColumn(3).setPreferredWidth(80);
	// table.getColumnModel().getColumn(4).setPreferredWidth(70);
	// table.getColumnModel().getColumn(5).setPreferredWidth(60);
	// table.getColumnModel().getColumn(6).setPreferredWidth(80);
	// table.getColumnModel().getColumn(7).setPreferredWidth(150);
	// gbc.insets = new Insets(1, 1, 10, 1);
	// gbc.gridheight = 1;
	// gbc.gridwidth = 1;
	// gbc.gridx = 0;
	// gbc.gridy = 0;
	// gbc.anchor = GridBagConstraints.EAST;
	// gbl.setConstraints(lblHeader, gbc);
	//
	// gbc.insets = new Insets(1, 1, 1, 1);
	// gbc.gridheight = 1;
	// gbc.gridwidth = 1;
	// gbc.weightx = 1;
	// gbc.weighty = 1;
	// gbc.gridx = 0;
	// gbc.gridy = 1;
	// // gbc.fill=GridBagConstraints.HORIZONTAL;
	// gbl.setConstraints(pane, gbc);
	//
	// gbc.insets = new Insets(10, 1, 1, 1);
	// gbc.gridheight = 1;
	// gbc.gridwidth = 2;
	// gbc.gridx = 0;
	// gbc.gridy = 2;
	// gbc.anchor = GridBagConstraints.WEST;
	// gbl.setConstraints(lblFooter, gbc);
	//
	// panel.add(lblHeader);
	// panel.add(pane);
	// panel.add(lblFooter);
	//
	// prints();
	// }

	public void prints() {

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}
}