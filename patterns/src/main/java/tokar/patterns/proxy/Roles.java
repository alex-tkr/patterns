package tokar.patterns.proxy;

import java.util.Objects;

public class Roles {
    private int idRole;
    private String roleName;
    public int getIdRole() {
        return idRole;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles role1 = (Roles) o;
        return Objects.equals(roleName, role1.roleName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
    public Roles(int idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    public String getRoles() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + idRole +
                ", role='" + roleName + '\'' +
                '}';
    }

    public static class Builder{
        private int idRole;
        private String roleName;
        public Roles.Builder setRoleName(String roleName) {
            this.roleName = roleName;
            return this;
        }
        public Roles.Builder setIdRole(int idRole) {
            this.idRole = idRole;
            return this;
        }

        public Roles build(){
            return new Roles(idRole, roleName);
        }
    }
    public static final Roles userUserRole = new Builder().setRoleName("User").build();
    public static final Roles adminUserRole = new Builder().setRoleName("Admin").build();
}