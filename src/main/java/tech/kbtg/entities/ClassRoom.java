package tech.kbtg.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "class_room")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "class_name")
    private String name;

    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.JOIN) // N + 1 Solution
    private Set<Student> students;

    public ClassRoom(String name) {
        this.name = name;
    }

    public ClassRoom() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
