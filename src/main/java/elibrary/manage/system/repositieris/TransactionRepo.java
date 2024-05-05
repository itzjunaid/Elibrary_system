package elibrary.manage.system.repositieris;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import elibrary.manage.system.entities.Book;
import elibrary.manage.system.entities.Student;
import elibrary.manage.system.entities.Transaction;


@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
     

	//boolean existByBookAndStudentAndOrder(Student student, Book book, String string);
	//boolean existsByTransactionType(String string);
   //	boolean existsByTransactionTypeAndBookId(String string, int id);

	Optional<Transaction> findByStudentAndBookAndTransactionType(Student student, Book book, String string);
	boolean existsByTransactionTypeAndBookIdAndStudentId(String string, int id, long id2);

	


}
