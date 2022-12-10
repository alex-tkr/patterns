package tokar.patterns.proxy;

import tokar.patterns.dao.DAO;
import tokar.patterns.models.Computer;
import tokar.patterns.models.Display;
import tokar.patterns.models.Processor;
import tokar.patterns.observer.Observer;

import java.sql.SQLException;
import java.util.List;

public class MySqlDAOProxy implements DAO {
    private final DAO dao;
    private final Users user;
    public MySqlDAOProxy(DAO dao, Users user) {
        this.dao = dao;
        this.user = user;
    }
    private void userAdminRoleRequired() throws IllegalAccessException {
        if (user.getRoles().equals(Roles.userUserRole)) {
            throw new IllegalAccessException("User " + user.getUserName() + "Admin access required!");
        }
    }
    @Override
    public Users signInUser(String userName) throws SQLException {
        return dao.signInUser(userName);
    }
    @Override
    public void signUpUser(Users user) throws SQLException {
        dao.signUpUser(user);
    }
    @Override
    public List<Roles> getUserRoles() throws SQLException {
        return dao.getUserRoles();
    }
    @Override
    public List<Computer> getComputers() throws Exception {
        return dao.getComputers();
    }

    @Override
    public void deleteComputerById(int idComputer) throws Exception {
        userAdminRoleRequired();
        dao.deleteComputerById(idComputer);
    }
    @Override
    public void addComputer(String Model, int idProcessor, int idDisplay) throws Exception {
        userAdminRoleRequired();
        dao.addComputer(Model, idProcessor, idDisplay);
    }
    @Override
    public void updateComputer(Computer computer) throws Exception {
        userAdminRoleRequired();
        dao.updateComputer(computer);
    }
    @Override
    public List<Computer> getComputerByModel(String Model) throws Exception {
        return dao.getComputerByModel(Model);
    }
    @Override
    public void addDisplay(int RefreshRate, String matrix_type) throws Exception {
        userAdminRoleRequired();
        dao.addDisplay(RefreshRate, matrix_type);
    }
    @Override
    public void addProcessor(String model, String cores, String frequency) throws Exception {
        userAdminRoleRequired();
        dao.addProcessor(model, cores, frequency);
    }
    @Override
    public List<Display> getDisplays() throws Exception {
        return dao.getDisplays();
    }
    @Override
    public List<Processor> getProcessors() throws Exception {
        return dao.getProcessors();
    }
    @Override
    public List<Computer> getComputerById(int idComputer) throws Exception {
        return dao.getComputerById(idComputer);
    }
    @Override
    public void insertObserver(String Model, int idProcessor, int idDisplay) throws Exception {
        dao.insertObserver(Model, idProcessor, idDisplay);
    }
    @Override
    public List<Observer> getObservers() throws Exception {
        return dao.getObservers();
    }

}
