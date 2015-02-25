package Server;

import java.io.*;
import java.net.*;

class Client {
	PrintWriter out;
	BufferedReader in;
	Socket skt;
	public Client() throws Exception{
		skt = new Socket("localhost", 1234);
		out = new PrintWriter(skt.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		set();
		get();
	}
	private void set() throws Exception{
		String data = "Bruker";
		System.out.print("Server has connected!\n");
		System.out.print("Sending string: '" + data + "'\n");
		out.print(data);
		out.flush();
		out.close();
	}
	private void get() throws Exception{
		while (!in.ready()) {}
		String hent = in.readLine();
		System.out.println(hent);
	}
	public static void main(String args[]) throws Exception {
		Client en = new Client();
	}
}