package com.hurynovich.data_unit_service.dao.filter;

import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.lang.NonNull;

public interface DataUnitQueryCriteriaBuilder {

    Criteria build(@NonNull DataUnitFilter filter);
}
