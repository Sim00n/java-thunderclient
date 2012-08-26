---------------------------
Thunderpush client for Java
---------------------------

A Java library for sending messages to the `Thunderpush <https://github.com/thunderpush/thunderpush>`_ server.

Example
=======

::
	
	import java.io.IOException;
	
	class Tester {
		
		public static void main(String[] args) throws IOException {

			String[] message = new String[2];
			message[0] = "msg=hello";
			message[1] = "name=tester";

			Thunder thunder = new Thunder("localhost", 8080, "key", "secret");		
			
			System.out.println(thunder.getUserInChannel("default-channel"));
			System.out.println(thunder.getUserCount());
			System.out.println(thunder.sendMessageToUser("test-user", message));
			System.out.println(thunder.sendMessageToChannel("test-channel", message));
			System.out.println(thunder.isUserOnline("test-user"));
			System.out.println(thunder.disconnectUser("test-user"));
		}
	}
