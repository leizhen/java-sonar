package sonar.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasureResultEntity {
    @JSONField(serialize = false)
    private String projectName;
    private int bugs;
    private String duplicatedLinesDensity;
    private int codeSmells;
    private long ncloc;
    private int vulnerabilities;
    private String sqaleDebtRatio;
}
