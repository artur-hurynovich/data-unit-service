package com.hurynovich.data_unit_service.model.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModelImpl.DataUnitPropertyApiModelImpl;

@JsonDeserialize(as = DataUnitPropertyApiModelImpl.class)
public interface DataUnitPropertyApiModel {

    String getSchemaId();

    Object getValue();
}
