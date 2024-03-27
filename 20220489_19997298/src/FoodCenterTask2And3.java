import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Customer {
    private String firstName; // Declare a private variable to store the customer's first name
    private String lastName; // Declare a private variable to store the customer's last name
    private int burgersRequired; // Declare a private variable to store the number of burgers required


    // Create a constructor to initialize the customer's first name, last name, and number of burgers required
    public Customer(String firstName, String lastName, int burgersRequired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgersRequired = burgersRequired;
    }


    // Create a method to retrieve the customer's first name
    public String getFirstName() {
        return firstName;
    }

    // Create a method to retrieve the customer's last name
    public String getLastName() {
        return lastName;
    }

    // Create a method to retrieve the number of burgers required by the customer

    public int getBurgersRequired() {
        return burgersRequired;
    }
} // End of the Customer class



class FoodQueue {
    private static final int MAX_CUSTOMERS = 5; // Declare a constant variable to represent the maximum number of customers in the queue

    private static final int MAX_WAITING_LIST = 10; // Declare a constant variable to represent the maximum number of customers in the waiting list

    private Customer[] customers;  // Declare an array to store the customers in the queue

    private int size; // Declare a variable to track the current number of customers in the queue

    private int front; // Declare a variable to represent the front index of the queue
    private int rear;  // Declare a variable to represent the rear index of the queue


    private Customer[] waitingList;  // Declare an array to store the customers in the waiting list

    private int waitingListSize;   // Declare a variable to track the current number of customers in the waiting list

    private int waitingListFront; // Declare a variable to represent the front index of the waiting list
    private int waitingListRear;   // Declare a variable to represent the rear index of the waiting list


    public FoodQueue() {
        customers = new Customer[MAX_CUSTOMERS]; // Initialize the customers array with the maximum number of customers
        size = 0; // Set the initial size of the queue to 0
        front = 0; // Set the initial front index of the queue to 0
        rear = -1; // Set the initial rear index of the queue to -1

        waitingList = new Customer[MAX_WAITING_LIST]; // Initialize the waitingList array with the maximum number of customers in the waiting list
        waitingListSize = 0; // Set the initial size of the waiting list to 0
        waitingListFront = 0; // Set the initial front index of the waiting list to 0
        waitingListRear = -1; // Set the initial rear index of the waiting list to -1
    }


    public boolean isFull() {
        return size == MAX_CUSTOMERS; // Check if the queue is full by comparing the current size with the maximum number of customers
    }

    public boolean isEmpty() {
        return size == 0; // Check if the queue is empty by comparing the current size with 0
    }

    public int getSize() {
        return size; // Return the current size of the queue
    }

    public boolean isWaitingListFull() {
        return waitingListSize == MAX_WAITING_LIST; // Check if the waiting list is full by comparing the current size with the maximum number of customers in the waiting list
    }

    public boolean isWaitingListEmpty() {
        return waitingListSize == 0; // Check if the waiting list is empty by comparing the current size with 0
    }

    private void enqueueWaitingList(Customer customer) {
        waitingListRear = (waitingListRear + 1) % MAX_WAITING_LIST; // Increment the rear index of the waiting list in a circular manner
        waitingList[waitingListRear] = customer; // Add the customer to the waiting list at the updated rear index
        waitingListSize++;  // Increment the size of the waiting list
    }

    private Customer dequeueWaitingList() {
        Customer customer = waitingList[waitingListFront]; // Get the customer at the front index of the waiting list
        waitingList[waitingListFront] = null;  // Remove the customer from the waiting list by setting the front index to null
        waitingListFront = (waitingListFront + 1) % MAX_WAITING_LIST; // Increment the front index of the waiting list in a circular manner
        waitingListSize--; // Decrement the size of the waiting list
        return customer; // Return the dequeued customer
    }


    public void addCustomer(Customer customer) {
        if (!isFull()) {
            rear = (rear + 1) % MAX_CUSTOMERS; // Increment the rear index of the queue in a circular manner
            customers[rear] = customer; // Add the customer to the queue at the updated rear index
            size++; // Increment the size of the queue
            System.out.println("Customer added to the queue.");
        } else if (!isWaitingListFull()) {
            enqueueWaitingList(new Customer(customer.getFirstName(), customer.getLastName(), customer.getBurgersRequired())); // Add the customer to the waiting list
            System.out.println("Customer added to the waiting list.");
        } else {
            System.out.println("Queue is full. Customer cannot be added.");
        }
    }



