package server_side;

import java.net.*;
import java.io.*;

public class MySerialServer implements Server {

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public MySerialServer() {
    	this.serverSocket = null;
    	this.clientSocket = null;
    	this.out = null;
    	this.in = null;
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
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Accepted client");
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }
        
        // Reading client message
        try {
        	out = new PrintWriter(clientSocket.getOutputStream());
        	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Read failed");
            System.exit(1);
		}
        
        // Send message to client
        String input;
        while (true) {
			try {
				input = in.readLine();
				if (input == "exit") {
					break;
				}
				out.println(input);
			} catch (IOException e) {
				System.out.println("Read failed");
		        System.exit(-1);
			}
		}
        
        stop();
	}

	@Override
	public void stop() {
        try {
        	out.close();
        	in.close();
        	clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
