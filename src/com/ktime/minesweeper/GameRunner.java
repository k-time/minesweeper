package com.ktime.minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        System.out.print("Please enter a board size between 8 and 26: ");
        Scanner in = new Scanner(System.in);
        int size = -1;
        while (size < 0) {
            try {
                size = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input, try again: ");
            }
        }
        Board board = new Board(size);
        board.print();
    }
}
