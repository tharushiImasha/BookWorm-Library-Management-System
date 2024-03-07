package lk.ijse.repository.custom.impl;

import lk.ijse.entity.Branch;
import lk.ijse.repository.custom.BranchRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {

    private Session session;
    private static BranchRepositoryImpl branchRepositoryImpl;

    public BranchRepositoryImpl(){}

    public static BranchRepositoryImpl getInstance(){
        return null == branchRepositoryImpl ? branchRepositoryImpl = new BranchRepositoryImpl() : branchRepositoryImpl;
    }

    @Override
    public boolean save(Branch entity) {
        try {
            session.save(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Branch entity) {
        try {
            session.update(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Branch entity) {
        try {
            session.delete(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Branch search(String id) {
        try {
            Branch branch = session.get(Branch.class, id);
            return branch;

        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Branch> getAll() {
        String sql = "SELECT b FROM Branch AS b";
        Query query = session.createQuery(sql);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
