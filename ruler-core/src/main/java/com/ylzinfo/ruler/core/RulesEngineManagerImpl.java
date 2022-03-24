package com.ylzinfo.ruler.core;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class RulesEngineManagerImpl implements RulesEngineManager {
    private final Map<String, ? extends RulesEngine<?>> map;

    public RulesEngineManagerImpl(Collection<RulesEngine<?>> rulesEngines) {
        this.map = rulesEngines.stream()
                .collect(Collectors.toMap(RulesEngine::getBusinessType, e -> e));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> RulesEngine<E> dispatch(String businessType, Object validRootNode, Class<E> validClass) {
        return (RulesEngine<E>) this.map.get(businessType);
    }
}