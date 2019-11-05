package sonar.api;

import lombok.extern.slf4j.Slf4j;
import sonar.entity.ProjectPermissions;

import java.net.URLEncoder;

@Slf4j
public class SonarPermissionApi extends SonarBaseApi{
    public SonarPermissionApi(String sonarUrl, String sonarToken){
        super(sonarUrl, sonarToken);
    }

    private static final String URI = "api/permissions/";

    private ProjectPermissions getAllProjectPermissions(){
        ProjectPermissions projectPermissions = new ProjectPermissions();
        projectPermissions.setAdmin(true);
        projectPermissions.setCodeViewer(true);
        projectPermissions.setIssueAdmin(true);
        projectPermissions.setScan(true);
        projectPermissions.setSecurityHotspotAdmin(true);
        projectPermissions.setUser(true);

        return projectPermissions;
    }

    /**
     * 把一个group添加到project的权限里面
     * @param groupName
     * @param permissions
     * @param projectKey
     * @throws Exception
     */
    public void addGroupToProject(String groupName, String permissions, String projectKey) throws Exception{
        groupName = URLEncoder.encode(groupName, "utf-8");
        String uriFormat = URI + "add_group?groupName=%s&permission=%s&projectKey=%s";
        for(String permission : permissions.split(",")) {
            String uri = String.format(uriFormat, groupName, permission, projectKey);
            post(uri);
        }
    }

    public void addGroupToProject(String groupName, String projectKey) throws Exception{
        addGroupToProject(groupName, getAllProjectPermissions().toString(), projectKey);
    }

    /**
     * 把用户添加到项目
     * @param login
     * @param permissions
     * @param projectKey
     * @throws Exception
     */
    public void addUserToProject(String login, String permissions, String projectKey) throws Exception{
        String uriFormat = URI + "add_user?login=%s&permission=%s&projectKey=%s";
        for(String permission : permissions.split(",")) {
            String uri = String.format(uriFormat, login, permission, projectKey);
            post(uri);
        }
    }

    public void addUserToProject(String login, String projectKey) throws Exception{
        addUserToProject(login, getAllProjectPermissions().toString(), projectKey);
    }

    /**
     * 移除组的权限
     * @param groupName
     * @param permissions
     * @param projectKey
     * @throws Exception
     */
    public void removeGroupFromProject(String groupName, String permissions, String projectKey) throws Exception{
        groupName = URLEncoder.encode(groupName, "utf-8");
        String uriFormat = URI + "remove_group?groupName=%s&permission=%s&projectKey=%s";
        for(String permission : permissions.split(",")) {
            String uri = String.format(uriFormat, groupName, permission, projectKey);
            post(uri);
        }
    }

    public void removeGroupFromProject(String groupName, String projectKey) throws Exception{
        removeGroupFromProject(groupName, getAllProjectPermissions().toString(), projectKey);
    }

    /**
     * 移除用于权限
     * @param login
     * @param permissions
     * @param projectKey
     * @throws Exception
     */
    public void removeUserFromProject(String login, String permissions, String projectKey) throws Exception{
        String uriFormat = URI + "remove_user?login=%s&permission=%s&projectKey=%s";
        for(String permission : permissions.split(",")) {
            String uri = String.format(uriFormat, login, permission, projectKey);
            post(uri);
        }
    }

    public void removeUserFromProject(String login, String projectKey) throws Exception{
        removeUserFromProject(login, getAllProjectPermissions().toString(), projectKey);
    }
}
