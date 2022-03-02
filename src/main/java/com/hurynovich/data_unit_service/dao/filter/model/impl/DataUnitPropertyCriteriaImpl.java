package com.hurynovich.data_unit_service.dao.filter.model.impl;

import com.hurynovich.data_unit_service.dao.filter.model.CriteriaComparison;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitPropertyCriteria;
import org.springframework.lang.NonNull;

public record DataUnitPropertyCriteriaImpl(@NonNull Long propertySchemaId,
                                           @NonNull CriteriaComparison comparison,
                                           @NonNull Object comparisonPattern)
        implements DataUnitPropertyCriteria {

}
