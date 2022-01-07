//I started with a tutorial to find words, and then made this monstrosity...
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//for next time, add something so that if you remove a word from the blacklist, it will also remove those words from the array with the occurrences

public class findword {
    public static void main(String[] args) throws FileNotFoundException {
        //create directories and files for you
        Create c3 = new Create();
        c3.createfolder();
        //Reading the word to be found from the user
        String check = null;
        String Y = "Y";
        String N = "N";
        String O = "O";
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> wu = new ArrayList<>();
        ArrayList<String> ga = new ArrayList<>();
        ArrayList<String> fc = new ArrayList<>();
        ArrayList<String> bc = new ArrayList<>();

        //testing arraylist
        ArrayList<String> te = new ArrayList<>();


        //This controls the load
        Scanner sc5 = new Scanner(new FileInputStream(String.valueOf(c3.g5))); // ban.txt


        //most of the program is written in this
        while (true){

        //choose what you will do
        Scanner sc1 = new Scanner(System.in);
        System.out.println("\nJust enter any word to begin \n-1 to exit \n-2 to re-go over blacklist words \n-3 to manually add or delete blacklist words \n-4 to load previous save \n-5 to delete scanned words from ban list");

        String word = sc1.nextLine();

        if (wu.contains(word)){
            System.out.println("Word already on blacklist");
            continue;
        }



        //goes to end of program
      else if (word.equalsIgnoreCase("-1")){
            System.out.println("exiting");
            break;
        }//end of exit (-1)

          //deletes words from list
      else  if (word.equalsIgnoreCase("-5")){
            System.out.println("Entering delete mode");
            c3.remove(String.valueOf(c3.g5));
            continue;
        }

        //loads in save
       else if (word.equalsIgnoreCase("-4")){
            System.out.println("Are you sure you want to load in file?");
            while (true){
                Scanner sc3 = new Scanner(System.in);
                String check2 = sc3.nextLine();
                if (check2.equalsIgnoreCase(Y)){
                    System.out.println("loading");

                    c3.PrintInput(String.valueOf(c3.g7),N,wu);
                    System.out.println("load successful \nWould you like to use rego now?");

                    while (true){
                    check2 = sc3.nextLine();
                    if (check2.equalsIgnoreCase(Y)){
                        c3.rego(String.valueOf(c3.g3),"n",sc1,word,check,wu,name,ga);
                        break;
                    }
                    else if(check2.equalsIgnoreCase(N)){
                        System.out.println("Alright, use the Rego at your own discretion then");
                        break;
                    }
                    else {
                        System.out.println("Please use Y or N");
                        continue;
                    }
                    }//end while true

//                    System.out.println("Load Successful" +"\n" + wu + "\nPlease be sure to use re-go to check for occurrences");
                    break;
                }
                else if (check2.equalsIgnoreCase(N)){
                    System.out.println("Alright not loading words");
                    break;
                }
                else {
                    System.out.println("Please use Y or N");
                }
            }
            continue;
        }// end of load (-4)


        //manual add in words to black list
       else if (word.equalsIgnoreCase("-3")){
            c3.ManualBlacklist(wu);

            Scanner sc3 = new Scanner(System.in);
            String check2;
            System.out.println("Would like like to use rego now?");
            while (true){
                check2 = sc3.nextLine();
                if (check2.equalsIgnoreCase(Y)){
                    c3.rego(String.valueOf(c3.g3),"n",sc1,word,check,wu,name,ga);
                    break;
                }
                else if(check2.equalsIgnoreCase(N)){
                    System.out.println("Alright, use the Rego at your own discretion then");
                    break;
                }
                else {
                    System.out.println("Please use Y or N");
                    continue;
                }
            }//end while true
            continue;
        }//end of manual add in (-3)

        //the rechecks document for new occurances of words
       else if (word.equalsIgnoreCase("-2")){
            c3.rego(String.valueOf(c3.g3),"o",sc1,word,check,wu,name,ga);
            continue;
        }//end of re-do (-2)


  //if you write a word in, it checks to see if it is contained and if it is it will add the word to ban list. If not, you still can add that word to ban list
        else {
            word = word;
            c3.FindWord(String.valueOf(c3.g3),sc1,word,check,wu,name,ga);
        }



            // justs prints out elements in arraylist
            System.out.println("Words in ban list");
            System.out.println(name);
            //prints out elements in blacklist
            System.out.println("Blacklisted words");
            System.out.println(wu);
//            ga.clear();
        }//end giant while

        System.out.println("Blacklist words are " + wu);
        String list;
        for (int i = 0; i < wu.size(); i++){
            list = wu.get(i);
//            System.out.println(list);
        }

        //asks if you want to overwrite the save file
        System.out.println("Would you like to save blacklist words? This will overwrite the previous save." + "\nUse Y or N");
        while (true) {
            Scanner sc3 = new Scanner(System.in);
            String c1 = sc3.nextLine();
            if (c1.equalsIgnoreCase(Y)) {
                System.out.println("Saving Blacklist words");
                //these 3 lines wipe the old blacklist, so it can be fully overwritten
              c3.ClearDocument(String.valueOf(c3.g7));

                c3.ArrayOutput(String.valueOf(c3.g7),wu,"","");
                break;
            } else if (c1.equalsIgnoreCase(N)) {
                System.out.println("Not saving black list words");
                break;
            } else {
                System.out.println("Please you Y or N");
                continue;
            }
        }


        //this while and for loop gather info to make sure that the names have no duplicates
        while(sc5.hasNextLine()) {
            sc5.skip("/ban");
            String bean = sc5.nextLine();
//            System.out.println(bean);
            bc.add(bean);

        }// end small while

        for ( int i = 0; i < name.size(); i ++){
            if (bc.contains(name.get(i))){
                //no statement needed.
            }
            else{
                fc.add(name.get(i));
            }

        }

        //shows the words then prints them into ban.txt with the correct ban format
        System.out.println("\nBanned names are: ");


        String names;
        for (int i = 0; i < name.size(); i++){
             names = name.get(i);
            System.out.println(names);
        }
        System.out.println("\nNew banned names are: ");
        for (int i = 0; i < fc.size(); i++){
            names = fc.get(i);
            System.out.println(names);
        }


        c3.ArrayOutput(String.valueOf(c3.g5), fc, "/ban","");

    }// end psvm
}// end findword