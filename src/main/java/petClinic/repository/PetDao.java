package petClinic.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import petClinic.model.Pet;
import petClinic.model.Veterinarian;
import petClinic.utility.HibernetUtil;

import java.util.List;

public class PetDao {
    public void createPet(Pet pet) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(pet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public List<Pet> findAllPets() {
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Pet", Pet.class);

            List<Pet> petList = query.list();

            return petList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Veterinarian> findByNamePet() {
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Pet p where p.firstName like 'XX'", Pet.class);

            List<Veterinarian> petList = query.list();

            return petList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updatePet(Pet pet) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(pet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deletePet(Pet pet) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(pet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
