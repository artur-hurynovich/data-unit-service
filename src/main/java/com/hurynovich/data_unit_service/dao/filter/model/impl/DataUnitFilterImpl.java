package com.hurynovich.data_unit_service.dao.filter.model.impl;

import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitPropertyCriteria;
import org.springframework.lang.NonNull;

import java.util.List;

public record DataUnitFilterImpl(@NonNull Long schemaId,
                                 @NonNull List<DataUnitPropertyCriteria> criteria)
        implements DataUnitFilter {

}
