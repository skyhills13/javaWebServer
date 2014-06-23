package javaWebServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.crypto.Data;

public class RequestHandler extends Thread{
	
	public final static String FILE_PATH = "/Users/soeunpark/Desktop/test.mp4";
	private final static Logger logger = Logger.getLogger(WebServer.class.getName());
	
	Socket socketConnection;
	
	public RequestHandler(Socket socket) {
		this.socketConnection = socket;
	}

	@Override
	public void start() {
		logger.log(Level.INFO, "WebServer Thread Created!");
		InputStream requeststream = null;
		//inputstream을 효과적으로 사용하기 위한 wrapper class
		//일반적인 inputStream은 한바이트씩 처리하나, inputStreamReader는 chunk 형태로 처리가 가능하다.
		//이렇게 wrapper클래스를 활용하는 패턴을 decorate pattern이라고 한다.
		//사용자가 데이터를 입력할때나 필요하지만, 난 필요음슴 
		//InputStreamReader inputStreamReader = null;
		
		OutputStream responseStream = null;
		DataOutputStream dataOutputStream = null;
		
		try {
			requeststream = socketConnection.getInputStream();
			responseStream = socketConnection.getOutputStream();
			
			//inputStreamReader = new InputStreamReader(requeststream);
			dataOutputStream = new DataOutputStream(responseStream);
			
			//filePath
			String filePath = FILE_PATH;
			File file = new File(filePath);
			
			if(file.exists()){
				//데이터를 여러가지 형태로 보낼수 있도록 도와주는 wrapper class
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				byte[] arr = new byte[1024];
				int len = 0;
				while((len = in.read(arr)) != -1){
					dataOutputStream.write(arr);
				}
			}
		} catch (IOException e) {
			logger.log(Level.INFO, "getStream Error : " + e);
		}
	}	
}
