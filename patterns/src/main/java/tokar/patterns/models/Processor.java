package tokar.patterns.models;

public class Processor{
    private int idProcessor;
    private String model;
    private String cores;
    private String frequency;

    public Processor(int idProcessor, String model, String cores, String frequency) {
        this.idProcessor = idProcessor;
        this.model = model;
        this.cores = cores;
        this.frequency = frequency;
    }

    public Processor() {

    }

    public int getIdProcessor() {
        return idProcessor;
    }
    public void setIdProcessor(int idProcessor) {
        this.idProcessor = idProcessor;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getCores() {
        return cores;
    }
    public void setCores(String cores) {
        this.cores = cores;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "idProcessor=" + idProcessor +
                ", model='" + model + '\'' +
                ", cores='" + cores + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }
    public Processor clone() {
        return new Builder()
                .setIdProcessor(idProcessor)
                .setModel(model)
                .setCores(cores)
                .setFrequency(frequency)
                .build();
    }
    public static class Builder{
        private int idProcessor;
        private String model;
        private String cores;
        private String frequency;

        public Builder setCores(String cores) {
            this.cores = cores;
            return this;
        }
        public Builder setIdProcessor(int idProcessor) {
            this.idProcessor = idProcessor;
            return this;
        }
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }
        public Builder setFrequency(String frequency) {
            this.frequency = frequency;
            return this;
        }

        public Processor build(){
            return new Processor(idProcessor, model, cores, frequency);
        }
    }
}