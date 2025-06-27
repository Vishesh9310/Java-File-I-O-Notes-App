import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Note{
    private static final String File_Name = "Notes.txt";

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do { 
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Add Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Delete All Notes");
            System.out.println("4. Update Existing Notes (by replacing matching text)");
            System.out.println("5. Exit");
            System.out.print("Enter Option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    AddNote(scanner);
                    break;
                case 2:
                    ViewNote();
                    break;
                case 3:
                    DeleteNote();
                    break;
                case 4:
                    UpdateNote(scanner);
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again...");
            }
            
        } while (choice != 5);
        scanner.close();
    }

    private static void AddNote(Scanner sc){
        System.out.println("Enter Your Note:");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(File_Name,true)){
            fw.write(note + "\n");
            System.out.println("Note Added Successfully");
        } catch (Exception e) {
            System.out.println("An error occurred while writing the note.");
        }
    }

    private static void ViewNote(){
        try(BufferedReader br = new BufferedReader(new FileReader(File_Name))){
            String line;
            System.out.println("\nAll Notes");
            boolean empty = true;
            while((line = br.readLine()) != null){
                System.out.println("- "+ line);
                empty = false;
            }
            if(empty){
                System.out.println("NO Notes Found.");
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    private static void DeleteNote(){
        try(FileWriter fr = new FileWriter(File_Name)){
            fr.write(" ");
            System.out.println("All Notes Deleted.");
        }catch(IOException e){
            System.out.println("An error occurred while deleting notes.");
        }
    }

    private static void UpdateNote(Scanner scanner){
        System.out.println("Update Note.");
        try {
            ViewNote();
            System.out.println("Enter Text to search for: ");
            String oldText = scanner.nextLine();
            System.out.println("Enter New Text to replace with: ");
            String newText = scanner.nextLine();

            File file = new File(File_Name);
            StringBuilder contentBuilder = new StringBuilder();

            //read origin file
            try(BufferedReader br = new BufferedReader(new FileReader(File_Name))){
                String line;
                while((line = br.readLine()) != null){
                    String updatedline = line.replace(oldText, newText);
                    contentBuilder.append(updatedline).append("\n");
                }
            }catch(IOException e){
                System.out.println("Error reading the file: " + e.getMessage());
                return;
            }

            //write back to the file
            try(FileWriter fw = new FileWriter(File_Name)){
                fw.write(contentBuilder.toString());
                System.out.println("File updated successfully");
            }catch(IOException e){
                System.out.println("Error writing to the file: "+ e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}