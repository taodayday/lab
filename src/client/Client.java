package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//����Socket����
		Socket client=new Socket("localhost",9999);
		
		//�������ͺͽ��յ��߳������
		Send send=new Send(client);
		Receive receive=new Receive(client);
		new Thread(send).start();
		new Thread(receive).start();
	}

}
