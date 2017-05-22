package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
@NamedQuery(query = "select s from Student s", name = "find all students")
// SELECT p.number FROM Employee e JOIN e.phones p WHERE e.department.name =
// 'NA42' AND p.type = 'Cell'

// Group ing
// SELECT d, COUNT(e), MAX(e.salary), AVG(e.salary)
// FROM Department d JOIN d.employees e
// GROUP BY d
// HAVING COUNT(e) >= 5

// Query Params
// SELECT e
// FROM Employee e
// WHERE e.department = :dept AND
// e.salary > :base
// em.createQuery(QUERY, Long.class).setParameter("deptName",
// deptName).getSingleResult()

// Read only queries ->
// @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)

// Criteria criteria = session.createCriteria(Book1.class)
// .add(Restrictions.eq("name", "Book 1"));
// List<Book1> books=criteria.list();

// Criterion nameRest = Restrictions.eq("name", "Hibernate
// Recipes").ignoreCase();
// gt (greater than), lt (less than), ge (greater than or equal to), isNull,
// isNotNull, isEmpty, isNotEmpty, between, in, le (less than or equal to)
// .add(Restrictions.like("name", "%Hibernate%"))
// .add(Restrictions.between("price", new Integer(100), new Integer(200)));
// .add(Restrictions.or(
// Restrictions.like("name", "%Hibernate%"),
// Restrictions.like("name", "%Java%")
// )
// )

// Criteria criteria = session.createCriteria(Book.class)
// .add(Restrictions.like("name", "%Hibernate%"))
// .createCriteria("publisher")
// .add(Restrictions.eq("name", "Manning"));
// List books = criteria.list();
// .addOrder(Order.asc("name"))
// .addOrder(Order.desc("publishDate"));

// .setProjection(Projections.projectionList()
// .add(Projections.groupProperty("publishDate"))
// .add(Projections.avg("price")));

// @MappedSuperclass
// public class Auditable {
// @Getter
// @Setter
// @Temporal(TemporalType.TIMESTAMP)
// Date createDate;
// }

// public class AuditableInterceptor extends EmptyInterceptor {
// @Override
// public boolean onSave(Object entity, Serializable id,
// Object[] state, String[] propertyNames,
// Type[] types) {
// if (entity instanceof Auditable) {
// for(int i=0;i<propertyNames.length;i++) {
// if("createDate".equals(propertyNames[i])) {
// state[i]=new Date();
// return true;
// }
// }
// }
// return false;
// }
// }

// Add interceptor to embeddedble context?

// Hibernate has a two-level cache architecture:

// The first-level cache is the persistence context cache, which is at the
// unit-of-work level. It corresponds to one session in Hibernate for a single
// request and is enabled by default for the Hibernate session.
// The second-level cache, which is either at the process scope or the cluster
// scope, is the cache of the state of the persistence entities. A
// cache-concurrency strategy defines the transaction isolation details for a
// particular item of data, and the cache provider represents the physical cache
// implementation.

public class Student {

	// ENUM
	// @Enumerated(EnumType.STRING)
	// private StudentType type;

	// @Transient
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToOne(cascade = { CascadeType.ALL })
	private Passport passport;

	private String email;

	@OneToMany(mappedBy = "student")
	private List<Task> tasks;

	@ManyToMany(mappedBy = "students")
	private List<Project> projects;

	@Embedded
	private Address address;

	// @ElementCollection
	// @CollectionTable(name="EMP_PHONE")
	// @MapKeyEnumerated(EnumType.STRING)
	// @MapKeyColumn(name="PHONE_TYPE")
	// @Column(name="PHONE_NUM")
	// private Map<PhoneType, String> phoneNumbers;
	// public enum PhoneType { Home, Mobile, Work }

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
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

	public Passport getPassport() {
		return passport;
	}

	public void setPassportId(Passport passport) {
		this.passport = passport;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name
				+ ", email=" + email + "]";
	}

}

