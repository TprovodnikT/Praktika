

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import Data.DBPerson;

public class CopyOfEditPerson {

	private static final int JURIDICAL = 5;
	private Font boldError = new Font("Arial", Font.BOLD, 12);
	private Font NormalFont = new Font("Arial", Font.PLAIN, 12);
	private int option;
	private DBPerson p;

	private String strFactory[] = { DBPerson.JAUDA_WORKERS,
			DBPerson.JAUDA_ADMINISTRATION, DBPerson.JAUDA_FREE, DBPerson.JAUDA_ITD, DBPerson.JURIDICAL };
	private String personalCode = "";
	private String registrationNumber = "";
	private String selectedPrevious = "";
	private JFrame framePerson;
	private JPanel panel;
	private MaskFormatter mask;

	private JFormattedTextField textCode;
	private JTextField textName;
	private JTextField textSurname;
	private JComboBox<String> cmbFactory;
	private JTextField textPhone;
	private JTextField textSimpleStockItem;
	private JTextField textPersonalStockItem;
	private JTextField textLibertyStockItem;
	private JTextField textAdress;
	private JTextField textAreaCode;
	private JFormattedTextField textPCodeOrRegNumber;
	private JTextField textBankCode;
	private JCheckBox chkCitizen;

	private JLabel labelCode;
	private JLabel labelName;
	private JLabel labelSurname;
	private JLabel labelFactory;
	private JLabel labelPhone;
	private JLabel labelSimpleStockItem;
	private JLabel labelPersonalStockItem;
	private JLabel labelLibertyStockItem;
	private JLabel labelAdress;
	private JLabel labelAreaCode;
	private JLabel labelPersonalCode;
	private JLabel labelBankCode;
	private JLabel labelCitizen;

	private JButton btnClose;
	private JButton btnSave;

	public void checkTheDigit(JTextField txtField) {
		if (!txtField.getText().equals("")) {
			try {
				txtField.setFont(NormalFont);
				txtField.setForeground(Color.black);
				Integer.parseInt(txtField.getText());
			} catch (NumberFormatException e) {
				txtField.requestFocus();
				txtField.setForeground(Color.RED);
				txtField.setFont(boldError);
				txtField.setText("Ievadiet veselo skaitļi");
			}
		} else {
			txtField.requestFocus();
			txtField.setForeground(Color.RED);
			txtField.setFont(boldError);
			txtField.setText("Lauks nevar būt tukšs");
		}
	}

	

	public void setNotEnabled() {
		textName.setEnabled(false);
		textSurname.setEnabled(false);
		cmbFactory.setEnabled(false);
		textPhone.setEnabled(false);
		textSimpleStockItem.setEnabled(false);
		textPersonalStockItem.setEnabled(false);
		textLibertyStockItem.setEnabled(false);
		textAdress.setEnabled(false);
		textAreaCode.setEnabled(false);
		textPCodeOrRegNumber.setEnabled(false);
		textBankCode.setEnabled(false);
		chkCitizen.setEnabled(false);
	}

	public void setAllFields() {
		textCode.setText(String.valueOf(p.getId()));
		textName.setText(p.getName());
		textSurname.setText(p.getSurname());
		selectedPrevious = String.valueOf(p.getFactory());
		cmbFactory.setSelectedItem(String.valueOf(p.getFactory()));
		textPhone.setText(p.getPhone());
		textSimpleStockItem.setText(String.valueOf(p.getSimpleStockItem()));
		textPersonalStockItem.setText(String.valueOf(p.getPersonalStockItem()));
		textLibertyStockItem.setText(String.valueOf(p.getLibertyStockItem()));
		textAdress.setText(p.getAdress());
		textAreaCode.setText(p.getAreaCode());
		textPCodeOrRegNumber.setText(p.getPersonalCodeOrRegistraionNumber());
		textBankCode.setText(p.getBankCode());
		if (p.isCitizenOrNot())
			chkCitizen.setSelected(true);
		else
			chkCitizen.setSelected(false);
	}

