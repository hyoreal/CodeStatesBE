package com.codestates.config;

import com.codestates.converter.IntegerToMoneyConverter;
import com.codestates.converter.MoneyToIntegerConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {
    @Override
    protected List<?> userConverters() {
        return Arrays.asList(new MoneyToIntegerConverter(), new IntegerToMoneyConverter());
    }
}
