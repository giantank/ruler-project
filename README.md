# Ruler

## 特性

- 框架入侵性低，可扩展性强
- 一处配置，处处使用
- 可根据需求配置不同的引擎类型(simple, incomplete, complete)

## 核心类

### RulesEngine接口

- simple类型的实现类只关心结果，有违规则直接返回结果
- incomplete类型的实现类会输出报告，有违规也会直接返回结果
- complete类型的实现类会输出报告，并且会执行完所有的规则

### Rule抽象类

定义了规则的主要方法，开发者可扩展该类，实现其他特殊的规则。
框架默认实现类三类普适规则：必填字段规则，字典字段规则，范围字段规则。

- RequiredFieldRule 必填字段规则
- DictFieldRule 字典字段规则
- ScopeFieldRule 范围字段规则