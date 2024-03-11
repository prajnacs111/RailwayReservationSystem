import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

class RailwayReservationSystem {
    static final int MAX_SEATS = 72;
    private static final int max_book = 2;
    private static boolean[] seats = new boolean[MAX_SEATS];
    private static HashMap<String, String> userData = new HashMap<>();
    private static HashMap<String, Integer> priceMap = new HashMap<>();
    private static HashMap<String, String> trainMap = new HashMap<>();
    private static HashMap<Integer, String> trainNumberToName = new HashMap<>(); // New map to store train numbers and names

    static {
        getUserData().put("Manasi", "123456");
        getUserData().put("Prathiksha", "78910");
        getUserData().put("Prajna", "1245");

        getPriceMap().put("Mumbai", 500);
        getPriceMap().put("Kolkata", 600);
        getPriceMap().put("Ahmedabad", 400);

        getTrainMap().put("Mumbai", "Vande Express");
        getTrainMap().put("Kolkata", "Rajdhaani");
        getTrainMap().put("Ahmedabad", "Indore");

        // Populate train number to name mapping
        getTrainNumberToName().put(1, "Vande Express");
        getTrainNumberToName().put(2, "Rajdhaani");
        getTrainNumberToName().put(3, "Indore");
    }

    @SuppressWarnings("fallthrough")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        boolean correct = false;
        while (!correct) {
            System.out.print("Enter your login ID: ");
            String ID = sc.next();

            System.out.print("Enter your password: ");
            String password = sc.next();

            correct = authenticateUser(ID, password);

            if (!correct) {
                System.out.println("Invalid login credentials. Please try again.");
                System.out.println();
            }
        }

        int choice;
        do {
            System.out.println("====IRCTC Railway Reservation System ====");
            System.out.println("1. Reserve a seat");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Check seat availability");
            System.out.println("4. Get train name from train number");
            System.out.println("5. Exit"); // Corrected option number
        
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 : reserveSeat(random, sc);
                case 2 : cancelReservation(sc);
                case 3 : checkAvailability();
                case 4 : getTrainName(sc);
                case 5 : System.out.println("Thank you for using the Railway Reservation System!");
                default : System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (choice != 5);
    }

    static void reserveSeat(Random random, Scanner scanner) {
        System.out.println("Enter your desired destination (Mumbai, Kolkata, Ahmedabad): ");
        String destination = scanner.next();

        if (!priceMap.containsKey(destination)) {
            System.out.println("Invalid destination.");
            return;
        }

        int bookingAttempts = 0;
        int seatnum;
        do {
            seatnum = random.nextInt(MAX_SEATS) + 1;
            bookingAttempts++;
        } while (getSeats()[seatnum - 1] && bookingAttempts <= max_book);

        if (getSeats()[seatnum - 1]) {
            System.out.println("All seats are occupied. Please try again later.");
        } else {
            getSeats()[seatnum - 1] = true;
            System.out.println("Seat reserved. Your seat number is " + seatnum);
            System.out.println("Train: " + getTrainMap().get(destination));
            System.out.println("Departure Time: 10:00 AM");
            System.out.println("Arrival Time: 5:00 PM");
            System.out.println("Price: " + getPriceMap().get(destination) + " INR");
        }
    }

    static void cancelReservation(Scanner scanner) {
        System.out.print("Enter the seat number to cancel the reservation: ");
        int seatnum = scanner.nextInt();

        if (seatnum < 1 || seatnum > MAX_SEATS) {
            System.out.println("Invalid seat number.");
        } else if (!getSeats()[seatnum - 1]) {
            System.out.println("No reservation found for seat " + seatnum + ".");
        } else {
            getSeats()[seatnum - 1] = false;
            System.out.println("Reservation for seat " + seatnum + " has been canceled.");
        }
    }

    static void checkAvailability() {
        System.out.println("Seat availability:");
        for (int i = 0; i < MAX_SEATS; i++) {
            if (getSeats()[i]) {
                System.out.println("Seat " + (i + 1) + ": Reserved");
            } else {
                System.out.println("Seat " + (i + 1) + ": Available");
            }
        }
    }

    static void getTrainName(Scanner scanner) {
        System.out.print("Enter the train number: ");
        int trainNumber = scanner.nextInt();

        String trainName = getTrainNumberToName().get(trainNumber);
        if (trainName != null) {
            System.out.println("Train name for train number " + trainNumber + " is " + trainName);
        } else {
            System.out.println("Train name not found for train number " + trainNumber);
        }
    }

    static boolean authenticateUser(String ID, String password) {
        String savedpass = getUserData().get(ID);

        if (savedpass != null && savedpass.equals(password)) {
            System.out.println("Login successful! Welcome " + ID + ".");
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the seats
     */
    public static boolean[] getSeats() {
        return seats;
    }

    /**
     * @param aSeats the seats to set
     */
    public static void setSeats(boolean[] aSeats) {
        seats = aSeats;
    }

    /**
     * @return the userData
     */
    public static HashMap<String, String> getUserData() {
        return userData;
    }

    /**
     * @param aUserData the userData to set
     */
    public static void setUserData(HashMap<String, String> aUserData) {
        userData = aUserData;
    }

    /**
     * @return the priceMap
     */
    public static HashMap<String, Integer> getPriceMap() {
        return priceMap;
    }

    /**
     * @param aPriceMap the priceMap to set
     */
    public static void setPriceMap(HashMap<String, Integer> aPriceMap) {
        priceMap = aPriceMap;
    }

    /**
     * @return the trainMap
     */
    public static HashMap<String, String> getTrainMap() {
        return trainMap;
    }

    /**
     * @param aTrainMap the trainMap to set
     */
    public static void setTrainMap(HashMap<String, String> aTrainMap) {
        trainMap = aTrainMap;
    }

    /**
     * @return the trainNumberToName
     */
    public static HashMap<Integer, String> getTrainNumberToName() {
        return trainNumberToName;
    }

    /**
     * @param aTrainNumberToName the trainNumberToName to set
     */
    public static void setTrainNumberToName(HashMap<Integer, String> aTrainNumberToName) {
        trainNumberToName = aTrainNumberToName;
    }
}