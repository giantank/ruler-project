package info.lostred.ruler.domain;

import info.lostred.ruler.constant.Grade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 引擎执行的结果
 *
 * @author lostred
 */
public class Result implements Serializable {
    /**
     * 校验结果等级
     */
    private Grade grade;
    /**
     * 校验报告集合
     */
    private Map<String, Report> reports;

    private Result() {
    }

    /**
     * 构建一个默认的校验结果，该结果的校验结果等级为合格
     *
     * @return 校验结果
     * @see Grade
     */
    public static Result newInstance() {
        Result result = new Result();
        result.grade = Grade.QUALIFIED;
        result.reports = new HashMap<>();
        return result;
    }

    /**
     * 添加一个报告
     *
     * @param ruleDefinition 规则定义
     * @param value          需要记录的值
     */
    public void addReport(RuleDefinition ruleDefinition, Object value) {
        Report report = Report.newInstance(ruleDefinition.getRuleType(), ruleDefinition.getGrade(), ruleDefinition.getDescription(), value);
        this.reports.put(ruleDefinition.getRuleCode(), report);
        this.updateGrade(ruleDefinition.getGrade());
    }

    /**
     * 更新校验结果的严重等级，"ILLEGAL"的优先级最高，其次是"SUSPECTED"和"REMINDER"，最后是"QUALIFIED"
     *
     * @param grade 严重等级
     * @see Grade
     */
    public void updateGrade(Grade grade) {
        if (this.grade.ordinal() < grade.ordinal()) {
            this.grade = grade;
        }
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Map<String, Report> getReports() {
        return reports;
    }

    public void setReports(Map<String, Report> reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "Result{" +
                "grade=" + grade +
                ", reports=" + reports +
                '}';
    }
}
