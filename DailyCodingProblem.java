package com.chetan.dsa;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DailyCodingProblem151 {

    public static void colorize(int n, int m, char[][] c, int i, int j, char color){
        if(j+1 < m && c[i][j] == c[i][j+1]){
            c[i][j+1] = c[i][j];
            c[i][j] = color;
            colorize(n, m, c, i, j+1, color);
        }
        if(j-1 >= 0 && c[i][j] == c[i][j-1]){
            c[i][j-1] = c[i][j];
            c[i][j] = color;
            colorize(n, m, c, i, j-1, color);
        }
        if(i+1 < n && c[i][j] == c[i+1][j]){
            c[i+1][j] = c[i][j];
            c[i][j] = color;
            colorize(n, m, c, i+1, j, color);
        }
        if(i-1 >= 0 && c[i][j] == c[i-1][j]){
            c[i-1][j] = c[i][j];
            c[i][j] = color;
            colorize(n, m, c, i-1, j, color);
        }
        c[i][j] = color;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        int n =  new Scanner(System.in).nextInt();
        int m = new Scanner(System.in).nextInt();

        char[][] c = new char[n][m];

        for(int i =0; i<n; i++){
            char[] s = new Scanner(System.in).nextLine().toCharArray();
            int k =0;
            for(int j =0; j<s.length && k< m; j++){
                if(s[j] != ' ' ) {
                    c[i][k] = s[j];
                    k++;
                }
            }
        }

        int p = new Scanner(System.in).nextInt();
        int q = new Scanner(System.in).nextInt();
        char color = new Scanner(System.in).next().toCharArray()[0];

        colorize(n, m, c, p, q, color);

        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }

    }
}
