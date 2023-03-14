package info.lostred.ruler.rule;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 编程式规则
 * <p>由规则的校验对象为评估上下文的根对象，作为supportsInternal、evaluateInternal和getValueInternal方法的入参，
 * 方便编程式开发。这种情况下无需定义parameterExp参数表达式、conditionExp条件表达式和predicateExp断定表达式。</p>
 *
 * @param <T> 校验值的类型
 * @author lostred
 * @since 2.2.0
 */
public abstract class ProgrammaticRule<T> extends AbstractRule {
    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public ProgrammaticRule() {
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
                throw new IllegalArgumentException("ProgrammaticRule must be a parameterized type");
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getValue(Object object) {
        return this.getValueInternal((T) object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Object object) {
        return this.supportsInternal((T) object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean evaluate(Object object) {
        return this.evaluateInternal((T) object);
    }

    public abstract Object getValueInternal(T object);

    public abstract boolean supportsInternal(T object);

    public abstract boolean evaluateInternal(T object);
}
