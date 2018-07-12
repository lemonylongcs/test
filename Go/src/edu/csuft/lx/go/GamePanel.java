package edu.csuft.lx.go;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 面板（画布）
 * 
 * @author longxia
 *
 */

public class GamePanel extends JPanel {

	GameModel model;

	/**
	 * 内部类：鼠标事件的监听器
	 * 
	 * @author longxia
	 *
	 */
	class Listener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			//当为人人对战模式时
			if (GameFrame.ismantoman) {
			Piece piece = null;
			
			int x = e.getX();
			int y = e.getY();
			
			//把当前鼠标点击的坐标赋给piece
			for (int i = 0; i < 9; i++) {
				if (x >= 12 + i * 55 && x <= 52 + i * 55) {
					for (int j = 0; j < 9; j++) {
						piece = new Piece(x, y);
					}
				}
			}

			// piece不为空时
			if (piece != null) {
				
				//判断棋子颜色
				piece.isBlack = pieceList.size() % 2 == 0;

				//当前位置没有棋子
				if (!exist(piece)) {

					pieceList.add(piece);

					// 更新模型
					model.update(piece);

				}
				// 画布的重绘
				repaint();

				//人人对战赢棋提示
				if (model.isWhiteWin()) {
					JOptionPane.showMessageDialog(null, "白棋获胜！！");
				}

				if (model.isBlackWin()) {
					JOptionPane.showMessageDialog(null, "黒棋获胜！！");
				}
			}
		}
			//当为人机模式时
		else{
			
			Piece piece = null;
			int x = e.getX();
			int y = e.getY();
			
			for (int i = 0; i < 9; i++) {
				if (x >= 12 + i * 55 && x <= 52 + i * 55) {
					for (int j = 0; j < 9; j++) {

						piece = new Piece(x, y);

					}
				}

			}

			if (piece != null) {

				if (!exist(piece)) {

					pieceList.add(piece);

					// 更新模型
					model.update(piece);

				}
				// 画布的重绘
				repaint();

				//出现赢棋的消息提示框之后不用再执行之后的语句则写入return;
				if (model.isWhiteWin()) {
					JOptionPane.showMessageDialog(null, "白棋获胜！！");
					return;
				}

				if (model.isBlackWin()) {
					JOptionPane.showMessageDialog(null, "黒棋获胜！！");
					return;
				}
				
				//电脑自动走棋
				Piece pc=new Piece(piece);
				while(true) {
					if (!exist(pc)) {
						
						pc.isBlack=false;
						
						pieceList.add(pc);

						// 更新模型
						model.update(pc);
						
					// 画布的重绘
					repaint();
					break;
					
					}
					else
					{
						pc=change(pc);
					}
				}
			}
			
			//人机模式赢棋提示
			if (model.isWhiteWin()) {
				JOptionPane.showMessageDialog(null, "白棋获胜！！");
				return;
			}

			if (model.isBlackWin()) {
				JOptionPane.showMessageDialog(null, "黒棋获胜！！");
				return;
			}
		}
	}
		
		//调用随机函数在周围随机产生棋子
		public Piece change(Piece p) {
			int x = (int) ((Math.random() - 0.5) * 4);
			int y = (int) ((Math.random() - 0.5) * 4);

			if ((p.x + x * 55 > 0) && (p.x + x * 55 < 468) && (p.y + y * 55 > 0) && (p.y + y * 55 < 468))
				p = new Piece(p.x + x * 65, p.y + y * 63);
			return p;
		}

		// 同一位置只能落一子
		boolean exist(Piece p) {
			for (Piece piece : pieceList) {
				if (p.equals(piece))
					return true;
			}
			return false;

		}

	}

	// 悔棋功能的实现
	public void TackBack() {
		if (pieceList.isEmpty())
			return;
		int s = pieceList.size();
		int i = pieceList.get(--s).i;
		int j = pieceList.get(s).j;
		model.data[i][j] = 0;
		pieceList.remove(s);
		repaint();

	}

	// 再来一局的实现
	public void reopen() {
		pieceList.clear();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				model.data[i][j] = 0;
			}
		}
		repaint();

	}

	/**
	 * 棋盘
	 */
	Image bg;

	/**
	 * 列表：存储所有的棋子
	 */
	ArrayList<Piece> pieceList = new ArrayList<>();

	/**
	 * GamePanel构造方法
	 * 
	 * @return
	 * @return
	 * @return
	 */
	public GamePanel(GameModel model) {

		this.model = model;

		try {
			bg = ImageIO.read(new File("res/go.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 注册（鼠标）事件监听器
		addMouseListener(new Listener());
	}

	/**
	 * 绘制
	 */
	public void paint(Graphics graphics) {
		super.paint(graphics);

		// 有更多的参数可设置
		Graphics2D g = (Graphics2D) graphics;
		// 设置渲染参数：抗锯齿 使棋子边缘变得光滑
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 绘制棋盘
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

		// 绘制棋子
		for (Piece piece : pieceList) {
			piece.paint(g);
		}
	}
}
