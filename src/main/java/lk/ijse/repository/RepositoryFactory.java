package lk.ijse.repository;

import lk.ijse.repository.custom.impl.BranchRepositoryImpl;
import lk.ijse.repository.custom.impl.UserRepositoryImpl;

public class RepositoryFactory {
    private static RepositoryFactory repositoryFactory;
    private RepositoryFactory(){

    }

    public static RepositoryFactory getRepositoryFactory(){
        return (repositoryFactory ==null)? repositoryFactory = new RepositoryFactory(): repositoryFactory;
    }

    public enum DaoTypes{
        USER, BRANCH
    }

    public SuperRepository getDAO(DaoTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserRepositoryImpl();
            case BRANCH:
                return new BranchRepositoryImpl();
            default:
                return null;
        }
    }
}
