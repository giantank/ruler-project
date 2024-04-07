package info.lostred.ruler.autoconfigure;

import info.lostred.ruler.constant.EngineType;
import info.lostred.ruler.constant.Grade;
import info.lostred.ruler.constant.RulerConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ruler配置类
 *
 * @author lostred
 */
@ConfigurationProperties("ruler")
public class RulerProperties {
    private String businessType = RulerConstants.BUSINESS_TYPE_COMMON;
    private String engineType = EngineType.NO_TERMINABLE.name();
    private Grade terminationGrade = Grade.REMINDER;
    private String[] ruleScanPackages = {};
    private String[] domainScanPackages = {};

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Grade getTerminationGrade() {
        return terminationGrade;
    }

    public void setTerminationGrade(Grade terminationGrade) {
        this.terminationGrade = terminationGrade;
    }

    public String[] getRuleScanPackages() {
        return ruleScanPackages;
    }

    public void setRuleScanPackages(String[] ruleScanPackages) {
        this.ruleScanPackages = ruleScanPackages;
    }

    public String[] getDomainScanPackages() {
        return domainScanPackages;
    }

    public void setDomainScanPackages(String[] domainScanPackages) {
        this.domainScanPackages = domainScanPackages;
    }
}
