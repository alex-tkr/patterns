package tokar.patterns.dao;

import tokar.patterns.models.Computer;
import tokar.patterns.models.Display;
import tokar.patterns.models.Processor;
import tokar.patterns.observer.ObserverImplementation;
import tokar.patterns.observer.Subject;
import tokar.patterns.observer.SubjectImplementation;
import tokar.patterns.observer.Observer;
import tokar.patterns.proxy.Roles;
import tokar.patterns.proxy.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDAO implements DAO{
    private static MySqlDAO instance;
    private Connection connection;
    private static Subject subject;
    public MySqlDAO(String URL, String username, String password) throws
            Exception {
        if (instance == null) {
            connection = DriverManager.getConnection(URL, username, password);
            connection.setAutoCommit(false);
            instance = this;
        }
    }
    @Override
    public List<Computer> getComputers() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DAOQueries.getComputers);
        List<Computer> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToComputer(queryResult));
        }
        return result;
    }
    @Override
    public void addComputer(String Model, int idProcessor, int idDisplay)
            throws Exception {
        try {
            int k = 1;
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DAOQueries.addComputer);
            preparedStatement.setString(k++, Model);
            preparedStatement.setInt(k++, idProcessor);
            preparedStatement.setInt(k++, idDisplay);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
    @Override
    public void updateComputer(Computer computer)
            throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DAOQueries.updateComputer);
            int k = 1;
            preparedStatement.setString(k++, computer.getModel());
            preparedStatement.setInt(k++, computer.getProcessor().getIdProcessor());
            preparedStatement.setInt(k++, computer.getDisplay().getId());
            preparedStatement.setInt(k++, computer.getIdComputer());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            System.out.println("Can't execute update!");
            throw e;
        }
    }
    @Override
    public void deleteComputerById(int idComputer) throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DAOQueries.deleteComputerById);
            preparedStatement.setInt(1, idComputer);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Nothing was updated");
            }
            subject = new SubjectImplementation(this);
            subject.notifyObserver();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
    @Override
    public List<Computer> getComputerByModel(String Model)
            throws Exception {
        PreparedStatement preparedStatement =
                connection.prepareStatement(DAOQueries.getComputerByModel);
        preparedStatement.setString(1, Model);
        ResultSet queryResult = preparedStatement.executeQuery();
        List<Computer> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToComputer(queryResult));
        }
        return result;
    }
    @Override
    public void addDisplay(int RefreshRate, String matrix_type)
            throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DAOQueries.addDisplay);
            preparedStatement.setInt(1, RefreshRate);
            preparedStatement.setString(2, matrix_type);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
    @Override
    public void addProcessor(String model, String cores, String frequency)
            throws Exception {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DAOQueries.addProcessor);
            preparedStatement.setString(1, model);
            preparedStatement.setString(2, cores);
            preparedStatement.setString(3, frequency);
            preparedStatement.executeUpdate();
            connection.commit();
            subject = new SubjectImplementation(this);
            subject.notifyObserver();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }
    @Override
    public List<Display> getDisplays() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DAOQueries.getDisplays);
        List<Display> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToDisplay(queryResult));
        }
        return result;
    }
    @Override
    public List<Processor> getProcessors() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DAOQueries.getProcessors);
        List<Processor> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToProcessor(queryResult));
        }
        return result;
    }

    @Override
    public void insertObserver(String Model, int idProcessor, int idDisplay) throws Exception {
        try {
            int k = 1;
            PreparedStatement preparedStatement =
                    connection.prepareStatement(DAOQueries.insertObserver);
            preparedStatement.setString(k++, Model);
            preparedStatement.setInt(k++, idProcessor);
            preparedStatement.setInt(k++, idDisplay);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    @Override
    public List<Observer> getObservers() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet queryResult = statement.executeQuery(DAOQueries.getObservers);
        List<Observer> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToObserver(queryResult));
        }
        return result;
    }

    @Override
    public List<Computer> getComputerById(int idComputer) throws Exception {
        PreparedStatement preparedStatement =
                connection.prepareStatement(DAOQueries.getComputerById);
        preparedStatement.setInt(1, idComputer);
        ResultSet queryResult = preparedStatement.executeQuery();
        List<Computer> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToComputer(queryResult));
        }
        return result;
    }
    private Processor recordToProcessor(ResultSet queryResult) throws Exception {
        return new Processor.Builder()
                .setIdProcessor(queryResult.getInt("idProcessor"))
                .setModel(queryResult.getString("model"))
                .setCores(queryResult.getString("cores"))
                .setFrequency(queryResult.getString("frequency"))
                .build();
    }
    private Display recordToDisplay(ResultSet queryResult) throws Exception {
        return new Display.Builder()
                .setId(queryResult.getInt("idDisplay"))
                .setRefreshRate(queryResult.getInt("refresh_rate"))
                .setMatrixType(queryResult.getString("matrix_type"))
                .build();
    }
    private Computer recordToComputer(ResultSet queryResult) throws Exception {
        Processor currentProcessor = recordToProcessor(queryResult);
        Display currentDisplay = recordToDisplay(queryResult);
        return new Computer.Builder()
                .setIdComputer(queryResult.getInt("idComputer"))
                .setModel(queryResult.getString("model"))
                .setProcessor(currentProcessor)
                .setDisplay(currentDisplay)
                .build();
    }
    private ObserverImplementation recordToObserver(ResultSet queryResult) throws Exception {
        Processor currentProcessor = recordToProcessor(queryResult);
        Display currentDisplay = recordToDisplay(queryResult);
        return new ObserverImplementation.Builder()
                .setIdComputer(queryResult.getInt("idComputer"))
                .setModel(queryResult.getString("model"))
                .setProcessor(currentProcessor)
                .setDisplay(currentDisplay)
                .build();
    }
    @Override
    public Users signInUser(String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DAOQueries.signInUser);
        preparedStatement.setString(1, username);
        ResultSet queryResult = preparedStatement.executeQuery();
        if (queryResult.next()) {
            return recordToUser(queryResult);
        } else {
            throw new IllegalAccessError("User not found!");
        }
    }
    @Override
    public void signUpUser(Users user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DAOQueries.signUpUser);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getRoles().getRoles());
        preparedStatement.executeUpdate();
        connection.commit();
    }
    @Override
    public List<Roles> getUserRoles() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DAOQueries.getRoles);
        ResultSet queryResult = preparedStatement.executeQuery();
        List<Roles> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(recordToRole(queryResult));
        }
        return result;
    }
    private Roles recordToRole(ResultSet queryResult) throws SQLException {
        return new Roles.Builder()
                .setRoleName(queryResult.getString("rolename"))
                .setIdRole(queryResult.getInt("idRole"))
                .build();
    }
    private Users recordToUser(ResultSet queryResult) throws SQLException {
        return new Users.Builder()
                .setRole(recordToRole(queryResult))
                .setUserName(queryResult.getString("username"))
                .setIdUser(queryResult.getInt("idUser"))
                .build();
    }

}