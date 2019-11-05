package sonar.entity;

import java.util.List;

public class SonarProjectSearch {
    private SonarPaging paging;
    private List<SonarProject> components;

    public SonarPaging getPaging() {
        return paging;
    }

    public void setPaging(SonarPaging paging) {
        this.paging = paging;
    }

    public List<SonarProject> getComponents() {
        return components;
    }

    public void setComponents(List<SonarProject> components) {
        this.components = components;
    }
}
