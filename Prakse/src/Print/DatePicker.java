package Print;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Data.DBPerson;

@SuppressWarnings("serial")
public class DatePicker extends JFrame {
	private JPanel panelMain;
	private JPanel panelMonthYear;
	private JPanel panelCalendar;
	private JTextField textDateYear;
	private JTextField textDateMonth;
	private JTextField textDate;
	private JButton btnGetDate;
	private String[] strArrWeekDays = { "P", " O", " T", " C", " P", " S", " S" };
	private ArrayList<JButton> arrButtons;
	private ArrayList<JLabel> lblWeekDay;
	private ArrayList<JButton> btnDays;
	private String dateOfMeeting;
	private JPanel jCalendar;
	private Font normalLbl = new Font("Arial", Font.BOLD, 12);
	private Font normalBtn = new Font("Arial", Font.PLAIN, 10);
	private Font nextPrevious = new Font("Arial", Font.BOLD, 12);
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private ArrayList<DBPerson> pList;

	public String getDateOfMeeting() {
		return dateOfMeeting;
	}

	public ArrayList<DBPerson> getpList() {
		return pList;
	}

	public void setpList(ArrayList<DBPerson> pList) {
		this.pList = pList;
	}

	public void addActionsToAll() {
		arrButtons.get(0).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textDateYear.setText(String.valueOf(Integer
						.parseInt(textDateYear.getText()) - 1));
			}
		});
		arrButtons.get(1).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textDateYear.setText(String.valueOf(Integer
						.parseInt(textDateYear.getText()) + 1));
			}
		});
		arrButtons.get(2).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int month = Integer.parseInt(textDateMonth.getText());
				if (month == 1)
					month = 12;
				else
					month--;
				textDateMonth.setText(String.valueOf(month));
			}
		});
		arrButtons.get(3).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int month = Integer.parseInt(textDateMonth.getText());
				if (month == 12)
					month = 1;
				else
					month++;
				textDateMonth.setText(String.valueOf(month));
			}
		});

		textDateMonth.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				btnDays.removeAll(btnDays);
				jCalendar.removeAll();
				viewCalendar();
				repaint();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		textDateYear.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				btnDays.removeAll(btnDays);
				jCalendar.removeAll();
				viewCalendar();
				repaint();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void onThePlace() {
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.weightx = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelMonthYear, gbc);
		panelMonthYear.add(arrButtons.get(0), gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelMonthYear, gbc);
		panelMonthYear.add(textDateYear, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.weightx = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelMonthYear, gbc);
		panelMonthYear.add(arrButtons.get(1), gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelMonthYear, gbc);
		panelMonthYear.add(arrButtons.get(2), gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelMonthYear, gbc);
		textDateMonth.setPreferredSize(new Dimension(35, 20));
		panelMonthYear.add(textDateMonth, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelCalendar, gbc);
		panelMonthYear.add(arrButtons.get(3), gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 6;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelCalendar, gbc);
		panelMonthYear.add(textDate, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 7;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelCalendar, gbc);
		panelMonthYear.add(btnGetDate, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelMonthYear, gbc);
		panelMain.add(panelMonthYear, gbc);

		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbl.setConstraints(panelCalendar, gbc);
		panelMain.add(panelCalendar, gbc);

		panelCalendar.add(jCalendar, gbc);

	}

	public DatePicker(ArrayList<DBPerson> pList) {
		this.setpList(pList);
		lblWeekDay = new ArrayList<>();
		arrButtons = new ArrayList<>();
		btnDays = new ArrayList<>();
		panelCalendar = new JPanel();
		panelMonthYear = new JPanel();
		panelMain = new JPanel();
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		panelMonthYear.setLayout(gbl);
		panelCalendar.setLayout(gbl);
		panelMain.setLayout(gbl);
		btnGetDate = new JButton("Saglabāt");
		btnGetDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new PrintForms(dateOfMeeting, getpList());
				dispose();
			}
		});

		textDateYear = new JTextField();
		SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
		String strDate = String.valueOf(dateFormat.format(new Date()));
		textDateYear.setText(strDate);
		textDateYear.setEditable(false);
		textDateYear.setText(String.valueOf(new SimpleDateFormat("yyyy")
				.format(new Date())));
		textDateMonth = new JTextField();
		textDateMonth.setText(String.valueOf(new SimpleDateFormat("M")
				.format(new Date())));
		textDateMonth.setEditable(false);
		textDate = new JTextField();
		textDate.setEditable(false);
		textDate.setText(strDate);
		textDate.setPreferredSize(new Dimension(80, 20));
		viewCalendar();

		arrButtons.add(new JButton("<"));
		arrButtons.add(new JButton(">"));
		arrButtons.add(new JButton("<"));
		arrButtons.add(new JButton(">"));
		for (JButton b : arrButtons) {
			b.setFont(nextPrevious);
			b.setPreferredSize(new Dimension(15, 18));
			b.setMargin(new Insets(1, 1, 1, 1));
		}
		btnGetDate.setMargin(new Insets(1, 1, 1, 1));
		addActionsToAll();
		onThePlace();
		panelCalendar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(panelMain);

		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void viewCalendar() {
		if (jCalendar == null)
			jCalendar = new JPanel();
		jCalendar.repaint();
		btnDays.removeAll(btnDays);
		GridLayout gl = new GridLayout(7, 7, 1, 1);
		jCalendar.setLayout(gl);
		Calendar c = new GregorianCalendar(Integer.parseInt(textDateYear
				.getText()), Integer.parseInt(textDateMonth.getText()) - 1, 1);
		// Wednesday is 1
		int startDateOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int endDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (startDateOfWeek > 1)
			startDateOfWeek = startDateOfWeek - 2;
		else
			startDateOfWeek = 6;
		for (int i = 0; i < 7; i++) {
			lblWeekDay.add(new JLabel());
			lblWeekDay.get(i).setText(strArrWeekDays[i]);
			jCalendar.add(lblWeekDay.get(i));
			lblWeekDay.get(i).setFont(normalLbl);
			if (i == 6) {
				lblWeekDay.get(i).setForeground(Color.RED);
			}
		}
		for (int i = 0; i < 42; i++) {
			if (i < startDateOfWeek || i > endDate + startDateOfWeek - 1) {
				jCalendar.add(new JLabel(""));
			} else {
				btnDays.add(new JButton(String.valueOf(i - startDateOfWeek + 1)));
				jCalendar.add(btnDays.get(i - startDateOfWeek));
				btnDays.get(i - startDateOfWeek).setFont(normalBtn);
				jCalendar.add(btnDays.get(i - startDateOfWeek));
				if (jCalendar.getComponentCount() % 7 == 0) {
					btnDays.get(i - startDateOfWeek).setForeground(Color.red);
				}
			}
		}
		for (JButton b : btnDays) {
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton x = (JButton) e.getSource();
					dateOfMeeting = x.getText() + "/" + textDateMonth.getText()
							+ "/" + textDateYear.getText();
					textDate.setText(dateOfMeeting);
				}
			});
		}

		jCalendar.repaint();
		jCalendar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
