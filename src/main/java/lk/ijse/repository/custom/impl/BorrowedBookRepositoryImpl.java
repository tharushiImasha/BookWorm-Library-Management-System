package lk.ijse.repository.custom.impl;

import lk.ijse.embedded.BorrowedDetailPK;
import lk.ijse.entity.BorrowedDetails;
import lk.ijse.repository.custom.BorrowedBookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BorrowedBookRepositoryImpl implements BorrowedBookRepository {

    private Session session;
    private static BorrowedBookRepositoryImpl borrowedBookRepositoryImpl;

    public BorrowedBookRepositoryImpl(){}

    public static BorrowedBookRepositoryImpl getInstance(){
        return null == borrowedBookRepositoryImpl ? borrowedBookRepositoryImpl = new BorrowedBookRepositoryImpl() : borrowedBookRepositoryImpl;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }


    @Override
    public boolean save(BorrowedDetails entity) {
        BorrowedDetailPK borrowedDetailPK = (BorrowedDetailPK) session.save(entity);

        if (borrowedDetailPK != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(BorrowedDetails entity) {
        try {
            session.update(entity);
            return true;

        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(BorrowedDetails entity) {
        return false;
    }

    @Override
    public BorrowedDetails search(String id) {
        return null;
    }

    @Override
    public List<BorrowedDetails> getAll() {
        String sql = "FROM BorrowedDetails";
        Query query = session.createQuery(sql);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public List<BorrowedDetails> getAllBorrowedFromUser(String userName) {

        Query<BorrowedDetails> query = session.createQuery("SELECT bd FROM BorrowedDetails bd WHERE bd.user.userName = :userName", BorrowedDetails.class);
        query.setParameter("userName", userName);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public BorrowedDetails searchBorrowedDetails(String id, String userName) {

        BorrowedDetailPK borrowedDetailPK = new BorrowedDetailPK(id, userName);

        try {
            BorrowedDetails borrowedDetails = session.get(BorrowedDetails.class, borrowedDetailPK);
            return borrowedDetails;

        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getUserNameFromBorrowed(String bookId) {
        String jpql = "SELECT bd.user.userName FROM BorrowedDetails bd WHERE bd.book.id = :bookId";
        String userName = session.createQuery(jpql, String.class)
                .setParameter("bookId", bookId)
                .getSingleResult();

        session.close();

        return userName;
    }

    @Override
    public boolean checkReturnDate(String bookId) {
        boolean isNull = false;

        String jpql = "SELECT bd.returnDate FROM BorrowedDetails bd WHERE bd.book.id = :bookId";
        List<Timestamp> returnDates = session.createQuery(jpql, Timestamp.class)
                .setParameter("bookId", bookId)
                .getResultList();

        for (Timestamp returnDate : returnDates) {
            if (returnDate == null) {
                System.out.println("Return date is null");
                isNull = true;
            } else {
                System.out.println("Return date: " + returnDate.toString());
                isNull = false;
            }
        }

        return isNull;

    }

    @Override
    public int getBorrowedCount() {
        try {
            String sql = "SELECT COUNT(*) \n" +
                    "FROM borrowed_detail\n" +
                    "WHERE return_date IS NULL";
            Query query = session.createNativeQuery(sql);
            int count = ((Number) query.getSingleResult()).intValue();

            return count;

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getOverDueCount() {
        Date currentDate = new Date();

        try {
            String sql = "SELECT COUNT(*) \n" +
                    "FROM borrowed_detail\n" +
                    "WHERE return_date IS NULL AND due_date < :currentDate";
            Query query = session.createNativeQuery(sql);
            query.setParameter("currentDate", currentDate);

            int count = ((Number) query.getSingleResult()).intValue();

            return count;

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<BorrowedDetails> getOverdueBorrowedDetails() {
        Date currentDate = new Date();

        String hql = "SELECT bd FROM BorrowedDetails bd " +
                "WHERE bd.returnDate IS NULL AND bd.dueDate < :currentDate";

        Query<BorrowedDetails> query = session.createQuery(hql, BorrowedDetails.class);
        query.setParameter("currentDate", currentDate);
        return query.getResultList();
    }

    @Override
    public List<BorrowedDetails> getBorrowedDetailsNotReturned() {
        String hql = "SELECT bd FROM BorrowedDetails bd " +
                "WHERE bd.returnDate IS NULL";

        Query<BorrowedDetails> query = session.createQuery(hql, BorrowedDetails.class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getUserAndBookTitle() {
        Date currentDate = new Date();

        String hql = "SELECT u.username, b.title FROM User u " +
                "JOIN u.borrowedDetails bd " +
                "JOIN bd.book b " +
                "WHERE bd.returnDate IS NULL AND bd.dueDate < :currentDate";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("currentDate", currentDate);
        return query.getResultList();
    }
}
