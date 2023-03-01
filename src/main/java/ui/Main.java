/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import board.Board;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author pawel
 */
public class Main {
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Player number");
        int choice = scanner.nextInt();
        
        Board board = new Board(); 
        board.setBoardLayout(choice);
        board.randomizeBoard();
        
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel(board);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setAlwaysOnTop(true);
       
        window.setLayout(new GridBagLayout());
        window.setMinimumSize(new Dimension(1000,1000));
        
        window.add(gamePanel);   
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
        gamePanel.startGameThread();
        
        
        while(true){
            System.out.println("Insert row column and value ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int value = scanner.nextInt();
            board.modifyCell(row, col, value);
            window.setVisible(false);
            window.setVisible(true);
        }
        
        
        
        
        
    }
    
}
