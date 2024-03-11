package lk.ijse.repository.custom.impl;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
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

    @Override
    public List<Book> getBookBranch(Branch branch) {

//        String sql = "SELECT b FROM Book AS b WHERE branch = :branch";
//        Query query = session.createQuery(sql).setParameter("branch", branch);
//
//        List list = query.list();
//
//        session.close();
//
//        return list;

        return null;
    }

    @Override
    public List<Book> getFictions() {
        String type = "Fiction";

        String sql = "SELECT b FROM Book AS b WHERE genre = :type";
        Query query = session.createQuery(sql).setParameter("type", type);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public List<Book> getFantasy() {
        String type = "Fantasy";

        String sql = "SELECT b FROM Book AS b WHERE genre = :type";
        Query query = session.createQuery(sql).setParameter("type", type);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public List<Book> getChildrens() {
        String type = "Children's";

        String sql = "SELECT b FROM Book AS b WHERE genre = :type";
        Query query = session.createQuery(sql).setParameter("type", type);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public List<Book> getHorror() {
        String type = "Horror";

        String sql = "SELECT b FROM Book AS b WHERE genre = :type";
        Query query = session.createQuery(sql).setParameter("type", type);

        List list = query.list();

        session.close();

        return list;
    }
}
