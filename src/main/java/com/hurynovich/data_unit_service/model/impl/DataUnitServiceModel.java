package com.hurynovich.data_unit_service.model.impl;

import com.hurynovich.data_unit_service.model.ServiceModel;

import java.util.List;

public interface DataUnitServiceModel extends ServiceModel<String> {

    String getSchemaId();

    List<DataUnitPropertyServiceModel> getProperties();
}
