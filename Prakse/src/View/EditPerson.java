package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import Data.DBPerson;

public class EditPerson extends ParentViewPerson{	
	
	public static final String PERSONAL_CODE = "Personas kods:";
	public static final String REGISTRATION_NUMBER = "Reģistrācijas numurs";
//	private Font boldError = new Font("Arial", Font.BOLD, 12);
//	private Font NormalFont = new Font("Arial", Font.PLAIN, 12);
	
	public DBPerson createNewDBPersonFromForm() {
		int id = Integer.parseInt((String) textCode.getText());
		String name = textName.getText();
		String surname = textSurname.getText();
		int factory = Integer.parseInt((String) cmbFactory.getSelectedItem());
		String phone = textPhone.getText();
		int simpleStockItem = Integer.parseInt((String) textSimpleStockItem
				.getText());
		int libertyStockItem = Integer.parseInt((String) textLibertyStockItem
				.getText());
		int personalStockItem = Integer.parseInt((String) textPersonalStockItem
				.getText());
		String adress = textAdress.getText();
		String personalCodeOrRegistraionNumber = textPCodeOrRegNumber.getText();
		String areaCode = textCode.getText();
		String bankCode = textBankCode.getText();
		boolean citizenOrNot = Boolean.getBoolean((String) textCode.getText()
				.toLowerCase());
		return new DBPerson(id, name, surname, factory, phone,
				personalCodeOrRegistraionNumber, simpleStockItem,
				libertyStockItem, personalStockItem, adress, areaCode,
				bankCode, citizenOrNot);

	}
	
	public void addButtons(){
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
		panel.add(btnSave);
		panel.add(btnClose);
		panel.repaint();
	}
	
	protected void checkingDigitForField(final JTextField textField) {
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				checkTheDigit(textField);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				textField.selectAll();
			}
		});
	}
	
	public EditPerson(JFrame parent,int id) throws ParseException {
		super(parent);
//		addActionToAllElements();
//		addButtons();
//		setAllFields();
	}
	
	public EditPerson(JFrame parent) throws ParseException {
		super(parent);
//		setAllFields();
	}
	
	public void addActionToAllElements(){
		
		checkingDigitForField(textSimpleStockItem);
		
		checkingDigitForField(textPersonalStockItem);
		
		checkingDigitForField(textLibertyStockItem);
		
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
						labelPersonalCode.setText(REGISTRATION_NUMBER);
						addMaskToTextField("###########");
						textPCodeOrRegNumber.setText(registrationNumber);
						selectedPrevious = DBPerson.JURIDICAL;
					} else if (selectedPrevious.equals(DBPerson.JURIDICAL)) {
						registrationNumber = textPCodeOrRegNumber.getText();
						textPCodeOrRegNumber.setValue(null);
						labelPersonalCode.setText(PERSONAL_CODE);
						addMaskToTextField("######-#####");
						textPCodeOrRegNumber.setText(personalCode);
						selectedPrevious = (String) cmbFactory
								.getSelectedItem();
					}
				} catch (ParseException e) {
				}
			}
		});
	}
	
	public void addMaskToTextField(String stringMask) throws ParseException{
		mask = new MaskFormatter(stringMask);
		DefaultFormatterFactory tf = new DefaultFormatterFactory(
				mask);
		textPCodeOrRegNumber.setFormatterFactory(tf);		
		
	}
	
}
