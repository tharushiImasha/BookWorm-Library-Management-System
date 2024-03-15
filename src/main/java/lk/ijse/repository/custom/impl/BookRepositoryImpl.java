package lk.ijse.repository.custom.impl;

import jakarta.persistence.TypedQuery;
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

    @Override
    public List<Book> getBooksFromType(String type) {
        String sql = "SELECT b FROM Book AS b WHERE genre = :type";
        Query query = session.createQuery(sql).setParameter("type", type);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public byte[] getBookImg(String id) {

        String hql = "SELECT ie.image FROM Book ie WHERE ie.id = :id";
        Query<byte[]> query = session.createQuery(hql, byte[].class);
        query.setParameter("id", id);
        byte[] imageData = query.uniqueResult();

        session.close();

        return imageData;
    }

    @Override
    public String getTitle(String bId) {
        String sql = "SELECT title FROM Book WHERE id = :id";
        Query query = session.createQuery(sql).setParameter("id", bId);

        String title = (String) query.list().get(0);

        session.close();

        return title;
    }

    @Override
    public String getAuthor(String bId) {
        String sql = "SELECT author FROM Book WHERE id = :id";
        Query query = session.createQuery(sql).setParameter("id", bId);

        String author = (String) query.list().get(0);

        session.close();

        return author;
    }

    @Override
    public String getDesc(String bId) {
        String sql = "SELECT desc FROM Book WHERE id = :id";
        Query query = session.createQuery(sql).setParameter("id", bId);

        String author = (String) query.list().get(0);

        session.close();

        return author;
    }

    @Override
    public String getStatus(String bId) {
        String sql = "SELECT status FROM Book WHERE id = :id";
        Query query = session.createQuery(sql).setParameter("id", bId);

        String status = (String) query.list().get(0);

        session.close();

        return status;
    }

    @Override
    public String getGenre(String bId) {
        String sql = "SELECT genre FROM Book WHERE id = :id";
        Query query = session.createQuery(sql).setParameter("id", bId);

        String status = (String) query.list().get(0);

        session.close();

        return status;
    }

    @Override
    public String getId(String title) {
        String sql = "SELECT id FROM Book WHERE title = :title";
        Query query = session.createQuery(sql).setParameter("title", title);

        List<String> resultList = query.list();

        if (resultList.isEmpty()) {
            session.close();
            return null; // Return null if no matching title is found
        }

        String status = resultList.get(0);
        session.close();
        return status;
    }

    @Override
    public List<Book> getRecentlyAddedBooks(int count) {
        TypedQuery<Book> query = session.createQuery(
                "SELECT b FROM Book b ORDER BY b.id DESC", Book.class);
        query.setMaxResults(count); // Limit to count results
        return query.getResultList();
    }

}
