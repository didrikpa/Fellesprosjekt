package Cliser;

import java.io.*;
import java.net.*;

class Servere {
	PrintWriter out;
	BufferedReader in;
	ServerSocket srvr;
	Socket skt;
	public Servere() throws Exception{
		srvr = new ServerSocket(1234);
		skt = srvr.accept();
		out = new PrintWriter(skt.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		set(get());
		
	}
	private void set(String tb) throws Exception{
		String data = tb;
		System.out.print("Server has connected!\n");
		System.out.print("About to send string: '" + data + "'\n");
		System.out.print("Sending string: '" + data + "'\n");
		out.print(data);
	}
	private String get() throws Exception{
		while (!in.ready()) {}
		String hent = in.readLine();
		System.out.println(hent);
		Server to = new Server();
		return to.getAll(hent);
	}
	public static void main(String args[]) throws Exception {
		Servere en = new Servere();
	}
}