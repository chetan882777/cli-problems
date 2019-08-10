package com.chetan.dsa;

import java.util.Scanner;

public class PascalsTriangle {

    public static void main(String[] args){
        int n = new Scanner(System.in).nextInt();

        int[] a = new int[n];
        for(int i =0; i<n; i++){
            for(int j=2*n; j>=2* i ; j = j-2){
                System.out.print(" ");
            }
            if(i == 0){
                System.out.println(1);
                a[i] = 1;
            }
            else if(i == 1){
                System.out.println("1 1");
                a[i] = 1;
            }
            else{
                int last = 0;
                for(int j = 0; j<=i; j++){

                    if(j == 0){
                        System.out.print(1 + " ");
                        last  = a[j];
                    }
                    else if(j == i){
                        System.out.println(1);
                        a[j] = last + a[j];
                    }
                    else{
                        System.out.print(a[j] + last + " ");
                        int temp = a[j];
                        a[j] = last + a[j];
                        last = temp;
                    }
                }
            }
        }
    }
}
