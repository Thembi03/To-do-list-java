import java.io.*;
import java.util.*;


public class TodoListFinal {
	//initializing scanner for the whole program
	
	private static final String FILE_NAME = "ToDoList.txt";
	
	
	
	public static void main(String[]args) {
		TodoListFinal tdl = new TodoListFinal();//create an instance of the To-do list class
		tdl.createOrLoadFile();
		tdl.displayMenu();
	}
	//method for opening or loading a txt file without the to-do list tasks
	public void createOrLoadFile() {
		File toDoFile = new File(FILE_NAME);
		try {
			if (toDoFile.createNewFile()) {
				System.out.println("To do list created successfully");
			}else {
				System.out.println("Existing To-do list loaded");
			}
		}catch(IOException e){
			System.err.println("Error creating or loading To-do list file:" + e.getMessage());
		}finally {}
	}
	public void displayMenu() {
		int userSelection;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("\n1. Add Task\n2. Remove Task\n3. View Tasks\n4. Exit  \n5.Clear To do list\n");
            System.out.print("Enter your choice: ");
            userSelection = input.nextInt();
            switch (userSelection) {
            case 1:
                addTask();
                break;
            case 2:
                removeTask();
                break;
            case 3:
                viewTasks();
                break;
            case 4:
                System.out.println("Exiting the program.");
                break;
            case 5:
            	clearToDoList(FILE_NAME);
            default:
                System.out.println("Please enter a valid option. \n");
		}
	}while(userSelection != 4);
		input.close();
}
	public void addTask() {
		Scanner input = new Scanner(System.in);
		input = new Scanner(System.in);
		System.out.println("You are now entering your tasks, enter done(case sensitive) to exit task adder");
		String taskDescription ="";
		while(!taskDescription.equalsIgnoreCase("done")) {
		System.out.print("\nEnter the task description: \t");
		taskDescription = input.nextLine();
		if(taskDescription.equalsIgnoreCase("done")) {
			break;
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))){
			
			writer.write(taskDescription);
			writer.newLine();
			System.out.println("Task added successfully");
		}catch (IOException e) {
            System.err.println("Error adding task: " + e.getMessage());
        }
		};
	}

	public static void removeTask() {
	    Scanner input = new Scanner(System.in);

	    while (true) {
	        List<String> tasks = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	            String task;

	            // Read tasks and display them with indices
	            
	            while ((task = reader.readLine()) != null) {
	                tasks.add(task);
	                System.out.println(task);
	                
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading tasks: " + e.getMessage());
	            return;
	        }

	        if (tasks.isEmpty()) {
	            System.out.println("No tasks available for removal.");
	            return;
	        }else {
	        	System.out.println("Current tasks: \n");
	        	int index = 1;
	            for (String element : tasks) {
	                System.out.println(index + ". " + element + "\n");
	                index++;
	            }
	        }

	        System.out.print("\nEnter the task index to remove or enter -1 to end task removal: ");
	        int taskIndex = input.nextInt();

	        if (taskIndex == -1) {
	            break;  // Exit the loop when -1 is entered
	        }

	        if (taskIndex < 1 || taskIndex > tasks.size()) {
	            System.out.println("Invalid task index");
	            continue;  // Restart the loop if an invalid index is entered
	        }

	        tasks.remove(taskIndex - 1);

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
	           

	            for (String updatedTask : tasks) {
	                // Update the index in the task description
	                writer.write(updatedTask);
	                writer.newLine();
	                
	            }

	            System.out.println("Task removed successfully.");
	        } catch (IOException e) {
	            System.err.println("Error writing tasks: " + e.getMessage());
	        }
	    }
	}
	
	
	public void viewTasks() {
	    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
	        String task;
	        System.out.println("\nCurrent Tasks:");
	        int index = 1;
	        while ((task = reader.readLine()) != null) {
	            System.out.println(index+". "+task+"\n");
	            index++;
	        }
	    } catch (IOException e) {
	        System.err.println("Error viewing tasks: " + e.getMessage());
	    }
	}
    public void clearToDoList(String filePath) {
        try {
            // Open the file in overwrite mode to clear its contents
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, false));

            // Closing the writer to ensure changes are flushed to the file
            writer.close();

            System.out.println("To-Do list cleared successfully.");
        } catch (IOException e) {
            System.err.println("Error clearing the to-do list: " + e.getMessage());
        }
    }
}


