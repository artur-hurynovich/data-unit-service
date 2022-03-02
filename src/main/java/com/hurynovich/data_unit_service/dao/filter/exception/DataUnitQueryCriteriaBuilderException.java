package com.hurynovich.data_unit_service.dao.filter.exception;

import org.springframework.lang.NonNull;

public class DataUnitQueryCriteriaBuilderException extends RuntimeException {

    public DataUnitQueryCriteriaBuilderException(@NonNull final String message) {
        super(message);
    }
}
