package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BookRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    private static BookBO bookBO;
    private Session session;

    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.BOOK);

    public static BookBO getInstance() {
        return null == bookBO
                ? bookBO = new BookBOImpl()
                : bookBO;
    }

    @Override
    public boolean saveBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookRepository.setSession(session);
            boolean save = bookRepository.save(new Book(bookDto.getId(),bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDesc(), bookDto.getBooksDetails()));

            transaction.commit();
            session.close();
            return save;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookRepository.setSession(session);
            boolean save = bookRepository.update(new Book(bookDto.getId(),bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDesc(), bookDto.getBooksDetails()));

            transaction.commit();
            session.close();
            return save;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            bookRepository.setSession(session);
            boolean save = bookRepository.delete(new Book(bookDto.getId(),bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDesc(), bookDto.getBooksDetails()));

            transaction.commit();
            session.close();
            return save;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Book searchBook(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            Book search = bookRepository.search(id);

            session.close();
            return search;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BookDto> getAllBook() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            List<Book> all = bookRepository.getAll();
            List<BookDto> bookDtoList = new ArrayList<>();

            for (Book book : all){
                bookDtoList.add(book.toDto());
            }

            session.close();
            return bookDtoList;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }
}
