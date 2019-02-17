/**
 *
 * Project 2, CS 180
 *
 * This program allows the user to guess the position of hidden balls
 *
 * @author Jonty Shah, Lab 10
 *
 * @version March 15, 2018
 *
 */

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Sripath Mishra on 9/8/2017.
 *
 */


public class BlackBox {
    public static char box[][]; // The matrix for the game.
    public static int size;       // to store the number of rows and columns.
    public static int numball;
    public static int numlink;
    public static boolean end;
    public static int score;
    public static int high_score=-1;
    public static Scanner in;
    public static int number;
    /**
     * The default constructor which places default values to the class variables
     */
    public BlackBox() {
        this.box=new char[0][0];
        this.size=0;
        this.numball=0;
        this.numlink=0;
        this.end=false;
        this.score=0;
    }
    /**
     * The parameterized constructor which places values to the class variables
     */
    public BlackBox(int size, int numball, int numlink, boolean end, int score)
    {
        this.box=new char[size][size];
        this.size=size;
        this.numball=numball;
        this.numlink=numlink;
        this.end=end;
        this.score=score;
    }
    /**
     * The main function takes input for the difficulty level and call the functions initialize() and
     * playgame()
     */
    public static void main(String[] args) {
        //Todo:start the game print the welcome message and ask for correct difficulty level.
        //Todo: end the game if the user says quit.
        //Todo:call the functions initialize and playgame()
        // Todo: take care of high score (To be done)
         in = new Scanner(System.in);
        String input = "";
        while(!input.equals("quit")) {
            System.out.println("Welcome to the Blackbox game. Please choose the difficulty level:easy/medium/hard or quit" +
                    " to end the game.");
            input = in.nextLine().toLowerCase();
            if(input.equals("quit")) {
                if(end){
                    System.out.println("Highest score => none");
                } else {
                    System.out.println("Highest score => " + high_score);
                }

                break;
            } else if (input.equals("easy")) {
                size = 5;
            } else if (input.equals("medium")) {
                size = 7;
            } else if (input.equals("hard")) {
                size = 8;
            }
            BlackBox black = new BlackBox(size, 3, 0, false, 0);
            black.initialize();
            playgame();
            high_score = score;
        }
    }
    /**
     * The initialize funtion
     */
    public void initialize() {
        //Todo:initialise the Box[][]
        //Todo: place the 'X' and the '#'
        // Todo: place 3 '0''s randomly.
        box = new char[size + 2][size + 2];
        for(int i = 1; i < size + 2; i++){
            box[0][i] = '#';
            box[i][0] = '#';
            box[i][size + 1] = '#';
            box[size + 1][i] = '#';
        }

        box[0][0] = 'X';
        box[0][size + 1] = 'X';
        box[size + 1][0] = 'X';
        box[size + 1][size + 1] = 'X';

        Random ran = new Random();
         int count = 0;
         while(count != 3){
             int rowCordinate = ran.nextInt(size + 1);
             int colCordinate = ran.nextInt(size + 1);
             if(rowCordinate == 0){
                 rowCordinate = 1;
             }
             if(colCordinate == 0){
                 colCordinate = 1;
             }

             if(box[rowCordinate][colCordinate] != '0') {
                box[rowCordinate][colCordinate] = '0';
                 count++;
             }
         }
    }
    /**
     * The printbox funtion prints out the matrix in a particular format as given in the handout.
     */
    public static void printbox() {
        //Todo:print the box in the correct order
        // for  5*5 example
        /* 1  2  3  4  5  6  7
         ======================
        1|X |# |# |# |# |# |X |
        2|# |  |  |  |  |  |# |
        3|# |  |  |  |  |  |# |
        4|# |  |  |  |  |  |# |
        5|# |  |  |  |  |  |# |
        6|# |  |  |  |  |  |# |
        7|X |# |# |# |# |# |X |
         ======================*/
        //place the guesses as the come and print the balls when the player enter sumbit.
        System.out.println("");
        for(int i = 1; i < size + 3; i++){
            System.out.print("  " + i);
        }
        System.out.println("");
        for(int i = 0; i < size * 4 + 2; i++){
            System.out.print("=");
        }

        System.out.println("");
        for(int i = 0; i < size + 2; i++){
            System.out.print(i + 1);
            for(int j = 0; j < size + 2; j++){
                if(box[i][j] == 0){
                    System.out.print("|  ");
                } else if(box[i][j] == '0' && !end){
                    System.out.print("|  ");
                } else {
                    System.out.print(String.format("|%1.2s ", box[i][j]));
                }
            }
            System.out.println("|");
        }

        for(int i = 0; i < size * 4 + 2; i++){
            System.out.print("=");
        }

        System.out.println("");
    }

