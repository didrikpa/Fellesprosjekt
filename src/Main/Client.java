package Cliser;

import java.io.*;
import java.net.*;

class Client {
	public static void main(String args[]) {
		try {
			String data = "all";
			Socket skt = new Socket("localhost", 1234);
			System.out.print("Server has connected!\n");
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			System.out.print("Sending string: '" + data + "'\n");
			out.print(data);
			out.close();
			skt.close();
			Socket stk = new Socket("localhost", 1234);
			BufferedReader in = new BufferedReader(new InputStreamReader(stk.getInputStream()));
			while (!in.ready()) {}
			String hent = in.readLine();
			System.out.println(hent);
			in.close();
			stk.close();
		}
		catch(Exception e) {
			System.out.print("Whoops! It didn't work!\n");
		}
	}
}