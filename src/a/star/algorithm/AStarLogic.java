/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.star.algorithm;

import java.lang.*;
import java.util.*;

/**
 *
 * @author Carrt
 */
public class AStarLogic {

    public void printBoard(Node[][] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < nodes[0].length; j++) {
                System.out.print(nodes[i][j].getType());
                System.out.print(" ");
            }
        }
    }
    public void printBoard(int[][] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < nodes[0].length; j++) {
                if(nodes[i][j] == 2) System.out.print('X');
                else System.out.print(nodes[i][j]);
                System.out.print(" ");
            }
        }
    }

    public void placeTenPercent(int[][] board) {
        int toPlace = (board.length * board[0].length) / 10;
        Random r = new Random();
        for (int i = 0; i < toPlace;) {
            int x = r.nextInt(board.length);
            int y = r.nextInt(board.length);
            if (board[x][y] == 0) {
                board[x][y] = 1;
                i++;
            }
        }
        // uncomment this, and use 14 14 as goal node to test path not found
        //board[13][14] = 1;
        //board[14][13] = 1;
    }

    public void intBoardToNodeBoard(int[][] iB, Node[][] nB) {
        for (int i = 0; i < iB.length; i++) {
            for (int j = 0; j < iB[0].length; j++) {
                nB[i][j] = new Node(i, j, iB[i][j]);
            }
        }
    }

    public Node getStartNode(Node[][] nodes) {
        Scanner sc = new Scanner(System.in);
        Boolean cont = false;
        Node n = nodes[0][0];
        while (!cont) {
            System.out.println("\nPlease enter an X value for the starting node");
            int x = sc.nextInt();
            System.out.println("\nPlease enter an Y value for the starting node");
            int y = sc.nextInt();
            if (x < nodes.length && y < nodes[0].length) {
                n = nodes[x][y];
                cont = true;
            } else {
                System.out.println("Node not found. Please try again");
            }
        }
        return n;
    }

    public Node getGoalNode(Node[][] nodes) {
        Scanner sc = new Scanner(System.in);
        Boolean cont = false;
        Node n = nodes[0][0];
        while (!cont) {
            System.out.println("\nPlease enter an X value for the goal node");
            int x = sc.nextInt();
            System.out.println("\nPlease enter an Y value for the goal node");
            int y = sc.nextInt();
            if (x < nodes.length && y < nodes[0].length) {
                n = nodes[x][y];
                cont = true;
            } else {
                System.out.println("Node not found. Please try again");
            }
        }
        return n;
    }

    public void getHeuristic(Node[][] nodes, Node n) {
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                //manhattan method |horizontal distance| + |vertical distance|
                nodes[i][j].setH((10 * (Math.abs(i - n.getRow()))) + (10 * (Math.abs(j - n.getCol()))));
            }
        }
    }
}
