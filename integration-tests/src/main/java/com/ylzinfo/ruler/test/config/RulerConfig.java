package com.ylzinfo.ruler.test.config;

import com.ylzinfo.ruler.annotation.RuleScan;
import com.ylzinfo.ruler.constants.ValidType;
import com.ylzinfo.ruler.core.ValidConfiguration;
import com.ylzinfo.ruler.domain.ValidInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@RuleScan("com.ylzinfo.ruler.test.rule")
public class RulerConfig {
    private static final String validClassName1 = "com.ylzinfo.ruler.test.entity.Country";
    private static final String validClassName2 = "com.ylzinfo.ruler.test.entity.Area";
    private static final String businessType = "common";

    @Bean
    public ValidConfiguration validConfiguration() {
        Collection<ValidInfo> validInfos = new ArrayList<>();
        ValidInfo validInfo1 = new ValidInfo("1", businessType, ValidType.REQUIRED.name(), "name", validClassName1);
        ValidInfo validInfo2 = new ValidInfo("2", businessType, ValidType.REQUIRED.name(), "areaCode", validClassName2);
        validInfos.add(validInfo1);
        validInfos.add(validInfo2);
        ValidConfiguration validConfiguration = new ValidConfiguration(validInfos);
        Map<String, Set<Object>> dict = new HashMap<>();
        Set<Object> set = new HashSet<>(Arrays.asList("hello", "world"));
        dict.put("string", set);
        validConfiguration.addDict(dict);
        return validConfiguration;
    }
}
