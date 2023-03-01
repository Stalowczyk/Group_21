/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author pawel
 */

import board.Board;
import javax.swing.JFrame;

public class testSquare {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
                Board board = new Board();
		GamePanel gp = new GamePanel(board);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Title");
		window.add(gp);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gp.startGameThread();
		
	}

}
