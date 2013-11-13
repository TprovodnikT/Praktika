package View;

import java.text.ParseException;

import javax.swing.JFrame;

public class ViewPerson extends ParentViewPerson{

	public ViewPerson(JFrame parent,int id) throws ParseException {
		super(parent);
		setAllFields(id);
		setNotEnabled();
	}
	
}
