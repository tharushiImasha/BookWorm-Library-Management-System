package lk.ijse.bo;

import lk.ijse.bo.custom.impl.AdminBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BoTypes{
        USER, ADMIN
    }

    public SuperBO getBO(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            default:
                return null;
        }
    }
}
