package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Data.DBPerson;
import DataBase.CreateDatabase;

public class AddNewPerson extends EditPerson {

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
	
	protected void addActionOnSave(){
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DBPerson dbPerson = createNewDBPersonFromForm();
				new CreateDatabase().insertIntoDatabase(dbPerson);
			}
		});		
	}

	public AddNewPerson(JFrame parent) throws ParseException {
		super(parent);
		addButtons();
		checkingDigitForField(textSimpleStockItem);

		checkingDigitForField(textPersonalStockItem);

		checkingDigitForField(textLibertyStockItem);
		
		setStartPersonlCodeLabeValue();
	}
	
	public void setStartPersonlCodeLabeValue(){
		labelPersonalCode.setText(EditPerson.PERSONAL_CODE);
		try {
			addMaskToTextField("######-#####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setAllFields() {
		textSimpleStockItem.setText(0+"");
		textLibertyStockItem.setText(0+"");
		textPersonalStockItem.setText(0+"");
		
	}

}
