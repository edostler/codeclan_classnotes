package models;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course {
    private int id;
    private String title;
    private String level;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private Set<Student> students;
    private Set<Lesson> lessons;
    private Set<Instructor> instructors;

    public Course() {
    }

    public Course(String title, String level, GregorianCalendar startDate, GregorianCalendar endDate) {
        this.title = title;
        this.level = level;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructors = new HashSet<Instructor>();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @OneToMany(mappedBy="course",fetch= FetchType.EAGER)
    public Set<Student> getStudents() {
        return students;
    }


    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @OneToMany(mappedBy = "course",fetch= FetchType.EAGER)
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
   public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public void addInstructor(Instructor instructor){
        this.instructors.add(instructor);
    }

    @Column(name="start_date")
    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    @Column(name="end_date")
    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }
}
