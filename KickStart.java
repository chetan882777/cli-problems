package com.chetan.dsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KickStart {


    static int consulates;
    static int guests;
    static int min;
    static byte[] c;
    static int[] anti;
    static int[] clock;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        try{

            int t = new Scanner(System.in).nextInt();

            for(int z = 0; z< t ; z++) {
                String[] values = reader.readLine().split(" ");
                consulates = Integer.parseInt(values[0]);
                guests = Integer.parseInt(values[1]);
                min = Integer.parseInt(values[2]);


                c = new byte[consulates * guests];
                clock = new int[guests];
                anti = new int[guests];

                int antiPos = 0, clockPos = 0;
                for (int i = 0; i < guests; i++) {
                    values = reader.readLine().split(" ");
                    if (values[1].equals("A")) {
                        anti[antiPos] = Integer.parseInt(values[0]);
                        antiPos++;
                    } else {
                        clock[clockPos] = Integer.parseInt(values[0]);
                        clockPos++;
                    }
                }

                if (min != 0) {
                    for (int i = 0; i < min; i++) {
                        int[] updatePos = new int[guests];
                        int[] cosupdate = new int[consulates];
                        int pos = 0;
                        for (int j = 0; j < clockPos; j++) {
                            clock[j] = clock[j] + 1;
                            if (clock[j] > consulates) {
                                clock[j] = clock[j] - consulates;
                            }
                            updatePos[pos] = guests * (clock[j] - 1) + j;
                            cosupdate[updatePos[pos] / guests] = 1;
                            pos++;
                        }
                        for (int j = 0; j < antiPos; j++) {
                            anti[j] = anti[j] - 1;
                            if (anti[j] < 1) {
                                anti[j] = anti[j] + consulates;
                            }
                            updatePos[pos] = guests * (anti[j] - 1) + clockPos + j;
                            cosupdate[updatePos[pos] / guests] = 1;
                            pos++;
                        }
                        for (int j = 0; j < consulates; j++) {
                            if (cosupdate[j] == 1) {
                                for (int k = j * guests; k < (j + 1) * (guests); k++) {
                                    c[k] = 0;
                                }
                            }
                        }
                        for (int j = 0; j < pos; j++) {
                            c[updatePos[j]] = 1;
                        }
                    }


                    int[] output = new int[guests];

                    for (int i = 0; i < c.length; i++) {
                        if (c[i] == 1) {
                            output[i % guests] = output[i % guests] + 1;
                        }
                    }
                    System.out.print("Case #" + Integer.valueOf(z+1).toString() +":");
                    for (int i = 0; i < guests; i++) {
                        System.out.print(" " + output[i]);
                    }
                    System.out.println();
                } else {
                    System.out.print("Case #" + Integer.valueOf(z+1).toString() +":");
                    for (int i = 0; i < guests; i++) {
                        System.out.print(" 1");
                    }
                    System.out.println();
                }
            }
        }catch(Exception e){
            return;
        }

    }
}
