package com.ylzinfo.ruler.factory;

import com.ylzinfo.ruler.core.AbstractRule;
import com.ylzinfo.ruler.core.ValidConfiguration;
import com.ylzinfo.ruler.domain.RuleInfo;

import java.util.List;

/**
 * 规则工厂
 *
 * @author dengluwei
 */
public interface RuleFactory {
    /**
     * 初始化工厂
     */
    void init();

    /**
     * 注册规则信息
     *
     * @param ruleInfo 规则信息
     */
    void registerRuleInfo(RuleInfo ruleInfo);

    /**
     * 创建规则
     *
     * @param ruleInfo 规则信息
     */
    void createRule(RuleInfo ruleInfo);

    /**
     * 获取规则
     *
     * @param ruleCode 规则编码
     * @return 规则
     */
    <E> AbstractRule<E> getRule(String ruleCode);

    /**
     * 获取校验配置
     *
     * @return 校验配置
     */
    ValidConfiguration getValidConfiguration();

    /**
     * 获取规则集合
     *
     * @param businessType 业务类型
     * @param validClass   规则约束类的类对象
     * @return 规则集合
     */
    <E> List<AbstractRule<E>> getRules(String businessType, Class<E> validClass);
}
