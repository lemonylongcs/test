package edu.csuft.lx.go;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 棋子
 * 
 * @author longxia
 *
 */

public class Piece {

	//为悔棋功能声明静态变量用以传递坐标
	static int i;
	static int j;

	/**
	 * x坐标
	 */
	int x;

	/**
	 * y坐标
	 */
	int y;

	/**
	 * 大小（直径）
	 */
	int size;

	/**
	 * 颜色：默认黑
	 */
	boolean isBlack = true;

	/**
	 * 创建棋子
	 * 
	 * @param x
	 *            X 坐标
	 * @param y
	 *            Y 坐标
	 */
	public Piece(int x, int y) {
//		super();

		// 棋子大小
		size = 40;

		// 让棋子落在线上
		i = x / 55;
		j = y / 55;
		this.x = 12 + i * 55;
		this.y = 12 + j * 55;

	}

	//拷贝构造函数
	public Piece(Piece piece1) {
		this.x = piece1.x;
		this.y = piece1.y;
	}

	//棋子的equal方法用来判断当前位置棋子是否存在
	public boolean equals(Piece p) {
		if (this.x == p.x && this.y == p.y)
			return true;
		return false;
	}

	/**
	 * 绘制
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		// 设置颜色
		g.setColor(isBlack ? Color.BLACK : Color.WHITE);
		// 画圆
		g.fillOval(x, y, size, size);
	}
}
