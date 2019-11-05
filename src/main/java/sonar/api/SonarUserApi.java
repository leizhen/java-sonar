package sonar.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SonarUserApi extends SonarBaseApi{
    public SonarUserApi(String sonarUrl, String sonarToken){
        super(sonarUrl, sonarToken);
    }
    static final String URI = "api/users/";


    /**
     * 创建用户
     * @param login
     * @param name
     * @throws Exception
     */
    public void createUser(String login, String name) throws Exception{
        String uriFormat = URI + "create?login=%s&name=%s&local=false";
        String uri = String.format(uriFormat, login, name);
        post(uri);
    }
}
