package lk.ijse.repository.custom.impl;

import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.embedded.BorrowedDetailPK;
import lk.ijse.entity.BorrowedDetails;
import lk.ijse.entity.User;
import lk.ijse.repository.custom.BorrowedBookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
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
        System.out.println(entity.getDueDate()+ " and " +entity.getReturnDate());
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

        System.out.println("list  "+list);

        session.close();

        return list;
    }

    @Override
    public List<BorrowedDetailsDto> getAllBorrowedFromUser(String userName) {

        Query<BorrowedDetails> query = session.createQuery("SELECT bd FROM BorrowedDetails bd " + "WHERE bd.user.username = :username", BorrowedDetails.class);
        query.setParameter("username", userName);

        List list = query.list();

        System.out.println(list);

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
}
