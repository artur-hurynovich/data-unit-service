package com.hurynovich.data_unit_service.model.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hurynovich.data_unit_service.model.ApiModel;

import java.io.Serializable;
import java.util.List;

@JsonDeserialize(as = DataUnitApiModelImpl.class)
public interface DataUnitApiModel<T extends Serializable> extends ApiModel<T> {

    String getSchemaId();

    List<DataUnitPropertyApiModel> getProperties();
}
