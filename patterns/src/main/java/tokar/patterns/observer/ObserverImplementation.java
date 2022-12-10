package tokar.patterns.observer;

import tokar.patterns.models.Display;
import tokar.patterns.models.Processor;

public class ObserverImplementation implements Observer {
    private int idComputer;
    private String model;
    private Processor processor;
    private Display display;
    private ObserverImplementation(int idComputer, String model, Processor processor, Display display) {
        this.idComputer = idComputer;
        this.model = model;
        this.processor = processor;
        this.display = display;
    }
    private ObserverImplementation(String model, Processor processor, Display display) {
        this.model = model;
        this.processor = processor;
        this.display = display;
    }

    public ObserverImplementation() {}

    public int getIdComputer() {
        return idComputer;
    }
    public void setIdComputer(int idComputer) {
        this.idComputer = idComputer;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }
    public Processor getProcessor() {
        return processor;
    }
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
    public Display getDisplay() {
        return display;
    }
    public void setDisplay(Display display) {
        this.display = display;
    }
    @Override
    public String toString() {
        return "Computer{" +
                "idComputer=" + idComputer +
                ", model='" + model + '\'' +
                ", processor=" + processor +
                ", display=" + display +
                '}';
    }
    @Override
    public void update(String m) {
        System.out.println("Message: " + m);
    }
    public static class Builder{
        private int idComputer;
        private String model;
        private Processor processor;
        private Display display;

        public Builder(){}
        public ObserverImplementation.Builder setIdComputer(int idComputer) {
            this.idComputer = idComputer;
            return this;
        }
        public ObserverImplementation.Builder setModel(String model) {
            this.model = model;
            return this;
        }
        public ObserverImplementation.Builder setProcessor(Processor processor) {
            this.processor = processor;
            return this;
        }
        public ObserverImplementation.Builder setDisplay(Display display) {
            this.display = display;
            return this;
        }
        public ObserverImplementation build(){
            return new ObserverImplementation(idComputer, model, processor, display);
        }
    }
}