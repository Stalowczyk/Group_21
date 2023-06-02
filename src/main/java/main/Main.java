package main;

import board.Board;
import entity.Bag;
import entity.CommonGoals;
import entity.PersonalGoalsCards;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws IOException {

		JFrame window = new JFrame();
		window.setLayout(new GridBagLayout());
		GamePanel gamePanel = new GamePanel();

		ImageIcon image = new ImageIcon("src/main/resources/logo-cranio.png");
		window.setIconImage(image.getImage());
		window.setTitle("  My Shelfie - Group 21");

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setAlwaysOnTop(false);
		window.add(gamePanel);

		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}
}
