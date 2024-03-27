import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//  Define a public class named Food+CenterTask1
public class FoodCenterTask1
{
    private static final int MAX_STOCK = 50;   // Represents the maximum stock of burgers (set to 50)
    private static final int WARNING_THRESHOLD = 10;    // Represents the threshold for the stock, below which a warning is triggered (set to 10)
    private static final int[] QUEUE_CAPACITY = { 2, 3, 5 };   //Represents the capacity of each queue (set to an array with values [2, 3, 5])

    private static String[][] queues = { { "", "" }, { "", "", "" }, { "", "", "", "", "" } };  //Represents a two-dimensional array that stores the customers in different
    private static int stock = MAX_STOCK;     //Represents the current stock of burgers and is initialized with the maximum stock value.

    // Define a private static method named an array of strings as input parameters
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            displayMenu();
            option = scanner.next();
            scanner.nextLine();

            switch (option) {
                case "100", "VFQ" :
                    viewAllQueues();
                    break;
                case "101", "VEQ":
                    viewAllEmptyQueues();
                    break;
                case "102", "ACQ":
                    addCustomerToQueue(scanner);
                    break;
                case "103", "RCQ":
                    removeCustomerFromQueue(scanner);
                    break;
                case "104", "PCQ":
                    removeServedCustomer();
                    break;
                case "105", "VCS":
                    viewCustomersSorted();
                    break;
                case "106", "SPD":
                    storeProgramData();
                    break;
                case "107", "LPD":
                    loadProgramData();
                    break;
                case "108", "STK":
                    viewRemainingStock();
                    break;
                case "109", "AFS":
                    addBurgersToStock(scanner);
                    break;
                case "999", "EXT":
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option != "999");

