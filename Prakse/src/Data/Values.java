package Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Values implements Serializable {
	private FileOutputStream fOut = null;
	private ObjectOutputStream oOut = null;
	private FileInputStream fIn;
	private ObjectInputStream oIn;
	// private ArrayList<Double> values;
	// public GetValuesFromTxt(double IIN, double sumOfDividents){
	// this.IIN=IIN;
	// this.sumOfDividents=sumOfDividents;
	// }
	//
	private double IIN;
	private double sumOfDividents;

	public void setIIN(double IIN) {
		this.IIN = IIN;
	}

	public void setSumOfDividents(double sumOfDividents) {
		this.sumOfDividents = sumOfDividents;
	}

	public double getIIN() {
		return IIN;
	}

	public double getSumOfDividents() {
		return sumOfDividents;
	}

	public Values(double IIN, double sumOfDividents) {
		this.IIN = IIN;
		this.sumOfDividents = sumOfDividents;
	}

	public Values() {
	}

	public Values getValuesFromFile() {
		try {
			fIn = new FileInputStream("Values.ser");
			oIn = new ObjectInputStream(fIn);
			Values v = (Values) oIn.readObject();
			return v;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Faila nav");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
			setIIN(0);
			setSumOfDividents(0);
			setValuesToFile(new Values(0, 0));
		}
		return new Values();

	}

	public void setValuesToFile(Values v) {
		try {
			fOut = new FileOutputStream("Values.ser");
			oOut = new ObjectOutputStream(fOut);
			System.out.println(v.getIIN() + " " + v.sumOfDividents);
			oOut.writeObject(v);
			// serializing employee
		} catch (IOException e) {
			// close the streams
			try {
				oOut.flush();
				oOut.close();
				fOut.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

//	public static void main(String args[]) {
////		new Values().getValuesFromFile();
//		
//		 Values gtx = new Values();
////		 gtx.setValuesToFile(new Values(75, 145));
//		 gtx=gtx.getValuesFromFile();
//		 System.out.println(gtx.getIIN() + " " + gtx.getSumOfDividents());
//	}

}
