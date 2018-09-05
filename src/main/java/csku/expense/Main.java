package csku.expense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Users users1 = new Users(1, 6499, 200);
        System.out.println("<<< Welcome >>>");

        Scanner in = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            System.out.print("Enter ID : ");
            int id = in.nextInt();
            System.out.print("Enter PIN: ");
            int pin = in.nextInt();
            if (users1.validateUser(id, pin)) {
                System.out.println("Welcome user id : " + users1.getId());
                System.out.println("Balance = " + users1.getUserBalance());

                while (true){
                    System.out.println("---------------");
                    System.out.print("I = Add income, E = Add expense, B = Check current balance, X = Exit: ");
                    String command = in.next();
                    if (command.equalsIgnoreCase("I")) {
                        System.out.print("Detail : ");
                        String detail = br.readLine();
                        if (detail != null){
                            System.out.print("Amount: ");
                            double amount = in.nextDouble();
                            users1.userAddIncome(amount);
                            System.out.println(detail + " " + amount + " "+ " Baht");
                            System.out.println("Your Balance is " + users1.getUserBalance() + " Baht");
                        }
                        //add income method
                    }
                    else if (command.equalsIgnoreCase("E")) {
                        System.out.print("Detail : ");
                        String detail = br.readLine();
                        if (detail != null){
                            System.out.print("Amount: ");
                            double amount = in.nextDouble();
                            users1.userAddExpense(amount);
                            System.out.println(detail + " " + amount + " "+ " Baht");
                            System.out.println("Your Balance is " + users1.getUserBalance() + " Baht");
                        }
                        //add expense

                    }
                    else if (command.equalsIgnoreCase("B")) {
                        System.out.println("Your balance is : " + users1.getUserBalance());
                    }
                    else if (command.equalsIgnoreCase("X"))
                        System.exit(0);
                    else
                        System.out.println("Illegal input!");
                }

            }
            else { System.out.println("Your ID or PIN incorrect"); }
        }


    }
}