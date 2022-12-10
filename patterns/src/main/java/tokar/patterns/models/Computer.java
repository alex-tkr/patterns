package tokar.patterns.models;

public class Computer {
    private int idComputer;
    private String model;
    private Processor processor;
    private Display display;
    private Computer(int idComputer, String model, Processor processor, Display display) {
        this.idComputer = idComputer;
        this.model = model;
        this.processor = processor;
        this.display = display;
    }
    private Computer(String model, Processor processor, Display display) {
        this.model = model;
        this.processor = processor;
        this.display = display;
    }

    public Computer() {
    }

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
    public Computer clone() {
        return new Computer.Builder().setIdComputer(idComputer)
                .setModel(model)
                .setProcessor(processor.clone())
                .setDisplay(display.clone())
                .build();
    }
    public static class Builder{
        private int idComputer;
        private String model;
        private Processor processor;
        private Display display;

        public Builder(){}
        public Builder setIdComputer(int idComputer) {
            this.idComputer = idComputer;
            return this;
        }
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }
        public Builder setProcessor(Processor processor) {
            this.processor = processor;
            return this;
        }
        public Builder setDisplay(Display display) {
            this.display = display;
            return this;
        }
        public Computer build(){
            return new Computer(idComputer, model, processor, display);
        }
    }
}