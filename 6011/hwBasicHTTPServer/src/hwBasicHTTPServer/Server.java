package hwBasicHTTPServer;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	public void openServer(int socketNum) throws Exception {
		ServerSocket myServer = new ServerSocket(socketNum);
		while (true) {
			System.out.println("Working");
			Socket socket = myServer.accept();

			// reads in argument sent by client
			Scanner input = new Scanner(socket.getInputStream());
			input.next(); //fix me to need "GET", else exception
			String filename = "Resources" + input.next();
			System.out.println(filename);

			byte[] fileBytes = {};
			PrintWriter outputHeader = new PrintWriter(socket.getOutputStream());

			try {
				// reads in bytes from file
				if (filename.equals("Resources/")) {
					filename = "Resources/Index.html";
				}
				FileInputStream fileStream = new FileInputStream(filename);
				// get cannonical path
				fileBytes = fileStream.readAllBytes();
				fileStream.close();

				// sends HTML protocol to client
				outputHeader.println("HTTP/1.1 200 OK");
				outputHeader.println("Content-Length: " + fileBytes.length);
				outputHeader.println();
				outputHeader.flush();

				// outputs bytes
				OutputStream outputBody = socket.getOutputStream();
				outputBody.write(fileBytes);
				outputBody.flush();

				// if filename not found throws 404 error
			} catch (Exception e) {
				outputHeader.println("HTTP/1.1 404 File Not Found");
				outputHeader.println("Content-Length: 19");
				outputHeader.println();
				outputHeader.println("404: File Not Found");
				outputHeader.flush();
			}

//////////////////////////////////////////////////////////////////////////////////////////
//			DOES NOT WORK CONSISTENTLY! .available() = BAD!!!
//			ArrayList<Byte> fileBytes = new ArrayList<Byte>();
//			while (fileStream.available() > 0) {
//			fileBytes.add((byte) fileStream.read());
//			}
//////////////////////////////////////////////////////////////////////////////////////////

			input.close();
			socket.close();
		}
	}
}
