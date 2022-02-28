package com.hurynovich.data_unit_service.model.impl;

import java.util.List;

public record DataUnitServiceModelImpl(String id, String schemaId, List<DataUnitPropertyServiceModel> properties)
        implements DataUnitServiceModel<String> {

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
