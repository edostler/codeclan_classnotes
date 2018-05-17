import db.DBHelper;
import db.DBHelper;
import db.DBHelper;
import db.DBHelper;
import models.Course;
import models.Instructor;
import models.Lesson;
import models.Student;

import java.util.Date;
import java.util.GregorianCalendar;

public class Runner {

    public static void main(String[] args) {

        DBHelper.deleteAll(Student.class);
        DBHelper.deleteAll(Instructor.class);
        DBHelper.deleteAll(Lesson.class);
        DBHelper.deleteAll(Course.class);
        Course course1 = new Course("Electrical Engineering", "BEng", new GregorianCalendar(2018,2,12), new GregorianCalendar(19,5,2018));
        DBHelper.save(course1);

        Student student1 = new Student("Bart Simpson", 12, 000001, course1);
        DBHelper.save(student1);

        Student student2 = new Student("Lisa Simpson", 11, 00002, course1);
        DBHelper.save(student2);

        Instructor instructor1 = new Instructor("Principle Skinner");
        DBHelper.save(instructor1);
        Instructor instructor2 = new Instructor("Mrs Krabappel");
        DBHelper.save(instructor2);

        Lesson lesson1 = new Lesson("Intro to CAD", "EE101", course1, instructor1);
        DBHelper.save(lesson1);
        Lesson lesson2 = new Lesson("Programming with C++", "EE210", course1, instructor2);
        DBHelper.save(lesson2);

        addStudentToLesson(student1, lesson1);
        addStudentToLesson(student2, lesson1);
        addStudentToLesson(student1, lesson2);

        addInstructorToCourse(course1, instructor1);
        addInstructorToCourse(course1, instructor2);
        addInstructorToCourse(course1, instructor2);

        Course myCourse = DBHelper.find(Course.class, course1.getId());

    }

    public static void addStudentToLesson(Student student, Lesson lesson){
        student.addLesson(lesson);
        lesson.addStudent(student);
        DBHelper.update(lesson);
        DBHelper.update(student);
    }

    public static void addInstructorToCourse(Course course, Instructor instructor){
        course.addInstructor(instructor);
        instructor.addCourse(course);
        DBHelper.update(instructor);
        DBHelper.update(course);
    }
}
