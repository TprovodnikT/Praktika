package Interfaces;

import java.util.ArrayList;
import java.util.Arrays;

public interface PrintInterface {
	public ArrayList<String> lineBreakers = new ArrayList<String>(
			Arrays.asList("<h1>", "<h2>", "<h3>", "<h4>", "<h5>", "<h6>",
					"<p>", "<br/>", "<tr>"));
	public String style = "<style  type=text/css>" + "table.header{"
			+ "font-family:Arial;" + "font-size:10pt" + "}" + "table.body{"
			+ "font-family:Arial;" + "font-size:7pt" + "}" + "body{font-family:Arial; font-size:10pt}" + "</style>";
	public int lineOnPages = 29;
}
