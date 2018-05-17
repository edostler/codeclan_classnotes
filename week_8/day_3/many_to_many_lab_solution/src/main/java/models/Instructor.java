package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="instructors")
public class Instructor {

    private int id;
    private String name;
    private Set<Course> courses;
    private Set<Lesson> lessons;

    public Instructor() {
    }

    public Instructor(String name) {
        this.name = name;
        this.courses = new HashSet<Course>();

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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "instructor_course",
            joinColumns = {@JoinColumn(name = "instructor_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "course_id", nullable = false, updatable = false)})

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }
}
