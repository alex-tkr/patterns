package tokar.patterns.models;

public class Display {
    private int idDisplay;
    private int RefreshRate;
    private String matrixType;

    public Display(int idDisplay, int RefreshRate, String matrixType) {
        this.idDisplay = idDisplay;
        this.RefreshRate = RefreshRate;
        this.matrixType = matrixType;
    }
    public Display(int RefreshRate, String matrixType) {
        this.RefreshRate = RefreshRate;
        this.matrixType = matrixType;
    }

    public Display() {

    }

    @Override
    public String toString() {
        return "Display{" +
                "id=" + idDisplay +
                ", screenRefreshRate='" + RefreshRate + '\'' +
                ", matrixType='" + matrixType + '\'' +
                '}';
    }
    public Display clone() {
        return new Display.Builder().setId(idDisplay)
                .setRefreshRate(RefreshRate)
                .setMatrixType(matrixType)
                .build();
    }
    public int getId() {
        return idDisplay;
    }

    public void setId(int idDisplay) {
        this.idDisplay = idDisplay;
    }

    public int getScreenRefreshRate() {
        return RefreshRate;
    }

    public void setScreenRefreshRate(int RefreshRate) {
        this.RefreshRate = RefreshRate;
    }

    public String getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(String matrixType) {
        this.matrixType = matrixType;
    }

    public static class Builder{
        private int idDisplay;
        private int RefreshRate;
        private String matrixType;

        public Builder setId(int idDisplay) {
            this.idDisplay = idDisplay;
            return this;
        }
        public Builder setRefreshRate(int RefreshRate) {
            this.RefreshRate = RefreshRate;
            return this;
        }
        public Builder setMatrixType(String matrixType) {
            this.matrixType = matrixType;
            return this;
        }
        public Display build(){
            return new Display(idDisplay, RefreshRate, matrixType);
        }
    }
}