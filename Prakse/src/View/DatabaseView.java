package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Data.DBPerson;
import Data.DatabaseList;
import DataBase.CreateDatabase;
import Print.PrintMenu;

public class DatabaseView {
	public static final String OK = "OK";
	public static final String FAIL = "FAIL";
	private static final String DELETING = "Dzēšana";
	private static final String NO = "Nē";
	private static final String YES = "Jā";
	private static final String ERROR = "Kļūda";
	private static final String DELETE_OWNERS_FROM_DATABASE = "Vai Jūs tiešam gribat šos akcionārus dzēst no sarakstā?";
	private static final String DELETE_OWNER_FROM_DATABASE = "Vai Jūs tiešam gribat šo akcionāru dzēst no sarakstā?";
	private static final String MAKE_NON_ACTIVE_OWNERS = "Vai Jūs tiešam gribat šos akcionārus izdarīt par neaktīviem?";
	private static final String MAKE_NON_ACTIVE_USER = "Vai Jūs tiešam gribat šo akcionārus izdarīt par neaktīvu?";
	private static final String CHOOSE_MORE_THAN_ONE_STOCK_OWNER = "Izvēlēties vismaz vienu akcionāru";
	private static final String NON_ACTIVE_PERSON = "Neaktīvi";
	private static final String ACTIVE_PERSON = "Aktīvi";
	private ArrayList<DBPerson> dbPersonList = new ArrayList<DBPerson>();
	private ArrayList<DBViewPerson> dbPersonViewList = new ArrayList<DBViewPerson>();
	private JFrame frameDBView;
	private JButton btnDbRefresh;
	private JButton btnDbPersonDelete;
	private JButton btnDbPersonView;
	private JButton btnDbPersonChange;
	private JButton btnDbPrint;
	private JButton btnNewPerson;
	private JButton btnSumOfDividents;
	private JComboBox<String> cmbActiveNonActivePersons;
	private JTable table;
	private String[] columnNames = { "Kods", "Vards", "Uzvards", "Ražotne",
			"Parastas akcijas", "Privileģetas akcijas", "Personalas akcijas" };
	private List<DBPerson> showList = new ArrayList<DBPerson>();
	DefaultTableModel model = new DefaultTableModel();
	private double IIN;
	private double sumOfDividents;

	public void view() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		JPanel panel = new JPanel();
		JPanel panelForButtons = new JPanel();
		panelForButtons.setLayout(gbl);
		panel.setLayout(gbl);
		frameDBView = new JFrame("Akciju sabiedrības saraksts");

