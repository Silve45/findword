//I started with a tutorial to find words, and then made this monstrosity...
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


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
        ArrayList<String> banlist = new ArrayList<>(); // this collects names and then at the end puts them in banlist
        ArrayList<String> blacklist = new ArrayList<>(); // aka blacklist
        ArrayList<String> ga = new ArrayList<>(); // aka collector
        ArrayList<String> fc = new ArrayList<>(); // checks words in banlist to see if they are already in banlist
        ArrayList<String> bc = new ArrayList<>(); // checks banned words to see if they match already banned words

        //testing arraylist
        ArrayList<String> te = new ArrayList<>();


        //This makes sure that there is no duplicates
        Scanner sc5 = new Scanner(new FileInputStream(String.valueOf(c3.g5))); // ban.txt


        //most of the program is written in this
        while (true){

        //choose what you will do
        Scanner sc1 = new Scanner(System.in);
        System.out.println("\nEnter any word to begin \n-1 to exit \n-2 to re-go over blacklist words \n-3 to manually add or delete blacklist words \n-4 to load previous save \n-5 to delete scanned words from banlist \n-6 to display the contents of the banlist and blacklist");

        String word = sc1.nextLine();

        if (blacklist.contains(word)){
            System.out.println("Word already on blacklist");
            continue;
        }


        //displays contents of Banlist and blacklist ( at any time )
        else if (word.equalsIgnoreCase("-6")){
            c3.displayblacklist(banlist,blacklist);
        }// end of displaylist -6

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

                    c3.PrintInput(String.valueOf(c3.g7),N,blacklist);
                    System.out.println("Load successful \nWould you like to use Rego now?");

                    while (true){
                    check2 = sc3.nextLine();
                    if (check2.equalsIgnoreCase(Y)){
                        c3.rego(String.valueOf(c3.g3),"n",sc1,word,check,blacklist,banlist,ga);
                        break;
                    }
                    else if(check2.equalsIgnoreCase(N)){
                        System.out.println("Alright, use the Rego method at your own discretion then");
                        break;
                    }
                    else {
                        System.out.println("Please use Y or N");
                    }
                    }//end while true

//                    System.out.println("Load Successful" +"\n" + blacklist + "\nPlease be sure to use re-go to check for occurrences");
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


        //manual add in words to blacklist
       else if (word.equalsIgnoreCase("-3")){
            c3.ManualBlacklist(blacklist, banlist,ga);

            Scanner sc3 = new Scanner(System.in);
            String check2;
            System.out.println("Would like like to use Rego now?");
            while (true){
                check2 = sc3.nextLine();
                if (check2.equalsIgnoreCase(Y)){
                    c3.rego(String.valueOf(c3.g3),"n",sc1,word,check,blacklist,banlist,ga);
                    break;
                }
                else if(check2.equalsIgnoreCase(N)){
                    System.out.println("Alright, use the Rego method at your own discretion then");
                    break;
                }
                else {
                    System.out.println("Please use Y or N");
                }
            }//end while true
            continue;
        }//end of manual add in (-3)

        //the rechecks document for new occurances of words
       else if (word.equalsIgnoreCase("-2")){
            c3.rego(String.valueOf(c3.g3),"o",sc1,word,check,blacklist,banlist,ga);
            continue;
        }//end of re-do (-2)

        //goes to end of program
        else if (word.equalsIgnoreCase("-1")){
            System.out.println("exiting");
            break;
        }//end of exit (-1)

  //if you write a word in, it checks to see if it is contained and if it is it will add the word to banlist. If not, you still can add that word to banlist
        else {
            word = word;
            c3.FindWord(String.valueOf(c3.g3),sc1,word,check,blacklist,banlist,ga);
        }
            if (!word.equalsIgnoreCase("-6")){
                 c3.displayblacklist(banlist,blacklist);
            }
        }//end giant while

        System.out.println("Blacklist words are " + blacklist);
        String list;
        for (int i = 0; i < blacklist.size(); i++){
            list = blacklist.get(i);
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

                c3.ArrayOutput(String.valueOf(c3.g7),blacklist,"","");
                break;
            } else if (c1.equalsIgnoreCase(N)) {
                System.out.println("Not saving blacklist words");
                break;
            } else {
                System.out.println("Please you Y or N");
            }
        }


        //this while and for loop gather info to make sure that the names have no duplicates
        while(sc5.hasNextLine()) {
           try {
               sc5.skip("/ban");
               String bean = sc5.nextLine();
//            System.out.println(bean);
               bc.add(bean);
//               System.out.println(bean);
           }catch (NoSuchElementException e){
              String bean = sc5.nextLine();// had to have something here

           }


        }// end small while

        for ( int i = 0; i < banlist.size(); i ++){
            if (bc.contains(banlist.get(i))){
                //no statement needed.
            }
            else{
                bc.add(banlist.get(i));//changed from fc
                fc.add(banlist.get(i));// re added fc to try and fix problem
            }

        }



        //shows the words then prints them into ban.txt with the correct ban format
        System.out.println("\nBanned names are: ");


        String names;
        for (int i = 0; i < banlist.size(); i++){
             names = banlist.get(i);
            System.out.println(names);
        }
        System.out.println("\nNew banned names are: ");
        for (int i = 0; i < fc.size(); i++){
            names = fc.get(i);
            System.out.println(names);
        }

        c3.ClearDocument(String.valueOf(c3.g5));
        c3.ArrayOutput(String.valueOf(c3.g5), bc, "/ban","");

    }// end psvm
}// end findword