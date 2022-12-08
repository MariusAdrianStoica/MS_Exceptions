package se.lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class ExceptionDemo {

    public static void main(String[] args) {

        /*do {
            try {
                String studentId =ex4();
                System.out.println("Valid StudentID: \t"+ studentId);
                break;
                //ex4 is correct but can throw exceptions
                //we need to catch them
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (true);*/

        /*try {
            ex6();
        } catch (DataNotFoundException e) {
            System.out.println("Error code: "+ e.getErrorCode() +" Error message: \""+e.getParamValue()+"\" - "+ e.getMessage());
        }*/

        /*try {
            ex7();
        } catch(InsufficientFoundsException e){
            System.out.println("Error code: "+ e.getErrorCode() +" Error message: "+e.getMessage());
        }*/

        //ex9();

        System.out.println(ConsoleColors.CYAN); //we can set the colour from the beginning
        System.out.println(ConsoleColors.BLUE+"Display Menu");
        System.out.println(ConsoleColors.PURPLE+"--------------");
        System.out.println(ConsoleColors.YELLOW+"Display Menu");
        System.out.println(ConsoleColors.RESET+"--------------");
        System.out.println(ConsoleColors.RED+"Display Menu");
        System.out.println(ConsoleColors.RESET+"--------------");
        System.out.println(ConsoleColors.GREEN+"Display Menu");
        System.out.println(ConsoleColors.RESET+"--------------");
        }
    //Checked exceptions
    public static void ex1() {

        //to read a file - created in dir
        //we have to handle the exception -> try - catch
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in); //ask the user after Path

                System.out.println("Enter a filepath:");
                String filePath = scanner.nextLine();

                //protected code
                //BufferedReader reader = Files.newBufferedReader(Paths.get("dir/skills.txt"));
                // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html

                BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));

                //read the info from file into a List of Strings - reader.lines()
                List<String> skillList = reader.lines().collect(Collectors.toList());

                skillList.forEach(System.out::println);
                System.out.println("----------------");

                //reading the file line by line -> no buffer
                //lines() -> read all lines
                //lines -> return Stream
                Files.lines(Paths.get(filePath)).forEach(System.out::println);
                System.out.println("----------------");

                //readAllLines -> return Array of Stream
                List<String> strings = Files.readAllLines(Paths.get(filePath));
                strings.forEach(System.out::println);
                break;
                // if the file was correct and we could print the file -> it is done (break)

            } catch (IOException e) {
                System.out.println("\n" + e);
                System.out.println("File Path is not valid!\n");
            }

        }
    }

    //Checked exceptions
    public static void ex2() {
        try {
            Path pathSource = Paths.get("source/th.png");
            Path pathDestination = Paths.get("destination/new_th.png");


            if (Files.exists(pathSource) && Files.isRegularFile(pathSource)) {
                Files.copy(
                        pathSource,
                        pathDestination,
                        StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println("Source file is not valid!");
            }
            //if we run again, we get exception -> file already exists
            // StandardCopyOption.REPLACE_EXISTING ->replace the file
            // this way we don't get exception

            //Paths.get("destination/new_th.png"));


        } catch (IOException e) {
            System.out.println(e);                  //1
            System.out.println(e.getMessage());     //2
            e.printStackTrace();                    //3

            //3 ways to display exception
        }
    }

    //Unchecked exceptions
    public static void ex3() {

        //Unchecked exceptions - we can run the code (we get exception only during Runtime)

        int[] numbers = {4, 5, 8, 9}; //length 4 -> index 0-3

        try {
            //protected code
            System.out.println(numbers[0]);
            System.out.println(numbers[1]);
            //System.out.println(numbers[4]); //index4 don't exists
            // it throws ArrayIndexOutOfBoundsException

            System.out.println("-------------------");
            String text = null;
            //if (text != null) {
            //System.out.println(text.toUpperCase());
            //}

            LocalDate localdate = LocalDate.parse("2020-1-1"); //YYYY-MM-DD

        /*} catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("Text was null");
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }*/

        //to simplify the catch structure
    }catch (ArrayIndexOutOfBoundsException | NullPointerException | DateTimeParseException e ){
            System.out.println(e.getMessage());
        }
        System.out.println("Done!");


    }

    //throw -> is used to throw an exception explicitly
    //concept of exceptions handling in Java
    public static String ex4(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid student id (A-1234) :");
        String studentId = scanner.nextLine();

        if (studentId.length()==0){
            throw new IllegalArgumentException("studentId was null!");
        }
        if (studentId.length()!=6){
            throw new IllegalArgumentException("studentID length was not valid");
        }
        if (!studentId.startsWith("A")){
            throw new IllegalArgumentException("studentID must start with A");

        }
        return studentId;
    }

    public static void ex5(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number:");
        double n1 = scanner.nextDouble();
        System.out.println("Enter a number:");
        double n2 = scanner.nextDouble();

        if (n2==0){
            throw new IllegalArgumentException("Number2 must not be 0");
            //we don't need to catch exception because we force number 2 not to be 0
        }

        double result = n1 / n2;
        System.out.println(result);

    }
    //throws custom exception -> throws is used to declare an exception
    public static void ex6() throws DataNotFoundException{ //ex6 will throw new created Exception
        List<String> names = Arrays.asList("Mehrdad", "Marcus", "Simon", "Åsa");
        String inputName = "Test";

        Optional<String> optional =names.stream()
                .filter(name-> name.equalsIgnoreCase(inputName))
                .findFirst();

        if(optional.isPresent()) System.out.println(optional.get());
        else throw new DataNotFoundException("Data not found", 1,inputName );


    }
    public static void ex7() throws InsufficientFoundsException{

        double balance=100;
        System.out.println("Current balance is: "+balance);

        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter a valid number:");
        double amount = scanner.nextDouble();

        if (amount>balance){
            throw new InsufficientFoundsException("Balance is insufficient", 1 );
        }

        balance = balance-amount;
        System.out.println("Balance after withdraw is: "+balance);

    }

    //finally block
    public static void ex8(){

        BufferedWriter writer =null; //defined field
        try {
            Path path = Paths.get("dir/skills.txt"); //to write in the file
            writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
            //we provide the path
            //StandardOpenOption.APPEND -> keeps the existing contain

            writer.newLine(); // it inserts a new line
            writer.append("Exception handling in Java"); //insert the content in the new line
            //without writer.newline() -> it will override - to delete content and replace with the new one

            writer.close(); //to save and close the file

        } catch (IOException e){
            System.out.println(e);
        } finally { //it will execute always -> even we got exception or not

            System.out.println("-- finally block --");
            try {
                if (writer !=null)  writer.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }
        System.out.println("Operation is done!");
    }

    //try with resources
    public static void ex9(){
        Path path = Paths.get("dir/skills.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND))
        // all the classes Closeable, can be put inside ()
        {   writer.newLine();
            writer.append("Test");

            //writer.close(); // we don't need to close the writer - >it will close automatically

        } catch (IOException e){
            System.out.println(e);
        }

        System.out.println("Operation is done!");
    }
}
