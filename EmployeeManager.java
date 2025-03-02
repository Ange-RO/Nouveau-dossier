import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager implements EmployeeOperations {
    static ArrayList<Employee> employes = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    int id = 0;
    int choixType;
    static boolean continuer = true;
    static EmployeeManager employeeManager = new EmployeeManager();

    /*
     * Afficher le menu
     */
    public static void menuBar() {
        System.out.println("*******Que voulez-vous faire?********");
        System.out.println("1. Ajouter des employés");
        System.out.println("2. Afficher la liste des employés");
        System.out.println("3. Afficher les informations d'un employe");
        System.out.println("4. Modifier un employé");
        System.out.println("5. Supprimer un employé");
        System.out.println("6. Quitter");
        String menu = scanner.nextLine();
        int identifiant;
        switch (menu) {
            case "1":
                employeeManager.ajout();
                continueMenu();
                break;
            case "2":
                employeeManager.getAllEmploye(employes);
                continueMenu();
                break;
            case "3":
                System.out.println("Entrez l'identifiant de l'utilisateur: ");
                identifiant = scanner.nextInt();
                employeeManager.getOneEmploye(employes, identifiant);
                continueMenu();
                break;
            case "4":
                System.out.println("Entrez l'identifiant de l'utilisateur a mettre a jour: ");
                identifiant = scanner.nextInt();
                employeeManager.updateEmploye(employes, identifiant);
                continueMenu();
                break;
            case "5":
                System.out.println("Entrez l'identifiant de l'utilisateur a supprimer: ");
                identifiant = scanner.nextInt();
                employeeManager.deleteEmploye(employes, identifiant);
                continueMenu();
            case "6":
                continuer = false; // fin
                break;
            default:
                System.out.println("Option non valide. Veuillez réessayer.");
                break;
        }
    }

    /*
     * Afficher le menu qui demande a l'utilisateur de continuer après une action
     */
    public static void continueMenu() {
        if (continuer == true) {
            scanner.nextLine();
            System.out.println("Voulez-vous continuer ? (oui/non)");
            String reponse = scanner.nextLine().trim().toLowerCase();
            if (!reponse.equals("oui")) {
                continuer = false;
                System.out.println("Merci d'avoir utilisé notre application !");
            }
        }
    }


    /*
     * Ajouter un employer en fonction de son type 
     */
    public  void ajout() {
        System.out.println("Quel type d'employe voulez vous ajouter?");
        System.out.println("1* Manager");
        System.out.println("2* Developer");
        choixType = scanner.nextInt();
        if (choixType == 1) {
            System.out.println("Combien de managers voulez vous ajouter?");
        } else if (choixType == 2) {
            System.out.println("Combien de developpeurs voulez vous ajouter?");
        }
        int addNum = scanner.nextInt();
        for (int i = 1; i <= addNum; i++) {
            if (choixType == 1) {
                Manager manager = new Manager();
                System.out.println("**Entrez les informations du manager**");
                System.out.println("Nombre d'employe a la charge : ");
                manager.setNbrPersonne(scanner.nextLine());
                createEmploye(manager);
                employes.add(manager);
                System.out.println("Manager enregistré avec succes");
            } else if (choixType == 2) {
                Developer developer = new Developer();
                System.out.println("Specialite : ");
                developer.setSpecialite(scanner.nextLine());
                scanner.nextLine();
                createEmploye(developer);
                employes.add(developer);
            }
        }
        // getAllEmploye(employes);
    }


    /*
    * Créer un employer 
    */
    public void createEmploye(Employee em) {
        em.setId(id++);
        System.out.println("Entrez le nom de l'employe: ");
        em.setNom(scanner.nextLine());
        System.out.println("Entrez le salaire");
        em.setSalaire(scanner.nextDouble());
    };
    
    /*
     * Afficher la liste des employes
     */
    public void getAllEmploye(ArrayList<Employee> employes) {
        for (Employee employee : employes) {
            employee.getDetails();
        }
    };

    /*
     * Afficher les informations d'un employe specifique
     */
    public void getOneEmploye(ArrayList<Employee> employes, int identifiant) {
        for (Employee employee : employes) {
            if (employee.getId() == identifiant) {
                employee.getDetails();
            }
        }
    };

    /*
     * Mettre a jour un employe
     */
    public void updateEmploye(ArrayList<Employee> employes, int identifiant) {
        for (Employee employee : employes) {
            if (employee.getId() == identifiant) {
                System.out.println("Entrez le nom de l'employe: ");
                scanner.nextLine();
                employee.setNom(scanner.nextLine());
                System.out.println("Entrez le salaire");
                employee.setSalaire(scanner.nextDouble());
                // if (employee.getClass()==Manager) {
                    
                // }
            }
        }
    };

    /*
     * Supprimer un employer
     */
    public void deleteEmploye(ArrayList<Employee> employes, int identifiant) {
        for (Employee employee : employes) {
            if (employee.getId() == identifiant) {
                employes.remove(employee);
                break;
            }
        }
    };

    public static void main(String[] args) {
        while (continuer) {
            menuBar();
            System.out.println("La valeur de continuer    "+continuer);
        }
    }
}