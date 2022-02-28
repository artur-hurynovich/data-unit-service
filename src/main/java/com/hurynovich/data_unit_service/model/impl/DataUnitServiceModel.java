package com.hurynovich.data_unit_service.model.impl;

import com.hurynovich.data_unit_service.model.ServiceModel;

import java.io.Serializable;
import java.util.List;

public interface DataUnitServiceModel<T extends Serializable> extends ServiceModel<T> {

    String getSchemaId();

    List<DataUnitPropertyServiceModel> getProperties();
}
