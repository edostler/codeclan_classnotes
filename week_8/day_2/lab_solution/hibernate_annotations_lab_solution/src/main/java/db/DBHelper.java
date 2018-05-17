package db;

import models.Employee;
import models.Manager;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria cr) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = cr.list();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUniqueResult(Criteria cr) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) cr.uniqueResult();
            transaction.commit();

        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria cr = session.createCriteria(classType);
        results = getList(cr);
        return results;
    }

    public static <T> T find(Class classType, int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.eq("id", id));
        result = getUniqueResult(cr);
        return result;
    }

    public static List<Employee> getEmployeesByManager(Manager manager){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> results = null;
        Criteria cr = session.createCriteria(Employee.class);
        cr.add(Restrictions.eq("manager", manager));
        results = getList(cr);
        return results;
    }
}
