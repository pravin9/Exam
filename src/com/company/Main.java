package com.company;

public class Main {
//1 represents green, 2 represents red, 3 represents blue
static int num;
static final int nodesVisited[][] = new int [11][10];
static final int outcome[][] = new int [11][10];

static int[][] array = grid(11,12);

    public static void main(String[] args) {
        //int[][] array = grid(11,12);
//        System.out.println(array.toString());
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                //System.out.println("["+i+"]"+"["+j+"]");
//                //System.out.print(array[i][j] + " ");
//            }
//        }
        gridCalculation(array);
    }

    public static int[][] grid(int col, int row){
        int[][] array = new int[row][col];
        for (int r = 0; r < array.length; r++)
        {
            for (int coloumn = 0; coloumn < array[r].length; coloumn++)
            {
                int max = 3;
                int min = 1;
                int number = (int)(Math.random()*(max-min+1)+min);
                array[r][coloumn] = number;
            }
        }
        return array;
    }



    static boolean isValid(int x, int y, int key, int input[][])
    {
        if (x < 11 && y < 10 &&
                x >= 0 && y >= 0)
        {
            if (nodesVisited[x][y] == 0 &&
                    input[x][y] == key)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    static void search(int x, int y, int i, int j, int input[][])
    {
        if (x != y)
            return;

        nodesVisited[i][j] = 1;
        num++;
        int x_move[] = { 0, 0, 1, -1 };
        int y_move[] = { 1, -1, 0, 0 };
        for (int u = 0; u < 4; u++)
            if ((isValid(i + y_move[u],
                    j + x_move[u], x, input)) == true)
                search(x, y, i + y_move[u],
                        j + x_move[u], input);
    }

    static void setVisitZero()
    {
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 10; j++)
                nodesVisited[i][j] = 0;
    }


    static void setOutcomeZero(int key, int input[][])
    {
        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (nodesVisited[i][j] ==1 &&
                        input[i][j] == key)
                    outcome[i][j] = nodesVisited[i][j];
                else
                    outcome[i][j] = 0;
            }
        }
    }

    static void printOutcome(int res)
    {
        int no =0;
        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (outcome[i][j] != 0){
                    no = array[i][j];
                }

            }

        }
        if(no==1){
            System.out.println("The most connected blocks is Green");
        }
        if(no==2){
            System.out.println("The most connected blocks is Red");
        }
        if(no==3){
            System.out.println("The most connected blocks is Blue");
        }
    }

    static void gridCalculation(int input[][])
    {
        int current_max = Integer.MIN_VALUE;

        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                setVisitZero();
                num = 0;
                if (j + 1 < 10)
                    search(input[i][j], input[i][j + 1], i, j, input);

                // updating result
                if (num >= current_max)
                {
                    current_max = num;
                    setOutcomeZero(input[i][j], input);
                }
                setVisitZero();
                num = 0;
                if (i + 1 < 11)
                    search(input[i][j], input[i + 1][j], i, j, input);
                if (num >= current_max)
                {
                    current_max = num;
                    setOutcomeZero(input[i][j], input);
                }
            }
        }
        printOutcome(current_max);
    }

}
