//I started with a tutorial to find words, and then made this monstrosity...
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> wu = new ArrayList<>();
        ArrayList<String> ga = new ArrayList<>();
        ArrayList<String> fc = new ArrayList<>();
        ArrayList<String> bc = new ArrayList<>();

        //testing arraylist
        ArrayList<String> te = new ArrayList<>();


        //This controls the load
        Scanner sc5 = new Scanner(new FileInputStream("C:/George/ban/ban.txt"));


        //most of the program is written in this
        while (true){

        //choose what you will do
        Scanner sc1 = new Scanner(System.in);
        System.out.println("\nJust enter any word to begin \n-1 to exit \n-2 to re-go over blacklist words \n-3 to manually add or delete blacklist words \n-4 to load previous save \n-5 to delete scanned words from ban list");
        String word = sc1.next();
        if (wu.contains(word)){
            System.out.println("Word already on blacklist");
            continue;
        }



        //goes to end of program
        if (word.equalsIgnoreCase("-1")){
            System.out.println("exiting");
            break;
        }//end of exit (-1)

          //deletes words from list
            if (word.equalsIgnoreCase("-5")){
                System.out.println("Entering delete mode");
                c3.remove(String.valueOf(c3.g5));
                continue;
            }

        //loads in save
        if (word.equalsIgnoreCase("-4")){
            System.out.println("Are you sure you want to load in file?");
            while (true){
                Scanner sc3 = new Scanner(System.in);
                String c1 = sc3.nextLine();
                if (c1.equalsIgnoreCase(Y)){
                    System.out.println("loading");

                    c3.PrintInput(String.valueOf(c3.g7),N,wu);

                    System.out.println("Load Successful" +"\n" + wu + "\nPlease be sure to use re-go to check for occurrences");
                    break;
                }
                else if (c1.equalsIgnoreCase(N)){
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
        if (word.equalsIgnoreCase("-3")){
            System.out.println("Manually add words to black list, -1 to exit, -2 to go into delete mode");
            System.out.println("Please make sure to use re-go to check occurrences of new words");
            while(true){
                int last = wu.size() - 1;
                int i = 0;

                Scanner a1 = new Scanner(System.in);
                System.out.println("Add new word");
                String a2 = a1.nextLine();

                if (a2.equals("-1")){
                    break;
                }//end -1
                else if (a2.equals("-2")){
                   while (i != -1){
                    try {
                       for (int j = 0; j < wu.size(); j++)
                       {
                           System.out.print("("+j+") "+ wu.get(j) + ", ");
                       }
                       System.out.println("\nWhich word would you like to delete? (first number is 0, -1 to leave)");
                       Scanner ic = new Scanner(System.in);
                       i = ic.nextInt();
                       System.out.println("Deleting " + wu.get(i));
                       wu.remove(i);

                   }
                   catch (IndexOutOfBoundsException j){
                       if ( i == -1){
                           System.out.println("Exiting delete");
                       }
                       else {
                           System.out.println("No word there");
                       }
                   }
                    catch (InputMismatchException j){
                        System.out.println("Use integers please");
                    }

                   }

                }//end -2
                else {
                    if (wu.contains(a2)){
                        System.out.println("Blacklist contains that word");
                    }
                    else {
                    wu.add(a2);
                    System.out.println("Added words are: ");
                    System.out.println(wu);}

                }

            }
            System.out.println("Please make sure to run these words in re-do");
            continue;
        }//end of manual add in (-3)

        //the rechecks document for new occurances of words
        if (word.equalsIgnoreCase("-2")){
            c3.wordcheck(String.valueOf(c3.g3),sc1,word,check,wu,name,ga);
            continue;
        }//end of re-do (-2)


  //if you write a word in, it checks to see if it is contained and if it is it will add the word to ban list. If not, you still can add that word to ban list
        boolean flag = false;
        int count = 0;
//        System.out.println("Contents of the line"); // part of the sout(line) thing below
        //Reading the contents of the file
        Scanner sc2 = new Scanner(new FileInputStream("C:/George/names/names.txt"));

        while(sc2.hasNextLine()) {
            String line = sc2.nextLine();
//            System.out.println(line); // removing, because it is too cumbersome to keep using that over and over again
            if(line.contains(word)) {
                flag = true;

                if(!name.contains(line)){
                    count = count+1;
                }
                else{
                    continue;
                }


                if(name.isEmpty()){
                    ga.add(line);
                }//end if
                else if (name.contains(line)){
                    ga.remove(ga);
                }
                else {
                    ga.add(line);
                }//end else
            }// end line .contains
        }//end small while

        if(flag) {
            System.out.println("File contains the specified word");
            System.out.println("Number of new occurrences is: "+count);
            if (count != 0){
            System.out.println("Add occurrence(s) to ban list?");}
           while (true){
            check = sc1.nextLine();
            if (check.equalsIgnoreCase(Y)){
                System.out.println("Adding occurrence(s)");
                name.addAll(ga);

                if(wu.contains(word)){
                    ga.clear();
                    break;
                }
                else {
                    wu.add(word);
                    ga.clear();
                    break;
                }
            }
            else if (check.equalsIgnoreCase(N)){
                System.out.println("Occurrence(s) not added");
                ga.clear();//this needs to be line or an equivalent
                break;
            }
            else if (count == 0){
                String you;
                System.out.println("No new words to add.");
                System.out.println("Do you want to add " + word + " to blacklist?"
                + "\nUse Y or N"
                );
                you = sc1.nextLine();
                if (you.equalsIgnoreCase(Y)){
                    System.out.println("Adding word");
                    wu.add(word);
                    break;
                }
                else if (you.equalsIgnoreCase(N)){
                    System.out.println("Skipping word");
                    ga.clear();
                    break;
                }
                else{
                    System.out.println("Please use Y or N");
                }
            }
            else{
                System.out.println("Please use Y or N");
            }
           }// end small while
        } else {
            String you; //Why did I name it, you? No idea...
            System.out.println("File does not contain the specified word");
            System.out.println("Do you still want to add " + word + " to black list?"
            + "\nUse Y or N"
            );

            while (true){
               Scanner sc3 =  new Scanner(System.in);
                you = sc3.nextLine();
            if (you.equalsIgnoreCase(Y)){
                System.out.println("Adding word");
                wu.add(word);
                break;
            }
            else if (you.equalsIgnoreCase(N)){
                System.out.println("Skipping word");
                break;
            }
            else {
                System.out.println("Please use Y or N");
                continue;
            }
            }

            ga.clear();
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