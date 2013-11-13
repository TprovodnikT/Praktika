package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import Data.DBPerson;
import Data.DatabaseList;

public class ParentViewPerson {

//	private static final int JURIDICAL = 5;
	private Font boldError = new Font("Arial", Font.BOLD, 12);
	private Font NormalFont = new Font("Arial", Font.PLAIN, 12);
//	private int option;
	private DBPerson person;

	private String strFactory[] = { DBPerson.JAUDA_WORKERS,
			DBPerson.JAUDA_ADMINISTRATION, DBPerson.JAUDA_FREE,
			DBPerson.JAUDA_ITD, DBPerson.JURIDICAL };
	protected String personalCode = "";
	protected String registrationNumber = "";
	protected String selectedPrevious = "";
	protected JFrame framePerson;
	protected JPanel panel;
	protected MaskFormatter mask;
	protected GridBagLayout gbl2 = new GridBagLayout();
	protected GridBagConstraints gbc2 = new GridBagConstraints();
	protected int positionY = 0;
	protected JFormattedTextField textCode;
	protected JTextField textName;
	protected JTextField textSurname;
	protected JComboBox<String> cmbFactory;
	protected JTextField textPhone;
	protected JTextField textSimpleStockItem;
	protected JTextField textPersonalStockItem;
	protected JTextField textLibertyStockItem;
	protected JTextField textAdress;
	protected JTextField textAreaCode;
	protected JFormattedTextField textPCodeOrRegNumber;
	protected JTextField textBankCode;
	protected JCheckBox chkCitizen;
	protected JFrame parent;

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
	protected JLabel labelPersonalCode;
	private JLabel labelBankCode;
	private JLabel labelCitizen;
	protected JButton btnClose;
	protected JButton btnSave;
	

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

	public void setAllFields(int id) {
		for (DBPerson dbPerson : DatabaseList.listOfStockHolders) {
			if (dbPerson.getId() == id) {
				person = dbPerson;
			}
		}
		setLabelPersonalCode();
		textCode.setText(String.valueOf(person.getId()));
		textName.setText(person.getName());
		textSurname.setText(person.getSurname());
		selectedPrevious = String.valueOf(person.getFactory());
		cmbFactory.setSelectedItem(String.valueOf(person.getFactory()));
		textPhone.setText(person.getPhone());
		textSimpleStockItem
				.setText(String.valueOf(person.getSimpleStockItem()));
		textPersonalStockItem.setText(String.valueOf(person
				.getPersonalStockItem()));
		textLibertyStockItem.setText(String.valueOf(person
				.getLibertyStockItem()));
		textAdress.setText(person.getAdress());
		textAreaCode.setText(person.getAreaCode());
		textPCodeOrRegNumber.setText(person
				.getPersonalCodeOrRegistraionNumber());
		textBankCode.setText(person.getBankCode());
		if (person.isCitizenOrNot())
			chkCitizen.setSelected(true);
		else
			chkCitizen.setSelected(false);
		System.out.println(labelPersonalCode.getText());
		framePerson.repaint();
	}

	public void setLabelPersonalCode() {
		if (person.getFactory() == 5) {
			labelPersonalCode.setText("Reģistrācijas numurs");
		} else {
			labelPersonalCode.setText("Personas kods:");
		}
	}

	public ParentViewPerson(final JFrame parent) throws ParseException {
		this.parent=parent;
		parent.setEnabled(false);
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
		labelPersonalCode=new JLabel();
		labelBankCode = new JLabel("Bankas kods:");
		labelCitizen = new JLabel("Pilsonis:");

		textCode = new JFormattedTextField(new NumberFormatter());
		textName = new JTextField();
		textSurname = new JTextField();
		cmbFactory = new JComboBox<String>(strFactory);
		textPhone = new JTextField();
		textPersonalStockItem = new JTextField();
		textSimpleStockItem = new JTextField();
		textLibertyStockItem = new JTextField();
		textAdress = new JTextField();
		textAreaCode = new JTextField();
		textPCodeOrRegNumber = new JFormattedTextField(mask);
		MaskFormatter msk = new MaskFormatter("UU##UUUU*************");
		msk.setValidCharacters("0123456789QWERTYUIOPASDFGHJKLZXCVBNM");
		textBankCode = new JFormattedTextField(msk);
		chkCitizen = new JCheckBox();
		textCode.setEnabled(false);
		onThePlace();
		framePerson.setPreferredSize(new Dimension(450, 350));
		framePerson.setResizable(false);
		framePerson.pack();
		framePerson.setVisible(true);
		WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                framePerson.dispose();
                parent.setEnabled(true);
            }
        };
        framePerson.addWindowListener(exitListener);;
	}

	public void onThePlace() {
		framePerson = new JFrame("Akcijas Ipašnieks");
		framePerson.setLayout(new GridLayout(1, 1));
		panel = new JPanel();
		panel.setLayout(gbl2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;
		gbc2.anchor = GridBagConstraints.WEST;
		gbl2.setConstraints(labelCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 2;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textCode, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;
		gbl2.setConstraints(labelName, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textName, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbl2.setConstraints(labelSurname, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textSurname, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbl2.setConstraints(labelFactory, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(cmbFactory, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbl2.setConstraints(labelPhone, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textPhone, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;
		gbl2.setConstraints(labelSimpleStockItem, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textSimpleStockItem, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;
		gbl2.setConstraints(labelPersonalStockItem, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textPersonalStockItem, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;
		gbl2.setConstraints(labelLibertyStockItem, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textLibertyStockItem, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(labelAdress, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textAdress, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;
		gbl2.setConstraints(labelAreaCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textAreaCode, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbl2.setConstraints(labelPersonalCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textPCodeOrRegNumber, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbl2.setConstraints(labelBankCode, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbl2.setConstraints(textBankCode, gbc2);
		positionY++;

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 0;
		gbc2.gridy = positionY;

		gbl2.setConstraints(labelCitizen, gbc2);

		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.weightx = 2.0;
		gbl2.setConstraints(chkCitizen, gbc2);
		positionY++;
		// setNotEnabled();

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
		framePerson.add(panel);
	}
	
	public void addButtons() {
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				framePerson.dispose();
				parent.setEnabled(true);
			}
		});
		btnSave = new JButton("Saglabāt");
		
		gbc2.insets = new Insets(1, 1, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.weightx = 1.0;
		gbc2.fill = GridBagConstraints.NONE;
		gbl2.setConstraints(btnClose, gbc2);

		gbc2.insets = new Insets(1, 80, 1, 1);
		gbc2.gridheight = 1;
		gbc2.gridwidth = 1;
		gbc2.gridx = 1;
		gbc2.gridy = positionY;
		gbc2.weightx = 1.0;
		gbc2.fill = GridBagConstraints.NONE;
		// gbc2.anchor=GridBagConstraints.EAST;
		gbl2.setConstraints(btnSave, gbc2);
		
		panel.add(btnClose);
		panel.add(btnSave);
	}
}
