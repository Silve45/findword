//I pulled this from online and then I edited it, the first git is the original
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class findword {
    public static void main(String[] args) throws FileNotFoundException {
        //create a way to create directories
        Path p = Paths.get("C:/");
        Path p1 = Paths.get("George");
        Path p2 = Paths.get("names");
        Path p3 = Paths.get("names.txt");
        Path p4 = Paths.get("ban");
        Path p5 = Paths.get("ban.txt");
        //load files
        Path p6 = Paths.get("load");
        Path p7 = Paths.get("load.txt");




        Path g1 = p.resolve(p1);
        Path g2 = g1.resolve(p2);
        Path g3 = g2.resolve(p3);
        Path g4 = g1.resolve(p4);
        Path g5 = g4.resolve(p5);
        //save and loaders
        Path g6 = g1.resolve(p6); // folder
        Path g7 = g6.resolve(p7);// file


        try {
            if (Files.notExists(g1)){
                Files.createDirectories(g1);
                System.out.println("Created George folder");
            }//end if
            if (Files.notExists(g2)){
                Files.createDirectories(g2);
                System.out.println("Created names folder");
            }//end if
            if (Files.notExists(g3)){
                Files.createFile(g3);
                System.out.println("Created names.txt. To add names you want to scan through, please write them in this document");
            }//end if
            if (Files.notExists(g4)){
                Files.createDirectories(g4);
                System.out.println("Created ban folder");
            }//end if
            if (Files.notExists(g5)){
                Files.createFile(g5);
                System.out.println("Created ban.txt");
            }//end if
            if (Files.notExists(g6)){
                Files.createDirectories(g6);
                System.out.println("Created load folder");
            }//end if
            if (Files.notExists(g7)){
                Files.createFile(g7);
                System.out.println("Created load.txt");
            }//end if




        }//end try
         catch (IOException e){
            System.err.println(e);
        }


        //Reading the word to be found from the user
        String check;
        String Y = "Y";
        String N = "N";
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> wu = new ArrayList<>();
        ArrayList<String> ga = new ArrayList<>();

        //starts the while true stuff
        while (true){

        Scanner sc1 = new Scanner(System.in);
        System.out.println("\nEnter the word to be found (-1 to exit, -2 to re-go over blacklist words, -3 to manually add blacklist words, -4 to load previous save)");
        String word = sc1.next();
        if (wu.contains(word)){
            System.out.println("Word already on blacklist");
            continue;
        }



        //adding so that I can exit out of while loop
        if (word.equalsIgnoreCase("-1")){
            System.out.println("exiting");
            break;
        }

        if (word.equalsIgnoreCase("-4")){
            System.out.println("Are you sure you want to load in file?");
            while (true){
                Scanner sc3 = new Scanner(System.in);
                String c1 = sc3.nextLine();
                if (c1.equalsIgnoreCase(Y)){
                    System.out.println("loading");
                    Scanner sc4 = new Scanner(new FileInputStream("C:/George/load/load.txt"));


                    while(sc4.hasNextLine()) {
                        String line = sc4.nextLine();
                        if (wu.contains(line)){
                            continue;
                        }
                        else {
                        wu.add(line);
                        }
                    }// end small while
                    System.out.println("Load Successful");
                    System.out.println(wu);
                    System.out.println("Please be sure to use re-go to check for occurrences");
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
        }



        if (word.equalsIgnoreCase("-3")){
            System.out.println("Manually add words to black list, -1 to exit, -2 to delete last word");
            System.out.println("Please make sure to use re-go to check occurrences of new words");
            while(true){
                Scanner a1 = new Scanner(System.in);
                String a2 = a1.nextLine();
                int last = wu.size() - 1;
                if (a2.equals("-1")){
                    break;
                }
                else if (a2.equals("-2")){
                    try {
                        wu.remove(last);
                        System.out.println("deleting");
                        System.out.println("Result " + wu);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("No words to undo");
                    }

                }
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
        }//end -3

        if (word.equalsIgnoreCase("-2")){
            System.out.println("rechecking document for new occurrences of blacklist words");
            for(int i = 0; i < wu.size(); i++) {
                word = String.valueOf(wu.get(i));// trying to get all the words instead of just one
                System.out.println("\nWord being checked is " + word);

                boolean flag = false;
                int count = 0;
                Scanner sc2 = new Scanner(new FileInputStream("C:/George/names/names.txt"));

                while(sc2.hasNextLine()) {
                    String line = sc2.nextLine();
//                    System.out.println(line);
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
                       if (count != 0){
                        check = sc1.nextLine();}
                       else {
                           check = "A";
                       }

                        if (check.equalsIgnoreCase(Y)){
                            System.out.println("Adding occurrence(s)");
                            name.addAll(ga);// replaced for loop, because it said I could

                            //this code is supposed to add a new banned word and ignore old (FIXED!!!)
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
                            System.out.println("No new words to add.");
                            break;
                        }
                        else{
                            System.out.println("Please use Y or N");
                            //took out continue, because it said I could
                        }
                    }// end small while
                } else {
                    System.out.println("File does not contain the specified word");
                    ga.clear();
                }
                // justs prints out elements in arraylist
                System.out.println("Words in ban list");
                System.out.println(name);
                //prints out elements in blacklist
                System.out.println("Blacklisted words");
                System.out.println(wu);
            }//end giant for
            continue;
        }//end -2 if

        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file
        Scanner sc2 = new Scanner(new FileInputStream("C:/George/names/names.txt"));

        while(sc2.hasNextLine()) {
            String line = sc2.nextLine();
            System.out.println(line);
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
//                    System.out.println("burger");
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
                name.addAll(ga);// replaced for loop, because it said I could


                //this code is supposed to add a new banned word and ignore old (FIXED!!!)
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
                //took out continue, because it said I could
            }
           }// end small while
        } else {
            System.out.println("File does not contain the specified word");
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
            System.out.println(list);
        }


        System.out.println("Would you like to save blacklist words? This will overwrite the previous save.");
        while (true) {
            Scanner sc3 = new Scanner(System.in);
            String c1 = sc3.nextLine();
            if (c1.equalsIgnoreCase(Y)) {
                System.out.println("Saving Blacklist words");
                PrintWriter w1 = new PrintWriter(String.valueOf(g7));
                w1.print("");
                w1.close();

                try(FileWriter fw = new FileWriter(String.valueOf(g7),true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw)) {
                    for (int i = 0; i < wu.size(); i++){
                        pw.println( wu.get(i));
                    }
                }catch (IOException i){
//            i.printStackTrace();
                }
                break;
            } else if (c1.equalsIgnoreCase(N)) {
                System.out.println("Not saving black list words");
                break;
            } else {
                System.out.println("Please you Y or N");
                continue;
            }
        }

        System.out.println("Words are: ");

        String names;
        for (int i = 0; i < name.size(); i++){
             names = name.get(i);
            System.out.println(names);
        }

        try(FileWriter fw = new FileWriter(String.valueOf(g5),true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < name.size(); i++){
                pw.println("/ban" + name.get(i));
            }

        }catch (IOException i){
//            i.printStackTrace();
        }

    }// end psvm
}
//problem 1 it only calls the first blacklist word
//problem 2 it re-puts the blacklist word into the thing and does it again