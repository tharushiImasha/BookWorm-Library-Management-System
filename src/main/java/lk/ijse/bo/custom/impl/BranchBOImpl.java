package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BranchRepository;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    private static BranchBO branchBO;
    private Session session;

    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.BRANCH);
    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.USER);

    public static BranchBO getInstance() {
        return null == branchBO
                ? branchBO = new BranchBOImpl()
                : branchBO;
    }

    @Override
    public boolean saveBranch(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        User user = userRepository.search(branchDto.getUserName());

        try {
            branchRepository.setSession(session);
            boolean save = branchRepository.save(new Branch(branchDto.getId(), branchDto.getLocation(), user, new ArrayList<>()));

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
    public boolean updateBranch(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        User user = userRepository.search(branchDto.getUserName());

        try {
            branchRepository.setSession(session);
            boolean update = branchRepository.update(new Branch(branchDto.getId(), branchDto.getLocation(), user,new ArrayList<>()));

            transaction.commit();
            session.close();
            return update;

        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBranch(BranchDto branchDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        User user = userRepository.search(branchDto.getUserName());

        try {
            branchRepository.setSession(session);
            boolean delete = branchRepository.delete(new Branch(branchDto.getId(), branchDto.getLocation(), user, new ArrayList<>()));

            transaction.commit();
            session.close();
            return delete;

        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Branch searchBranch(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            branchRepository.setSession(session);
            Branch search = branchRepository.search(id);

            session.close();
            return search;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BranchDto> getAllBranch() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            branchRepository.setSession(session);
            List<Branch> all = branchRepository.getAll();
            List<BranchDto> branchDtoList = new ArrayList<>();

            for (Branch branch : all){
                branchDtoList.add(branch.toDto());
            }
            session.close();
            return branchDtoList;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }
}
