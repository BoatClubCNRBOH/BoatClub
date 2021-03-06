package View;

import Controller.DatabaseControll;
import Model.BoatModel;

import java.util.Scanner;

public class BoatView {
    BoatModel boat = new BoatModel();
    InterfaceView mainMenu = new InterfaceView();
    Scanner scan = new Scanner(System.in);
    private String memID;



    public void boatInterface(String memID) {
        DatabaseControll db = new DatabaseControll();
        this.memID = memID;
        System.out.print("\nBoat Options\n\n\t1. Add Boat\n\t2. Edit Boat\n\t3. Remove Boat\n\t4. Go Back\n\nChoose: ");
        switch (scan.nextLine()) {
            case "1":
                db.writeObject(registerBoat(), "boatDB.csv");
                boatInterface(memID);
            case "2":
                boatEdit();
                boatInterface(memID);
            case "3":
                removeBoat();
                boatInterface(memID);
            case "4":
                mainMenu.getAuthenticatedPage(memID);
            default:
                System.out.println("Invalid Option");
                boatInterface(memID);
                break;
        }
    }

    public void removeBoat() {
        System.out.println("Which boat would you like to remove?\nBoats:");
        System.out.println("\n" + boat.personalBoatsToString(memID));
        System.out.print("Choice: ");
        if (boat.deleteBoat(memID, scan.nextLine())) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed to remove boat.");
        }
    }

    public String[] registerBoat() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nTypes\n\n\t1. Sailboat\n\t2. Motorsailer\n\t3. Kayak/Canoe\n\t4. Other\n\nChoose:  ");
        boat.setBoatType(scan.nextLine());
        System.out.print("Length: ");
        boat.setBoatLength(scan.nextLine());
        return boat.makeBoat(memID);
    }

    public void boatEdit() {
        System.out.println("Which boat would you like to edit?\nBoats:");
        System.out.println("\n" + boat.personalBoatsToString(memID));
        System.out.print("Choice: ");
        String boatChoice = scan.nextLine();
        System.out.print("What would you like to edit\n\n\t1. Type\n\t2. Length\n\nChoice: ");
        String editChoice = scan.nextLine();
        if (editChoice.equals("1")) {
            System.out.print("\n\nTypes\n\n\t1. Sailboat\n\t2. Motorsailer\n\t3. Kayak/Canoe\n\t4. Other\n\nChoose:  ");
            boat.setBoatType(scan.nextLine());
            boat.changeInfo(memID, String.valueOf(boat.getBoatType()), 1,  Integer.parseInt(boatChoice));
        } else if (editChoice.equals("2")){
            System.out.print("\nNew length: ");
            boat.changeInfo(memID, scan.nextLine(), 2, Integer.parseInt(boatChoice));
        } else {
            System.out.println("Invalid");
            boatEdit();
        }
    }


}
