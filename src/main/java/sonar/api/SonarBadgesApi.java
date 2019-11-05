package sonar.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SonarBadgesApi extends SonarBaseApi {
    public SonarBadgesApi(String sonarUrl, String sonarToken){
        super(sonarUrl, sonarToken);
    }

    static final String MEASURE_URI = "api/badges/measure";
    static final Pattern PATTERN = Pattern.compile("<text x=\".*?\" y=\".*?\">(.*?)</text>");


    /**
     * 获取所有需要的measure
     * @param key
     * @return
     */
    public String measures(String key){
        Map<String, Object> map = new HashMap<String, Object>();
        String[] metrics = {"bugs", "ncloc", "duplicated_lines_density", "code_smells", "bugs", "vulnerabilities", "sqale_debt_ratio"};
        for(String metric : metrics){
            try {
                map.put(metric, measure(key, metric));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                continue;
            }
        }
        return JSON.toJSONString(map);
    }

    public String measure(String key, String metric) throws Exception{
        String uriFormat = MEASURE_URI + "?key=%s&metric=%s";
        String uri = String.format(uriFormat, key, metric);
        String content = getToString(uri).toString();

        //值在最后一个text中
        Matcher matcher = PATTERN.matcher(content);
        String value = "";
        while(matcher.find()){
            value = matcher.group(1);
        }

        return value;
    }
}
