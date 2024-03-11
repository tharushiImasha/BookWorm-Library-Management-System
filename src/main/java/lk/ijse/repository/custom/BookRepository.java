package lk.ijse.repository.custom;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book> {
    List<Book> getBookBranch (Branch branch);

    List<Book> getFictions();

    List<Book> getFantasy();

    List<Book> getChildrens();

    List<Book> getHorror();
}
