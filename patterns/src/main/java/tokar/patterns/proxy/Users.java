package tokar.patterns.proxy;

public class Users {
    private int idUser;
    private String userName;
    private Roles role;
    public Users(int idUser, String userName, Roles role) {
        this.idUser = idUser;
        this.userName = userName;
        this.role = role;
    }
    public int getIdUser() {
        return idUser;
    }
    public Users setIdUser(int idUser) {
        this.idUser = idUser;
        return this;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Roles getRoles() {
        return role;
    }
    public void setRoles(Roles role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + idUser +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder{
        private int idUser;
        private String userName;
        private Roles role;
        public Users.Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }
        public Users.Builder setIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }
        public Users.Builder setRole(Roles role) {
            this.role = role;
            return this;
        }
        public Users build(){
            return new Users(idUser, userName, role);
        }
    }
}