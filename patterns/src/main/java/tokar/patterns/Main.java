package tokar.patterns;

import tokar.patterns.dao.DAO;
import tokar.patterns.dao.DaoInstance;
import tokar.patterns.models.Computer;
import tokar.patterns.models.Display;
import tokar.patterns.models.Processor;
import tokar.patterns.observer.Observer;
import tokar.patterns.observer.ObserverImplementation;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DAO dao = GetInstance.getDAOInstance(DaoInstance.MySqlDao);
        ObserverImplementation message = new ObserverImplementation();

        boolean loop = true;
        while (loop) {
            try {
                System.out.println("Select the option\n" +
                        "1. View all\n" +
                        "2. Delete display\n" +
                        "3. Add computer\n" +
                        "4. Edit computer\n" +
                        "5. Find by model\n" +
                        "6. Add display\n" +
                        "7. Add processor\n" +
                        "8. Add observer\n"+
                        "9. View observers\n"+
                        "10. Exit");
                switch(scanner.nextInt()) {
                    case 1:
                        List<Computer> computers = dao.getComputers();
                        for (Computer computer : computers) {
                            System.out.println(computer);
                        }
                        break;
                    case 2:
                        System.out.println("Enter id of computer that will be deleted: ");
                        int id = scanner.nextInt();
                        dao.deleteComputerById(id);
                        message.update("The computer has been deleted!");
                        break;
                    case 3:
                        System.out.println("Enter the name of model of new computer: ");
                        String modelName = scanner.next();
                        System.out.println("Enter id of processor: ");
                        for (Processor processor : dao.getProcessors()) {
                            System.out.println(processor);
                        }
                        int idProcessor = scanner.nextInt();
                        System.out.println("Enter id of display: ");
                        for (Display display : dao.getDisplays()) {
                            System.out.println(display);
                        }
                        int idDisplay = scanner.nextInt();
                        dao.addComputer(modelName, idProcessor, idDisplay);
                        break;
                    case 4:
                        System.out.println("Enter id of computer that you want to edit: ");
                        id = scanner.nextInt();
                        Computer editable = dao.getComputerById(id).get(0);
                        System.out.println(editable);
                        boolean isEditing = true;
                        while (isEditing) {
                            System.out.println("1. Update model\n" +
                                    "2. Save\n" +
                                    "3. Exit without saving");
                            switch (scanner.nextInt()) {
                                case 1:
                                    System.out.println("Enter model: ");
                                    String model = scanner.next();
                                    editable.setModel(model);
                                    break;
                                case 2:
                                    dao.updateComputer(editable);
                                    isEditing = false;
                                    break;
                                case 3:
                                    isEditing = false;
                                    break;
                                default:
                                    System.out.println("Wrong argument!");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Enter name of model: ");
                        String brand = scanner.next();
                        for (Computer display :
                                dao.getComputerByModel(brand)) {
                            System.out.println(display);
                        }
                        break;
                    case 6:
                        System.out.println("Enter refresh_rate: ");
                        int rate = scanner.nextInt();
                        System.out.println("Enter matrix_type: ");
                        String matrixType = scanner.next();
                        dao.addDisplay(rate, matrixType);
                        break;
                    case 7:
                        System.out.println("Enter name of model: ");
                        String model = scanner.next();
                        System.out.println("Enter cores: ");
                        String cores = scanner.next();
                        System.out.println("Enter frequency: ");
                        String frequency = scanner.next();
                        dao.addProcessor(model, cores, frequency);
                        message.update("The processor has been added! Please know that you can upgrade!");
                        break;
                    case 8:
                        System.out.println("Enter the name of model of new observer: ");
                        String modelName_ = scanner.next();
                        System.out.println("Enter id of processor: ");
                        for (Processor processor : dao.getProcessors()) {
                            System.out.println(processor);
                        }
                        int idProcessor_ = scanner.nextInt();
                        System.out.println("Enter id of display: ");
                        for (Display display : dao.getDisplays()) {
                            System.out.println(display);
                        }
                        int idDisplay_ = scanner.nextInt();
                        dao.insertObserver(modelName_, idProcessor_, idDisplay_);
                        break;
                    case 9:
                        List<Observer> observers = dao.getObservers();
                        for (Observer observer : observers) {
                            System.out.println(observer);
                        }
                        break;
                    case 10:
                        loop = false;
                        break;
                    default:
                        System.out.println("Wrong argument!");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage()
                        + "\n" + e.getLocalizedMessage());
            }
        }
    }
}