package sonar.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import sonar.entity.SonarProject;
import sonar.entity.SonarProjectSearch;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SonarProjectApi extends SonarBaseApi {
    public SonarProjectApi(String sonarUrl, String sonarToken){
        super(sonarUrl, sonarToken);
    }
    private static final String URI = "api/projects/";

    private List<SonarProject> search(String uriFormat) throws Exception{
        List<SonarProject> list = new ArrayList<SonarProject>();
        int page = 1;
        int total, ps;
        String uri;
        String content;
        SonarProjectSearch sonarProjectSearch;
        do{
            uri = String.format(uriFormat, page);
            content = get(uri).toString();
            log.info(content);
            sonarProjectSearch = JSON.parseObject(content, SonarProjectSearch.class);
            list.addAll(sonarProjectSearch.getComponents());

            total = sonarProjectSearch.getPaging().getTotal();
            ps = sonarProjectSearch.getPaging().getPageSize();
            page ++;
            if(page > Math.ceil(total / ps)){
                break;
            }
        }while(true);

        return list;
    }

    /**
     * 获取所有的项目
     * @return
     * @throws Exception
     */
    public List<SonarProject> getProjects() throws Exception{
        String uriFormat = URI + "search?p=%d";
        return search(uriFormat);
    }

    /**
     * 根据项目名称或者key获取项目
     * @param nameOrKey
     * @return
     * @throws Exception
     */
    public List<SonarProject> getProjectsByNameOrKey(String nameOrKey) throws Exception{
        String uriFormat = URI + "search?p=%d&q=" + nameOrKey;
        return search(uriFormat);

    }
}
