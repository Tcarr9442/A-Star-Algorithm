/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.star.algorithm;

import java.util.*;

/**
 *
 * @author Carrt
 */
public class AStarAlgorithm {

    //globals
    public static int x = 15;
    public static int y = 15;

    public static int[][] board = new int[x][y];
    public static Node[][] node = new Node[x][y];
    public static Node start;
    public static Node goal;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //make board
        AStarLogic A = new AStarLogic();

        //place 10%
        A.placeTenPercent(board);

        //convert int board to node board
        A.intBoardToNodeBoard(board, node);

        //print board
        A.printBoard(node);

        //get start point
        start = A.getStartNode(node);

        //get end point
        goal = A.getGoalNode(node);

        //get heuristic of goal
        A.getHeuristic(node, goal);

        //prepare the starting node
        start.setG(0);
        start.setH(node[start.getRow()][start.getCol()].getH());
        start.setF();

        //create array lists to store visited and unvisited nodes
        ArrayList<Node> toVisit = new ArrayList();
        ArrayList<Node> visited = new ArrayList();
        toVisit.add(start); //adding starting node to to visit as a place to start

        //while loop to continue path finding until path is found
        boolean cont = true;//true means loop, false means done

        //iteration counter
        int iteration = 0;
        
        while (cont) {
            //get the first path to go
            Node n = toVisit.remove(0);

            //check to see if you are at goal
            if (n.equals(goal)) {
                System.out.println("\n\nGoal Reached\n");
                do {
                    board[n.getRow()][n.getCol()] = 2; //2 gets replaced with X
                    n = n.getParent();
                } while (!n.equals(start));
                //the start isnt getting set to X, so heres this :^)
                board[start.getRow()][start.getCol()] = 2;
                A.printBoard(board);
                
                //closing information
                System.out.println("\n\nThe solution was found! It took " + iteration + " iterations to get there.\nSee the X's on the map above for the path.\nScroll highers to see the steps the algorithm took");

                //break the loop
                cont = false;
            } else {
                //get current position
                int currentRow = n.getRow();
                int currentColumn = n.getCol();

                //search for neighbors at current position
                //(+1, 0)
                if (currentRow != 14 && board[currentRow + 1][currentColumn] == 0) {
                    //create temp node for evaluation                    
                    Node temp = new Node(currentRow + 1, currentColumn, 0);

                    //set parent
                    temp.setParent(n);

                    //assign an F value
                    temp.setG(n.getG() + 10); //10 is arbitrary
                    temp.setH(node[temp.getRow()][temp.getCol()].getH());
                    temp.setF();

                    //check to see if it has been visited before
                    if (!visited.contains(temp) && !toVisit.contains(temp)) {
                        //add to neighbor arraylist
                        toVisit.add(temp);
                    }
                }

                //(-1,0)
                if (currentRow != 0 && board[currentRow - 1][currentColumn] == 0) {
                    //create temp node for evaluation                    
                    Node temp = new Node(currentRow - 1, currentColumn, 0);

                    //set parent
                    temp.setParent(n);

                    //assign an F value
                    temp.setG(n.getG() + 10); //10 is arbitrary
                    temp.setH(node[temp.getRow()][temp.getCol()].getH());
                    temp.setF();

                    //check to see if it has been visited before
                    if (!visited.contains(temp) && !toVisit.contains(temp)) {
                        //add to neighbor arraylist
                        toVisit.add(temp);
                    }
                }

                //(0,+1)
                if (currentColumn != 14 && board[currentRow][currentColumn + 1] == 0) {
                    //create temp node for evaluation                    
                    Node temp = new Node(currentRow, currentColumn + 1, 0);

                    //set parent
                    temp.setParent(n);

                    //assign an F value
                    temp.setG(n.getG() + 10); //10 is arbitrary
                    temp.setH(node[temp.getRow()][temp.getCol()].getH());
                    temp.setF();

                    //check to see if it has been visited before
                    if (!visited.contains(temp) && !toVisit.contains(temp)) {
                        //add to neighbor arraylist
                        toVisit.add(temp);
                    }
                }

                //(0,-1)
                if (currentColumn != 0 && board[currentRow][currentColumn - 1] == 0) {
                    //create temp node for evaluation                    
                    Node temp = new Node(currentRow, currentColumn - 1, 0);

                    //set parent
                    temp.setParent(n);

                    //assign an F value
                    temp.setG(n.getG() + 10); //10 is arbitrary
                    temp.setH(node[temp.getRow()][temp.getCol()].getH());
                    temp.setF();

                    //check to see if it has been visited before
                    if (!visited.contains(temp) && !toVisit.contains(temp)) {
                        //add to neighbor arraylist
                        toVisit.add(temp);
                    }

                }

                //check for empty list
                if (toVisit.size() == 0) {
                    System.out.println("Path was not found");
                    cont = false;
                }

                //sort toVisit list
                Collections.sort(toVisit);

                //add current node to visited
                visited.add(n);

                //print current location
                int boardClone[][] = new int[x][y];
                for(int i = 0; i < board.length;i++){
                    boardClone[i] = board[i].clone();
                }
                boardClone[currentRow][currentColumn] = 2; //2 gets replaced with X
                iteration++;
                System.out.println("Iteration " + iteration);
                A.printBoard(boardClone);
                System.out.println("\n");
            }
        }
    }

}
