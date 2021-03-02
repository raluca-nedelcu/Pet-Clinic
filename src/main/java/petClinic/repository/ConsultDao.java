package petClinic.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import petClinic.model.Consult;
import petClinic.utility.HibernetUtil;

import java.util.List;

public class ConsultDao {
    public void createConsult(Consult consult) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(consult);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public List<Consult> findAllConsults() {
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Consult", Consult.class);

            List<Consult> consultList = query.list();

            return consultList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Consult> findByDateConsult() {
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Consult c where c.date like 'XX'", Consult.class);

            List<Consult> consultList = query.list();

            return consultList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateConsult(Consult consult) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(consult);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deleteConsult(Consult consult) {
        Transaction transaction = null;
        try {
            Session session = HibernetUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(consult);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }


}
