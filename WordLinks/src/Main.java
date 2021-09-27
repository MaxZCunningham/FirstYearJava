

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Collections.binarySearch;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter a comma separated list of words (or an empty list to quit) [e.g. 'cat, hat, bat' or '']: ");
            String userString = "";
            userString = input.nextLine();
            if (userString.isEmpty()) {
                exit = true;
                System.out.println("Goodbye.");
            } else {
                ArrayList<String> userList = readWordList(userString);
                if (isWordChain(userList)) {
                    System.out.println("Valid chain of words from Lewis Carroll's word-links game.\n");
                } else {
                    System.out.println("Invalid chain of words from Lewis Carroll's word-links game.\n");
                }
            }

        }
    }

    public static ArrayList<String> readDictionary() throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();
        FileReader fr = new FileReader("words.txt");
        BufferedReader br = new BufferedReader(fr);
        String strCurrentLine;
        while ((strCurrentLine = br.readLine()) != null) {
            stringList.add(strCurrentLine);
        }
        return stringList;
    }

    public static ArrayList<String> readWordList(String commaSeparatedString) {
        if (commaSeparatedString != null) {
            ArrayList<String> stringList = new ArrayList<String>();
            commaSeparatedString = commaSeparatedString.replaceAll(" ","");
            String[] array = commaSeparatedString.split(",");
            for(int i =0;i< array.length;i++){  //Collections.addAll(stringList, array);
                stringList.add(array[i]);
            }
            return stringList;
        } else {
            return null;
        }
    }

    public static boolean isUniqueList(ArrayList<String> stringList) {
        if (stringList != null) {
            for (int i = 0; i < stringList.size(); i++) {
                for (int b = i + 1; b < stringList.size(); b++) {
                    if (stringList.get(i).equals( stringList.get(b))) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEnglishWord(String wordToCheck, ArrayList<String> dictionary) throws IOException {
        if (wordToCheck != null && dictionary != null) {
            return (binarySearch(dictionary,wordToCheck)>0);

        } else {
            return false;
        }
    }

    public static boolean isDifferentByOne(String firstWord, String secondWord) {
        if (firstWord != null && secondWord != null) {
            char[] wordOne = firstWord.toCharArray();
            char[] wordTwo = secondWord.toCharArray();
            if (wordOne.length != wordTwo.length) {
                return false;
            } else {
                boolean oneDifferent = false;
                for (int i = 0; i < wordOne.length; i++) {
                    if (wordOne[i] != wordTwo[i] && !oneDifferent) {
                        oneDifferent = true; // this means that at least one letter is different
                    } else if (wordOne[i] != wordTwo[i] && oneDifferent) {
                        return false; // more then one letter is different
                    }
                }
                return oneDifferent; // if this is not true after the for loop it means no letters are different and they are the same word
            }
        } else {
            return false;
        }
    }

    public static boolean isWordChain(ArrayList<String> stringList) throws IOException {
        if (stringList != null) {
            ArrayList<String> dictionary = readDictionary();

            if (!isUniqueList(stringList) || stringList.size() <= 1) {
                return false;
            }

            String lastWord = stringList.get(0);
            if (!isEnglishWord(lastWord, dictionary)) {
                return false;
            }
            String newWord = "";

            for (int i = 1; i < stringList.size(); i++) {
                newWord = stringList.get(i);
                if (!isEnglishWord(newWord, dictionary) || !isDifferentByOne(lastWord, newWord)) {
                    return false;
                }
                lastWord = newWord;
            }
            return true;
        } else {
            return false;
        }
    }

}
