import java.io.*;
import java.net.*;
import java.util.Scanner;

import server_side.MySerialServer;
import server_side.MyTestClientHandler;
import server_side.Server;

public class RunProj {

	public static void main(String[] args) {
		Server s;
		int port = 8000;
		
		s = new MySerialServer();
		s.start(port, new MyTestClientHandler());
		
		runClient(port);
		
		s.stop();
	}
	
	public static void runClient(int port) {
		Socket s=null;
		PrintWriter out=null;
		BufferedReader in=null;
		try{
			s=new Socket("127.0.0.1",port);
			s.setSoTimeout(3000);
			out=new PrintWriter(s.getOutputStream());
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					
			
			System.out.println("\tsending problem...");
			out.println("Hello");
			out.flush();
			System.out.println("\tproblem sent, waiting for solution...");
			System.out.println(in.readLine());
			System.out.println("\tsolution received");
			
			
			out.println("end");
			out.flush();
			
		}catch(SocketTimeoutException e){
			System.out.println("\tYour Server takes over 3 seconds to answer (-20)");
		}catch(IOException e){
			System.out.println("\tYour Server ran into some IOException (-20)");
		}finally{
			try {
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				System.out.println("\tYour Server ran into some IOException (-20)");
			}
		}
	}

}
