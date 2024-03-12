package lk.ijse.repository.custom;

import lk.ijse.entity.Book;
import lk.ijse.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book> {
    List<Book> getFictions();

    List<Book> getFantasy();

    List<Book> getChildrens();

    List<Book> getHorror();

    byte[] getBookImg(String id);

    String getTitle(String id);

    String getAuthor(String id);

    String getDesc(String id);
}