        scanner.close();
    }

    // Design the main menu of the program & Retrieving that data after the user selects a preferred option.
    private static void displayMenu()
    {
        System.out.println("________________________");
        System.out.println("*         Menu          *");
        System.out.println("========================");
        System.out.println("\nPlease select an option:");
        System.out.println("100 or VFQ: View all Queues.");
        System.out.println("101 or VEQ: View all Empty Queues.");
        System.out.println("102 or ACQ: Add customer to a Queue.");
        System.out.println("103 or RCQ: Remove a customer from a Queue.");
        System.out.println("104 or PCQ: Remove a served customer.");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order.");
        System.out.println("106 or SPD: Store Program Data into file.");
        System.out.println("107 or LPD: Load Program Data from file.");
        System.out.println("108 or STK: View Remaining burgers Stock.");
        System.out.println("109 or AFS: Add burgers to Stock.");
        System.out.println("999 or EXT: Exit the Program.");
        System.out.print("Enter your option: ");   // Getting the user's preferred option.
    }


    // Define a private static method named printQueues & Display the three queues separately
    private static void printQueues() {
        // Iterate through each queue in the 'queues' array
        for (int i = 0; i < queues.length; i++) {
            // Print the queue number
            System.out.print("Queue " + (i + 1) + ": ");

            // Iterate through each customer position in the current queue
            for (int j = 0; j < QUEUE_CAPACITY[i]; j++) {
                // Check if the customer position is empty
                if (queues[i][j].isEmpty()) {
                    // Print 'X' to represent an empty position
                    System.out.print("X ");
                } else {
                    // Print 'O' to represent a filled position
                    System.out.print("O ");
                }
            }

            // Move to the next line after printing all customer positions in the current queue
            System.out.println();
        }
    }


    // Define a private static method named viewAllQueues
    private static void viewAllQueues() {
        // Print a header indicating the section for cashiers
        System.out.println("***********************");
        System.out.println("*      Cashiers       *");
        System.out.print("***********************");
        for(int j=0;j<2;j++){
            System.out.println();
            for(int i=j;i<j+1;i++){
                System.out.print("\t");
                if(queues[0][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print("O\t");
                }
                if(queues[1][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print("O\t");
                }
                if(queues[2][i]==""){
                    System.out.print("X");
                }
                else{
                    System.out.print("O");
                }
            }
        }
        System.out.println();
        System.out.print("\t\t");
        if(queues[1][2]==""){
            System.out.print("X\t");
        }
        else{
            System.out.print("O\t");
        }
        if(queues[2][2]==""){
            System.out.print("X");
        }
        else{
            System.out.print("O");
        }

        System.out.println();
        System.out.print("\t\t\t");

        if(queues[2][3]==""){
            System.out.print("X");
        }
        else{
            System.out.print("O");
        }
        System.out.println();
        System.out.print("\t\t\t");

        if(queues[2][4]==""){
            System.out.print("X");
        }
        else{
            System.out.print("O");
        }
        System.out.print("\nX- Not Occupied   O- Occupied \n");  // Print a legend indicating the meaning of 'X' and 'O'

    }



    // Define a private static method named view Customers all empty queues
    private static void viewAllEmptyQueues()
    {
        System.out.println("***********************");// Print a header indicating cashiers
        System.out.println("*      Cashiers       *");
        System.out.print("***********************");
        // Iterate over the queues array
        for(int j=0;j<2;j++){
            System.out.println();
            // Iterate over each element in the current queue
            for(int i=j;i<j+1;i++){
                System.out.print("\t");
                // Check if the current element is not empty
                if(queues[0][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print(" \t");
                }
                if(queues[1][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print(" \t");
                }
                if(queues[2][i]==""){
                    System.out.print("X");
                }
                else{
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
        System.out.print("\t\t");
        if(queues[1][2]==""){
            System.out.print("X\t");
        }
        else{
            System.out.print(" \t");
        }
        if(queues[2][2]==""){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }

        System.out.println();
        System.out.print("\t\t\t");

        if(queues[2][3]==""){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("\t\t\t");

        if(queues[2][4]==""){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.print("\nX- Not Occupied   O- Occupied \n");

    }


    // The user can add customers from the queue at will
    private static void addCustomerToQueue(Scanner scanner) {
        // Prompt the user to enter the queue number
        System.out.print("Enter the queue number (1, 2, 3): ");
        String input = scanner.nextLine();// Consume newline character

        // Check if the input is a valid queue number
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("Invalid queue number. Please try again.");
            System.out.print("Enter the queue number (1, 2, 3): ");
            input = scanner.nextLine();
        }

        // Convert the input to an integer
        int queueNumber = Integer.parseInt(input);

        // Calculate the queue index based on the queue number
        int queueIndex = queueNumber - 1;

        // Check if the queue is already full
        if (isQueueFull(queueIndex)) {
            System.out.println("Queue " + queueNumber + " is already full.");
            return;
        }

        // Prompt the user to enter the customer name
        System.out.print("Enter the customer name: ");
        String customerName = scanner.nextLine();

        // Check if the stock level is below the warning threshold
        if (stock <= WARNING_THRESHOLD) {
            System.out.println("Warning: Low stock! Remaining burgers: " + stock);
        }

        // Add the customer name to the next empty slot in the queue
        queues[queueIndex][getNextEmptySlot(queueIndex)] = customerName;

        // Reduce the stock by 5 (assuming each customer consumes 5 burgers)
        stock -= 5;

        // Print a confirmation message indicating the customer has been added to the queue
        System.out.println("Customer " + customerName + " added to Queue " + queueNumber);
    }



    private static boolean isQueueFull(int queueIndex)
    {
        // Iterate over each slot in the specified queue
        for (int i = 0; i < QUEUE_CAPACITY[queueIndex]; i++) {
            // Check if the current slot is empty
            if (queues[queueIndex][i].isEmpty()) {
                // If an empty slot is found, the queue is not full
                return false;
            }
        }
        // If all slots are occupied, the queue is considered full
        return true;
    }


    // Define a private static method named view empty queue slot
    private static int getNextEmptySlot(int queueIndex)
    {
        // Iterate over each slot in the specified queue
        for (int i = 0; i < QUEUE_CAPACITY[queueIndex]; i++) {
            // Check if the current slot is empty
            if (queues[queueIndex][i].isEmpty()) {
                // If an empty slot is found, return its index
                return i;
            }
        }
        // If no empty slots are found, return -1 to indicate that the queue is full
        return -1;
    }


    // The user can remove customers from the queue at will
    private static void removeCustomerFromQueue(Scanner scanner)
    {
        // Prompt the user to enter the queue number
        System.out.print("Enter the queue number (1, 2, 3): ");
        int queueNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the entered queue number is valid
        if (queueNumber < 1 || queueNumber > 3) {
            System.out.println("Invalid queue number. Please try again.");
            return;
        }

        int queueIndex = queueNumber - 1;

        // Check if the specified queue is already empty
        if (isQueueEmpty(queueIndex)) {
            System.out.println("Queue " + queueNumber + " is already empty.");
            return;
        }

        // Prompt the user to enter the customer position in the queue
        System.out.print("Enter the customer position in the queue (1, 2, 3, ...): ");
        int position = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the entered position is valid for the specified queue
        if (position < 1 || position > QUEUE_CAPACITY[queueIndex]) {
            System.out.println("Invalid position. Please try again.");
            return;
        }

        // Retrieve the customer name from the specified position in the queue
        String customerName = queues[queueIndex][position - 1];
        // Clear the slot by assigning an empty string to it
        queues[queueIndex][position - 1] = "";

        stock += 5;

        // Display a message indicating the successful removal of the customer from the queue
        System.out.println("Customer " + customerName + " removed from Queue " + queueNumber);
    }


    // Define a private static method named view empty queue at this time
    private static boolean isQueueEmpty(int queueIndex)
    {
        // Iterate over each slot in the specified queue
        for (int i = 0; i < QUEUE_CAPACITY[queueIndex]; i++) {
            // Check if the slot is not empty (contains a non-empty string)
            if (!queues[queueIndex][i].isEmpty()) {
                // If a non-empty slot is found, the queue is not empty
                return false;
            }
        }
        // If all slots are empty, the queue is empty
        return true;
    }


    // Define a private static method named remove the served customer
    private static void removeServedCustomer()
    {
        // Iterate over each queue
        for (int i = 0; i < queues.length; i++) {
            // Iterate over each slot in the current queue
            for (int j = 0; j < QUEUE_CAPACITY[i]; j++) {
                // Check if the slot is not empty (contains a non-empty string)
                if (!queues[i][j].isEmpty()) {
                    // Retrieve the customer name from the slot
                    String customerName = queues[i][j];
                    // Clear the slot by assigning an empty string
                    queues[i][j] = "";
                    // Increase the stock by 5 (assuming each customer represents 5 units)
                    stock += 5;
                    // Print a message indicating the customer removal
                    System.out.println("Customer " + customerName + " removed from Queue " + (i + 1));
                    // Exit the method after removing the first served customer found
                    return;
                }
            }
        }
        // If no served customers are found in any queue, print a message
        System.out.println("No served customers found.");
    }


    // Define a private static method named view Sorted Customers
    private static void viewCustomersSorted()
    {
        // Print a message indicating that customers will be sorted
        System.out.println("\nCustomers Sorted in alphabetical order:");

        // Create a new array to store sorted customers
        String[] sortedCustomers = new String[getTotalCustomers()];

        // Initialize an index variable to keep track of the position in the sortedCustomers array
        int index = 0;

        // Iterate through each queue in the 'queues' array
        for (String[] queue : queues) {
            // Iterate through each customer in the current queue
            for (String customer : queue) {
                // Check if the customer is not empty (i.e., a valid customer)
                if (!customer.isEmpty()) {
                    // Add the customer to the sortedCustomers array at the current index position
                    sortedCustomers[index] = customer;
                    // Increment the index to move to the next position in sortedCustomers array
                    index++;
                }
            }
        }

        // Call the sortCustomers method to sort the array of customers in alphabetical order
        sortCustomers(sortedCustomers);

        // Iterate through each customer in the sortedCustomers array
        for (String customer : sortedCustomers) {
            // Print the customer
            System.out.println(customer);
        }
    }


    // Define a private static method named getTotalCustomers that returns an integer
    private static int getTotalCustomers()
    {
        // Initialize a variable to keep track of the total number of customers
        int totalCustomers = 0;

        // Iterate through each queue in the 'queues' array
        for (String[] queue : queues) {
            // Iterate through each customer in the current queue
            for (String customer : queue) {
                // Check if the customer is not empty (i.e., a valid customer)
                if (!customer.isEmpty()) {
                    // Increment the totalCustomers count by 1
                    totalCustomers++;
                }
            }
        }

        // Return the total number of customers
        return totalCustomers;
    }


    // Define a private static method named sortCustomers that takes an array of strings as input
    private static void sortCustomers(String[] customers)
    {
        // Iterate through each element of the customers array except the last one
        for (int i = 0; i < customers.length - 1; i++) {
            // Iterate through each element from the beginning up to the (last element - i - 1)
            for (int j = 0; j < customers.length - i - 1; j++) {
                // Compare the current customer and the next customer using the compareCustomers method
                if (compareCustomers(customers[j], customers[j + 1]) > 0) {
                    // Swap the current customer with the next customer
                    String temp = customers[j];
                    customers[j] = customers[j + 1];
                    customers[j + 1] = temp;
                }
            }
        }
    }


    // Define a private static method named compare Customers that takes two strings as input and returns an integer
    private static int compareCustomers(String customer1, String customer2)
    {
        // Determine the minimum length between customer1 and customer2
        int minLength = Math.min(customer1.length(), customer2.length());

        // Iterate through each character up to the minimum length
        for (int i = 0; i < minLength; i++) {
            // Compare the characters at the current position
            if (customer1.charAt(i) != customer2.charAt(i)) {

                // If the characters are different, return the difference between their Unicode values
                return customer1.charAt(i) - customer2.charAt(i);
            }
        }

        // If the characters up to the minimum length are the same, compare the lengths of customer1 and customer2
        return customer1.length() - customer2.length();
    }


    // Define a private static method named storeProgramData
    private static void storeProgramData()
    {
        // Create a file writer to write data to a file
        try {
            // Create a FileWriter object and specify the filename as "program_data.txt"
            FileWriter writer = new FileWriter("store_data.txt");

            // Write the stock value to the file
            writer.write("Queue 1:\n");
            // Iterate through each queue in the 'queues' array
            for (String customer : queues[0]) {
                if (customer != "") {
                    writer.write(customer + "\n");// Write the customer name to the file
                }
            }

            writer.write("\nQueue 2:\n");
            // Iterate through each queue in the 'queues' array
            for (String customer : queues[1]) {
                if (customer != "") {
                    writer.write(customer + "\n");// Write the customer name to the file
                }
            }

            writer.write("\nQueue 3:\n");
            // Iterate through each queue in the 'queues' array
            for (String customer : queues[2]) {
                if (customer != "") {
                    writer.write(customer + "\n");// Write the customer name to the file
                }
            }

            writer.write("\nBurger Stock: " + stock);

            // Close the FileWriter
            writer.close();
            System.out.println("Program data stored successfully.\n"); // Print a success message indicating that the program data was stored successfully
        } catch (IOException e) {
            System.out.println("An error occurred while storing program data.");// If an IOException occurs while writing or closing the file, print an error message
            e.printStackTrace();
        }
    }



    // Define a private static method named loadProgramData
    private static void loadProgramData()
    {
        // Print a message indicating that program data will be loaded
        System.out.println("Loading program data from a file...");

        // Attempt to load program data from a file
        try {
            File file = new File("store_data.txt");// Create a File object with the filename "program_data.txt"
            Scanner file_reader = new Scanner(file); // Create a Scanner object to read data from the file
            while (file_reader.hasNextLine()) {
                String text = file_reader.nextLine();
                System.out.println(text);
            }
            file_reader.close();
        }
        catch (IOException e) {
            System.out.println("Error while reading a file.");// If the file is not found, print a message indicating that no program data was found
            e.printStackTrace();
        }
    }


    // Define a private static method named viewRemainingStock
    private static void viewRemainingStock()
    {
        // Print a message indicating the remaining burgers in stock
        System.out.println("Remaining burgers in stock: " + stock);
    }


    // Define a private static method named addBurgersToStock that takes a Scanner object as input
    private static void addBurgersToStock(Scanner scanner) {
        // Prompt the user to enter the number of burgers to add
        System.out.print("Enter the number of burgers to add: ");

        // Read the number of burgers to add from the user input
        int burgersToAdd = scanner.nextInt();

        // Consume the newline character to prepare for the next input
        scanner.nextLine();

        // Check if adding burgers will exceed the maximum stock limit
        if (stock + burgersToAdd > MAX_STOCK) {
            // Print an error message if the maximum stock limit will be exceeded
            System.out.println("Maximum stock limit exceeded. Cannot add more burgers.");
        } else {
            // If the maximum stock limit will not be exceeded, update the stock count and print a success message
            stock += burgersToAdd;
            System.out.println("Added " + burgersToAdd + " burgers to the stock. Total stock: " + stock);
        }
    }
}