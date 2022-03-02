package com.hurynovich.data_unit_service.model.impl;

import com.hurynovich.GenerateMetamodel;

import java.util.List;

@GenerateMetamodel
public record DataUnitServiceModelImpl(String id, String schemaId, List<DataUnitPropertyServiceModel> properties)
        implements DataUnitServiceModel {

    @Override
    public String getId() {
        return id();
    }

    @Override
    public String getSchemaId() {
        return schemaId();
    }

    @Override
    public List<DataUnitPropertyServiceModel> getProperties() {
        return properties();
    }

    @GenerateMetamodel
    public record DataUnitPropertyServiceModelImpl(String schemaId, Object value)
            implements DataUnitPropertyServiceModel {

        @Override
        public String getSchemaId() {
            return schemaId();
        }

        @Override
        public Object getValue() {
            return value();
        }
    }
}
