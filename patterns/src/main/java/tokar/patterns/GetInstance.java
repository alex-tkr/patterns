package tokar.patterns;

import tokar.patterns.dao.DAO;
import tokar.patterns.dao.DaoInstance;
import tokar.patterns.dao.MySqlDAO;

public class GetInstance {
    private static DAO dao = null;
    public static DAO getDAOInstance(DaoInstance choice) throws Exception {
        switch (choice) {
            case MySqlDao:
                if (dao == null) {
                    String URL = "jdbc:mysql://localhost:3306/displays";
                    String USER = "root";
                    String PASSWORD = "1111";
                    dao = new MySqlDAO(URL, USER, PASSWORD);
                }
                break;
            default:
                throw new Exception("DAOInstance is not working!");
        }
        return dao;
    }
}