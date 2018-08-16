package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;//id
	private JTextField textField_1;//�̸�
	private JTextField textField_2;//�����ð�
	private JTextField textField_3;//���ð�
	
	int hour = 0; 
	int min = 0;
	int sec = 0;

	javax.swing.Timer timer; 
	
	public void setTextField_1(String text) {
		textField_1.setText(text);
	}

	public void setTextField_2(String text) {
		textField_2.setText(text);;
	}

	/**
	 * Create the frame.
	 */
	////////////////////////////////////
	public Client(int i, String cur_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPcnumber = new JLabel((i+1) + " \uBC88 PC");
		lblPcnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPcnumber.setFont(new Font("Gulim", Font.PLAIN, 27));
		lblPcnumber.setBounds(145, 39, 200, 32);
		contentPane.add(lblPcnumber);
		// PC ��ȣ ��� ��
		
		JLabel lblClientid = new JLabel("\uD68C\uC6D0 I.D.");
		lblClientid.setFont(new Font("Gulim", Font.PLAIN, 27));
		lblClientid.setBounds(40, 155, 121, 32);
		contentPane.add(lblClientid);
	
		JLabel lblClientname = new JLabel("\uD68C\uC6D0 \uC774\uB984");
		lblClientname.setFont(new Font("Gulim", Font.PLAIN, 27));
		lblClientname.setBounds(40, 225, 121, 32);
		contentPane.add(lblClientname);
		
		JLabel lblResttime = new JLabel("\uB0A8\uC740 \uC2DC\uAC04");
		lblResttime.setFont(new Font("Gulim", Font.PLAIN, 27));
		lblResttime.setBounds(40, 285, 121, 32);
		contentPane.add(lblResttime);
		
		JLabel lblSpendtime = new JLabel("\uC0AC\uC6A9 \uC2DC\uAC04");
		lblSpendtime.setFont(new Font("Gulim", Font.PLAIN, 27));
		lblSpendtime.setBounds(40, 345, 121, 32);
		contentPane.add(lblSpendtime);
		// �� : ȸ�� id, ȸ�� �̸�, ���� �ð�, ��� �ð� 
		
		textField = new JTextField(cur_id);
		textField.setFont(new Font("Gulim", Font.PLAIN, 27));
		textField.setBounds(200, 150, 236, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Gulim", Font.PLAIN, 27));
		textField_1.setBounds(200, 220, 236, 38);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Gulim", Font.PLAIN, 27));
		textField_2.setBounds(200, 280, 236, 38);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Gulim", Font.PLAIN, 27));
		textField_3.setBounds(200, 340, 236, 38);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		// ��� �ʵ� : ȸ�� ID, ȸ�� �̸�, ���� �ð�, ��� �ð�
		
		JButton btnFood = new JButton("�԰Ÿ�����");
//		btnAddtime.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				Addtime at_Clientid=new Addtime();
//				at_Clientid.run();
//			}
//		});
		btnFood.setFont(new Font("Gulim", Font.PLAIN, 27));
		btnFood.setBounds(155, 449, 175, 40);
		contentPane.add(btnFood);
		
		JButton btnLogout = new JButton("\uC0AC\uC6A9 \uC885\uB8CC");
		btnLogout.setFont(new Font("Gulim", Font.PLAIN, 27));
		btnLogout.setBounds(40, 616, 175, 40);
		contentPane.add(btnLogout);
		
		JButton btnclose = new JButton("\uB2EB\uAE30");
		btnclose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				dispose();
			}
		});
		btnclose.setFont(new Font("Gulim", Font.PLAIN, 27));
		btnclose.setBounds(250, 616, 175, 40);
		contentPane.add(btnclose);
		// ��ư : �� ��, ��� ���� , �ݱ�'
		this.setVisible(true);
		//192.168.0.84
		String host="localhost";
		int port=7777;
		Socket socket=null;
		BufferedReader read=null;
		PrintWriter pw=null;
		
		try {
			socket=new Socket(host, port);
			
			pw=new PrintWriter(socket.getOutputStream());
			read= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			pw.println(i);
			pw.flush();
			pw.println(cur_id);
			pw.flush();
			
			timer = new javax.swing.Timer(1000, this); 
			timer.setInitialDelay(0); 
			timer.start(); 
		}catch(IOException ex){
			System.out.println(ex);
		}finally {
//			if(pw!=null) try { pw.close();} catch(Exception ex) {}
//			if(read!=null) try { read.close();} catch(IOException ex) {}
//			if(socket!=null) try { socket.close();} catch(IOException ex) {}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sec++; 		
		if(sec>=60) {
			min++;
			sec=0;
		}
		if(min>=60) {
			hour++;
			min=0;
		}
		
		textField_3.setText(hour + ":" + min + ": " + sec); 
	}
}