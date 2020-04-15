package microservices.course.core.repository;


import microservices.course.core.model.ApplicationUser;
import microservices.course.core.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
