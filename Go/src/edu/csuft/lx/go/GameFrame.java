package edu.csuft.lx.go;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * ��Ϸ����
 * 
 * @author longxia
 *
 */

public class GameFrame extends JFrame {

	/**
	 * ���
	 */
	GamePanel gamePanel;

	GameModel model;

	// ���˶�ս
	public static boolean ismantoman = true;

	/**
	 * ���췽����������Ϸ����
	 */
	public GameFrame(GameModel model) {

		this.model = model;

		// ����
		setTitle("������ - lx ��Ʒ ");
		// ��С
		setSize(516, 563);
		setResizable(false); // ���ڴ�С���ɸ���
		// λ��
		setLocationRelativeTo(null); // ��������������λ�ã�����Ϊnull��Ϊ���ھ���
		// �˳�����
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ����GameJPanel
		gamePanel = new GamePanel(model);
		// setContentPane(gamePanel);
		add(gamePanel);

		// ��Ӳ˵���
		JMenuBar bar = new JMenuBar();

		JMenu pattern = new JMenu("��սģʽ");
		JRadioButtonMenuItem mantomachine = new JRadioButtonMenuItem("�˻���ս");
		JRadioButtonMenuItem mantoman = new JRadioButtonMenuItem("���˶�ս",true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(mantomachine);
		bg.add(mantoman);
		pattern.add(mantomachine);
		pattern.add(mantoman);
		bar.add(pattern);

		JMenu help = new JMenu("��Ϸ����");
		JMenuItem back = new JMenuItem("��Ҫ����");
		JMenuItem restart = new JMenuItem("����һ��");
		JMenuItem exit = new JMenuItem("�˳���Ϸ");
		help.add(back);
		help.add(restart);
		help.add(exit);
		bar.add(help);

		JMenu about = new JMenu("������Ϸ");
		JMenuItem author = new JMenuItem("�����ˣ���ϼ");
		about.add(author);
		bar.add(about);

		//ע��˵����˳����¼�������
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//ע�Ṵѡ�˻�ģʽ���¼�������
		mantomachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mantomachine.isSelected()) {
					ismantoman = false;
				}
			}
		});

		//ע�Ṵѡ����ģʽ���¼�������
		mantoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mantoman.isSelected()) {
					ismantoman = true;
				}
			}
		});

		//ע������һ�ֵ��¼�������
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.reopen();
			}
		});

		//ע�����������¼�������
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.TackBack();
			}
		});

		add(bar, BorderLayout.NORTH);

		// ��ʾ
		setVisible(true);
	}
}
