package info.lostred.ruler.rule;

import info.lostred.ruler.domain.RuleDefinition;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 编程式规则
 * <p>由规则定义的parameterExp参数表达式解析出需要校验的对象，作为doSupports和doJudge方法的入参，
 * 方便编程式开发。这种情况下无需定义conditionExp条件表达式和predicateExp断定表达式。</p>
 *
 * @param <T> 校验值的类型
 * @author lostred
 */
public abstract class ProgrammaticRule<T> extends AbstractRule {
    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public ProgrammaticRule(RuleDefinition ruleDefinition) {
        super(ruleDefinition);
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            Type superType = types[0];
            if (superType instanceof Class) {
                this.type = (Class<T>) superType;
            } else if (superType instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) superType).getRawType();
                if (rawType instanceof Class) {
                    this.type = (Class<T>) rawType;
                }
            } else {
                throw new IllegalArgumentException("GenericRule must be a parameterized type");
            }
        }
    }

    @Override
    public boolean supports(EvaluationContext context, ExpressionParser parser) {
        String parameterExp = this.ruleDefinition.getParameterExp();
        T value = parser.parseExpression(parameterExp).getValue(context, type);
        return this.doSupports(value);
    }

    @Override
    public boolean judge(EvaluationContext context, ExpressionParser parser) {
        String parameterExp = this.ruleDefinition.getParameterExp();
        T value = parser.parseExpression(parameterExp).getValue(context, type);
        return this.doJudge(value);
    }

    /**
     * 判断校验值是否需要该规则校验
     *
     * @param value 校验值
     * @return 需要返回true，否则返回false
     */
    protected abstract boolean doSupports(T value);

    /**
     * 判断校验值是否违反该规则
     *
     * @param value 校验值
     * @return 违反返回true，否则返回false
     */
    protected abstract boolean doJudge(T value);
}