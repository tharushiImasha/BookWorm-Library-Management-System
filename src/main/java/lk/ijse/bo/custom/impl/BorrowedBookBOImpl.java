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

            System.out.println("ooo "+dto.getBorrowedDetailPK());

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
}
