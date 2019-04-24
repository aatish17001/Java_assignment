import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.util.List;
import  java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Patient_DB_Manager patient_db_manager= new Patient_DB_Manager();
        Scanner in= new Scanner(System.in);
        int exit=1;
        do {
            System.out.println(">>>>------Welcome to Database------<<<<");
            System.out.println("1.Add a patient's record");
            System.out.println("2.Delete a patient's record");


        }while (exit==1);

    }

}
