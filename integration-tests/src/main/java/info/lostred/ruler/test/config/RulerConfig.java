package info.lostred.ruler.test.config;

import info.lostred.ruler.annotation.RuleScan;
import info.lostred.ruler.constants.ValidType;
import info.lostred.ruler.core.ValidConfiguration;
import info.lostred.ruler.domain.ValidInfo;
import info.lostred.ruler.test.entity.Area;
import info.lostred.ruler.test.entity.Contact;
import info.lostred.ruler.test.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Configuration
@RuleScan("info.lostred.ruler.test.rule")
public class RulerConfig {
    @Bean
    public ValidConfiguration validConfiguration() {
        Collection<ValidInfo> validInfos = new ArrayList<>();
        ValidInfo validInfo1 = ValidInfo.ofRequired("name", Person.class.getName());
        ValidInfo validInfo2 = ValidInfo.ofRequired("gender", Person.class.getName());
        ValidInfo validInfo3 = ValidInfo.ofDict(ValidType.DICT.name(), "gender", Person.class.getName());
        ValidInfo validInfo4 = ValidInfo.ofNumberScope("age", new BigDecimal(18), null, Person.class.getName());
        ValidInfo validInfo5 = ValidInfo.ofDateTimeScope("birthday",
                LocalDateTime.of(1990, 1, 1, 0, 0, 0),
                LocalDateTime.of(2004, 1, 1, 0, 0, 0),
                Person.class.getName());
        ValidInfo validInfo6 = ValidInfo.ofRequired(ValidType.REQUIRED.name(), "type", Contact.class.getName());
        ValidInfo validInfo7 = ValidInfo.ofRequired(ValidType.REQUIRED.name(), "account", Contact.class.getName());
        ValidInfo validInfo8 = ValidInfo.ofRequired(ValidType.REQUIRED.name(), "password", Contact.class.getName());
        ValidInfo validInfo9 = ValidInfo.ofRequired(ValidType.REQUIRED.name(), "country", Area.class.getName());
        ValidInfo validInfo10 = ValidInfo.ofRequired(ValidType.REQUIRED.name(), "province", Area.class.getName());
        ValidInfo validInfo11 = ValidInfo.ofRequired(ValidType.REQUIRED.name(), "city", Area.class.getName());

//        validInfos.add(validInfo1);
//        validInfos.add(validInfo2);
//        validInfos.add(validInfo3);
//        validInfos.add(validInfo4);
//        validInfos.add(validInfo5);
//        validInfos.add(validInfo6);
//        validInfos.add(validInfo7);
//        validInfos.add(validInfo8);
//        validInfos.add(validInfo9);
//        validInfos.add(validInfo10);
//        validInfos.add(validInfo11);
        ValidConfiguration validConfiguration = new ValidConfiguration(validInfos);

        Set<Object> set = new HashSet<>(Arrays.asList("1", "2"));
        validInfo3.setDict(set);
        return validConfiguration;
    }
}
