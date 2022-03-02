package com.hurynovich.data_unit_service.dao.filter.model;

public interface DataUnitPropertyCriteria {

    Long propertySchemaId();

    CriteriaComparison comparison();

    Object comparisonPattern();
}
