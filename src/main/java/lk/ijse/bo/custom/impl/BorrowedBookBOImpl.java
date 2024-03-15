package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BorrowedBookBO;
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.entity.BorrowedDetails;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BorrowedBookRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BorrowedBookBOImpl implements BorrowedBookBO {

    private static BorrowedBookBO borrowedBookBO;
    private Session session;

    BorrowedBookRepository borrowedBookRepository = (BorrowedBookRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.BORROWEDDETAILS);

    public static BorrowedBookBO getInstance() {
        return null == borrowedBookBO
                ? borrowedBookBO = new BorrowedBookBOImpl()
                : borrowedBookBO;
    }

    @Override
    public boolean saveBorrowedDetail(BorrowedDetailsDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            borrowedBookRepository.setSession(session);

            boolean save = borrowedBookRepository.save(new BorrowedDetails(dto.getBorrowedDetailPK(), dto.getBorrowedDate(), dto.getDueDate(), dto.getReturnDate()));

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
    public boolean updateBorrowedDetail(BorrowedDetailsDto dto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            borrowedBookRepository.setSession(session);

            boolean save = borrowedBookRepository.update(new BorrowedDetails(dto.getBorrowedDetailPK(), dto.getBorrowedDate(), dto.getDueDate(), dto.getReturnDate()));

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
    public List<BorrowedDetailsDto> getAllBorrowed() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            List<BorrowedDetails> all = borrowedBookRepository.getAll();
            List<BorrowedDetailsDto> borrowedDetailsDtos = new ArrayList<>();

            for (BorrowedDetails borrowedDetails : all){
                borrowedDetailsDtos.add(borrowedDetails.toDto());
            }

            session.close();
            return borrowedDetailsDtos;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowedDetailsDto> getAllBorrowedFromUser(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            List<BorrowedDetails> allBorrowedFromUser = borrowedBookRepository.getAllBorrowedFromUser(userName);
            List<BorrowedDetailsDto> borrowedDetailsDtos = new ArrayList<>();

            for (BorrowedDetails borrowedDetails : allBorrowedFromUser){
                borrowedDetailsDtos.add(borrowedDetails.toDto());
            }

            session.close();
            return borrowedDetailsDtos;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public BorrowedDetails searchBorrowedDetails(String id, String userName) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            BorrowedDetails search = borrowedBookRepository.searchBorrowedDetails(id, userName);

            session.close();
            return search;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getUserNameFromBorrowed(String bookId) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            String userNameFromBorrowed = borrowedBookRepository.getUserNameFromBorrowed(bookId);

            session.close();
            return userNameFromBorrowed;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean checkReturnDate(String bookId) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            boolean isNull = borrowedBookRepository.checkReturnDate(bookId);

            session.close();
            return isNull;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowedDetailsDto> getOverdueBorrowedDetails() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            List<BorrowedDetails> all = borrowedBookRepository.getOverdueBorrowedDetails();
            List<BorrowedDetailsDto> borrowedDetailsDtos = new ArrayList<>();

            for (BorrowedDetails borrowedDetails : all){
                borrowedDetailsDtos.add(borrowedDetails.toDto());
            }

            session.close();
            return borrowedDetailsDtos;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowedDetailsDto> getBorrowedDetailsNotReturned() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            List<BorrowedDetails> all = borrowedBookRepository.getBorrowedDetailsNotReturned();
            List<BorrowedDetailsDto> borrowedDetailsDtos = new ArrayList<>();

            for (BorrowedDetails borrowedDetails : all){
                borrowedDetailsDtos.add(borrowedDetails.toDto());
            }

            session.close();
            return borrowedDetailsDtos;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Object[]> getUserAndBookTitle() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            List<Object[]> userAndBookTitle = borrowedBookRepository.getUserAndBookTitle();

            session.close();
            return userAndBookTitle;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean checkNumberOfBooks(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        borrowedBookRepository.setSession(session);
        List<BorrowedDetails> borrowingDetails = borrowedBookRepository.getAll();

        int count = 0;

        for (BorrowedDetails bDs : borrowingDetails){
            if (bDs.getBorrowedDetailPK().getUserName().equals(userName)){

                if (bDs.getReturnDate() == null){
                    count++;
                }
            }
        }

        if (count == 2){
            session.close();
            return false;
        }
        session.close();
        return true;
    }

    @Override
    public int getBorrowedCount() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            int borrowedCount = borrowedBookRepository.getBorrowedCount();

            session.close();
            return borrowedCount;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int getOverDueCount() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            borrowedBookRepository.setSession(session);
            int borrowedCount = borrowedBookRepository.getOverDueCount();

            session.close();
            return borrowedCount;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

}
