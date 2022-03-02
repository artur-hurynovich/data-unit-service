package com.hurynovich.data_unit_service.dao.filter.model;

import java.util.List;

public interface DataUnitFilter {

    Long schemaId();

    List<DataUnitPropertyCriteria> criteria();
}
