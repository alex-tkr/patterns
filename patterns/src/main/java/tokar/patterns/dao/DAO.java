package tokar.patterns.dao;
import tokar.patterns.models.Computer;
import tokar.patterns.models.Display;
import tokar.patterns.models.Processor;
import tokar.patterns.observer.Observer;
import tokar.patterns.observer.ObserverImplementation;
import tokar.patterns.proxy.Roles;
import tokar.patterns.proxy.Users;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<Computer> getComputers() throws Exception;
    void addComputer(String Model, int idProcessor, int idDisplay) throws Exception;
    void updateComputer(Computer computer) throws Exception;
    List<Computer> getComputerByModel(String Model) throws Exception;
    List<Computer> getComputerById(int idComputer) throws Exception;
    void deleteComputerById(int idComputer) throws Exception;
    void addDisplay(int RefreshRate, String matrix_type) throws Exception;
    List<Display> getDisplays() throws Exception;
    void addProcessor(String model, String cores, String frequency) throws Exception;
    List<Processor> getProcessors() throws Exception;
    void insertObserver(String Model, int idProcessor, int idDisplay) throws Exception;
    List<Observer> getObservers() throws Exception;

    Users signInUser(String userName) throws SQLException;
    void signUpUser(Users user) throws SQLException;
    List<Roles> getUserRoles() throws SQLException;
}