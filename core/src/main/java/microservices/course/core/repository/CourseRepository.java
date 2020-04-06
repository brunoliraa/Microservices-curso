package microservices.course.core.repository;


import microservices.course.core.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
