import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.parser.ParseException;

public class Tester {

	public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException {
		
		Thunder thunder = new Thunder("localhost", 8080, "key", "secret");		
		System.out.println(thunder.getUserInChannel("default-channel"));
		System.out.println(thunder.getUserCount());
	}	
}
