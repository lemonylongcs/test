package edu.csuft.lx.go;

/**
 * ��������
 * 
 * @author longxia
 *
 */

public class App {
	
	public static void main(String[] args) {

		// ��Ϸ�߼���ģ��
		GameModel model = new GameModel();

		// ��Ϸ���ڣ����桢��ͼ
		GameFrame frame = new GameFrame(model);
	}
}
