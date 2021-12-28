package org.example;

import java.util.Scanner;

public class App
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean run = true;

        while(run){
            System.out.print("1: Create\n2: Retrieve\n3: Update\n4: Delete\n5: Exit\n:: ");
            int choice = sc.nextInt();

            DataBaseCon db = new DataBaseCon();

            db.sessionSetup();

            switch (choice){

                case 1: //Create
                    sc.nextLine();
                    System.out.print("Please Enter Name - ");
                    String name = sc.nextLine();
                    System.out.print("Please Enter RollNo - ");
                    int rollNo = sc.nextInt();
                    System.out.print("Please Enter Marks - ");
                    int marks = sc.nextInt();
                    db.create(rollNo,marks,name);
                    break;

                case 2:  //Retrieve
                    db.retrieve();
                    break;

                case 3:  //Update
                    System.out.print("Please Enter the RollNumber you want to update - ");
                    db.update(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Please Enter the RollNumber you want to Delete - ");
                    db.delete(sc.nextInt());
                    break;
                case 5:  run = false;
                    break;
                default:
                    System.out.println("Please select a valid option !!");
                    break;

            }

            System.out.println("");

        }


    }
}
