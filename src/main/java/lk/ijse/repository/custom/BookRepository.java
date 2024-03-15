package lk.ijse.repository.custom;

import lk.ijse.entity.Book;
import lk.ijse.repository.CrudRepository;
import org.hibernate.Session;

import java.util.List;

public interface BookRepository extends CrudRepository<Book> {
    byte[] getBookImg(String id);

    String getTitle(String id);

    String getAuthor(String id);

    String getDesc(String id);

    String getStatus(String id);

    String getGenre(String id);

    String getId(String title);

    List<Book> getBooksFromType(String type);

    List<Book> getRecentlyAddedBooks(int count);

}
