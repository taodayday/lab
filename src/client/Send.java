package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	//从键盘获取数据
	private BufferedReader br;
	//发送数据的数据流
	private DataOutputStream dos;
	private boolean flag=true;
	public Send() {
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	public Send(Socket client) {
		this();
		try {
			dos=new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			flag=false;
			CloseUtil.closeAll(dos,client);
		}
		
	}
	
	private String getMessage() {
		String str="";
		/*try {
			str =br.readLine();
		}catch(IOException e) {
			flag=false;
			CloseUtil.closeAll(br);
		}*/
		str=UI.str1;
		return str;
	}
	private void send(String str) {
		try {
			dos.writeUTF(str);
			dos.flush();
		} catch (Exception e) {
			flag=false;
			CloseUtil.closeAll(dos);
		}
	}
	
	@Override
	public void run() {
		while(flag) {
			this.send(getMessage());
			
		}
	}
}