    /**
     * The playgame funtion opens the first cell and is the main controller for the game. It calls various function when needed.
     */
    public static void playgame() {
        //Todo:Take input of a guess or hint from the user.
        //Todo:Check for valid input
        //Todo:call required functions
        //Todo:keep tab on score.
         number = 3;
        String coordinates = "";
            while (number != 0 || !coordinates.equals("submit")) {
                printbox();
                System.out.println("Choose the new coordinates (row,column) to play the next step or say submit/quit to end the game");
                coordinates = in.nextLine().toLowerCase();

                if (number != 0 && coordinates.equals("submit")) {
                    System.out.println("Please make sure you have entered three guesses");
                    printbox();
                    System.out.println("Choose the new coordinates (row,column) to play the next step or say submit/quit to end the game");
                    coordinates = in.nextLine().toLowerCase();
                }
                if(number == 0 && !coordinates.equals(("submit"))){
                    System.out.println("Please Enter Submit to Finalize the guesses as you have already placed three balls.");
                }

                if (coordinates.equals("quit") || coordinates.equals("submit")) {
                    for (int i = 0; i < size + 2; i++) {
                        for (int j = 0; j < size + 2; j++) {
                            if (box[i][j] == '0') {
                                end = true;
                            }
                        }
                    }
                    if (coordinates.equals("quit") && !end) {
                        if (high_score > score) {
                            high_score = score;
                        }
                        System.out.println("Highest score => " + high_score);
                    }
                    if (coordinates.equals("submit")) {
                        printbox();
                        if (end) {
                            System.out.println("Fail");
                        } else {
                            System.out.println("Score: " + score);
                        }
                    }
                    break;
                }

                int separator = coordinates.indexOf(',');
                     int rowCordinate = Integer.parseInt(coordinates.substring(0, separator));
                     int colCordinate = Integer.parseInt(coordinates.substring(separator + 1));

                while(rowCordinate == 1 && colCordinate == 1 || rowCordinate == 1 && colCordinate == size + 2
                        || rowCordinate == size + 2 && colCordinate == 1 || rowCordinate == size + 2 && colCordinate == size + 2) {
                        System.out.println("Wrong input please choose a block which is open");
                        System.out.println("Choose the new coordinates (row,column) to play the next step or say submit/quit to end the game");
                        coordinates = in.nextLine().toLowerCase();
                     rowCordinate = Integer.parseInt(coordinates.charAt(0) + "");
                     colCordinate = Integer.parseInt(coordinates.charAt(2) + "");
                }
                rowCordinate--;
                colCordinate--;
                    check(rowCordinate, colCordinate);
                if(box[rowCordinate][colCordinate] == '*'){
                    number--;
                }
                score++;
            }
    }



