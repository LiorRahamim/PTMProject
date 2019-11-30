package server_side;

import java.net.*;
import java.io.*;

public class MySerialServer implements Server {

	private ServerSocket serverSocket;
    private Socket clientSocket;
    
    public MySerialServer() {
    	this.serverSocket = null;
    	this.clientSocket = null;
    }
	
	@Override
	public void start(int port, ClientHandler c) {
		
		// Starting server on given port
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listening on port: " + port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
        
        // Accepting client connection
        while (true) {
	        try {
	            clientSocket = serverSocket.accept();
	            
	            c.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
	            
	            // TODO Create Thread for every connection
	            
	        } catch (IOException e) {
	            System.err.println("Accept failed");
	            System.exit(1);
	        }
        }
        
//        // Reading client message
//        try {
//        	out = new PrintWriter(clientSocket.getOutputStream());
//        	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		} catch (IOException e) {
//			System.err.println("Read failed");
//            System.exit(1);
//		}
//        
//        // Send message to client
//        String input;
//        while (true) {
//			try {
//				input = in.readLine();
//				if (input == "exit") {
//					break;
//				}
//				out.println(input);
//			} catch (IOException e) {
//				System.out.println("Read failed");
//		        System.exit(-1);
//			}
//		}
//        
//        stop();
	}

	@Override
	public void stop() {
        try {
        	clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
