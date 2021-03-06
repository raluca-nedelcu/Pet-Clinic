package petClinic.service;

import com.opencsv.exceptions.CsvValidationException;
import petClinic.model.Consult;
import petClinic.model.Owner;
import petClinic.model.Pet;
import petClinic.model.Veterinarian;
import petClinic.repository.ConsultDao;
import petClinic.repository.OwnerDao;
import petClinic.repository.PetDao;
import petClinic.repository.VeterinarianDao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * 1.1 Introduceti un constraint pe campurile firstname, lastname din clasa Veterinarian case sa specifice ca
 * lungimea minima a strigului este de 3 caractere.2. Implementati o interfata la consola pentru applicatia pet clinic.
 * Va afisa un meniu cu urmatoarele optiuni:
 * 0. Exit
 * 1. Create
 * 2. Update
 * 3. Detele
 * 4. List/Find
 * Dupe selectia oricarei option utilizatorul va alege tabelul pentru care doreste sa faca aceasta operatie.
 * •	In cazul unui create utilizatorul va trebui sa introduca de la tastatura toare campurile necesare pentru
 * crearea acelei entitati.
 * •	In cazul unui update utilizatorul va selecta ce camp doreste sa updateze si va introduce noua valoare.
 * •	In cazul unui delete utilizatorul va introduce id-ul entitatii ce doreste sa o stearga.
 * •	In cazul unui list/find utilizatorul poate alege sa afiseze toate intrarile din db sau sa introduca id-ul
 * entitatii ce doreste sa o afiseze.
 */

public class PetClinicService {


    public static void showMenu() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Create");
        System.out.println("\t 2 - Update");
        System.out.println("\t 3 - Delete");
        System.out.println("\t 4 - List/Find");
        System.out.println("\t 5 - Import from .csv");
        System.out.println("\t 6 - Export to .csv");

    }

    public static void showOption() throws IOException, CsvValidationException {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int option;

        System.out.println("Enter your choice: ");
        option = scanner.nextInt();


        switch (option) {
            case 0:
                quit = true;
                break;
            case 1:
                create();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
            case 4:
                find();
                break;
            case 5:
                importCsv();
                break;
            case 6:
                exportCsv();
                break;

        }

    }

