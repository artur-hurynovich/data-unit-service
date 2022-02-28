package com.hurynovich.data_unit_service.model.impl;

import java.util.List;

public record DataUnitApiModelImpl(String id, String schemaId, List<DataUnitPropertyApiModel> properties)
        implements DataUnitApiModel<String> {

    @Override
    public String getId() {
        return id();
    }

    @Override
    public String getSchemaId() {
        return schemaId();
    }

    @Override
    public List<DataUnitPropertyApiModel> getProperties() {
        return properties();
    }

    public record DataUnitPropertyApiModelImpl(String schemaId, Object value) implements DataUnitPropertyApiModel {

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
