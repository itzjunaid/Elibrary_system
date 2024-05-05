package elibrary.manage.system.repositieris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import elibrary.manage.system.entities.Book;


@Repository
public interface BooksRepo extends JpaRepository<Book,Long>{
	

}
