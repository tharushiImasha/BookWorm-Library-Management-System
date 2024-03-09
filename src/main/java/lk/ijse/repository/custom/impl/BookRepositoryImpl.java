package lk.ijse.repository.custom.impl;

import lk.ijse.entity.Book;
import lk.ijse.repository.custom.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private Session session;
    private static BookRepositoryImpl bookRepositoryimpl;

    public BookRepositoryImpl(){}

    public static BookRepositoryImpl getInstance(){
        return null == bookRepositoryimpl ? bookRepositoryimpl = new BookRepositoryImpl() : bookRepositoryimpl;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Book entity) {
        try {
            session.save(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Book entity) {
        try {
            session.update(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Book entity) {
        try {
            session.delete(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Book search(String id) {
        try {
            Book book = session.get(Book.class, id);
            return book;

        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT b FROM Book AS b";
        Query query = session.createQuery(sql);

        List list = query.list();

        session.close();

        return list;
    }

}