	public CopyOfEditPerson(int row, ArrayList<DBPerson> dbPersonList, int option)
			throws ParseException {
		if (row != -1) {
			p = dbPersonList.get(row);
		}
		this.option = option;
		labelCode = new JLabel("Kods:");
		labelName = new JLabel("Vārds:");
		labelSurname = new JLabel("Uzvārds:");
		labelFactory = new JLabel("Ražotne:");
		labelPhone = new JLabel("Tel. Nr.:");
		labelSimpleStockItem = new JLabel("Parasto vārds akciju skaits:");
		labelPersonalStockItem = new JLabel("Personāla akciju skaits:");
		labelLibertyStockItem = new JLabel("Priekšrocības akciju skaits:");
		labelAdress = new JLabel("Adrese:");
		labelAreaCode = new JLabel("Teritorijās kods:");
		if (row != -1 && p.getFactory() == 5) {
			labelPersonalCode = new JLabel("Reģistrācijas numurs");
		} else {
			labelPersonalCode = new JLabel("Personas kods:");
		}
		labelBankCode = new JLabel("Bankas kods:");
		labelCitizen = new JLabel("Pilsonis:");

		textCode = new JFormattedTextField(new NumberFormatter());
		if (row == -1) {
			int i = 0;
			for (DBPerson p2 : dbPersonList) {
				if (i < p2.getId())
					i = p2.getId();
			}
			i++;
			textCode.setText(String.valueOf(i));
		}
		textName = new JTextField();
		textSurname = new JTextField();
		cmbFactory = new JComboBox<String>(strFactory);
		cmbFactory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// System.out.println("change");
				try {
					
					if (cmbFactory.getSelectedItem().equals(DBPerson.JURIDICAL)) {
						personalCode = textPCodeOrRegNumber.getText();
						// System.out.println(personalCode);
						textPCodeOrRegNumber.setValue(null);
						labelPersonalCode.setText("Reģistrācijas numurs");
						mask = new MaskFormatter("###########");
						DefaultFormatterFactory tf = new DefaultFormatterFactory(
								mask);
						textPCodeOrRegNumber.setFormatterFactory(tf);
						textPCodeOrRegNumber.setText(registrationNumber);
						selectedPrevious = DBPerson.JURIDICAL;
					} else if (selectedPrevious.equals(DBPerson.JURIDICAL)) {
						registrationNumber = textPCodeOrRegNumber.getText();
						textPCodeOrRegNumber.setValue(null);
						labelPersonalCode.setText("Personas kods:");
						mask = new MaskFormatter("######-#####");
						DefaultFormatterFactory tf = new DefaultFormatterFactory(
								mask);
						textPCodeOrRegNumber.setFormatterFactory(tf);
						textPCodeOrRegNumber.setText(personalCode);
						selectedPrevious = (String) cmbFactory
								.getSelectedItem();
					}
				} catch (ParseException e) {
				}
			}
		});
		textPhone = new JTextField();
		textPhone.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				checkTheDigit(textSimpleStockItem);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textSimpleStockItem.selectAll();
			}
		});
		textSimpleStockItem = new JTextField();
		textSimpleStockItem.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				checkTheDigit(textSimpleStockItem);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textSimpleStockItem.selectAll();
			}
		});

		textPersonalStockItem = new JTextField();
		textPersonalStockItem.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				checkTheDigit(textPersonalStockItem);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textPersonalStockItem.selectAll();
			}
		});
		textLibertyStockItem = new JTextField();
		textLibertyStockItem.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				checkTheDigit(textLibertyStockItem);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textLibertyStockItem.selectAll();
			}
		});
		textAdress = new JTextField();
		textAreaCode = new JTextField();
		if (row != -1 && p.getFactory() == JURIDICAL) {
			cmbFactory.setEnabled(false);
			mask = new MaskFormatter("###########");
			mask.install(textPCodeOrRegNumber);
		} else {
			mask = new MaskFormatter("######-#####");
			mask.install(textPCodeOrRegNumber);
		}
		textPCodeOrRegNumber = new JFormattedTextField(mask);
		textPCodeOrRegNumber.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				textPCodeOrRegNumber.selectAll();
			}
		});
		MaskFormatter msk = new MaskFormatter("UU##UUUU*************");
		msk.setValidCharacters("0123456789QWERTYUIOPASDFGHJKLZXCVBNM");
		textBankCode = new JFormattedTextField(msk);

		chkCitizen = new JCheckBox();

		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				framePerson.dispose();
			}
		});
		btnSave = new JButton("Saglabāt");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i;
			}
			// DBPerson p1 = new DBPerson(
			// Integer.parseInt(textCode.getText()), textName
			// .getText(), textSurname.getText(), cmbFactory
			// .getSelectedIndex(), textPhone.getText(),
			// textPersonalCode.getText(), Integer
			// .parseInt(textSimpleStockItem.getText()),
			// Integer.parseInt(textLibertyStockItem.getText()),
			// Integer.parseInt(textPersonalStockItem.getText()),
			// textAdress.getText(), textAreaCode.getText(),
			// textBankCode.getText(), chkCitizen.isSelected());
			// }
		});
		textCode.setEnabled(false);
		if (option == 1 || option == 2) {
			setAllFields();
		}
		onThePlace();
		// setNotEnabled();
		framePerson.setPreferredSize(new Dimension(450, 330));
		framePerson.setResizable(false);
		framePerson.pack();
		framePerson.setVisible(true);
		framePerson.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void onThePlace() {
		int i = 0;
		framePerson = new JFrame("Akcijas Ipašnieks");
		framePerson.setLayout(new GridLayout(1, 1));
		GridBagLayout gbl2 = new GridBagLayout();
		GridBagConstraints gbc2 = new GridBagConstraints();
		panel = new JPanel();
		panel.setLayout(gbl2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;
		gbc2.anchor = GridBagConstraints.WEST;
		gbl2.setConstraints(labelCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 2;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textCode, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;
		gbl2.setConstraints(labelName, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textName, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbl2.setConstraints(labelSurname, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textSurname, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbl2.setConstraints(labelFactory, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(cmbFactory, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbl2.setConstraints(labelPhone, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textPhone, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;
		gbl2.setConstraints(labelSimpleStockItem, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textSimpleStockItem, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;
		gbl2.setConstraints(labelPersonalStockItem, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textPersonalStockItem, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;
		gbl2.setConstraints(labelLibertyStockItem, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textLibertyStockItem, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(labelAdress, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textAdress, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;
		gbl2.setConstraints(labelAreaCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textAreaCode, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbl2.setConstraints(labelPersonalCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textPCodeOrRegNumber, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbl2.setConstraints(labelBankCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textBankCode, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = i;

		gbl2.setConstraints(labelCitizen, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.weightx = 2.0;
		gbl2.setConstraints(chkCitizen, gbc2);
		i++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = i;
		gbc2.weightx = 1.0;
		gbc2.fill = GridBagConstraints.NONE;
		gbl2.setConstraints(btnClose, gbc2);

		if (option == 2 || option == 0) {
			gbc2.insets = new Insets(1, 80, 1, 1);
			gbc2.gridheight = 1;
			gbc2.gridwidth = 1;
			gbc2.gridx = 1;
			gbc2.gridy = i;
			gbc2.weightx = 1.0;
			gbc2.fill = GridBagConstraints.NONE;
			// gbc2.anchor=GridBagConstraints.EAST;
			gbl2.setConstraints(btnSave, gbc2);
			panel.add(btnSave);
		} else if (option == 1) {
			setNotEnabled();
		}

		panel.add(labelCode);
		panel.add(textCode);
		panel.add(labelName);
		panel.add(textName);
		panel.add(labelSurname);
		panel.add(textSurname);
		panel.add(labelFactory);
		panel.add(cmbFactory);
		panel.add(labelPhone);
		panel.add(textPhone);
		panel.add(labelSimpleStockItem);
		panel.add(textSimpleStockItem);
		panel.add(labelPersonalStockItem);
		panel.add(textPersonalStockItem);
		panel.add(labelLibertyStockItem);
		panel.add(textLibertyStockItem);
		panel.add(labelAdress);
		panel.add(textAdress);
		panel.add(labelAreaCode);
		panel.add(textAreaCode);
		panel.add(labelPersonalCode);
		panel.add(textPCodeOrRegNumber);
		panel.add(labelBankCode);
		panel.add(textBankCode);
		panel.add(labelCitizen);
		panel.add(chkCitizen);
		panel.add(btnClose);
		framePerson.add(panel);
	}
}
