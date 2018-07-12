package edu.csuft.lx.go;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * ����
 * 
 * @author longxia
 *
 */

public class Piece {

	//Ϊ���幦��������̬�������Դ�������
	static int i;
	static int j;

	/**
	 * x����
	 */
	int x;

	/**
	 * y����
	 */
	int y;

	/**
	 * ��С��ֱ����
	 */
	int size;

	/**
	 * ��ɫ��Ĭ�Ϻ�
	 */
	boolean isBlack = true;

	/**
	 * ��������
	 * 
	 * @param x
	 *            X ����
	 * @param y
	 *            Y ����
	 */
	public Piece(int x, int y) {
//		super();

		// ���Ӵ�С
		size = 40;

		// ��������������
		i = x / 55;
		j = y / 55;
		this.x = 12 + i * 55;
		this.y = 12 + j * 55;

	}

	//�������캯��
	public Piece(Piece piece1) {
		this.x = piece1.x;
		this.y = piece1.y;
	}

	//���ӵ�equal���������жϵ�ǰλ�������Ƿ����
	public boolean equals(Piece p) {
		if (this.x == p.x && this.y == p.y)
			return true;
		return false;
	}

	/**
	 * ����
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		// ������ɫ
		g.setColor(isBlack ? Color.BLACK : Color.WHITE);
		// ��Բ
		g.fillOval(x, y, size, size);
	}
}
