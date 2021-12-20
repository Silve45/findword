//I pulled this from online and then I edited it
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class findword {
    public static void main(String args[]) throws FileNotFoundException {
        //Reading the word to be found from the user


        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter the word to be found");
        String word = sc1.next();



        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file
        Scanner sc2 = new Scanner(new FileInputStream("C:/George/names/names.txt"));
        while(sc2.hasNextLine()) {
            String line = sc2.nextLine();
            System.out.println(line);
            if(line.indexOf(word)!=-1) {
                flag = true;
                count = count+1;
            }
        }




        if(flag) {
            System.out.println("File contains the specified word");
            System.out.println("Number of occurrences is: "+count);
        } else {
            System.out.println("File does not contain the specified word");
        }
    }

    public void getAll (){
        ArrayList<String> bacon = new ArrayList<String>();
        for (int i = 0; i < bacon.size(); i++){
            bacon.indexOf(i);
            System.out.println(bacon.indexOf(i));
        }
    }


}