package Cliser;

import java.io.*;
import java.net.*;

class Servere {
	public static void main(String args[]) {
		try {
			while(true){
				String data = null;
				ServerSocket srvr = new ServerSocket(4321);
				Socket skt = srvr.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
				PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
				while (!in.ready()) {}
				String hent = in.readLine();
				in.close();
				System.out.println("Mottat data: " + hent);
				Server en = new Server();
				data = en.getAll("Ansatt");
				System.out.print("Sending string: '" + data + "'\n");
				out.print(data);
				out.close();
				skt.close();
				srvr.close();
				break;
			}
		}
		catch(Exception e) {
			System.out.print("Whoops! It didn't work!\n");
		}
	}
}