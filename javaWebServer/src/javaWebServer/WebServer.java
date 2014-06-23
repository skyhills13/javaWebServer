package javaWebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer {
	private final static Logger logger = Logger.getLogger(WebServer.class.getName());
	
	public static void main(String[] args) {
		try {
			//서버 소켓 생성. 웹서버는 기본적으로 80포트 사용 
			ServerSocket listenSocket = new ServerSocket(3000);
			
			logger.log(Level.INFO, "WebServer Socket Created");
			
			//클라이언트가 연결될때까지 대기한다.
			Socket connection;
			while((connection = listenSocket.accept()) != null) {
				RequestHandler requestHandler = new RequestHandler(connection);
				requestHandler.start();
			}
			
			if(listenSocket != null){
				listenSocket.close();
			}
			
		} catch (IOException e) {
			logger.log(Level.INFO, "Create Webserver Error: " + e);
		}
		
		
	}
}
