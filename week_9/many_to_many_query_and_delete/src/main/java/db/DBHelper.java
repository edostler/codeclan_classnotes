package db;

import models.Course;
import models.Instructor;
import models.Lesson;
import models.Student;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object o) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria criteria) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUnique(Criteria criteria) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        return getList(cr);
    }

    public static <T> T find(Class classType, int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.eq("id", id));
        return getUnique(cr);

    }

    public static List<Instructor> getCourseInstructors(Course course) {
        List<Course> courses = new ArrayList<Course>();
        courses.add(course);
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Instructor.class);
        cr.createAlias("courses", "course");
        cr.add(Restrictions.eq("course.id", course.getId()));
        return getList(cr);
    }

    public static List<Lesson> getStudentLessons(Student student) {
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Lesson.class);
        cr.createAlias("students", "student");
        cr.add(Restrictions.eq("student.id", student.getId()));
        return getList(cr);
    }

    public static void addStudentToLesson(Student student, Lesson lesson) {
        student.addLesson(lesson);
        save(student);
    }

    public static void addInstructorToCourse(Course course, Instructor instructor) {
        course.addInstructor(instructor);
        save(course);
    }


}