    public void removeCustomer(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                customers[i] = customers[i + 1]; // Shift the customers in the queue to the left starting from the given index
            }
            customers[size - 1] = null; // Set the last position in the queue to null
            size--; // Decrement the size of the queue
            System.out.println("Customer removed from the queue.");
        } else {
            System.out.println("Invalid index. Customer cannot be removed.");
        }
    }

    public void removeServedCustomer() {
        if (!isEmpty()) {
            front = (front + 1) % MAX_CUSTOMERS; // Increment the front index of the queue in a circular manner
            size--; // Decrement the size of the queue
            System.out.println("Served customer removed from the queue.");

            if (!isWaitingListEmpty()) {
                Customer nextCustomer = dequeueWaitingList(); // Dequeue the next customer from the waiting list
                addCustomer(nextCustomer); // Add the next customer to the queue
                System.out.println("Next customer from the waiting list added to the queue.");
            }
        } else {
            System.out.println("Queue is empty. No served customer to remove.");
        }
    }
    public void viewCustomersSorted() {
        if (!isEmpty()) {
            Customer[] sortedCustomers = new Customer[size]; // Create a new array to store the sorted customers
            System.arraycopy(customers, 0, sortedCustomers, 0, size); // Copy the customers from the queue to the sorted customers array


            // Bubble sort the customers by their last names
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    if (sortedCustomers[j].getLastName().compareTo(sortedCustomers[j + 1].getLastName()) > 0) {
                        Customer temp = sortedCustomers[j];
                        sortedCustomers[j] = sortedCustomers[j + 1];
                        sortedCustomers[j + 1] = temp;
                    }
                }
            }

            System.out.println("Customers sorted in alphabetical order:");
            for (int i = 0; i < size; i++) {
                Customer customer = sortedCustomers[i]; // Get each customer from the sorted customers array
                System.out.println(customer.getLastName() + ", " + customer.getFirstName() +
                        " - Burgers required: " + customer.getBurgersRequired());
            }
        } else {
            System.out.println("Queue is empty. No customers to view.");
        }
    }
}

public class FoodCenterTask2And3  {
    private static final int MAX_STOCK = 50; // Declare a constant variable to represent the maximum number of customers in the queue

    private static final int WARNING_THRESHOLD = 10; // Declare a constant variable to represent the maximum number of customers in the waiting list

    private static int stock = MAX_STOCK; // Declare an array to store the customers in the queue
    private static FoodQueue[] queues; // Declare a variable to track the current number of customers in the queue


    private static final int[] QUEUE_CAPACITY = { 2, 3, 5 }; // Define the capacity of each queue


    private static String[][] queue = { { "", "" }, { "", "", "" }, { "", "", "", "", "" } }; // Initialize a 2D array to represent the queues and their customers


    public static void main(String[] args) {
        queues = new FoodQueue[3]; // Create an array to store the three queues
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new FoodQueue(); // Initialize each queue in the array
        }

        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
        String option;

