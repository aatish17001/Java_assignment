import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class Patient_DB_Manager {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    Patient_DB_Manager(){
        entityManagerFactory= Persistence.createEntityManagerFactory("com.hibernate.tutorial.jpa");
        entityManager= entityManagerFactory.createEntityManager();
    }
    int add_record(Patient patient){
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        return 1;
    }
    int update_record(Patient patient){
        entityManager.getTransaction().begin();
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        for (Patient p1 : listPatient){
            if(check(p1,patient)){
                p1.setName(patient.getName());
                p1.setAddress(patient.getAddress());
                p1.setPassword(patient.getPassword());
                p1.setEmail(patient.getEmail());
                p1.setPhone_number(p1.getPhone_number());
                entityManager.getTransaction().commit();
                return 1;
            }
        }
        entityManager.getTransaction().commit();
        System.out.println("Patient record not found!! ERROR!");
        return 0;
    }

    int delete_record(Patient patient){
        entityManager.getTransaction().begin();
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        for (Patient p1 : listPatient){
            if(check(p1,patient)){
                entityManager.remove(p1);
                entityManager.getTransaction().commit();
                return 1;
            }
        }
        entityManager.getTransaction().commit();
        System.out.println("Patient record not found!! ERROR!");
        return 0;
    }
    List<Patient> Get_all_records(){
        @SuppressWarnings("unchecked")
        List<Patient> listPatient = entityManager.createQuery("SELECT p FROM Patient p").getResultList();
        listPatient.sort(new sortbyid());
        for (Patient p : listPatient){
            System.out.println(p.getId()+"  "+p.getName()+" "+p.getEmail());
           }
        return listPatient;
    }
    void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    private  boolean  check(Patient p1,Patient p2){
        int count=0;
        if(p1.getName().contains(p2.getName()) && p2.getName().contains(p1.getName()))
            count++;
        if(p1.getPassword().contains(p2.getPassword()) && p2.getPassword().contains(p1.getPassword()))
            count++;
        if (count==2)
            return true;
        return false;
    }

}
class  sortbyid implements Comparator<Patient>{
    public int compare(Patient o1, Patient o2) {
        return o1.getId()-o2.getId();
    }
}
