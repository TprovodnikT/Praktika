package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Data.Values;

public class IINAndDividentsView {
	
	public boolean isDouble(String number) {
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public void view(JFrame frameDBView){
		final JTextField textIIN = new JTextField();
		final JTextField textSumOfDividents = new JTextField();
		JPanel btnPanel = new JPanel();
		JButton btnOK = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		btnPanel.add(btnOK);
		btnPanel.add(btnCancel);
		final Values v = new Values().getValuesFromFile();
		textIIN.setText(String.valueOf(v.getIIN()));
		textSumOfDividents.setText(String.valueOf(v.getSumOfDividents()));

		Object[] message = { "IIN%:", textIIN, "Dividentu summa:",
				textSumOfDividents, btnPanel };
		final JDialog dialog = new JDialog(frameDBView,
				"IIN un Dividentes", true);
		JOptionPane optionPane = new JOptionPane(message,
				JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION,
				null, new Object[] {}, null);
		dialog.setContentPane(optionPane);
		System.out.println(optionPane.getValue());
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean isDouble = true;
				System.out.println(isDouble(textIIN.getText()));
				if (!isDouble(textIIN.getText())) {
					textIIN.setText("Vajag ievadit skaitļu vertību");
					isDouble = false;
				}
				if (!isDouble(textSumOfDividents.getText())) {
					textSumOfDividents
							.setText("Vajag ievadit skaitļu vertību");
					isDouble = false;
				}
				if (isDouble) {
					v.setValuesToFile(new Values(Double
							.parseDouble(textIIN.getText()), Double
							.parseDouble(textSumOfDividents.getText())));
					dialog.dispose();
				}

			}
		});
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.dispose();
			}
		});

		// }
		dialog.pack();
		dialog.setLocationRelativeTo(frameDBView);
		dialog.setVisible(true);
	}
}
