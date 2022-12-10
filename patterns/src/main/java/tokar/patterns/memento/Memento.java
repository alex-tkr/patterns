package tokar.patterns.memento;

import tokar.patterns.dao.DAO;
import tokar.patterns.models.Computer;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private List<Computer> states;
    private DAO dao;

    public Memento(DAO dao, Computer entryState) {
        states = new ArrayList<>();
        this.dao = dao;
        states.add(entryState.clone());
    }

    public void update(Computer computer) throws Exception {
        if (states.isEmpty()) {
            throw new Exception("You can't update entity before it is assigned!");
        }
        states.add(computer.clone());
    }

    public void delete() throws Exception {
        if (states.isEmpty()) {
            throw new Exception("You can't delete not existing instance!");
        }
        dao.deleteComputerById(states.get(states.size() - 1).getIdComputer());
        states.clear();
    }

    public Computer get() throws Exception {
        if (states.isEmpty()) {
            throw new Exception("You can't get entity before it is assigned!");
        }
        return states.get(states.size() - 1).clone();
    }

    public Computer rollback() throws Exception {
        if (states.size() <= 1) {
            throw new Exception("Can't rollback!");
        }
        Computer res = states.get(states.size() - 1);
        states.remove(states.size() - 1);
        return res;
    }

    public void commit() throws Exception {
        if (states.isEmpty()) {
            throw new Exception("You can't commit entity before it is assigned!");
        }
        dao.updateComputer(get());
        Computer last = states.get(states.size() - 1);
        states.clear();
        states.add(last);
    }
}