package sonar.api;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.JsonNode;
import lombok.extern.slf4j.Slf4j;
import sonar.entity.SonarGroup;
import sonar.entity.SonarGroupSearch;
import sonar.entity.SonarGroupUser;
import sonar.entity.SonarGroupUserSearch;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SonarGroupApi extends SonarBaseApi{
    public SonarGroupApi(String sonarUrl, String sonarToken){
        super(sonarUrl, sonarToken);
    }

    static final String URI = "api/user_groups/";

    public List<SonarGroup> getGroups() throws Exception{
        List<SonarGroup> list = new ArrayList<SonarGroup>();
        String uriFormat = URI + "search?p=%d";
        String uri;
        int total = 1;
        int page = 1;
        int pageSize = 0;
        do {
            log.info("当前页为{},一共{}", page, total);
            uri = String.format(uriFormat, page);
            JsonNode response = get(uri);
            String content = response.toString();
            log.info(content);
            SonarGroupSearch sonarGroupSearch = JSON.parseObject(content, SonarGroupSearch.class);

            total = sonarGroupSearch.getPaging().getTotal();
            pageSize = sonarGroupSearch.getPaging().getPageSize();
            list.addAll(sonarGroupSearch.getGroups());

            page ++;

            if(page > Math.ceil(total / pageSize)){
                break;
            }
        }while(true);

        return list;
    }

    public List<SonarGroup> searchGroup(String groupName) throws Exception{
        String uri = URI + "search?q=" + URLEncoder.encode(groupName, "utf-8");
        log.info("查找group:{}", groupName);
        String content = get(uri).toString();
        SonarGroupSearch sonarGroupSearch = JSON.parseObject(content, SonarGroupSearch.class);
        if(sonarGroupSearch.getPaging().getTotal() == 0){
            throw new Exception(groupName + "不存在");
        }
        else {
            return sonarGroupSearch.getGroups();
        }
    }

    /**
     * 创建group
     * @param groupName
     * @param groupDescription
     * @throws Exception
     */
    public boolean createGroup(String groupName, String groupDescription) throws Exception{
        try{
            searchGroup(groupName);
            return false;
        }catch(Exception e){
            String uriFormat = URI + "create?name=%s&description=%s";
            String uri = String.format(uriFormat, URLEncoder.encode(groupName, "utf-8"), URLEncoder.encode(groupDescription, "utf-8"));
            post(uri);
            return true;
        }
    }

    /**
     * 添加用户到组
     * @param groupName
     * @param login
     * @throws Exception
     */
    public void addUserByGroupName(String groupName, String login) throws Exception{
        String uriFormat = URI + "add_user?login=%s&name=%s";
        String uri = String.format(uriFormat, URLEncoder.encode(login, "utf-8"), URLEncoder.encode(groupName, "utf-8"));
        post(uri);
    }

    /**
     * 从组删除用户
     * @param groupName
     * @param login
     * @throws Exception
     */
    public void removeUser(String groupName, String login) throws Exception{
        String uriFormat = URI + "remove_user?login=%s&name=%s";
        String uri = String.format(uriFormat, login, URLEncoder.encode(groupName, "utf-8"));
        post(uri);
    }

    /**
     * 删除组
     * @param groupName
     * @throws Exception
     */
    public void removeGroupByName(String groupName) throws Exception{
        String uriFormat = URI + "delete?name=%s";
        String uri = String.format(uriFormat, URLEncoder.encode(groupName, "utf-8"));
        post(uri);
    }

    /**
     * 返回group的成员
     * @param groupName
     * @throws Exception
     */
    public List<SonarGroupUser> groupUsers(String groupName) throws Exception{
        String uriFormat = URI + "users?name=%s&p=%d";
        int page = 1;
        int total, ps;
        List<SonarGroupUser> list = new ArrayList<SonarGroupUser>();
        do{
            String uri = String.format(uriFormat, URLEncoder.encode(groupName, "utf-8"), page);
            String content = get(uri).toString();
            log.info(content);
            SonarGroupUserSearch sonarGroupUserSearch = JSON.parseObject(content, SonarGroupUserSearch.class);
            total = sonarGroupUserSearch.getTotal();
            ps = sonarGroupUserSearch.getPs();

            list.addAll(sonarGroupUserSearch.getUsers());
            page ++;

            if(page > Math.ceil(total / ps)){
                break;
            }
        }while(true);

        return list;
    }
}
