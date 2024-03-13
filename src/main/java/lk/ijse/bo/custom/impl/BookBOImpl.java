package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BookRepository;
import lk.ijse.repository.custom.BranchRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    private static BookBO bookBO;
    private Session session;

    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.BOOK);
    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.BRANCH);

    public static BookBO getInstance() {
        return null == bookBO
                ? bookBO = new BookBOImpl()
                : bookBO;
    }

    @Override
    public boolean saveBook(BookDto bookDto) {

        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        branchRepository.setSession(session);
        Branch b1 = branchRepository.search(bookDto.getBranchId());

        System.out.println("bbb "+b1);

        try {
            bookRepository.setSession(session);
            boolean save = bookRepository.save(new Book(bookDto.getId(),bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDesc(), bookDto.getGenre(), bookDto.getImage(),bookDto.getStatus(), b1, null));
            transaction.commit();
            session.close();
            return save;
        } catch (Exception e) {
            transaction.rollback();
            session.close();

            System.out.println(e);

            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        branchRepository.setSession(session);
        Branch b1 = branchRepository.search(bookDto.getBranchId());

        try {
            bookRepository.setSession(session);
            boolean update = bookRepository.update(new Book(bookDto.getId(),bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDesc(), bookDto.getGenre(), bookDto.getImage(), bookDto.getStatus(), b1, null));

            transaction.commit();
            session.close();
            return update;
        } catch (Exception e) {
            transaction.rollback();
            session.close();

            System.out.println(e);

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        branchRepository.setSession(session);
        Branch b1 = branchRepository.search(bookDto.getBranchId());

        try {
            bookRepository.setSession(session);
            boolean delete = bookRepository.delete(new Book(bookDto.getId(),bookDto.getTitle(), bookDto.getAuthor(), bookDto.getDesc(), bookDto.getGenre(), bookDto.getImage(), bookDto.getStatus(), b1, null));

            transaction.commit();
            session.close();
            return delete;
        } catch (Exception e) {
            transaction.rollback();
            session.close();

            System.out.println(e);

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
               bookDtoList.add(new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getDesc(), book.getGenre(), book.getBranch().getId(), book.getStatus()));
               System.out.println(book.getBranch());
            }


            session.close();
            return bookDtoList;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BookDto> getFictions() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            List<Book> all = bookRepository.getFictions();
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

    @Override
    public List<BookDto> getFantasy() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            List<Book> all = bookRepository.getFantasy();
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

    @Override
    public List<BookDto> getChildrens() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            List<Book> all = bookRepository.getChildrens();
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

    @Override
    public List<BookDto> getHorror() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            List<Book> all = bookRepository.getHorror();
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

    @Override
    public byte[] getBookImg(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            byte[] bookImg = bookRepository.getBookImg(id);

            session.close();

            return bookImg;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getTitle(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            String title = bookRepository.getTitle(id);

            session.close();

            return title;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getAuthor(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            String author = bookRepository.getAuthor(id);

            session.close();

            return author;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getDescd(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            String author = bookRepository.getDesc(id);

            session.close();

            return author;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getStatus(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            bookRepository.setSession(session);
            String status = bookRepository.getStatus(id);

            session.close();

            return status;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }
}
