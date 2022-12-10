package tokar.patterns.memento;

import tokar.patterns.GetInstance;
import tokar.patterns.dao.DAO;
import tokar.patterns.dao.DaoInstance;
import tokar.patterns.models.Computer;
import tokar.patterns.models.Display;

public class MainMemento {
    public static void main(String[] args) throws Exception {
        DAO dao = GetInstance.getDAOInstance(DaoInstance.MySqlDao);
        Computer instance = dao.getComputers().get(0);
        Memento memento = new Memento(dao, instance);
        try {
            System.out.println("Initial state");
            System.out.println(memento.get());
            Computer tmpComp = memento.get();
            tmpComp.setModel("Nokia");
            memento.update(tmpComp);

            System.out.println("After model update: ");
            System.out.println(memento.get());
            tmpComp = memento.get();
            tmpComp.setDisplay(new Display.Builder().setMatrixType("lcd").setRefreshRate(100).build());
            memento.update(tmpComp);

            System.out.println("After display updated: ");
            System.out.println(memento.get());

            memento.rollback();
            System.out.println("After rollback: ");
            System.out.println(memento.get());

            memento.rollback();
            System.out.println("After rollback: ");
            System.out.println(memento.get());
            memento.delete();
            try {
                System.out.println("Trying to rollback");
                memento.rollback();
            } catch (Exception e) {
                System.out.println("Rollback canceled with result: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Program finished with exception: " + e.getMessage());
        }
    }
}
