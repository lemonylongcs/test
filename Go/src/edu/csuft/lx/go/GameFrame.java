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
 * 游戏窗口
 * 
 * @author longxia
 *
 */

public class GameFrame extends JFrame {

	/**
	 * 面板
	 */
	GamePanel gamePanel;

	GameModel model;

	// 人人对战
	public static boolean ismantoman = true;

	/**
	 * 构造方法：定义游戏窗口
	 */
	public GameFrame(GameModel model) {

		this.model = model;

		// 标题
		setTitle("五子棋 - lx 作品 ");
		// 大小
		setSize(516, 563);
		setResizable(false); // 窗口大小不可更改
		// 位置
		setLocationRelativeTo(null); // 窗口相对于组件的位置，设置为null即为窗口居中
		// 退出程序
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 创建GameJPanel
		gamePanel = new GamePanel(model);
		// setContentPane(gamePanel);
		add(gamePanel);

		// 添加菜单栏
		JMenuBar bar = new JMenuBar();

		JMenu pattern = new JMenu("对战模式");
		JRadioButtonMenuItem mantomachine = new JRadioButtonMenuItem("人机对战");
		JRadioButtonMenuItem mantoman = new JRadioButtonMenuItem("人人对战",true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(mantomachine);
		bg.add(mantoman);
		pattern.add(mantomachine);
		pattern.add(mantoman);
		bar.add(pattern);

		JMenu help = new JMenu("游戏帮助");
		JMenuItem back = new JMenuItem("我要悔棋");
		JMenuItem restart = new JMenuItem("再来一局");
		JMenuItem exit = new JMenuItem("退出游戏");
		help.add(back);
		help.add(restart);
		help.add(exit);
		bar.add(help);

		JMenu about = new JMenu("关于游戏");
		JMenuItem author = new JMenuItem("制作人：龙霞");
		about.add(author);
		bar.add(about);

		//注册菜单项退出的事件监听器
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//注册勾选人机模式的事件监听器
		mantomachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mantomachine.isSelected()) {
					ismantoman = false;
				}
			}
		});

		//注册勾选人人模式的事件监听器
		mantoman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mantoman.isSelected()) {
					ismantoman = true;
				}
			}
		});

		//注册再来一局的事件监听器
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.reopen();
			}
		});

		//注册悔棋操作的事件监听器
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.TackBack();
			}
		});

		add(bar, BorderLayout.NORTH);

		// 显示
		setVisible(true);
	}
}
