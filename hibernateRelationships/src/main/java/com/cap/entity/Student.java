package com.cap.entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String studentName;
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//		 @JoinTable(
//			        name = "student_course",
//			        joinColumns = @JoinColumn(name = "student_id"),
//			        inverseJoinColumns = @JoinColumn(name = "course_id")
//			    )
		private List<Course> courses;
		
		public Student() {}
		
		public Student(String studentName) {
			this.studentName = studentName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public List<Course> getCourses() {
			return courses;
		}

		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}
}
