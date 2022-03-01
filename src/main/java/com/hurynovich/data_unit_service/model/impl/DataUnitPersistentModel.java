package com.hurynovich.data_unit_service.model.impl;

import com.hurynovich.data_unit_service.model.PersistentModel;

import java.util.List;

public interface DataUnitPersistentModel extends PersistentModel<String> {

    String getSchemaId();

    List<DataUnitPropertyPersistentModel> getProperties();
}
