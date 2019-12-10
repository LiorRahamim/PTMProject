package server_side;

import java.net.*;
import java.io.*;

public class MySerialServer implements Server {

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;
    private ClientHandler ch;
    private volatile boolean stop;
    
    public MySerialServer() {
    	this.serverSocket = null;
    	this.clientSocket = null;
    	stop = false;
    }
	
    @Override
    public void start(int port, ClientHandler ch) {
    	new Thread(()->{
			try {
				runServer(port, ch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
    }
    
    @Override
    public void stop() {
    	stop = true;
    }
    
    private void runServer(int port, ClientHandler ch) throws Exception {
    	ServerSocket server = new ServerSocket(port);
    	server.setSoTimeout(1000);
    	while(!stop) {
    		try {
    			Socket aClient = server.accept(); // blocking call
    			try {
    				ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
    				
    				// TODO: input output to socket
    				
    				aClient.getInputStream().close();
    				aClient.getOutputStream().close();
    				aClient.close();
    			}
    			catch(IOException e) {
    				// TODO: handle exception
    			}
    		}
    		catch (SocketTimeoutException e) {
				// TODO: handle exception
			}
    	}
    	server.close();
    }
}

