package info.lostred.ruler.constant;

/**
 * 引擎类型
 *
 * @author lostred
 */
public enum EngineType {
    /**
     * 规则引擎将执行完所有的规则
     */
    NO_TERMINABLE,
    /**
     * 规则引擎在设定终止等级的规则触发后将结束执行
     */
    TERMINABLE
}
