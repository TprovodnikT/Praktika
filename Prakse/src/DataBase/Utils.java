package DataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Utils {
	
	public static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String DB_URL = "jdbc:derby:myDB;create=true;";
	
	public static List<String> parseSQL(String filename) throws Exception{
		return parseSQL(new FileReader(filename));
	}

	public static List<String> parseSQL(Reader reader) throws Exception{
		ArrayList<String> result = new ArrayList<>();
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer();
		String line = null;
		while( (line = br.readLine()) != null){
			sb.append(line);
		}
		br.close();
		StringTokenizer st = new StringTokenizer(sb.toString(), ";");
		while(st.hasMoreTokens()){
			result.add(st.nextToken().trim());
		}
		return result;
	}
}
