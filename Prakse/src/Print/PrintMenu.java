package Print;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import Data.DBPerson;

public class PrintMenu {
	JFrame printMenuframe;
	JPanel panel;
	JList<String> list;
	JButton btnPrint;
	DBPerson p;
	ArrayList<DBPerson> pList;

	public PrintMenu(DBPerson p, ArrayList<DBPerson> pList) {
		this.pList = pList;
		this.p = p;
		printMenuframe = new JFrame("Print");
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(gbl);
		String[] printMenuArray = { "Dividenžu forma",
				"Dividenžu maksājumi kopā", "Akcionāru saraksts datums",
				"Akcionāru sraksts" };
		list = new JList<String>(printMenuArray);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gbc.insets = new Insets(1, 1, 10, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(list, gbc);

		btnPrint = new JButton("Print");
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(btnPrint, gbc);
		
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				caseOfMenu(list.getSelectedIndex());
			}
		});

		panel.add(list);
		panel.add(btnPrint);
		printMenuframe.add(panel);
		printMenuframe.pack();
		printMenuframe.setVisible(true);
		printMenuframe.setLocationRelativeTo(null);
	}

	public void caseOfMenu(int i) {
		switch (i) {
		case 0:			
			new DatePicker(pList);
			break;
		case 1:
//			new PrinterClass().printForm(pList);
//			break;
		case 2:

			break;
		case 3:

			break;
		}
	}
}
