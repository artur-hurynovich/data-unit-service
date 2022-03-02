package com.hurynovich.data_unit_service.dao.filter.impl;

import com.hurynovich.data_unit_service.dao.filter.DataUnitQueryCriteriaBuilder;
import com.hurynovich.data_unit_service.dao.filter.config.FilterConfig;
import com.hurynovich.data_unit_service.dao.filter.model.CriteriaComparison;
import com.hurynovich.data_unit_service.dao.filter.model.impl.DataUnitFilterImpl;
import com.hurynovich.data_unit_service.dao.filter.model.impl.DataUnitPropertyCriteriaImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class DataUnitQueryCriteriaBuilderImplTest {

    private static final String EXPECTED_QUERY =
            "Query: { \"schemaId\" : 1, \"$and\" : " +
                    "[ { \"properties\" : { \"$elemMatch\" : { \"schemaId\" : 1, \"value\" : 100}}}, " +
                    "{ \"properties\" : { \"$elemMatch\" : { \"schemaId\" : 2, \"value\" : { \"$gt\" : { \"$java\" : 2021-11-20 } } } } }, " +
                    "{ \"properties\" : { \"$elemMatch\" : { \"schemaId\" : 3, \"value\" : { \"$regularExpression\" : { \"pattern\" : \"^Test\", \"options\" : \"i\"}}}}} ] }, " +
                    "Fields: {}, " +
                    "Sort: {}";

    private final DataUnitQueryCriteriaBuilder queryCriteriaBuilder = new FilterConfig().dataUnitQueryCriteriaBuilder();

    @Test
    void buildTest() {
        final Criteria criteria = queryCriteriaBuilder.build(new DataUnitFilterImpl(
                1L, Arrays.asList(
                new DataUnitPropertyCriteriaImpl(1L, CriteriaComparison.EQ, 100),
                new DataUnitPropertyCriteriaImpl(2L, CriteriaComparison.GT, LocalDate.of(2021, 11, 20)),
                new DataUnitPropertyCriteriaImpl(3L, CriteriaComparison.STARTS_WITH, "Test"))));
        final Query query = new Query(criteria);

        Assertions.assertEquals(EXPECTED_QUERY, query.toString());
    }
}
