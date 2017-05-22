package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToMany(mappedBy = "project")
	private List<Task> tasks;

	// There are some important differences between this many-to-many
	// relationship and the one-to-many relationship discussed earlier. The
	// first is a mathematical inevitability: when a many-to-many relationship
	// is bidirectional, both sides of the relationship are many-to-many
	// mappings.
	// The second difference is that there are no join columns on either side of
	// the relationship. You will see in the next section that the only way to
	// implement a many-to-many relationship is with a separate join table. The
	// consequence of not having any join columns in either of the entity tables
	// is that there is no way to determine which side is the owner of the
	// relationship. Because every bidirectional relationship has to have both
	// an owning side and an inverse side, we must pick one of the two entities
	// to be the owner. In this example, we picked Employee to be owner of the
	// relationship, but we could have just as easily picked Project instead. As
	// in every other bidirectional relationship, the inverse side must use the
	// mappedBy element to identify the owning attribute.
	// Note that no matter which side is designated as the owner, the other side
	// should include the mappedBy element; otherwise, the provider will think
	// that both sides are the owner and that the mappings are separate
	// unidirectional relationships.
	@ManyToMany
	// @JoinTable(name="STUDENT_PROJ",
	// joinColumns=@JoinColumn(name="STUDENT_ID"),
	// inverseJoinColumns=@JoinColumn(name="PROJECT_ID"))
	private List<Student> students;

	public Project() {
		super();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}

}