//    public static void showCreateMenu() {
//        System.out.println("\t 0 - Veterinarian");
//        System.out.println("\t 1 - Pet");
//        System.out.println("\t 2 - Consult");
//
//        Scanner scanner = new Scanner(System.in);
//        int option = scanner.nextInt();
//        System.out.println("Ai ales optiunea: " + option);
//
//
//    }

    public static void create() throws IOException, CsvValidationException {

        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Create Veterinar");
        System.out.println("\t 2 - Create Pet");
        System.out.println("\t 3 - Create Consult");
        System.out.println("\t 4 - Create owner");
        System.out.println("\t 5 - return to main menu");

        System.out.println("Enter your choice: ");
        int createOption;

        Scanner scanner = new Scanner(System.in);
        createOption = scanner.nextInt();
        boolean quit = false;

        switch (createOption) {
            case 0:
                quit = true;
                break;
            case 1:
                System.out.println("Introduceti numele veterinarului");
                String nume = scanner.next() + scanner.nextLine();
                System.out.println("Introduceti prenumele");
                String prenume = scanner.next() + scanner.nextLine();
                System.out.println("introduceti adresa");
                String address = scanner.next() + scanner.nextLine();
                System.out.println("Introduceti specialitatea");
                String speciality = scanner.next() + scanner.nextLine();

                VeterinarianDao veterinarianDao = new VeterinarianDao();
                Veterinarian veterinarian = new Veterinarian(nume, prenume, address, speciality);
                veterinarianDao.createVeterinarian(veterinarian);
                System.out.println("veterinarul cu numele " + nume + " prenumele " + prenume +" adresa " + address +
                        " si specialiitatea " + speciality + " a fost creat cu succes!");
                showOption();
                break;
            case 2:
                System.out.println("Introduceti rasa animaluluii");
                String rasa = scanner.next();
                System.out.println("Introduceti data nastere");
                String birthdate = scanner.next();
                System.out.println("este vaccinat? (true sau false )");
                boolean isVaccinated = scanner.nextBoolean();
                System.out.println("Introduceti idul propietarului");
                Long ownerId = scanner.nextLong();
                OwnerDao ownerDao = new OwnerDao();

                PetDao petDao = new PetDao();
                Pet pet = new Pet(rasa, birthdate, isVaccinated,ownerDao.findByIdOwner(ownerId) );
                petDao.createPet(pet);
                System.out.println("animalul cu rasa " + rasa + " cu data de nastere " + birthdate +" vaccinat " + isVaccinated +
                        " si stapanul  a fost creat cu succes");
                showOption();
                break;
            case 3:
                LocalDate localDate = LocalDate.now();
                String date = String.valueOf(localDate);
                System.out.println("Introduceti descrierea");
                String description = scanner.next() + scanner.nextLine();
                VeterinarianDao veterinarianDao1 = new VeterinarianDao();
                PetDao petDao1 = new PetDao();
                System.out.println("Introduceti id-ul veterinarului");
                long idVeterinar = scanner.nextLong();
                System.out.println("Introduceti id-ul animalului");
                long idPet = scanner.nextLong();

                ConsultDao consultDao = new ConsultDao();
                Consult consult = new Consult(date, description, veterinarianDao1.findByIdVeterinarian(idVeterinar), petDao1.findByIdPet(idPet));
                consultDao.createConsult(consult);
                showOption();
                break;
            case 4:
                System.out.println("Please insert owner's first name:");
                String firstName1 = scanner.next() + scanner.nextLine();;
                System.out.println("Please insert owner's last name:");
                String lastName1 = scanner.next() + scanner.nextLine();;
                System.out.println("Please insert owner's phone number:");
                String phoneNumber = scanner.next() + scanner.nextLine();;
                System.out.println("Please insert owner's email:");
                String email = scanner.next();
                EmailValid emailValid = new EmailValid();
                emailValid.isValidEmailAddress(email);
                while( !emailValid.isValidEmailAddress(email)) {
                    System.out.println("Introduceti o adresa de email valida");
                    email = scanner.next();
                }
                OwnerDao ownerDao1 = new OwnerDao();
                Owner owner1 = new Owner(firstName1, lastName1, phoneNumber, email);
                ownerDao1.createOwner(owner1);

                System.out.println("The owner with first name " + firstName1 + " last name " + lastName1 + " phone number " + phoneNumber +
                        " and email " + email + " was successfully created!");

                showOption();
                break;
            case 5:
                showOption();
                break;
        }
    }


    public static void update() throws IOException, CsvValidationException {
        int updateOption;
        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Update Veterinar");
        System.out.println("\t 2 - Update Pet");
        System.out.println("\t 3 - Update Consult");
        System.out.println("\t 4 - Update Owner");
        System.out.println("\t 5 - return to main menu");

        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        updateOption = scanner.nextInt();

        switch (updateOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println("update veterinar");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                System.out.println("Introduceti id-ul veterinarului pe care vreti sa il modificati ");
                Long idVeterinar = scanner.nextLong();
                Veterinarian veterinarian1 = veterinarianDao.findByIdVeterinarian(idVeterinar);
                System.out.println("\nPress ");
                System.out.println("\t 0 - Return to main menu.");
                System.out.println("\t 1 - Update FirstName");
                System.out.println("\t 2 - Update lastName");
                System.out.println("\t 3 - Update address");
                System.out.println("\t 4 - Update speciality");
                System.out.println("\t 5 - return to update menu");
                System.out.println("\t 6 - return to main menu");

                System.out.println("\t Enter your choice: ");
                int updateVeterinarian = scanner.nextInt();

                switch (updateVeterinarian) {
                    case 0:
                        System.exit(0);
                    case 1:
                        System.out.println("Introduceti prenumele veterinarului");
                        veterinarian1.setFirstName(scanner.next() + scanner.nextLine());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        break;
                    case 2:
                        System.out.println("Introduceti numele");
                        veterinarian1.setLastName(scanner.next() + scanner.nextLine());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        update();
                        break;
                    case 3:
                        System.out.println("introduceti adresa");
                        veterinarian1.setAddress(scanner.next() + scanner.nextLine());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        update();
                        break;
                    case 4:
                        System.out.println("Introduceti specialitatea");
                        veterinarian1.setSpeciality(scanner.next() + scanner.nextLine());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        update();
                        break;
                    case 5:
                        update();
                        break;
                    case 6:
                        showOption();
                        break;
                }
                break;
            case 2:
                System.out.println("update Pet");
                PetDao petDao = new PetDao();
                System.out.println("Introduceti id-ul animalului care vreti sa il modificati ");
                Long idPet = scanner.nextLong();
                Pet pet = petDao.findByIdPet(idPet);
                System.out.println("\nPress ");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update Race");
                System.out.println("\t 2 - Update BirthDate");
                System.out.println("\t 3 - Update isVaccinated");
                System.out.println("\t temp - Update ownerName");
                System.out.println("\t 5 - return to update menu");
                System.out.println("\t 6 - return to main menu");

                System.out.println("\t Enter your choice: ");
                int updatePet = scanner.nextInt();

                switch (updatePet) {
                    case 0:
                        System.exit(0);
                    case 1:
                        System.out.println("Introduceti rasa animalului");
                        pet.setRace(scanner.next() + scanner.nextLine());
                        petDao.updatePet(pet);
                        update();
                        break;
                    case 2:
                        System.out.println("Introduceti Birthdate");
                        pet.setBirthDate(scanner.next() + scanner.nextLine());
                        petDao.updatePet(pet);
                        update();
                        break;
                    case 3:
                        System.out.println("este vaccinat?");
                        pet.setVaccinated(scanner.nextBoolean());
                        petDao.updatePet(pet);
                        update();
                        break;

                    case 5:
                        update();
                        break;
                    case 6:
                        showOption();
                        break;

                }
            case 3:
                System.out.println("update consult");
                ConsultDao consultDao = new ConsultDao();
                System.out.println("Introduceti id-ul consultatiei care vreti sa o modificati ");
                Long idConsult = scanner.nextLong();
                Consult consult = consultDao.findByIdConsult(idConsult);
                System.out.println("\nPress ");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update date");
                System.out.println("\t 2 - Update Description");
                System.out.println("\t 3 - return to update menu");
                System.out.println("\t 4 - return to main menu");

                System.out.println("\t Enter your choice: ");
                int updateConsult = scanner.nextInt();

                switch (updateConsult) {
                    case 0:
                        System.exit(0);
                    case 1:
                        System.out.println("Introduceti data");
                        consult.setDate(scanner.next() + scanner.nextLine());
                        consultDao.updateConsult(consult);
                        update();
                        break;
                    case 2:
                        System.out.println("Introduceti description");
                        consult.setDescription(scanner.next() + scanner.nextLine());
                        consultDao.updateConsult(consult);
                        update();
                        break;
                    case 3:
                        update();
                        break;
                    case 4:
                        showOption();
                        break;
                }
            case 4:
                System.out.println("update owner");
                OwnerDao ownerDao = new OwnerDao();
                System.out.println("Introduceti id-ul ownerului pe care vreti sa il modificati ");
                Long idOwmer = scanner.nextLong();
                Owner owner = ownerDao.findByIdOwner(idOwmer);
                System.out.println("\nPress ");
                System.out.println("\t 0 - Return to main menu.");
                System.out.println("\t 1 - Update FirstName");
                System.out.println("\t 2 - Update lastName");
                System.out.println("\t 3 - Update PhoneNumber");
                System.out.println("\t 4 - Update email");
                System.out.println("\t 5 - return to update menu");
                System.out.println("\t 6 - return to main menu");

                System.out.println("\t Enter your choice: ");
                int updateOwner = scanner.nextInt();

                switch (updateOwner) {
                    case 0:
                        System.exit(0);
                    case 1:
                        System.out.println("Introduceti prenumele owner-ului");
                        owner.setFirstName(scanner.next() + scanner.nextLine());
                        ownerDao.updateOwner(owner);
                        break;
                    case 2:
                        System.out.println("Introduceti numele");
                        owner.setLastName(scanner.next() + scanner.nextLine());
                        ownerDao.updateOwner(owner);
                        update();
                        break;
                    case 3:
                        System.out.println("introduceti phoneNumber");
                        owner.setPhoneNumber(scanner.next() + scanner.nextLine());
                        ownerDao.updateOwner(owner);
                        update();
                        break;
                    case 4:
                        System.out.println("Introduceti email");
                        String email = scanner.next();
                        EmailValid emailValid = new EmailValid();
                        emailValid.isValidEmailAddress(email);
                        while( !emailValid.isValidEmailAddress(email)) {
                            System.out.println("Introduceti o adresa de email valida");
                            email = scanner.next();
                        }
                        owner.setEmail(email);
                        ownerDao.updateOwner(owner);
                        update();
                        break;
                    case 5:
                        update();
                        break;
                    case 6:
                        showOption();
                        break;
                }
            case 5:
                showOption();
                break;
        }
    }

    public static void delete() throws IOException, CsvValidationException {
        int deleteOption;
        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Delete Veterinar");
        System.out.println("\t 2 - Delete Pet");
        System.out.println("\t 3 - Delete Consult");
        System.out.println("\t 4 - Delete Owner");
        System.out.println("\t 5 - return to main menu");

        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        deleteOption = scanner.nextInt();

        switch (deleteOption) {
            case 0:
                System.exit(0);
            case 1:
                System.out.println("Introduceti id-ul veterinarului care vreti sa il stergeti");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                long idvet = scanner.nextLong();
                Veterinarian v1 = veterinarianDao.findByIdVeterinarian(idvet);
                veterinarianDao.deleteVeterinarian(v1);
                delete();
                break;
            case 2:
                System.out.println("Introduceti id-ul animalului care vreti sa il stergeti");
                PetDao petDao = new PetDao();
                long idpet = scanner.nextLong();
                Pet p1 = petDao.findByIdPet(idpet);
                petDao.deletePet(p1);
                delete();
                break;
            case 3:
                System.out.println("Introduceti id-ul consultatiei care vreti sa o stergeti");
                ConsultDao consultDao = new ConsultDao();
                long idcon = scanner.nextLong();
                Consult c1 = consultDao.findByIdConsult(idcon);
                consultDao.deleteConsult(c1);
                delete();
                break;
            case 4:
                System.out.println("Introduceti id-ul ownerului care vreti sa o stergeti");
                OwnerDao ownerDao = new OwnerDao();
                long idowner = scanner.nextLong();
                Owner o1 = ownerDao.findByIdOwner(idowner);
                ownerDao.deleteOwner(o1);
                delete();
                break;
            case 5:
                showOption();
                break;
        }
    }


    public static void find() throws IOException, CsvValidationException {
        int findOption;
        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Find Veterinar");
        System.out.println("\t 2 - Find Pet");
        System.out.println("\t 3 - Find Consult");
        System.out.println("\t 4 - Find Owner");
        System.out.println("\t 5 - Display all Veterinars");
        System.out.println("\t 6 - Display all Pets");
        System.out.println("\t 7 - Display all Consults");
        System.out.println("\t 8 - Display all Owners");
        System.out.println("\t 9 - find by Name vet");

        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        findOption = scanner.nextInt();

        switch (findOption) {
            case 0:
                System.exit(0);
            case 1:
                System.out.println("Introduceti id-ul veterinarului care vreti sa il afisati");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                long idvet = scanner.nextLong();
                Veterinarian v1 = veterinarianDao.findByIdVeterinarian(idvet);
                System.out.println(v1.toString());
                break;
            case 2:
                System.out.println("Introduceti id-ul animalului care vreti sa il afisati");
                PetDao petDao = new PetDao();
                long idpet = scanner.nextLong();
                Pet p1 = petDao.findByIdPet(idpet);
                System.out.println(p1);
                showOption();
                break;
            case 3:
                System.out.println("Introduceti id-ul consultatiei care vreti sa o afisati");
                ConsultDao consultDao = new ConsultDao();
                long idcon = scanner.nextLong();
                Consult c1 = consultDao.findByIdConsult(idcon);
                System.out.println(c1);
                showOption();
                break;
            case 4:
                System.out.println("Introduceti id-ul ownerului care vreti sa o afisati");
                OwnerDao ownerDao = new OwnerDao();
                long idOwner = scanner.nextLong();
                Owner o1 = ownerDao.findByIdOwner(idOwner);
                System.out.println(o1);
                showOption();
                break;
            case 5:
                VeterinarianDao veterinarianDao2 = new VeterinarianDao();
                veterinarianDao2.displayAllVets();
                showOption();
                break;
            case 6:
                PetDao petDao1 = new PetDao();
                petDao1.displayAllPets();
                showOption();
                break;
            case 7:
                ConsultDao consultDao1 = new ConsultDao();
                consultDao1.displayAllCons();
                showOption();
                break;
            case 8:
                OwnerDao ownerDao1 = new OwnerDao();
                ownerDao1.displayAllOwners();
                showOption();
                break;
            case 9:
                System.out.println("Introduceti numele veterinarului care vreti sa il afisati");
                VeterinarianDao veterinarianDao1 = new VeterinarianDao();
                String idvet1 = scanner.next();
                List<Veterinarian> v2 = veterinarianDao1.findByNameVeterinarian(idvet1);
                System.out.println(v2.toString());
                showOption();
                break;
        }
    }
    public static void importCsv() throws IOException, CsvValidationException {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Import Veterinarian");
        System.out.println("\t 2 - Import Pet");
        System.out.println("\t 3 - Import Consult");
        System.out.println("\t 4 - Import Owner");
        System.out.println("\t 5 - return to main menu");

        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int createOption = scanner.nextInt();

        switch (createOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                ImportCsv.importCsvVet();
                importCsv();
                break;
            case 2:
                ImportCsv.importCsvPet();
                importCsv();
                break;
            case 3:
                ImportCsv.importCsvConsult();
                importCsv();
                break;
            case 4:
                ImportCsv.importCsvOwner();
                importCsv();
            case 5:
                showOption();
                break;


        }
    }
    public static void exportCsv() throws IOException, CsvValidationException {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Export Veterinarian");
        System.out.println("\t 2 - Export Pet");
        System.out.println("\t 3 - Export Consult");
        System.out.println("\t 4 - Export Owner");
        System.out.println("\t 5 - return to main menu");

        System.out.println("Enter your choice: ");
        ExportCsv exportCsv = new ExportCsv();


        Scanner scanner = new Scanner(System.in);
        int exportOption = scanner.nextInt();

        switch (exportOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                exportCsv.exportCsvVet(veterinarianDao.displayVet());
                showOption();
                break;
            case 2:
                PetDao petDao = new PetDao();
                exportCsv.exportCsvPet(petDao.displayPets());
                showOption();
                break;
            case 3:
                ConsultDao consultDao = new ConsultDao();
                exportCsv.exportCsvConsult(consultDao.displayConsults());
                showOption();
                break;
            case 4:
                OwnerDao ownerDao = new OwnerDao();
                exportCsv.exportCsvOwner(ownerDao.displayOwner());
                showOption();
                break;
            case 5:
                showOption();
                break;
        }
    }

}