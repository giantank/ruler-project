package info.lostred.ruler.engine;

import info.lostred.ruler.constant.Grade;
import info.lostred.ruler.domain.Result;
import info.lostred.ruler.exception.RulesEnginesException;
import info.lostred.ruler.factory.RuleFactory;
import info.lostred.ruler.rule.AbstractRule;

import java.util.Set;

/**
 * 可终止的规则引擎
 *
 * @author lostred
 */
public class TerminableRulesEngine extends AbstractRulesEngine {
    private Grade terminationGrade;

    public Grade getTerminationGrade() {
        return terminationGrade;
    }

    public void setTerminationGrade(Grade terminationGrade) {
        this.terminationGrade = terminationGrade;
    }

    @Override
    public Result execute(Object rootObject) {
        try {
            this.initContext(rootObject);
            Result result = Result.newInstance();
            for (AbstractRule rule : rules) {
                try {
                    if (this.executeInternal(rootObject, rule, result) && terminationGrade.equals(rule.getRuleDefinition().getGrade())) {
                        return result;
                    }
                } catch (Exception e) {
                    String message = this.getExceptionMessage(rule, e);
                    throw new RulesEnginesException(message, e, this.getBusinessType(), this.getClass());
                }
            }
            return result;
        } finally {
            this.destroyContext();
        }
    }

    @Override
    public Result executeWithRules(Object rootObject, Set<String> ruleCodes) {
        try {
            this.initContext(rootObject);
            Result result = Result.newInstance();
            RuleFactory ruleFactory = this.getRuleFactory();
            for (String ruleCode : ruleCodes) {
                AbstractRule rule = ruleFactory.getRule(ruleCode);
                try {
                    if (this.executeInternal(rootObject, rule, result)) {
                        return result;
                    }
                } catch (Exception e) {
                    String message = this.getExceptionMessage(rule, e);
                    throw new RulesEnginesException(message, e, this.getBusinessType(), this.getClass());
                }
            }
            return result;
        } finally {
            this.destroyContext();
        }
    }
}
