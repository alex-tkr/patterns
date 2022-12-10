package tokar.patterns.proxy;

import tokar.patterns.GetInstance;
import tokar.patterns.dao.DAO;
import tokar.patterns.dao.DaoInstance;
import tokar.patterns.models.Computer;

import java.util.List;

public class Lab4Main {
    public static void main(String[] args) throws Exception {
        DAO dao = GetInstance.getDAOInstance(DaoInstance.MySqlDao);
        Users user = new Users.Builder().setUserName("userAlex").setRole(Roles.userUserRole).build();
        //dao.signUpUser(user);
        MySqlDAOProxy DAO_user = new MySqlDAOProxy(dao, dao.signInUser("userAlex"));

        Users admin = new Users.Builder().setUserName("adminAlex").setRole(Roles.adminUserRole).build();
        //dao.signUpUser(admin);
        MySqlDAOProxy DAO_admin = new MySqlDAOProxy(dao, dao.signInUser("adminAlex"));

        try {
            System.out.println("User tries do operations (" + user.getUserName() + "):");
            List<Computer> computers = DAO_user.getComputers();
            System.out.println(computers);
            System.out.println("Updating model name of phone");
            computers.get(0).setModel("new model name");
            DAO_user.updateComputer(computers.get(0));
            System.out.println("successfully updated");
        } catch (Exception e) {
            System.out.println("Error with access: " + e.getMessage());
        }
        try {
            System.out.println("User tries do operations (" + admin.getUserName() + "):");
            List<Computer> computers = DAO_admin.getComputers();
            System.out.println(computers);
            System.out.println("Updating model name of phone");
            computers.get(0).setModel("new model name");
            DAO_admin.updateComputer(computers.get(0));
            System.out.println("successfully updated");
        } catch (Exception e) {
            System.out.println("Error with access: " + e.getMessage());
        }
    }
}