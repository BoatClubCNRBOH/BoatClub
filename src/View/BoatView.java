package View;

import Controller.DatabaseControll;
import Model.BoatModel;
import Model.UserModel;

import java.util.Scanner;

public class BoatView {
    InterfaceView mainMenu = new InterfaceView();

    private String memID;



    public void boatInterface(String memID) {
        DatabaseControll db = new DatabaseControll();
        this.memID = memID;
        Scanner scan = new Scanner(System.in);
        System.out.print("\nBoat Options\n\n\t1. Add Boat\n\t2. Edit Boat\n\t3. Remove Boat\n\t4. Go Back\n\nChoose: ");
        switch (scan.nextLine()) {
            case "1":
                db.writeObject(registerBoat(), "boatDB.csv");
                boatInterface(memID);
            case "2":

            case "3":

            case "4":
                mainMenu.getAuthenticatedPage(memID);
            default:
                System.out.println("Invalid Option");
                boatInterface(memID);
                break;
        }
    }

    public String[] registerBoat() {
        BoatModel boat = new BoatModel();
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\nTypes\n\n\t1. Sailboat\n\t2. Motorsailer\n\t3. Kayak/Canoe\n\t4. Other\n\nChoose:  ");
        boat.setBoatType(scan.nextLine());
        System.out.print("Length: ");
        boat.setBoatLength(scan.nextLine());
        return boat.makeBoat(memID);
    }


}
