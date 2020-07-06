package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class UI {

	public JFrame frame;
	private JTextField textField;
	public static String str1="";
	public String textString="";
	public static int flag=1;
	public static String TextString="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					//创建Socket对象
					Socket client=new Socket("localhost",9999);
					
					//创建发送和接收的线程类对象
					Send send=new Send(client);
					Receive receive=new Receive(client);
					new Thread(send).start();
					new Thread(receive).start();
					UI window = new UI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("信息窗口");
		frame.setBounds(100, 100, 510, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(14, 28, 268, 233);
		frame.getContentPane().add(textArea);
		textArea.setLineWrap(true);
		
		
		textField = new JTextField();
		textField.setBounds(296, 53, 182, 66);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("发送");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flag=1;
				str1=textField.getText();
				textString=textString+"\n"+str1;
				textArea.setText(textString);
			}
		});
		btnNewButton.setBounds(342, 155, 103, 27);
		frame.getContentPane().add(btnNewButton);
	}
	
}
