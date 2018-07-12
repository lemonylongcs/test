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
 * ��壨������
 * 
 * @author longxia
 *
 */

public class GamePanel extends JPanel {

	GameModel model;

	/**
	 * �ڲ��ࣺ����¼��ļ�����
	 * 
	 * @author longxia
	 *
	 */
	class Listener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			//��Ϊ���˶�սģʽʱ
			if (GameFrame.ismantoman) {
			Piece piece = null;
			
			int x = e.getX();
			int y = e.getY();
			
			//�ѵ�ǰ����������긳��piece
			for (int i = 0; i < 9; i++) {
				if (x >= 12 + i * 55 && x <= 52 + i * 55) {
					for (int j = 0; j < 9; j++) {
						piece = new Piece(x, y);
					}
				}
			}

			// piece��Ϊ��ʱ
			if (piece != null) {
				
				//�ж�������ɫ
				piece.isBlack = pieceList.size() % 2 == 0;

				//��ǰλ��û������
				if (!exist(piece)) {

					pieceList.add(piece);

					// ����ģ��
					model.update(piece);

				}
				// �������ػ�
				repaint();

				//���˶�սӮ����ʾ
				if (model.isWhiteWin()) {
					JOptionPane.showMessageDialog(null, "�����ʤ����");
				}

				if (model.isBlackWin()) {
					JOptionPane.showMessageDialog(null, "�\���ʤ����");
				}
			}
		}
			//��Ϊ�˻�ģʽʱ
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

					// ����ģ��
					model.update(piece);

				}
				// �������ػ�
				repaint();

				//����Ӯ�����Ϣ��ʾ��֮������ִ��֮��������д��return;
				if (model.isWhiteWin()) {
					JOptionPane.showMessageDialog(null, "�����ʤ����");
					return;
				}

				if (model.isBlackWin()) {
					JOptionPane.showMessageDialog(null, "�\���ʤ����");
					return;
				}
				
				//�����Զ�����
				Piece pc=new Piece(piece);
				while(true) {
					if (!exist(pc)) {
						
						pc.isBlack=false;
						
						pieceList.add(pc);

						// ����ģ��
						model.update(pc);
						
					// �������ػ�
					repaint();
					break;
					
					}
					else
					{
						pc=change(pc);
					}
				}
			}
			
			//�˻�ģʽӮ����ʾ
			if (model.isWhiteWin()) {
				JOptionPane.showMessageDialog(null, "�����ʤ����");
				return;
			}

			if (model.isBlackWin()) {
				JOptionPane.showMessageDialog(null, "�\���ʤ����");
				return;
			}
		}
	}
		
		//���������������Χ�����������
		public Piece change(Piece p) {
			int x = (int) ((Math.random() - 0.5) * 4);
			int y = (int) ((Math.random() - 0.5) * 4);

			if ((p.x + x * 55 > 0) && (p.x + x * 55 < 468) && (p.y + y * 55 > 0) && (p.y + y * 55 < 468))
				p = new Piece(p.x + x * 65, p.y + y * 63);
			return p;
		}

		// ͬһλ��ֻ����һ��
		boolean exist(Piece p) {
			for (Piece piece : pieceList) {
				if (p.equals(piece))
					return true;
			}
			return false;

		}

	}

	// ���幦�ܵ�ʵ��
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

	// ����һ�ֵ�ʵ��
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
	 * ����
	 */
	Image bg;

	/**
	 * �б��洢���е�����
	 */
	ArrayList<Piece> pieceList = new ArrayList<>();

	/**
	 * GamePanel���췽��
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

		// ע�ᣨ��꣩�¼�������
		addMouseListener(new Listener());
	}

	/**
	 * ����
	 */
	public void paint(Graphics graphics) {
		super.paint(graphics);

		// �и���Ĳ���������
		Graphics2D g = (Graphics2D) graphics;
		// ������Ⱦ����������� ʹ���ӱ�Ե��ù⻬
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// ��������
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

		// ��������
		for (Piece piece : pieceList) {
			piece.paint(g);
		}
	}
}
