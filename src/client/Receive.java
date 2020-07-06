package client;

import java.awt.TextArea;
import java.io.DataInputStream;
import java.net.Socket;

public class Receive implements Runnable{

	//用于接收数据的数据流
	DataInputStream dis;
	private boolean flag=true;
	
	public Receive(Socket client) {
		try {
			dis=new DataInputStream(client.getInputStream());
		} catch (Exception e) {
			flag=false;
			CloseUtil.closeAll(dis,client);
		}
	}

	private String getMessage() {
		String str="";
		try {
			str=dis.readUTF();
		} catch (Exception e) {
			flag=false;
			CloseUtil.closeAll(dis);
		}
		return str;
	}
	@Override
	public void run() {
		while(flag) {
			//System.out.println(this.getMessage());
			if(UI.flag==1) {
				System.out.println(this.getMessage());
				UI.TextString=this.getMessage();
				UI.flag=0;
			}
		}
	}

}
