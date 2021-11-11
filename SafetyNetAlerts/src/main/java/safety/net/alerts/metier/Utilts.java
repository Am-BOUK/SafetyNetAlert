package safety.net.alerts.metier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utilts {
	public static JSONObject GET_API(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line=rd.readLine())!=null) {
			result.append(line);
		}
		rd.close();
		JSONParser parse= new JSONParser();
		JSONObject json = (JSONObject) parse.parse(result.toString());
		
		return json;
		
	}
	public static void main(String[] args) throws Exception {
	
		System.out.println(GET_API("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json").toJSONString());
	}

}
