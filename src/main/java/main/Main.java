/*
ssdddd * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import board.Board;
import entity.Bag;
import entity.PersonalGoalsCards;

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

    public static void main(String[] args) {
        //BUG SISTEMARE DIMENSIONI IN BASE ALLA RISOLUZIONE DEL PC 
        GamePanel gamePanel = new GamePanel();

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setAlwaysOnTop(false);

        window.setLayout(new GridBagLayout());

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
