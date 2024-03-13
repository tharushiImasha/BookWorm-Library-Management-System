package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.util.List;

public interface BookBO extends SuperBO {
    boolean saveBook(BookDto bookDto);

    boolean updateBook(BookDto bookDto);

    boolean deleteBook(BookDto bookDto);

    Book searchBook(String id);

    List<BookDto> getAllBook();

    List<BookDto> getFictions();

    List<BookDto> getFantasy();

    List<BookDto> getChildrens();

    List<BookDto> getHorror();

    byte[] getBookImg(String id);

    String getTitle(String id);

    String getAuthor(String id);

    String getDescd(String id);

    String getStatus(String id);
}
