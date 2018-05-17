package db;

import models.Department;
import models.Employee;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

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

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria criteria = session.createCriteria(classType);
        results = getList(criteria);
        return results;
    }

    public static <T> T findById(Class classType, int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;

        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.idEq(id));
        result = (T) cr.uniqueResult();

        return result;
    }

    public static List<Employee> findByMinSalary(int salary) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> results = null;

        Criteria cr = session.createCriteria(Employee.class);
        cr.add(Restrictions.gt("salary", salary));
        results = cr.list();

        return results;
    }

    public static List<Employee> orderBySalary() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> results = null;

        Criteria cr = session.createCriteria(Employee.class);
        cr.addOrder(Order.desc("salary"));
        results = cr.list();

        return results;
    }

    public static Double getAveSalary() {
        session = HibernateUtil.getSessionFactory().openSession();
        Double average = null;
        Criteria cr = session.createCriteria(Employee.class);
        cr.setProjection(Projections.avg("salary"));
        average = (Double) getUniqueResult(cr);
        return average;
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


}
