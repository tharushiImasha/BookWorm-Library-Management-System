package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.BorrowedBookBOImpl;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BoTypes{
        USER, BRANCH, BOOK, BORROWEDDETAILS
    }

    public SuperBO getBO(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BORROWEDDETAILS:
                return new BorrowedBookBOImpl();
            default:
                return null;
        }
    }
}
