package javaWebServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler extends Thread{
	
	public final static String FILE_PATH = "/Users/soeunpark/Documents/lecture/this/hd/javascript/liveLecture/performance/performance1.mp4";
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
		InputStreamReader inputStreamReader = null;
		
		OutputStream responseStream = null;
		DataOutputStream dataOutputStream = null;
		
		try {
			requeststream = socketConnection.getInputStream();
			responseStream = socketConnection.getOutputStream();
			
			inputStreamReader = new InputStreamReader(requeststream);
			dataOutputStream = new DataOutputStream(responseStream);
			
			//filePath
			String file = FILE_PATH;
		} catch (IOException e) {
			logger.log(Level.INFO, "getStream Error : " + e);
		}
	}	
}
