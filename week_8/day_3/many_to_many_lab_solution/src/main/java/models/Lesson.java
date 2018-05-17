package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="lessons")
public class Lesson {

    private int id;
    private String title;
    private String classRoom;
    private Course course;
    private Instructor instructor;
    private Set<Student> students;

    public Lesson() {
    }

    public Lesson(String title, String classRoom, Course course, Instructor instructor) {
        this.title = title;
        this.classRoom = classRoom;
        this.course = course;
        this.instructor = instructor;
        this.students = new HashSet<Student>();
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

    @Column(name="class_room")
    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "lessons")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    @ManyToOne
    @JoinColumn(name="instructor_id", nullable=false)
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
