package hwBasicHTTPServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void respond404(int socketNum) {

	}

	public void openServer(int socketNum) throws Exception {
		ServerSocket myServer = new ServerSocket(socketNum);

		while (true) {
			System.out.println("Working");
			Socket socket = myServer.accept();
			// reads in argument sent by client
			Scanner input = new Scanner(socket.getInputStream());
			input.next(); // fix me to need "GET", else exception
			String filename = "Resources" + input.next();
			if (filename.equals("Resources/")) {
				filename = "Resources/Index.html";
			}
			File file = new File(filename);

			PrintWriter outputHeader = new PrintWriter(socket.getOutputStream());

			System.out.println(file.getCanonicalPath());
			if (!file.getAbsolutePath().equals(file.getCanonicalPath())) {
				throw new FileNotFoundException();
//				outputHeader.println("HTTP/1.1 404 FILE NOT FOUND");
//				outputHeader.println("Content-Length: 0");
//				outputHeader.println();
//				outputHeader.flush();
//				input.close();
//				continue;
			}

			byte[] fileBytes = {};
			// reads in bytes from file
			FileInputStream fileStream = new FileInputStream(file.getCanonicalPath());
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

			input.close();
			socket.close();
		}
	}
}
