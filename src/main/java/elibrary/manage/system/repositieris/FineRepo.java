package elibrary.manage.system.repositieris;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elibrary.manage.system.entities.Fine;


@Repository
public interface FineRepo  extends JpaRepository<Fine, Long>{


	List<Fine> findByStudentIdAndBookId(long studentId, long bookId);

}
