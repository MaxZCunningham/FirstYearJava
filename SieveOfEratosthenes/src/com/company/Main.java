/* SELF ASSESSMENT

   1.    createSequence:
Did I use the correct method definition?
Mark out of 5:5
Comment: yes i did it returns an int array
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5:5
Comment: i created an array of size n and initialised the array
Did I return the correct item?
Mark out of 5:5
Comment:yes i returned the correct array

   2.    crossOutMultiples
Did I use the correct method definition?
Mark out of 5:5
Comment:yes i did it returns an int array
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2:2
Comment:yes i did
Did I loop through the array using the correct multiple?
Mark out of 5:5
Comment:yes i looped through the array moving up in the N parameter
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3:2
Comment:yes i didnt un cross out anything that was crossed out

   3.    sieve
Did I have the correct function definition?
Mark out of 5:5
Comment:yes
Did I make calls to other methods?
Mark out of 5:5
Comment:I made calls to all other methods stated in the brief
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2:2
Comment:yes though it want really needed for this program

   4.    sequenceTostring
Did I have the correct function definition?
Mark out of 5:5
Comment:yes it returns the string of numbers including the crossed out ones in square brackets
Did I ensure the parameter to be used is not null?
Mark out of 3:3
Comment:yes i ensured the array!=null
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets?
Mark out of 10:10
Comment:yes at each index i checked if the number was crossed out or not and put it in brackets if it was crossed out

   5.    nonCrossedOutSubseqToString
Did I have the correct function definition
Mark out of 5:5
Comment:yes it returns a string of only the non crossed out numbers
Did I ensure the parameter to be used is not null?
Mark out of 3:3
Comment:yes
Did I loop through the array updating the String variable with just the non-crossed out numbers?
Mark out of 5:5
Comment:yes any crossed out numbers were ignored and not added to the string

   6.    main
Did I ask  the user for input n and handles input errors?
Mark out of 5:5
Comments:yes if the number inputted is not a valid int the program asks them to put in a valid int and repeat
Did I make calls to other methods (at least one)?
Mark out of 5:5
Comment:yes i made a call to sieve
Did I print the output as shown in the question?
Mark out of 5:5
Comment:yes output appears to match the sample output

   7.    Overall
Is my code indented correctly?
Mark out of 4:4
Comments:my code is correctly indented
Do my variable names make sense?
Mark out of 4:4
Comments:my variable names make sense in relation to the assignment
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4:4
Comments:my code follows all relevent coding stadards using lowerCamelCase ect.
      Total Mark out of 100 (Add all the previous marks):100
*/


package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int userInput;
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while(exit == false) {
            System.out.print("Enter int >= 2 : ");
            if (input.hasNextInt()) {
                userInput = input.nextInt();
                if (userInput >= 2) {
                    int[] array = sieve(userInput);
                    exit = true;
                }else {
                    System.out.println("Please Enter a valid integer.");
                }
            } else {
                System.out.println("Please Enter a valid integer.");
            }

        }
    }

    public static int[] createSequence(int N){
        if(N>=2) {
            int[] array = new int[(N - 1)];
            int count = 2;
            for (int index = 0; index < array.length; index ++) {
                array[index] = count++;
            }
            return array;
        } else {
            return null;
        }
    }

    public static int[] crossOutHigherMultiples(int[] array, int N){
        if(array!=null && N >=2 && N<= array.length-1){
            for(int index=N-2; index<array.length;index+=N) {
                if (array[index] >0 && array[index] % N == 0 && array[index] != N) {
                    array[index] *= -1;
                }
            }
            return array;
        } else{
            return null;
        }

    }

    public static int[] sieve(int N){
        if (N>=2){
            int[] array = createSequence(N);
            if(array!=null){
                System.out.println(sequenceToString(array));
                for(int index=2;index<= Math.sqrt(N);index++) {
                    if(array[index]>0){
                        array = crossOutHigherMultiples(array, index);
                        System.out.println(sequenceToString(array));
                    }
                }
                System.out.println(nonCrossedOutSubseqToString(array));
                return array;
            } else{
                return null;
            }
        } else {
            return null;
        }
    }

    public static String sequenceToString(int[] array){
        if(array!=null) {
            String Str = "";
            for (int index = 0; index < array.length; index ++) {
                if (array[index] > 0 && index != array.length - 1) {
                    Str += array[index] + ", ";
                } else if (array[index] > 0) {
                    Str += array[index];
                } else if (array[index] <0 && index != array.length - 1) {
                    Str += "[" + (array[index]*-1) + "], ";
                } else if (array[index] < 0) {
                    Str += "[" + (array[index]*-1) + "]";
                }
            }
            return Str;
        } else {
            return null;
        }
    }

    public static String nonCrossedOutSubseqToString(int[] array){
        if(array!=null){
            String Str="";
            int lastIndex = findLastPrime(array);
            for(int index =0; index<array.length;index++) {
                if (array[index] > 0 && index != lastIndex) {
                    Str += array[index] + ", ";
                } else if (array[index] > 0 && index == lastIndex) {
                    Str += array[index];
                }
            }
            return Str;
        } else {
            return null;
        }
    }

    public static int findLastPrime(int[] array){
        int lastIndex=0;
        if(array!=null){
            for(int index=0;index<array.length;index++) {
                if (array[index] > 0) {
                    lastIndex = index;
                }
            }
        }
        return lastIndex;
    }

}