		cmbActiveNonActivePersons = new JComboBox<>(new String[] {
				ACTIVE_PERSON, NON_ACTIVE_PERSON });
		cmbActiveNonActivePersons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeListActiveOrNon();
				refreshLocal();
			}
		});
		Comparator<String> intComparator = new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				Integer i1 = Integer.parseInt(s1);
				Integer i2 = Integer.parseInt(s2);
				return i1.compareTo(i2);
			}
		};
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(
				model);
		table = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3053983289163117934L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowSorter(sorter);
		model.setColumnIdentifiers(columnNames);
		JScrollPane pane = new JScrollPane(table);

		fillTable(model);
		sorter.setComparator(0, intComparator);
		sorter.setComparator(3, intComparator);
		sorter.setComparator(4, intComparator);
		sorter.setComparator(5, intComparator);
		sorter.setComparator(6, intComparator);
		ImageIcon refresh = new ImageIcon("Images/refresh.gif");
		btnDbRefresh = new JButton(refresh);
		btnDbRefresh.setBounds(new Rectangle(0, 0, 20, 20));
		btnDbRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshFromDB();
			}
		});
		btnDbPersonDelete = new JButton("Dzēst");
		btnDbPersonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		btnDbPersonView = new JButton("Skatīt");
		btnDbPersonView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow = -1;
					selectedRow = table.getSelectedRow();
					System.out.println(selectedRow);
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(frameDBView,
								CHOOSE_MORE_THAN_ONE_STOCK_OWNER);
					} else {
						int id = Integer.parseInt((String) table.getValueAt(
								selectedRow, 0));
						new ViewPerson(frameDBView,id);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		btnDbPersonChange = new JButton("Izmainīt");
		btnDbPersonChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedRow = -1;
					selectedRow = table.getSelectedRow();
					System.out.println(selectedRow);
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(frameDBView,
								CHOOSE_MORE_THAN_ONE_STOCK_OWNER);
					} else {
						int id = Integer.parseInt((String) table.getValueAt(
								selectedRow, 0));
						EditPerson editPerson = new EditPerson(frameDBView);
						editPerson.addActionToAllElements();
						editPerson.addButtons();
						editPerson.setAllFields(id);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnNewPerson = new JButton("Pievienot");
		btnNewPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					AddNewPerson addNewPerson;
					try {
						addNewPerson = new AddNewPerson(frameDBView);
						addNewPerson.addActionToAllElements();
						addNewPerson.addButtons();
						addNewPerson.setAllFields();
						addNewPerson.addActionOnSave();
//						EditPerson editPerson = new EditPerson(frameDBView);
//						editPerson.addActionToAllElements();
//						editPerson.addButtons();
//						editPerson.setAllFields(id);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
			}
		});

		btnDbPrint = new JButton("Drūkāt");
		btnDbPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DBPerson toPrintPerson = null;
				ArrayList<DBPerson> toPrintPersonList = new ArrayList<DBPerson>();
				int codeFromTable = Integer.parseInt((String) table.getValueAt(
						table.getSelectedRow(), 0));
				// System.out.println(codeFromTable);
				for (DBPerson p : dbPersonList) {
					if (p.getId() == codeFromTable) {
						toPrintPerson = p;
						// new DividentsForm().printForm(p);
						break;
					}
				}
				for (DBPerson p : dbPersonList) {
					for (int i : table.getSelectedRows()) {
						codeFromTable = Integer.parseInt((String) table
								.getValueAt(i, 0));
						if (p.getId() == codeFromTable) {
							toPrintPersonList.add(p);
							// new DividentsForm().printForm(p);
						}

					}
				}
				new PrintMenu(toPrintPerson, toPrintPersonList);

			}
		});

		btnSumOfDividents = new JButton("Izmainīt IIN un Dividentes");
		btnSumOfDividents.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new IINAndDividentsView().view(frameDBView);
			}
		});
		// gbl.setConstraints(dbRefresh, gbc1);

		panelForButtons.add(btnDbRefresh);
		panelForButtons.add(btnDbPersonView);
		panelForButtons.add(btnDbPersonChange);
		panelForButtons.add(btnNewPerson);
		panelForButtons.add(btnDbPersonDelete);
		panelForButtons.add(btnDbPrint);
		panelForButtons.add(btnSumOfDividents);
		panelForButtons.add(cmbActiveNonActivePersons);

		gbc1.gridheight = 1;
		gbc1.gridwidth = 1;
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		gbc1.weightx = 1.0;
		gbc1.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelForButtons, gbc1);
		panel.add(panelForButtons);

		gbc1.insets = new Insets(1, 1, 1, 1);
		gbc1.gridheight = 1;
		gbc1.gridwidth = 5;
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(pane, gbc1);
		panel.add(pane);

		frameDBView.add(panel);
		frameDBView.setPreferredSize(new Dimension(800, 500));
		frameDBView.pack();
		frameDBView.setVisible(true);
		frameDBView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public double getIIN() {
		return IIN;
	}

	public void setIIN(double iIN) {
		IIN = iIN;
	}

	public double getSumOfDividents() {
		return sumOfDividents;
	}

	public void setSumOfDividents(double d) {
		this.sumOfDividents = d;
	}

	public ArrayList<DBPerson> getDbPersonList() {
		return dbPersonList;
	}

	public void setDbPersonList(ArrayList<DBPerson> dbPersonList) {
		this.dbPersonList = dbPersonList;
	}

	protected void clearTable(DefaultTableModel model) {
		int rowCount = model.getRowCount();
		System.out.println("Row count " + rowCount);
		// model.fireTableRowsDeleted(0, model.getRowCount()-1);
		for (int i = 0; i < rowCount; i++) {
			model.removeRow(0);
		}
	}

	protected void fillTable(DefaultTableModel model) {
		changeListActiveOrNon();
		for (DBPerson dbPerson : showList) {
			dbPersonViewList.add(new DBViewPerson(dbPerson.getId(), dbPerson
					.getName(), dbPerson.getSurname(), dbPerson.getFactory(),
					dbPerson.getSimpleStockItem(), dbPerson
							.getLibertyStockItem(), dbPerson
							.getPersonalStockItem()));
			model.addRow(new String[] { String.valueOf(dbPerson.getId()),
					dbPerson.getName(), dbPerson.getSurname(),
					String.valueOf(dbPerson.getFactory()),
					String.valueOf(dbPerson.getSimpleStockItem()),
					String.valueOf(dbPerson.getLibertyStockItem()),
					String.valueOf(dbPerson.getPersonalStockItem()) });
		}
	}

	protected void changeListActiveOrNon() {
		showList.clear();
		if (cmbActiveNonActivePersons.getSelectedItem().equals(ACTIVE_PERSON)) {
			for (DBPerson dbPerson : DatabaseList.listOfStockHolders) {
				if (dbPerson.getAllStocks() != 0) {
					showList.add(dbPerson);
				}
			}
		}
		if (cmbActiveNonActivePersons.getSelectedItem().equals(
				NON_ACTIVE_PERSON)) {
			for (DBPerson dbPerson : DatabaseList.listOfStockHolders) {
				if (dbPerson.getAllStocks() == 0) {
					showList.add(dbPerson);
				}
			}
		}
	}

	protected void refreshFromDB() {
		new DatabaseList().clearListOfStockHolders();
		new CreateDatabase().insertFromDBIntoStaticList();
		refreshLocal();
	}

	protected void refreshLocal() {
		clearTable(model);
		fillTable(model);
	}

	public void delete() {
		String message = getMessageForDelete();
		if (!message.equals(CHOOSE_MORE_THAN_ONE_STOCK_OWNER)) {
			int choise = JOptionPane.showOptionDialog(frameDBView, message,
					DELETING, JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, new String[] { YES, NO },
					null);
			if (choise == JOptionPane.YES_OPTION) {
				int[] selectedRows = table.getSelectedRows();
				int selectedRowsCount=selectedRows.length;
				 for(int i=0;i<selectedRowsCount; i++) {
					int id = Integer.parseInt((String) table.getValueAt(
							selectedRows[0], 0));
					String resultFromDBAction = new CreateDatabase()
							.changeToInactive(id);
					if (resultFromDBAction.equals(OK)) {
						deleteStocksFromStaticListOwner(id);
						refreshLocal();
					}
					if (!resultFromDBAction.equals(OK)) {
						JOptionPane.showMessageDialog(frameDBView,
								resultFromDBAction + "numurs " + id + " ",
								ERROR, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	protected String getMessageForDelete() {
		String message = "";
		int selectedRowsCount = table.getSelectedRows().length;
		Object tableStatuss = cmbActiveNonActivePersons.getSelectedItem();
		if (selectedRowsCount == 0) {
			message = CHOOSE_MORE_THAN_ONE_STOCK_OWNER;
			JOptionPane.showMessageDialog(frameDBView, message);
		} else {
			if (tableStatuss.equals(ACTIVE_PERSON)) {
				if (selectedRowsCount == 1) {
					message = MAKE_NON_ACTIVE_USER;
				} else {
					message = MAKE_NON_ACTIVE_OWNERS;
				}
			}
			if (tableStatuss.equals(NON_ACTIVE_PERSON)) {
				if (selectedRowsCount == 1) {
					message = DELETE_OWNER_FROM_DATABASE;
				} else {
					message = DELETE_OWNERS_FROM_DATABASE;
				}
			}
		}
		return message;
	}

	protected void deleteStocksFromStaticListOwner(int id) {
		List<DBPerson> listOfStockHolders = DatabaseList.listOfStockHolders;
		for (int i = 0; i < listOfStockHolders.size(); i++) {
			DBPerson dbPerson = listOfStockHolders.get(i);
			if (dbPerson.getId() == id) {
				dbPerson.setSimpleStockItem(0);
				dbPerson.setLibertyStockItem(0);
				dbPerson.setPersonalStockItem(0);
			}
		}
	}
}
