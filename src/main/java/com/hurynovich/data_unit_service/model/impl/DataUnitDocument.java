package com.hurynovich.data_unit_service.model.impl;

import com.hurynovich.GenerateMetamodel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@GenerateMetamodel
public class DataUnitDocument implements DataUnitPersistentModel {

    @Id
    private String id;

    private String schemaId;

    private List<DataUnitPropertyPersistentModel> properties;

    @Override
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(final String schemaId) {
        this.schemaId = schemaId;
    }

    @Override
    public List<DataUnitPropertyPersistentModel> getProperties() {
        return properties;
    }

    public void setProperties(final List<DataUnitPropertyPersistentModel> properties) {
        this.properties = properties;
    }

    public static class DataUnitPropertyDocument implements DataUnitPropertyPersistentModel {

        private String schemaId;

        private Object value;

        @Override
        public String getSchemaId() {
            return schemaId;
        }

        public void setSchemaId(final String schemaId) {
            this.schemaId = schemaId;
        }

        @Override
        public Object getValue() {
            return value;
        }

        public void setValue(final Object value) {
            this.value = value;
        }
    }
}
