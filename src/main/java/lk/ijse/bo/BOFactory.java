package lk.ijse.bo;

import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BoTypes{
        USER
    }

    public SuperBO getBO(BoTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
