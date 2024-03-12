package lk.ijse.repository.custom.impl;

import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.embedded.BorrowedDetailPK;
import lk.ijse.entity.BorrowedDetails;
import lk.ijse.repository.custom.BorrowedBookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        return false;
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

//        String sql = "SELECT bo FROM BorrowedDetails AS bo WHERE borrowedDetailPK.userName = :name";
//        Query query = session.createQuery(sql).setParameter("name", userName);
//
//        List list = query.list();

        Query<BorrowedDetails> query = session.createQuery("SELECT bd FROM BorrowedDetails bd " + "WHERE bd.user.username = :username", BorrowedDetails.class);
        query.setParameter("username", userName);

        List list = query.list();

        System.out.println(list);

        session.close();

        return list;
    }
}
