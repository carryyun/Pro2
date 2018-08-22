package views.server;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import server.Main_GUI_Event;
import server.Seat;
import server.SeatThread;

public class SeatView extends JFrame{
	JFrame jf_main;			// ����������
	JPanel pan_main_seat;	//��ü �¼� �г�
	JPanel[] pan_seat;		//�¼�1ĭ�� ������ �г�
	JPanel[] pan_left;		//�¼��� ���� �׸���� JLabel�� ������ �г�
	JPanel[] pan_right;		//�¼��� ������ ������ JLabel�� ������ �г�

	JLabel[] lb_time;		//�����ð��� �˷��� ��
	JLabel[] lb_cur_time;	//���ð��� �˷��� ��
	JLabel[] lb_id; 		//���� �α����� ���̵� �˷��� ��

	JLabel[] lb_time_value;		//�����ð��� �˷��� ��
	JLabel[] lb_cur_time_value;	//���ð��� �˷��� ��
	JLabel[] lb_id_value;		//���� �α����� ���̵� �˷��� ��
	
	private Socket socket;

	public SeatView(){
		//��ü �¼����� �ѹ��� �����ϱ� ���� MAX���
		final int MAX=20;
		jf_main=new JFrame("PC��");
		jf_main.setLayout(new BorderLayout(10,20));
		//JPanel
		pan_main_seat=new JPanel();
		pan_main_seat.setLayout(new GridLayout(4, 5, 30, 50));
		pan_seat=new JPanel[MAX];
		pan_left=new JPanel[MAX];
		pan_right=new JPanel[MAX];
		//JLabel
		lb_time=new JLabel[MAX];
		lb_cur_time=new JLabel[MAX];
		lb_id=new JLabel[MAX];
		lb_time_value=new JLabel[MAX];
		lb_cur_time_value=new JLabel[MAX];
		lb_id_value=new JLabel[MAX];

		//���� ������Ʈ�� ���� �¼��� 1ĭ ������ �߰��ϱ����� �г�.
		for(int i=0;i<MAX;i++) {
			pan_seat[i]=new JPanel();
			pan_seat[i].setLayout(new GridLayout(1, 2));
			pan_seat[i].setBackground(Color.GRAY);
			pan_seat[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			pan_seat[i].setBorder(new TitledBorder(new LineBorder(Color.white,4), (i+1)+" PC"));

			pan_left[i]=new JPanel();
			pan_right[i]=new JPanel();
			pan_left[i].setBackground(Color.GRAY);
			pan_right[i].setBackground(Color.GRAY);
			pan_left[i].setLayout(new BoxLayout(pan_left[i],BoxLayout.Y_AXIS));
			pan_right[i].setLayout(new BoxLayout(pan_right[i],BoxLayout.Y_AXIS));

			lb_time[i]=new JLabel("�����ð�:");
			lb_cur_time[i]=new JLabel("���ð�:");
			lb_id[i] = new JLabel("���̵�:");
			lb_time_value[i]=new JLabel(" ");
			lb_cur_time_value[i]=new JLabel(" ");
			lb_id_value[i] = new JLabel("�����");
			lb_time_value[i].setForeground(Color.white);
			lb_cur_time_value[i].setForeground(Color.white);
			lb_id_value[i].setForeground(Color.white);

			pan_left[i].add(lb_time[i]);
			pan_left[i].add(lb_cur_time[i]);
			pan_left[i].add(lb_id[i]);
			pan_right[i].add(lb_time_value[i]);
			pan_right[i].add(lb_cur_time_value[i]);
			pan_right[i].add(lb_id_value[i]);

			pan_seat[i].add(pan_left[i]);
			pan_seat[i].add(pan_right[i]);
			pan_main_seat.add(pan_seat[i]);
		}
		jf_main.add(pan_main_seat,BorderLayout.CENTER);//�¼��г��� CENTER�� �߰�
		
		//������ ��ư�޴��鸦 ���� GridLayout
		JPanel pan_btn = new JPanel();
		pan_btn.setLayout(new GridLayout(7, 1));

		//�� ��ư�� ���� ������ ����
		Main_GUI_Event env = new  Main_GUI_Event();

		JButton btn1 =new JButton("������Ȳ");
		btn1.addActionListener(env);
		JButton btn2 =new JButton("�������");
		btn2.addActionListener(env);
		JButton btn3 =new JButton("����");
		btn3.addActionListener(env);
		JButton btn4 =new JButton("ȸ������");
		btn4.addActionListener(env);

		pan_btn.add(btn1);
		pan_btn.add(new JLabel(""));
		pan_btn.add(btn2);
		pan_btn.add(new JLabel(""));
		pan_btn.add(btn3);
		pan_btn.add(new JLabel(""));
		pan_btn.add(btn4);
		jf_main.add(pan_btn,BorderLayout.EAST);

		jf_main.setSize(900, 800);
		jf_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_main.setVisible(true);
	}
	
	//�� �¼��� �����ʸ� �߰����� �޼���(SeatView������ ���ȴ�)
	//i	:PC��ȣ/ pan_seat	: �¼�ĭ
	//lb_id_value	 	:ID ��� ��
	//lb_cur_time_value	:���ð� ��� ��
	//lb_time_value		:�����ð� ��� ��
	//Seat				:�¼��� �������� ClientInfo�� ����� ������(�����ʹ�ư���� �����ʿ� ������ �����ڰ� �ٸ���)
	private Seat GetCom(int i) {
		Seat seat =new Seat(lb_time_value[i], lb_cur_time_value[i], lb_id_value[i], pan_seat[i]);
		pan_seat[i].addMouseListener((MouseListener) new Main_GUI_Event(i, seat, lb_id_value[i], lb_cur_time_value[i], lb_time_value[i],this));
		return seat;
	}
	//Ŭ���̾�Ʈ������ ������ �����ְ� �������� �����Ҷ� Ŭ���̾��Ʈ�� �޽����� �����µ� ����ϱ�����
	private void SetSocket(Socket socket) {
		this.socket=socket;
	}
	public Socket GetSocket() {
		return socket;
	}

	public static void main(String[] args) {
		SeatView sv = new SeatView();
		ServerSocket server=null;
		PrintWriter[] arr=null;
		Seat[] seat=new Seat[20];
		
		//�� �¼��� �����ʸ� �߰����ֱ� ����
		for(int i=0; i<20;i++) {
			seat[i] =sv.GetCom(i);
		}
		try{
			server=new ServerSocket(7777);
			//�迭�� ä�ñ���� ������ �����ص� �������� ====================================================================================
			arr=new PrintWriter[20];
			while(true) {
				System.out.println("���Ӵ����");
				Socket soc=server.accept(); 
				SeatThread th=new SeatThread(soc,arr,seat,sv.lb_time_value,sv.lb_cur_time_value);
				sv.SetSocket(soc);
				Thread job=new Thread(th);
				job.start();
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());  
		}
	}
}
