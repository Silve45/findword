import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Create {

    String Y = "y";
    String N = "N";
    //all the quick file paths
    Path p = Paths.get("C:/");
    Path p1 = Paths.get("George");
    Path p2 = Paths.get("names");
    Path p3 = Paths.get("names.txt");
    Path p4 = Paths.get("ban");
    Path p5 = Paths.get("ban.txt");
    //load files
    Path p6 = Paths.get("load");
    Path p7 = Paths.get("load.txt");


    Path g1 = p.resolve(p1);//C:/George
    Path g2 = g1.resolve(p2);//George/names
    Path g3 = g2.resolve(p3);//names/names.txt
    Path g4 = g1.resolve(p4);//George/ban
    Path g5 = g4.resolve(p5);//ban/ban.txt
    //save and loaders
    Path g6 = g1.resolve(p6);//George/load
    Path g7 = g6.resolve(p7);//load/load.txt

    public void createfolder(){
        try {
            //creates george folder if not already created
            if (Files.notExists(g1)){
                Files.createDirectories(g1);
                System.out.println("Created George folder");
            }//end if
            //creates names folder if not already created
            if (Files.notExists(g2)){
                Files.createDirectories(g2);
                System.out.println("Created names folder");
            }//end if
            //creates names file if not already created
            if (Files.notExists(g3)){
                Files.createFile(g3);
                System.out.println("Created names.txt. To add names you want to scan through, please write them in this document");
            }//end if
            //creates ban folder if not already created
            if (Files.notExists(g4)){
                Files.createDirectories(g4);
                System.out.println("Created ban folder");
            }//end if
            //creates ban file if not already created
            if (Files.notExists(g5)){
                Files.createFile(g5);
                System.out.println("Created ban.txt");
            }//end if
            //creates ban folder if not already created
            if (Files.notExists(g6)){
                Files.createDirectories(g6);
                System.out.println("Created load folder");
            }//end if
            //creates ban file if not already created
            if (Files.notExists(g7)){
                Files.createFile(g7);
                System.out.println("Created load.txt");
            }//end if
        }//end try
        catch (IOException e){
            System.err.println(e);
        }


    }// end create folder

    public void ArrayOutput(String path, ArrayList<String>info, String bmessage, String amessage){
        try(FileWriter fw = new FileWriter(path,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < info.size(); i++){
                pw.println(bmessage + info.get(i) + amessage);
            }

        }catch (IOException i){
//            i.printStackTrace();
        }


    }// end output

    //this reads a file and adds all of its contents into an arraylist, so in essence a loader
    public void PrintInput(String path, String displayInput,ArrayList<String> Add) throws FileNotFoundException {
        Scanner sc1 = new Scanner(new FileInputStream(path));

        if (displayInput.equalsIgnoreCase("o")){
           while (sc1.hasNext()){
            System.out.println(sc1.nextLine());
           }// end while
        }
        // deleted empty else loop
        //end check


        try {
        if (Add.equals(null)){

        }
        else {
            while (sc1.hasNext()){
                String line = (sc1.nextLine());

                if (Add.contains(line)){
                    // based on old lines in findword
                }
                else {
                    Add.add(line);
                }
            }
        }//end add
        }//end try
        catch (NullPointerException e){
            //if Add is null, nothing will happen
        }//end catch

    }// end input

    public void ClearDocument (String path) throws FileNotFoundException {
        //these 3 lines wipe the old blacklist, so it can be fully overwritten
        PrintWriter w1 = new PrintWriter(String.valueOf(path));
        w1.print("");
        w1.close();
    }

    public void remove(String path) throws FileNotFoundException {
        while (true) {// begin big while
            ArrayList<String> gather = new ArrayList<>();
            ArrayList<String> bean = new ArrayList<>();
            Scanner sc1 = new Scanner(System.in);
            PrintInput(path, "n", bean);
//            System.out.println(bean);
            boolean flag = false;
            int count = 0;
//        System.out.println("Contents of the line"); // part of the sout(line) thing below
            //Reading the contents of the file
            System.out.println("Print a word to be deleted \n-1 to exit \nPress enter to include all the words");
            String word = sc1.nextLine();
            Scanner sc2 = new Scanner(new FileInputStream(path));

            //exits if word equals -1
            if (word.equals("-1")){
                break;
            }

            // if it doesn't equal -1 we can keep going
            String line;
            while (sc2.hasNextLine()) {
                line = sc2.nextLine();
//            System.out.println(line); // removing, because it is too cumbersome to keep using that over and over again
                if (line.contains(word)) {
                    flag = true;

                    if (bean.contains(line)) {
                        count = count + 1;
                    }
                    // removed else statement
                }// end line .contains
            }//end small while

            if (flag) {
                System.out.println("File contains the specified word");
                System.out.println("Number of new occurrences is: " + count);
                System.out.println("Do you want to remove all occurrences from banlist?");


                while (true) {
                    String check = sc1.next();
                    if (check.equalsIgnoreCase("y")) {
                        System.out.println("Removing");
                        for (int i = 0; i < bean.size(); i++) {
                            if (bean.get(i).contains(word)) {// it will be if bean contains line then write it in get array list, and then I will rewrite file using an arrayoutputmethod
                            }//end if
                            else {
                                gather.add(bean.get(i));
                            }// end else
                        }

                        //clears the document and then outputs the new list
                        ClearDocument(path);
                        ArrayOutput(path, gather, "", "");
                        break;
                    } else if (check.equalsIgnoreCase("n")) {
                        System.out.println("Okay not removing occurrences");
                        break;
                    } else {
                        System.out.println("please use Y or N");
                    }

                }

            } else if (!flag) {
                System.out.println("File does not contain word");
            }

        }// end big while
        System.out.println("Exiting Remove method");
    }//end remove

    //overloaded remove, this one just deletes specified word!
    public void remove(String path, String word) throws FileNotFoundException {
        while (true) {// begin big while
            ArrayList<String> gather = new ArrayList<>();
             ArrayList<String >bean = new ArrayList<>();
            // deleted scanner sc1
            PrintInput(path, "n", bean);

            boolean flag = false;
            int count = 0;

             word = word;

             Scanner sc2 = new Scanner(new FileInputStream(path));

            //exits if word equals -1
            if (word.equals("-1")){
                break;
            }

            // if it doesn't equal -1 we can keep going
            String line = "lll";
            while (sc2.hasNextLine()) {
                line = sc2.nextLine();
                if (line.contains(word)) {
                    flag = true;

                    if (bean.contains(line)) {
                        count = count + 1;
                    }
                    //removed empty else statement
                }// end line .contains
            }//end small while

            if (flag) {
                System.out.println("Removing Words");
                for (int i = 0; i < bean.size(); i++) {
                    if (bean.get(i).contains(word)) {// it will be if bean contains line then write it in get array list, and then I will rewrite file using an arrayoutputmethod
                    }//end if
                    else {
                        gather.add(bean.get(i));
                    }// end else
                }


                //clears the document and then outputs the new list
                ClearDocument(path);
                ArrayOutput(path, gather, "", "");
            break;
            }
            else {
                System.out.println("nothing to remove");
                break;
            }

        }//end big while

//        System.out.println("Exiting Remove method");
    }//end overloaded remove

    public void rego(String path, String on, Scanner sc1, String word,String check, ArrayList<String> blacklist, ArrayList<String> name, ArrayList <String> ga ) throws FileNotFoundException {
        System.out.println("Rechecking document for new occurrences of blacklist words");



        for(int i = 0; i < blacklist.size(); i++) {
            word = String.valueOf(blacklist.get(i));// trying to get all the words instead of just one
            System.out.println("\nWord being checked is " + word);

            boolean choice;

            if (on.equalsIgnoreCase("o")){
                 choice = true;
            }
            else {
                 choice = false;
            }

            boolean flag = false;
            int count = 0;
            Scanner sc2 = new Scanner(new FileInputStream(path));

            //this is the code from the tutorial, making it very hard for me to delete. I remade all of it in PrintInput, but the flag system
            while(sc2.hasNextLine()) {
                String line = sc2.nextLine();
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
                        //ga.remove(ga); changed it, to simpify code
                        ga.clear();
                    }
                    else {
                        ga.add(line);
                    }//end else
                }// end line .contains
            }//end small while

            //still part of -2, an almost exact copy of the find word
            if(flag) {
                if (choice == true){
                System.out.println("File contains the specified word");
                System.out.println("Number of new occurrences is: "+count);}

                if (choice == true) {
                    if (count != 0) {
                        System.out.println("Add occurrence(s) to banlist?");
                    }
                    while (true) {
                        if (count != 0) {
                            check = sc1.nextLine();
                        } else {
                            check = "A";
                        }

                        if (check.equalsIgnoreCase(Y)) {
                            System.out.println("Adding occurrence(s)");
                            name.addAll(ga);// replaced for loop, because it said I could

                            //this code is supposed to add a new banned word and ignore old (FIXED!!!)
                            if (blacklist.contains(word)) {
                                ga.clear();
                                break;
                            } else {
                                blacklist.add(word);
                                ga.clear();
                                break;
                            }
                        } else if (check.equalsIgnoreCase(N)) {
                            System.out.println("Occurrence(s) not added");
                            ga.clear();//this needs to be line or an equivalent
                            break;
                        } else if (count == 0) {
                            System.out.println("No new words to add.");
                            break;
                        } else {
                            System.out.println("Please use Y or N");
                            //took out continue, because it said I could
                        }
                    }// end small while
                }// end choice
                else { // choice is not true
                    System.out.println("Adding occurrence(s)");
                    name.addAll(ga);// replaced for loop, because it said I could

                    //this code is supposed to add a new banned word and ignore old (FIXED!!!)
                    if (blacklist.contains(word)) {
                        ga.clear();
//                        break;
                    } else {
                        blacklist.add(word);
                        ga.clear();
//                        break;
                    }
                }//end choice = false
            } else {
                System.out.println("File does not contain the specified word");
                ga.clear();
            }
            // all it does is prints out elements in arraylist
            if (choice == false){
                System.out.println("Re-do successful");
            }
            if (choice == true){
            displayblacklist(name, blacklist);}

        }//end giant for
    }//end rego

    public void ManualBlacklist(ArrayList<String> banlist, ArrayList<String> namelist, ArrayList<String> collect) throws FileNotFoundException{

        while(true){
            int last = banlist.size() - 1;
            int i = 0;

            Scanner a1 = new Scanner(System.in);
            System.out.println("Manually add words to blacklist \n-1 to exit \n-2 to go into delete mode");
            String a2 = a1.nextLine();

            if (a2.equals("-1")){
                break;
            }//end -1
            else if (a2.equals("-2")){
                while (i != -1){
                    try {
                        for (int j = 0; j < banlist.size(); j++)
                        {
                            System.out.print("("+j+") "+ banlist.get(j) + ", ");
                        }
                        System.out.println("\nWhich word would you like to delete? \nFirst number is 0 \n-1 to leave");
                        Scanner ic = new Scanner(System.in);
                        i = ic.nextInt();
                        System.out.println("Deleting " + banlist.get(i));

                        int keep = namelist.size();
                        for (int j = 0; j < namelist.size(); j++){
                            if (!namelist.get(j).contains(banlist.get(i))){
                                collect.add(namelist.get(j));
                            }
                        }
                        namelist.clear();

                        for (int j = 0; j < collect.size(); j++){
                            namelist.add(String.valueOf(collect.get(j)));
                        }
                        collect.clear();

                        System.out.println(namelist);



                        while (true){
                        System.out.println("Would you like to delete occurrences in banlist also? Y or N?");
                        a2 = a1.nextLine();
                        if (a2.equalsIgnoreCase(Y)){
                            remove(String.valueOf(g5), banlist.get(i));
                            break;
                        }
                        else if (a2.equalsIgnoreCase(N)){
                            System.out.println("Not removing occurrences");
                            break;
                        }
                        else {
                            System.out.println("Please use Y or N");
                        }
                        }// end while
                        banlist.remove(i);

                    }
                    catch (IndexOutOfBoundsException j){
                        if ( i == -1){
                            System.out.println("Exiting delete");
                            break;
                        }
                        else {
                            System.out.println("No word there");
                        }
                    }
                    catch (InputMismatchException j){
                        System.out.println("Use integers please");
                    }
//                    catch (IOException e) {
//                        System.err.println(e);
//                    }

                }

            }//end -2
            else {
                if (banlist.contains(a2)){
                    System.out.println("Blacklist contains that word");
                }
                else {
                    banlist.add(a2);
                    System.out.println("Added words are: ");
                    System.out.println(banlist);}

            }

        }
    }//end ManualBlacklist

    public void FindWord(String path, Scanner sc1, String word,String check, ArrayList<String> blacklist, ArrayList<String> banlist, ArrayList <String> ga ) throws FileNotFoundException {
        //if you write a word in, it checks to see if it is contained and if it is it will add the word to banlist. If not, you still can add that word to banlist


        if (word == null){
            System.out.println("Print a word");
            word = sc1.nextLine();
        }
        else {
        word = word;
        }



        boolean flag = false;
        int count = 0;
//        System.out.println("Contents of the line"); // part of the sout(line) thing below
        //Reading the contents of the file
        Scanner sc2 = new Scanner(new FileInputStream(path));

        while(sc2.hasNextLine()) {
            String line = sc2.nextLine();
//            System.out.println(line); // removing, because it is too cumbersome to keep using that over and over again
            if(line.contains(word)) {
                flag = true;

                if(!banlist.contains(line)){
                    count = count+1;
                }
                else{
                    continue;
                }


                if(banlist.isEmpty()){
                    ga.add(line);
                }//end if
                else if (banlist.contains(line)){
//                    ga.remove(ga); same reason as other one
                    ga.clear();
                }
                else {
                    ga.add(line);
                }//end else
            }// end line .contains
        }//end small while

        if(flag) {
            System.out.println("File contains the specified word");
            System.out.println("Number of new occurrences is: "+count);
            while (true){
                if (count != 0){
                    System.out.println("Add occurrence(s) to banlist?");
                    break;
                }
//
                else if (count == 0) {
                    //deleted String you
                    System.out.println("No new words to add.");
                    System.out.println("File does not contain new instance of " + word);
                    if (blacklist.contains(word)){
                        //this should never be called, but is here to catch people who get past
                        System.out.println("Word already on blacklist \nSkipping word");
                    }
                    else if(!blacklist.contains(word)){
                        System.out.println("Add word to blacklist?");
                    }
                    check = sc1.nextLine();
                    if (check.equalsIgnoreCase(Y)){
                        System.out.println("Adding occurrence(s)");
                        blacklist.add(word);
                        ga.clear();
                    }
                    else if (check.equalsIgnoreCase(N)){
                        System.out.println("Occurrence(s) not added");
                        ga.clear();//this needs to be line or an equivalent
                        break;
                    }
                    break;
                }

            }// end while true

            while (true){
                if (count == 0){ // this makes sure that if there is no new words that it doesn't go through this loop
                    break;
                }


                check = sc1.nextLine();
                if (check.equalsIgnoreCase(Y)){
                    System.out.println("Adding occurrence(s)");
                    banlist.addAll(ga);

                    if(blacklist.contains(word)){
                        ga.clear();
                        break;
                    }
                    else {
                        blacklist.add(word);
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
                        blacklist.add(word);
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
            System.out.println("Do you still want to add " + word + " to blacklist?" + "\nUse Y or N");

            while (true){
                Scanner sc3 =  new Scanner(System.in);
                you = sc3.nextLine();
                if (you.equalsIgnoreCase(Y)){
                    System.out.println("Adding word");
                    blacklist.add(word);
                    break;
                }
                else if (you.equalsIgnoreCase(N)){
                    System.out.println("Skipping word");
                    break;
                }
                else {
                    System.out.println("Please use Y or N");
                }
            }

            ga.clear();
        }

    }

    public void displayblacklist(ArrayList<String> banlist, ArrayList<String> blacklist){
        //all it does is prints out elements in arraylist
        System.out.println("Words in banlist");
        System.out.println(banlist);
        //prints out elements in blacklist
        System.out.println("Blacklisted words");
        System.out.println(blacklist);

    }


}// end Create
