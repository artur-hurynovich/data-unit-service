package com.hurynovich.data_unit_service.model.impl;

import com.hurynovich.data_unit_service.model.PersistentModel;

import java.io.Serializable;
import java.util.List;

public interface DataUnitPersistentModel<T extends Serializable> extends PersistentModel<T> {

    String getSchemaId();

    List<DataUnitPropertyPersistentModel> getProperties();
}
