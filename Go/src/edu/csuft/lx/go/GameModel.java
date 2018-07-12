package edu.csuft.lx.go;

/**
 * 游戏的逻辑（规则）
 * 
 * @author longxia
 *
 */

public class GameModel {

	/**
	 * 9x9 二维数组
	 */
	int[][] data = new int[9][9];

	/**
	 * 显示数据模型
	 */
	public void show() {
		System.out.println("-----------------------------------------------------------------");
		for (int[] row : data) {
			for (int e : row) {
				System.out.print(e + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 落了新的棋子，更新模型
	 * 
	 * @param piece
	 */
	public void update(Piece piece) {

		int j = piece.x / 52;
		int i = piece.y / 52;

		data[i][j] = piece.isBlack ? 1 : 2;

		show();
	}

	/**
	 * 判断棋局是否赢棋
	 */
	public boolean isBlackWin() {
		
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 9; j++) {
				if ((data[i][j] == data[i + 1][j]) && (data[i + 1][j] == data[i + 2][j])
						&& (data[i + 2][j] == data[i + 3][j]) && (data[i + 3][j] == data[i + 4][j])
						&& (data[i][j] == 1))
					return true;
			}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 5; j++) {
				if ((data[i][j] == data[i][j + 1]) && (data[i][j + 1] == data[i][j + 2])
						&& (data[i][j + 2] == data[i][j + 3]) && (data[i][j + 3] == data[i][j + 4])
						&& (data[i][j] == 1))
					return true;
			}
		
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				if ((data[i][j] == data[i + 1][j + 1]) && (data[i + 1][j + 1] == data[i + 2][j + 2])
						&& (data[i + 2][j + 2] == data[i + 3][j + 3]) && (data[i + 3][j + 3] == data[i + 4][j + 4])
						&& (data[i][j] == 1))
					return true;
			}
		
		for (int i = 0; i < 5; i++)
			for (int j = 8; j > 3; j--) {
				if ((data[i][j] == data[i + 1][j - 1]) && (data[i + 1][j - 1] == data[i + 2][j - 2])
						&& (data[i + 2][j - 2] == data[i + 3][j - 3]) && (data[i + 3][j - 3] == data[i + 4][j - 4])
						&& (data[i][j] == 1))
					return true;
			}
		return false;
	}
	
	public boolean isWhiteWin() {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 9; j++) {
				if ((data[i][j] == data[i + 1][j]) && (data[i + 1][j] == data[i + 2][j])
						&& (data[i + 2][j] == data[i + 3][j]) && (data[i + 3][j] == data[i + 4][j])
						&& (data[i][j] == 2))
					return true;
			}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 5; j++) {
				if ((data[i][j] == data[i][j + 1]) && (data[i][j + 1] == data[i][j + 2])
						&& (data[i][j + 2] == data[i][j + 3]) && (data[i][j + 3] == data[i][j + 4])
						&& (data[i][j] == 2))
					return true;
			}
		
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				if ((data[i][j] == data[i + 1][j + 1]) && (data[i + 1][j + 1] == data[i + 2][j + 2])
						&& (data[i + 2][j + 2] == data[i + 3][j + 3]) && (data[i + 3][j + 3] == data[i + 4][j + 4])
						&& (data[i][j] == 2))
					return true;
			}
		
		for (int i = 0; i < 5; i++)
			for (int j = 8; j > 3; j--) {
				if ((data[i][j] == data[i + 1][j - 1]) && (data[i + 1][j - 1] == data[i + 2][j - 2])
						&& (data[i + 2][j - 2] == data[i + 3][j - 3]) && (data[i + 3][j - 3] == data[i + 4][j - 4])
						&& (data[i][j] == 2))
					return true;
			}
		return false;
	}
}