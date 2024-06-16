package cinema;
import java.util.Scanner;

public class Cinema {

    //static Scanner sc = new Scanner(System.in);
    static int rows;
    static int seats;
    static char[][] sala;
    static int numPurchTickets = 0;
    static int totalIncome = 0;



    public static void main(String[] args) {
        // Write your code here
        setNumberOfRowsAndSeats();

        initUserMenu();

    }

    private static void initUserMenu() {
        Scanner sc = new Scanner(System.in);
        int option;
        boolean running = true;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = sc.nextInt();
            switch(option){
                case 1:
                    printSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please select a valid option (0, 1, 2)");
            }
        } while (running);

    }

    private static void showStatistics() {
        System.out.println();
        System.out.println("Number of purchased tickets: " + numPurchTickets);
        System.out.printf("Percentage: %.2f%%\n", (float) numPurchTickets * 100 / (rows * seats));
        System.out.println("Current income: $" + totalIncome);
        System.out.println("Total income: $" + calculateProfit() );

    }


    static void setNumberOfRowsAndSeats(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        sala = new char[rows][seats];

        for (int i = 0; i < sala.length; i++){
            for (int j = 0; j < sala[0].length; j++) {
                sala[i][j] = 'S';
            }
        }
    }

    static void buyTicket() {

        Scanner sc = new Scanner(System.in);
        int rowSelect;
        int seatSelect;
        int totalSeats;
        int ticketPrice;

        System.out.println();
        System.out.println("Enter a row number:");
        rowSelect = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatSelect = sc.nextInt();


        if (rowSelect - 1 < 0 || rowSelect - 1 > sala.length - 1 ||
            seatSelect -1 < 0 || seatSelect -1 > sala[0].length -1) {

            System.out.println("Wrong input!");
            buyTicket();

        } else if (sala[rowSelect - 1][seatSelect - 1] == 'B') {

            System.out.println("That ticket has already been purchased!");
            buyTicket();

        } else {

            sala[rowSelect - 1][seatSelect - 1] = 'B';
            numPurchTickets++;
            totalSeats = rows * seats;
            ticketPrice = totalSeats < 60? 10 : rowSelect <= rows/2? 10 : 8;
            totalIncome += ticketPrice;
            System.out.println("Ticket price: $" + ticketPrice);
        }


    }
    static int calculateProfit(){

        int totalSeats;
        int totalIncome;

        totalSeats = rows * seats;

        if (totalSeats < 60) {
            totalIncome = totalSeats * 10;
        } else {
          int front = rows / 2;
          int back = rows - (front);
          totalIncome = front * seats * 10 + back * seats * 8;

        }

        return totalIncome;
        //System.out.println("Total income:");
        //System.out.printf("$%d\n", totalIncome);

    }

    static void printSeats() {

        System.out.println();
        String title = "Cinema:";

        System.out.println(title);

        for (int i = 0; i <= sala.length; i++) {
            for(int j = 0; j <= sala[0].length; j++) {
                if(i == 0) { //si es la primera fila i=0
                    if(j == 0) { //si es la primera columna en la primera fila
                        System.out.print("  ");
                    }else {
                        System.out.print(j + " "); // primera fila si no es la primera columna
                    }
                }else { //no es la primera fila i=1

                    if (j == 0) {
                        System.out.print(i + " ");
                    } else {
                       System.out.print(sala[i-1][j-1] + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}