package com.ylzinfo.ruler.rule;

import com.ylzinfo.ruler.annotation.Rule;
import com.ylzinfo.ruler.core.ValidConfiguration;
import com.ylzinfo.ruler.domain.Report;
import com.ylzinfo.ruler.domain.RuleInfo;
import com.ylzinfo.ruler.domain.ValidInfo;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 必填字段校验规则
 *
 * @param <E> 规则约束的参数类型
 * @author dengluwei
 */
@Rule(ruleCode = "required", desc = "规定的字段必须填写")
public class RequiredFieldRule<E> extends SingleFieldRule<E> {

    public RequiredFieldRule(ValidConfiguration validConfiguration, RuleInfo ruleInfo) {
        super(validConfiguration, ruleInfo);
    }

    @Override
    public boolean isSupported(E element) {
        return !validConfiguration.getRequiredValidInfos().isEmpty();
    }

    @Override
    public boolean judge(E element) {
        return validConfiguration.getRequiredValidInfos().stream()
                .filter(validInfo -> validInfo.getBusinessType().equals(this.ruleInfo.getBusinessType()))
                .anyMatch(validInfo -> this.check(element, validInfo));
    }

    @Override
    public Report buildReport(E element) {
        Map<String, Object> map = validConfiguration.getRequiredValidInfos().stream()
                .flatMap(validInfo -> this.collectIllegals(element, validInfo).stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return Report.of(ruleInfo).putIllegal(map);
    }

    @Override
    protected boolean isNotMatch(ValidInfo validInfo, Object value) {
        return value == null || "".equals(value);
    }

    @Override
    protected Set<Map.Entry<String, Object>> wrap(E element, ValidInfo validInfo, Object value) {
        if (value == null) {
            return super.wrap(element, validInfo, "-");
        }
        return super.wrap(element, validInfo, value);
    }
}
