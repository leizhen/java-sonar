package sonar.entity;

import java.util.List;

public class SonarGroupUserSearch {
    private int p;
    private int ps;
    private int total;
    private List<SonarGroupUser> users;

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SonarGroupUser> getUsers() {
        return users;
    }

    public void setUsers(List<SonarGroupUser> users) {
        this.users = users;
    }
}
