package info.lostred.ruler.constant;

/**
 * 规则严重等级/校验结果等级
 *
 * @author lostred
 */
public enum Grade {
    /**
     * 合格
     * <p>
     * 对于校验结果Result来说，表示校验数据对象中没有违反对应规则引擎中的任何一条规则
     * </p>
     *
     * @see info.lostred.ruler.domain.Result
     */
    QUALIFIED,
    /**
     * 提醒
     * <p>
     * 对于校验结果Result来说，表示校验数据对象中违反了对应规则引擎中严重等级为提醒的规则，但没有违反等级为可疑及以上等级的规则
     * </p>
     *
     * @see info.lostred.ruler.domain.Result
     */
    REMINDER,
    /**
     * 可疑
     * <p>
     * 对于校验结果Result来说，表示校验数据对象中违反了对应规则引擎中严重等级为可疑的规则，但没有违反等级为非法的规则
     * </p>
     *
     * @see info.lostred.ruler.domain.Result
     */
    SUSPECTED,
    /**
     * 非法
     * <p>
     * 对于校验结果Result来说，表示校验数据对象中违反了对应规则引擎中任意一条严重等级为非法的规则
     * </p>
     *
     * @see info.lostred.ruler.domain.Result
     */
    ILLEGAL
}
