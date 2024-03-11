package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;

import java.util.List;

public interface BookBO extends SuperBO {
    boolean saveBook(BookDto bookDto);

    boolean updateBook(BookDto bookDto);

    boolean deleteBook(BookDto bookDto);

    Book searchBook(String id);

    List<BookDto> getAllBook();

    List<BookDto> getBookBranch (Branch branch);

    List<BookDto> getFictions();

    List<BookDto> getFantasy();

    List<BookDto> getChildrens();

    List<BookDto> getHorror();
}
