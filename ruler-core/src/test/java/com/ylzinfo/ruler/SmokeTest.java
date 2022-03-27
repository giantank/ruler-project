package com.ylzinfo.ruler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ylzinfo.ruler.config.RulerConfig;
import com.ylzinfo.ruler.constants.ValidType;
import com.ylzinfo.ruler.factory.ContextRuleFactory;
import com.ylzinfo.ruler.factory.RuleFactory;
import com.ylzinfo.ruler.core.ValidConfiguration;
import com.ylzinfo.ruler.domain.Result;
import com.ylzinfo.ruler.domain.ValidInfo;
import com.ylzinfo.ruler.domain.model.SubValidClass;
import com.ylzinfo.ruler.domain.model.ValidClass;
import com.ylzinfo.ruler.engine.CompleteRulesEngine;
import com.ylzinfo.ruler.engine.DetailRulesEngine;
import com.ylzinfo.ruler.factory.DefaultRulesEngineFactory;
import com.ylzinfo.ruler.support.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SmokeTest {
    static String validClassName = "com.ylzinfo.ruler.domain.model.SubValidClass";
    static String businessType = "common";
    static RuleFactory ruleFactory;
    static DetailRulesEngine<ValidClass> engine;
    static Collection<ValidInfo> validInfos;
    static ValidConfiguration validConfiguration;

    String toJson(Object object) {
        return JSON.toJSONString(object, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty);
    }

    static ValidConfiguration buildValidInfos() {
        validInfos = new ArrayList<>();
        ValidInfo validInfo1 = new ValidInfo("1", businessType, ValidType.REQUIRED.name(), "string", validClassName);
        ValidInfo validInfo2 = new ValidInfo("2", businessType, ValidType.REQUIRED.name(), "number", validClassName);
        ValidInfo validInfo3 = new ValidInfo("3", businessType, ValidType.REQUIRED.name(), "time", validClassName);
        ValidInfo validInfo4 = new ValidInfo("4", businessType, ValidType.DICT.name(), "string", validClassName);
        ValidInfo validInfo5 = new ValidInfo("5", businessType, ValidType.NUMBER_SCOPE.name(), "number", validClassName);
        validInfo5.setUpperLimit(BigDecimal.TEN);
        ValidInfo validInfo6 = new ValidInfo("6", businessType, ValidType.DATETIME_SCOPE.name(), "time", validClassName);
        validInfo6.setEndTime(LocalDateTime.now());
        validInfos.add(validInfo1);
        validInfos.add(validInfo2);
        validInfos.add(validInfo3);
        validInfos.add(validInfo4);
        validInfos.add(validInfo5);
        validInfos.add(validInfo6);
        return new ValidConfiguration(validInfos);
    }

    @BeforeAll
    static void init() {
        validConfiguration = buildValidInfos();
        ruleFactory = new ContextRuleFactory(validConfiguration, RulerConfig.class);
        TypeReference<CompleteRulesEngine<ValidClass>> typeReference = new TypeReference<CompleteRulesEngine<ValidClass>>() {
        };
        engine = DefaultRulesEngineFactory.builder(ruleFactory, businessType, typeReference).build();
    }

    @Test
    void sample1() {
        ValidClass validClass = new ValidClass();
        validClass.setNumber(BigDecimal.ZERO);
        SubValidClass subValidClass = new SubValidClass();
        subValidClass.setNumber(new BigDecimal(11));
        subValidClass.setTime(LocalDateTime.now());
        validClass.setSubValidClasses(Collections.singletonList(subValidClass));

        Result result = engine.execute(validClass);
        System.out.println(this.toJson(result));
    }
}