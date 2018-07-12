package edu.csuft.lx.go;

/**
 * 程序的起点
 * 
 * @author longxia
 *
 */

public class App {
	
	public static void main(String[] args) {

		// 游戏逻辑：模型
		GameModel model = new GameModel();

		// 游戏窗口：界面、视图
		GameFrame frame = new GameFrame(model);
	}
}
