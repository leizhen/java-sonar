package sonar.entity;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjectPermissions {
    private boolean admin;
    private boolean codeViewer;
    private boolean issueAdmin;
    private boolean securityHotspotAdmin;
    private boolean scan;
    private boolean user;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isCodeViewer() {
        return codeViewer;
    }

    public void setCodeViewer(boolean codeViewer) {
        this.codeViewer = codeViewer;
    }

    public boolean isIssueAdmin() {
        return issueAdmin;
    }

    public void setIssueAdmin(boolean issueAdmin) {
        this.issueAdmin = issueAdmin;
    }

    public boolean isSecurityHotspotAdmin() {
        return securityHotspotAdmin;
    }

    public void setSecurityHotspotAdmin(boolean securityHotspotAdmin) {
        this.securityHotspotAdmin = securityHotspotAdmin;
    }

    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean scan) {
        this.scan = scan;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public String toString(){
        List<String> permissions = new ArrayList<String>();
        if(admin){
            permissions.add("admin");
        }
        if(codeViewer){
            permissions.add("codeviewer");
        }
        if(issueAdmin){
            permissions.add("issueadmin");
        }
        if(securityHotspotAdmin){
            permissions.add("securityhotspotadmin");
        }if(scan){
            permissions.add("scan");
        }if(user){
            permissions.add("user");
        }
        return StringUtils.join(permissions, ",");
    }
}
