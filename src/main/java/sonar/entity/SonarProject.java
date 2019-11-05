package sonar.entity;

public class SonarProject {
    private String organization;
    private String id;
    private String key;
    private String name;
    private String qualifier;
    private String visibility;
    private String lastAnalysisDate;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getLastAnalysisDate() {
        return lastAnalysisDate;
    }

    public void setLastAnalysisDate(String lastAnalysisDate) {
        this.lastAnalysisDate = lastAnalysisDate;
    }
}
