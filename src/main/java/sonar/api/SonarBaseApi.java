package sonar.api;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SonarBaseApi {
    String sonarUrl;
    String sonarToken;

    public SonarBaseApi(String sonarUrl, String sonarToken){
        this.sonarUrl = sonarUrl;
        if(!this.sonarUrl.endsWith("/")){
            this.sonarUrl += "/";
        }
        this.sonarToken = sonarToken;
    }

    public JsonNode get(String uri) throws Exception{
        log.info(uri);
        return Unirest.get(sonarUrl + uri).basicAuth(sonarToken, "")
                .asJson().getBody();
    }

    public String getToString(String uri) throws Exception{
        log.info(uri);
        return Unirest.get(sonarUrl + uri).basicAuth(sonarToken, "")
                .asString().getBody();
    }

    public JsonNode post(String uri) throws Exception{
        log.info(uri);
        return Unirest.post(sonarUrl + uri).basicAuth(sonarToken, "")
                .asJson().getBody();
    }

    public JsonNode delete(String uri) throws Exception{
        log.info(uri);
        return Unirest.delete(sonarUrl + uri).basicAuth(sonarToken, "")
                .asJson().getBody();
    }

    public JsonNode put(String uri) throws Exception{
        log.info(uri);
        return Unirest.put(sonarUrl + uri).basicAuth(sonarToken, "")
                .asJson().getBody();
    }
}
