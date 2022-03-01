package com.hurynovich.data_unit_service.model.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hurynovich.data_unit_service.model.ApiModel;

import java.util.List;

@JsonDeserialize(as = DataUnitApiModelImpl.class)
public interface DataUnitApiModel extends ApiModel<String> {

    String getSchemaId();

    List<DataUnitPropertyApiModel> getProperties();
}
