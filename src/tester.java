import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class tester {
    public static void main(String[] args) throws FileNotFoundException {
        Create c2 = new Create();


        ArrayList<String> ga = new ArrayList<>(), name = new ArrayList<>(), wu = new ArrayList<>();
        String check = "";
        String word = null;
        Scanner sc1 = new Scanner(System.in);
       while (true){
        c2.FindWord(String.valueOf(c2.g3),sc1,word,check,wu,name,ga);

           System.out.println("banned names");
            System.out.println(name);
           System.out.println("blacklist words");
           System.out.println(wu);
       }


    }
}
