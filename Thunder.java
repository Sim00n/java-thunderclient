import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Thunder {

	private final String API_VERSION = "1.0.0";
	private final String API_URL = "/api/";
	private String host;
	private int port;
	private String hosturl;
	private String apikey;
	private String apisecret;
	
	public Thunder(String host, int port, String apikey, String apisecret) {
		this.host = host;
		this.port = port;
		this.apikey = apikey;
		this.apisecret = apisecret;
		this.hosturl = "http://" + this.host + ":" + this.port;
	}
	
	public String getUserCount() throws IOException {
		return makeRequest("GET", makeUrl("users"));
	}
	
	public String getUserInChannel(String channel) throws IOException {
		return makeRequest("GET", makeUrl("channels", channel));
	}
	
	public String sendMessageToUser(String userid, String[] message) throws MalformedURLException, IOException {
		return makeRequest("POST", makeUrl("users", userid), message);
	}
	
	public String sendMessageToChannel(String channel, String[] message) throws MalformedURLException, IOException {
		return makeRequest("POST", makeUrl("channels", channel), message);
	}
	
	public String isUserOnline(String userid) throws MalformedURLException, IOException {
		return makeRequest("GET", makeUrl("users", userid));
	}
	
	public String disconnectUser(String userid) throws MalformedURLException, IOException {
		return makeRequest("DELETE", makeUrl("users", userid));
	}
	
	private String makeRequest(String method, URL url) throws IOException {
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("X-Thunder-Secret-Key", this.apisecret);
		conn.setDoInput(true);
		conn.setDoOutput(true);
				
		switch(method) {
		case "POST":
			conn.setRequestMethod("POST");
			break;
		case "GET":
			conn.setRequestMethod("GET");
			break;
		default:
			return "Unsported request method.";
		}

		InputStream is = conn.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String response = rd.readLine();
		return response;
	}
	
	private String makeRequest(String method, URL url, String[] data) throws IOException {
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("X-Thunder-Scret-Key", this.apisecret);
		conn.setDoInput(true);
		conn.setDoOutput(true);
				
		switch(method) {
		case "POST":
			conn.setRequestMethod("POST");
			break;
		case "GET":
			conn.setRequestMethod("GET");
			break;
		default:
			return "Unsported request method.";
		}
		
		DataOutputStream output = new DataOutputStream(conn.getOutputStream());
		for(String s : data)
		{
			output.writeBytes(s);
		}
		output.flush();
		output.close();
		
		InputStream is = conn.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String response = rd.readLine();
		return response;
	}
	
	
	private URL makeUrl(String command) throws MalformedURLException {
		String url = this.API_URL + this.API_VERSION + "/" + this.apikey + "/" + command + "/";
		return new URL(this.hosturl + url);
	}
	
	private URL makeUrl(String command, String arg) throws MalformedURLException {
		String url = this.API_URL + this.API_VERSION + "/" + this.apikey + "/" + command + "/";
		url += arg + "/";
		return new URL(this.hosturl + url);
	}
}
