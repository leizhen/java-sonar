package sonar.entity;

import java.util.List;

public class SonarGroupSearch {
    private SonarPaging paging;
    private List<SonarGroup> groups;

    public SonarPaging getPaging() {
        return paging;
    }

    public void setPaging(SonarPaging paging) {
        this.paging = paging;
    }

    public List<SonarGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SonarGroup> groups) {
        this.groups = groups;
    }
}
