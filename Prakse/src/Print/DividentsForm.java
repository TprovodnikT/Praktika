package Print;
import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.DBPerson;

public class DividentsForm implements Printable {

	DBPerson p;
	JTable table;
	JPanel panel;

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		JFrame dividentsFormFrame = new JFrame();
		panel.setBackground(Color.white);
		dividentsFormFrame.add(panel);
		dividentsFormFrame.pack();
		dividentsFormFrame.setVisible(false);
		dividentsFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("length: " + panel.getComponents());
		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		panel.printAll(g);
		return PAGE_EXISTS;
	}

	/* tell the caller that this page is part of the printed document */

	public void printForm(DBPerson p) {

		panel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(gbl);
		Date dNow = new Date();
		String sDate = new SimpleDateFormat("dd/MM/yyyy").format(dNow);
		JLabel lblHeader = new JLabel("Dividenžu aprēķina lapiņa");
		JLabel lblFooter = new JLabel(sDate+"  ");
		int sum = 640;
		this.p = p;
		 DefaultTableModel model = new DefaultTableModel();
		 String[] columnNames = { "<html>Kods", "<html>Vārds,<br/>Uzvārds", "<html>Personas&nbsp;kods",
		 "<html>Kopā<br/>akcijas", "<html>Summa", "<html>IIN 10%", "<html>Summa<br/>izmaksai",
		 "<html>Bankas&nbsp;kods" };
		 model.setColumnIdentifiers(columnNames);
		 model.addRow(new String[] { String.valueOf(p.getId()),
		 p.getName() + " " + p.getSurname(), p.getPersonalCodeOrRegistraionNumber(),
		 String.valueOf(p.getAllStocks()), String.valueOf(sum),
		 String.valueOf(0.1 * sum), String.valueOf(sum - 0.1 * sum),
		 p.getBankCode() });
		 table = new JTable(model);
		 table.setBackground(Color.white);
//		 table.setForeground(Color.white);
		 table.getTableHeader().setBackground(Color.white);
		 table.getTableHeader().setPreferredSize(new Dimension(480, 50));
		 table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 9));
		 table.setFont(new Font("Arial", Font.PLAIN, 8));
		 JScrollPane pane = new JScrollPane(table);
		 pane.setPreferredSize(new Dimension(460, 70));
		 pane.setBackground(Color.white);
		 pane.setForeground(Color.white);
		 table.getColumnModel().getColumn(0).setPreferredWidth(40);
		 table.getColumnModel().getColumn(1).setPreferredWidth(100);
		 table.getColumnModel().getColumn(2).setPreferredWidth(100);
		 table.getColumnModel().getColumn(3).setPreferredWidth(50);
		 table.getColumnModel().getColumn(4).setPreferredWidth(50);
		 table.getColumnModel().getColumn(5).setPreferredWidth(50);
		 table.getColumnModel().getColumn(6).setPreferredWidth(65);
		 table.getColumnModel().getColumn(7).setPreferredWidth(150);
		gbc.insets = new Insets(1, 1, 10, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbl.setConstraints(lblHeader, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		// gbc.fill=GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(pane, gbc);
		
		gbc.insets = new Insets(10, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(lblFooter, gbc);

		panel.add(lblHeader);
		panel.add(pane);
		panel.add(lblFooter);

		prints();
	}

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