    /**
     * The check funtion takes in the row and column in the matrix, checks for Hit (H), Reflection (R) or Divergence(#num)
     *
     */
    public static void check(int i,int j) {
        //Todo:place a guess when the input of i and j are valid
        //Todo:Check for a Hit
        //Todo:Check for a reflection
        //Todo:Check for a bounce
        //Todo:Print a statement telling the user they cannot place a fourth ball.
        boolean check = false;
        for(int k = 1; k < size + 2; k++){
            if(i == 0 && k == j || i == k && j == 0 || i == k && j == size + 1 || i == size + 1 && j == k){
                check = true;
                break;
            }
        }
        if(check) {
            if (hitcheck(i, j)) {
                box[i][j] = 'H';
            } else if (reflectionCheck(i, j)) {
                box[i][j] = 'R';
            } else if (deflectionCheck(i, j)) {
                String temp = numlink + "";
                box[i][j] = temp.charAt(0);
            } else if (straightRay(i, j)) {
                numlink++;
                String temp = numlink + "";
                box[i][j] = temp.charAt(0);
                if(i == 0){
                    box[size + 1][j] = temp.charAt(0);
                } else if(i == size + 1){
                    box[0][j] = temp.charAt(0);
                } else if(j == 0){
                    box[i][size + 1] = temp.charAt(0);
                } else if(j == size + 1){
                    box[i][0] = temp.charAt(0);
                }
            }
        } else if(number <= 3 && number > 0){
            box[i][j] = '*';
        }
    }
    /**
     * The hitcheck funtion takes in the row and column in the matrix, checks for Hit (H)
     *
     */
    public static boolean hitcheck(int i,int j) {
        //todo: check if the ray causes a HIT as defined in the handout
        boolean result = false;
        for(int k = 1; k < size + 2; k++){
            if(i == 0 || i == size + 1){
                if(box[k][j] == '0'){
                    result = true;
                }
            } else {
                if(box[i][k] == '0'){
                    result =  true;
                }
            }
        }
        return result;
    }
    /**
     * The check funtion takes in the row and column in the matrix, checks for Reflection (R)
     *
     */
    public static boolean reflectionCheck(int i,int j) {
        //todo: check if the ray causes a Reflection as defined in the handout
        if(j == 0) {
            if ( i != size && box[i + 1][j + 1] == '0' || i != 1 && box[i - 1][j + 1] == '0') {
                return true;
            }
        } else if(j == size + 1) {
            if ( i != size && box[i + 1][j - 1] == '0' || i != 1 && box[i - 1][j - 1] == '0') {
                return true;
            }
        } else if(i == 0){
            if ( j != size && box[i + 1][j + 1] == '0' || j != 1 && box[i + 1][j - 1] == '0') {
                return true;
            }
        } else if(i == size + 1){
            if ( j != size && box[i - 1][j + 1] == '0' || j != 1 && box[i - 1][j - 1] == '0') {
                return true;
            }
        } else {
            for(int k = 1; k < size + 2; k++){
                if(i == 0 || i == size + 1){
                    if(box[k][j + 1] == '0' && box[k][j - 1] == '0'){
                        return true;
                    }
                } else {
                    if(box[i + 1][k] == '0' && box[i - 1][k] == '0'){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * The check funtion takes in the row and column in the matrix, checks for Divergence(#num)
     *
     */
    public static boolean deflectionCheck(int i,int j) {
        //todo: check if the ray causes a Deflection as defined in the handout
       if(i == 0 && j == 1){
           for(int k = 1; k < size + 2; k++){
               if(box[k][k] == '0'){
                   numlink++;
                   String temp = numlink + "";
                  // box[i][j] = temp.charAt(0);
                   box[0][k - 1] = temp.charAt(0);
                   return true;
               }
           }
       } else if(i == 0 && j == size){
           j++;
           for(int k = 1; k < size + 2; k++){
               if(box[k][j - 1] == '0'){
                   numlink++;
                   String temp = numlink + "";
                   //box[i][j] = temp.charAt(0);
                   box[0][k + 1] = temp.charAt(0);
                   return true;
               }
           }
       } else if(i == size && j == 1 ){
           for(int k = 1; k < size + 2; k++){
               if(box[size + 1 - k][k] == '0'){
                   numlink++;
                   String temp = numlink + "";
                  // box[i][j] = temp.charAt(0);
                   box[k+ 1][0] = temp.charAt(0);
                   return true;
               }
           }
       } else if(i == size + 1 && j == size){
           for(int k = size + 1; k < 0; k--){
               if(box[k][k] == '0'){
                   numlink++;
                   String temp = numlink + "";
                  // box[i][j] = temp.charAt(0);
                   box[0][k + 1] = temp.charAt(0);
                   return true;
               }
           }
       } else if(i == 0 || j == size){
           int num = i;
           for(int k = j; k < size + 2; k++){
               if(box[num++][k] == '0'){
                   numlink++;
                   String temp = numlink + "";
                  // box[i][j] = temp.charAt(0);
                   box[num - 1][0] = temp.charAt(0);
                   return true;
               }
           }

       }
        return false;
    }
    /**
     * The straightRay funtion takes in the row and column in the matrix, checks for Straight ray
     *
     */
    public static boolean straightRay(int i,int j){
        //todo: check if the ray is a straight ray as defined in the handout
        boolean result = false;
        for(int k = 1; k < size + 2; k++){
            if(i == 0 || i == size + 1){
                if(box[k][j] != '0'){
                    result = true;
                }
            } else {
                if(box[i][k] != '0'){
                    result =  true;
                }
            }
        }
        return result;
    }
    /**
     * The following definitions are the getters and setter functions which have to be implemented
     *
     */
    public char[][] getbox() {
        return box;
    }
    public int getscore() {
        return score;
    }
    public int getNumball() {
        return numball;
    }
    public int getNumlink() {
        return numlink;
    }
    public boolean getend() {
        return true;
    }
    public void setbox(char box[][]) {
        this.box = box;
    }
    public void setSize(int size){
        this.size = size;
    }
    public void setNumball(int Numball) {
        numball = Numball;
    }
    public void setNumlink(int Numlink) {
        numlink = Numlink;
    }
    public void setEnd(boolean end) {
        this.end = end;
    }
    public void setScore(int score) {
        this.score = score;
    }

}