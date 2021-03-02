package petClinic;

import java.util.Scanner;

import static petClinic.service.PetClinicService.showMenu;
import static petClinic.service.PetClinicService.showOption;

public class Main {
    public static void main(String[] args) {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println(option);
        showOption(option);
    }
}
