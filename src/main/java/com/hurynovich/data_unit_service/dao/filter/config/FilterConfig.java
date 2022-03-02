package com.hurynovich.data_unit_service.dao.filter.config;

import com.hurynovich.data_unit_service.dao.filter.DataUnitQueryCriteriaBuilder;
import com.hurynovich.data_unit_service.dao.filter.impl.DataUnitQueryCriteriaBuilderImpl;
import com.hurynovich.data_unit_service.dao.filter.model.CriteriaComparison;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitPropertyCriteria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Configuration
public class FilterConfig {

    private static final String START_OF_LINE_REGEX = "^";

    private static final String END_OF_LINE_REGEX = "$";

    private static final String ANY_SYMBOLS_REGEX = ".*";

    private static final String CASE_INSENSITIVE_OPTION = "i";

    @Bean
    public DataUnitQueryCriteriaBuilder dataUnitQueryCriteriaBuilder() {
        final Map<CriteriaComparison, BiConsumer<Criteria, DataUnitPropertyCriteria>> valueCriteriaAppliersByComparison =
                new EnumMap<>(CriteriaComparison.class);
        valueCriteriaAppliersByComparison.put(CriteriaComparison.EQ, (criteria, filterCriteria) ->
                criteria.is(filterCriteria.comparisonPattern()));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.GT, (criteria, filterCriteria) ->
                criteria.gt(filterCriteria.comparisonPattern()));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.GE, (criteria, filterCriteria) ->
                criteria.gte(filterCriteria.comparisonPattern()));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.LT, (criteria, filterCriteria) ->
                criteria.lt(filterCriteria.comparisonPattern()));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.LE, (criteria, filterCriteria) ->
                criteria.lte(filterCriteria.comparisonPattern()));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.STARTS_WITH, (criteria, filterCriteria) ->
                criteria.regex(START_OF_LINE_REGEX + filterCriteria.comparisonPattern(), CASE_INSENSITIVE_OPTION));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.ENDS_WITH, (criteria, filterCriteria) ->
                criteria.regex(filterCriteria.comparisonPattern() + END_OF_LINE_REGEX, CASE_INSENSITIVE_OPTION));
        valueCriteriaAppliersByComparison.put(CriteriaComparison.CONTAINS, (criteria, filterCriteria) ->
                criteria.regex(ANY_SYMBOLS_REGEX + filterCriteria.comparisonPattern() + ANY_SYMBOLS_REGEX, CASE_INSENSITIVE_OPTION));

        return new DataUnitQueryCriteriaBuilderImpl(valueCriteriaAppliersByComparison);
    }
}