        do {
            displayMenu(); // Display the menu to the user
            option = scanner.next(); // Read the user's option
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case "100", "VFQ" :
                    viewAllQueues(); // Display all the queues and their customers
                    break;
                case "101", "VEQ":
                    viewAllEmptyQueues(); // Display all the queues that are currently empty
                    break;
                case "102", "ACQ":
                    addCustomerToQueue(scanner); // Add a customer to a specific queue
                    break;
                case "103", "RCQ":
                    removeCustomerFromQueue(scanner); // Remove a customer from a specific queue
                    break;
                case "104", "PCQ":
                    removeServedCustomerFromQueue(); // Remove a served customer from the queues
                    break;
                case "105", "VCS":
                    viewCustomersSortedInAlphabeticalOrder(); // View all customers sorted in alphabetical order
                    break;
                case "106", "SPD":
                    storeProgramDataToFile();  // Store program data to a file
                    break;
                case "107", "LPD":
                    loadProgramDataFromFile(); // Load program data from a file
                    break;
                case "108", "STK":
                    viewRemainingBurgersStock(); // View the remaining burgers in stock
                    break;
                case "109", "AFS":
                    addBurgersToStock(scanner); // Add burgers to the stock
                    break;
                case "110", "IFQ":
                    printIncomeOfEachQueue(); // Print the income of each queue
                    break;
                case "999", "EXT":
                    exitProgram(); // Exit the program
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Handle invalid input
                    break;

            }
        } while (option != "999"); // Continue the loop until the user chooses to exit
        scanner.close(); // Close the scanner object
    }


    private static void displayMenu() {
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
        System.out.println("110 or IFQ: Print the income of each queue.");
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
                if (queue[i][j].isEmpty()) {
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
    private static void viewAllQueues() {
        // Print a header indicating the section for cashiers
        System.out.println("***********************");
        System.out.println("*      Cashiers       *");
        System.out.print("***********************");
        for(int j=0;j<2;j++){
            System.out.println();
            for(int i=j;i<j+1;i++){
                System.out.print("\t");
                if(queue[0][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print("O\t");
                }
                if(queue[1][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print("O\t");
                }
                if(queue[2][i]==""){
                    System.out.print("X");
                }
                else{
                    System.out.print("O");
                }
            }
        }
        System.out.println();
        System.out.print("\t\t");
        if(queue[1][2]==""){
            System.out.print("X\t");
        }
        else{
            System.out.print("O\t");
        }
        if(queue[2][2]==""){
            System.out.print("X");
        }
        else{
            System.out.print("O");
        }

        System.out.println();
        System.out.print("\t\t\t");

        if(queue[2][3]==""){
            System.out.print("X");
        }
        else{
            System.out.print("O");
        }
        System.out.println();
        System.out.print("\t\t\t");

        if(queue[2][4]==""){
            System.out.print("X");
        }
        else{
            System.out.print("O");
        }
        System.out.print("\nX- Not Occupied   O- Occupied \n");  // Print a legend indicating the meaning of 'X' and 'O'

    }

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
                if(queue[0][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print(" \t");
                }
                if(queue[1][i]==""){
                    System.out.print("X\t");
                }
                else{
                    System.out.print(" \t");
                }
                if(queue[2][i]==""){
                    System.out.print("X");
                }
                else{
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
        System.out.print("\t\t");
        if(queue[1][2]==""){
            System.out.print("X\t");
        }
        else{
            System.out.print(" \t");
        }
        if(queue[2][2]==""){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }

        System.out.println();
        System.out.print("\t\t\t");

        if(queue[2][3]==""){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("\t\t\t");

        if(queue[2][4]==""){
            System.out.print("X");
        }
        else{
            System.out.print(" ");
        }
        System.out.print("\nX- Not Occupied   O- Occupied \n");

    }

    private static void addCustomerToQueue(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter the queue number (1, 2, 3) or \n 'exit' to go back to the main menu: ");
            String input = scanner.nextLine();

            // Check if the user wants to exit
            if (input.equalsIgnoreCase("exit")) {
                exit = true;
                continue;
            }

            // Check if the input is a valid queue number
            while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                System.out.println("Invalid queue number. Please try again.");
                System.out.print("Enter the queue number (1, 2, 3): ");
                input = scanner.nextLine();
            }

            int queueNumber = Integer.parseInt(input);
            int queueIndex = queueNumber - 1;

            // Check if the queue is already full
            if (isQueueFull(queueIndex)) {
                System.out.println("Queue " + queueNumber + " is already full.");
                continue;
            }

            System.out.println("Enter the customer's first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter the customer's last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter the number of burgers required:");
            int burgersRequired = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            // Check if the stock level is below the warning threshold
            if (stock <= WARNING_THRESHOLD) {
                System.out.println("Warning: Low stock! Remaining burgers: " + stock);
            }

            // Add the customer name to the next empty slot in the queue
            queue[queueIndex][getNextEmptySlot(queueIndex)] = firstName +" " + lastName;


            // Reduce the stock by 5 (assuming each customer consumes 5 burgers)
            stock -= 5;

            Customer customer = new Customer(firstName, lastName, burgersRequired);

            FoodQueue queue = queues[queueIndex];
            queue.addCustomer(customer);
            updateStock(-burgersRequired);

            // Print a confirmation message indicating the customer has been added to the queue
            System.out.println("Customer " + firstName +" "+ lastName + " added to Queue " + queueNumber);
        }
    }


    private static boolean isQueueFull(int queueIndex)
    {
        // Iterate over each slot in the specified queue
        for (int i = 0; i < QUEUE_CAPACITY[queueIndex]; i++) {
            // Check if the current slot is empty
            if (queue[queueIndex][i].isEmpty()) {
                // If an empty slot is found, the queue is not full
                return false;
            }
        }
        // If all slots are occupied, the queue is considered full
        return true;
    }

    private static int getNextEmptySlot(int queueIndex)
    {
        // Iterate over each slot in the specified queue
        for (int i = 0; i < QUEUE_CAPACITY[queueIndex]; i++) {
            // Check if the current slot is empty
            if (queue[queueIndex][i].isEmpty()) {
                // If an empty slot is found, return its index
                return i;
            }
        }
        // If no empty slots are found, return -1 to indicate that the queue is full
        return -1;
    }

    private static void removeCustomerFromQueue(Scanner scanner) {
        System.out.println("Enter the queue number (1, 2, or 3):");
        int queueNumber = scanner.nextInt();
        System.out.println("Enter the customer index (starting from 0):");
        int customerIndex = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        if (isValidQueueNumber(queueNumber) && isValidCustomerIndex(queueNumber, customerIndex)) {
            FoodQueue queue = queues[queueNumber - 1];
            queue.removeCustomer(customerIndex);
        } else {
            System.out.println("Invalid queue number or customer index. Customer cannot be removed.");
        }
    }

    private static boolean isValidQueueNumber(int queueNumber) {
        return queueNumber >= 1 && queueNumber <= queues.length;
    }

    private static boolean isValidCustomerIndex(int queueNumber, int customerIndex) {
        return customerIndex >= 0 && customerIndex < queues[queueNumber - 1].getSize();
    }

    private static void removeServedCustomerFromQueue() {
        for (FoodQueue queue : queues) {
            queue.removeServedCustomer();
        }
    }

    private static void viewCustomersSortedInAlphabeticalOrder() {
        System.out.println("----- Customers Sorted in Alphabetical Order -----");
        for (FoodQueue queue : queues) {
            queue.viewCustomersSorted();
        }
    }

    private static void storeProgramDataToFile() {
        // Create a file writer to write data to a file
        try {
            // Create a FileWriter object and specify the filename as "program_data.txt"
            FileWriter writer = new FileWriter("store_data.txt");

            // Write the stock value to the file
            writer.write("Queue 1:\n");
            // Iterate through each queue in the 'queues' array
            for (String customer : queue[0]) {
                if (customer != "") {
                    writer.write(customer + "\n");// Write the customer name to the file
                }
            }

            writer.write("\nQueue 2:\n");
            // Iterate through each queue in the 'queues' array
            for (String customer : queue[1]) {
                if (customer != "") {
                    writer.write(customer + "\n");// Write the customer name to the file
                }
            }

            writer.write("\nQueue 3:\n");
            // Iterate through each queue in the 'queues' array
            for (String customer : queue[2]) {
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

    private static void loadProgramDataFromFile() {
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

    private static void viewRemainingBurgersStock() {
        System.out.println("Remaining burgers stock: " + stock);
    }

    private static void addBurgersToStock(Scanner scanner) {
        System.out.println("Enter the number of burgers to add:"); // getting required count of burgers
        int burgersToAdd = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        if (burgersToAdd > 0) {
            updateStock(burgersToAdd);
            System.out.println("Burgers added to stock."); // added the new burger stock
        } else {
            System.out.println("Invalid number of burgers. Burgers cannot be added to stock.");
        }
    }

    private static void updateStock(int quantity) {
        stock += quantity;
        if (stock <= 10) {
            System.out.println("Warning: Low stock! Remaining burgers: " + stock);
        }
    }

    private static void printIncomeOfEachQueue() {
        System.out.println("----- Income of Each Queue -----");
        int pricePerBurger = 650;
        int totalIncome = 0;
        for (int i = 0; i < queues.length; i++) {
            FoodQueue queue = queues[i];
            int income = queue.getSize() * pricePerBurger;
            System.out.println("Queue " + (i + 1) + " income: " + income);
            totalIncome += income;
        }
        System.out.println("Total income: " + totalIncome);
    }

    private static void exitProgram() {
        System.out.println("Have a Nice Day");
    }
}
