package lk.ijse.repository;

import lk.ijse.repository.custom.impl.BookRepositoryImpl;
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
        USER, BRANCH, BOOK
    }

    public SuperRepository getDAO(DaoTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserRepositoryImpl();
            case BRANCH:
                return new BranchRepositoryImpl();
            case BOOK:
                return new BookRepositoryImpl();
            default:
                return null;
        }
    }
}
