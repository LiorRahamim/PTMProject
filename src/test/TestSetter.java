package test;

// change this to match your code

import server_side.*;

public class TestSetter {
	

	static Server s; 
	
	public static void runServer(int port) {
		// put the code here that runs your server
		// for example:
		s=new MySerialServer(); 
		s.start(port, new MyTestClientHandler());
	}

	public static void stopServer() {
		// put the code here that stops your server
		// for example:
		s.stop();
	}
	

}
