package petClinic.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import petClinic.model.Veterinarian;
import petClinic.utility.HibernetUtil;

import java.util.List;

public class VeterinarianDao {

    public void createVeterinarian(Veterinarian veterinarian) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(veterinarian);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public List<Veterinarian> findAllVeterinarians() {
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Veterinarian", Veterinarian.class);

            List<Veterinarian> veterinarianList = query.list();

            return veterinarianList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Veterinarian> findByNameVet(String firstName) {
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Veterinarian where firstName=:firstName", Veterinarian.class);
            query.setParameter("firstName", firstName);
            List<Veterinarian> veterinarianList = query.list();

            return veterinarianList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateVeterinarian(Veterinarian veterinarian) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(veterinarian);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deleteVeterinarian(Veterinarian veterinarian) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(veterinarian);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }


}
