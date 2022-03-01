package com.hurynovich.data_unit_service.model_generator.impl;

import com.hurynovich.data_unit_service.model.impl.DataUnitDocument;
import com.hurynovich.data_unit_service.model.impl.DataUnitDocument.DataUnitPropertyDocument;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyPersistentModel;
import com.hurynovich.data_unit_service.model_generator.ModelConstants;
import com.hurynovich.data_unit_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_VALUE;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_ID_1;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_ID_2;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_ID_3;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_VALUE;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_VALUE;

public class DataUnitPersistentModelGenerator implements ModelGenerator<DataUnitPersistentModel> {

    @Override
    public DataUnitPersistentModel generateWithNullId() {
        return processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_1, generateProperties());
    }

    private DataUnitPersistentModel processGenerate(final String id, final String schemaId,
                                                    final List<DataUnitPropertyPersistentModel> properties) {
        final DataUnitDocument dataUnit = new DataUnitDocument();
        dataUnit.setId(id);
        dataUnit.setSchemaId(schemaId);
        dataUnit.setProperties(properties);

        return dataUnit;
    }

    private List<DataUnitPropertyPersistentModel> generateProperties() {
        return List.of(processGenerateProperty(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_VALUE),
                processGenerateProperty(DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID, DATA_UNIT_INTEGER_PROPERTY_VALUE),
                processGenerateProperty(DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID, DATA_UNIT_FLOAT_PROPERTY_VALUE));
    }

    private DataUnitPropertyPersistentModel processGenerateProperty(final String schemaId, final Object value) {
        final DataUnitPropertyDocument property = new DataUnitPropertyDocument();
        property.setSchemaId(schemaId);
        property.setValue(value);

        return property;
    }

    @Override
    public DataUnitPersistentModel generate() {
        return processGenerate(DATA_UNIT_ID_1, ModelConstants.DATA_UNIT_SCHEMA_ID_1, generateProperties());
    }

    @Override
    public List<DataUnitPersistentModel> generateListWithNullIds() {
        final DataUnitPersistentModel schema1 = processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_1,
                generateProperties());
        final DataUnitPersistentModel schema2 = processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_2,
                generateProperties());
        final DataUnitPersistentModel schema3 = processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_3,
                generateProperties());

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitPersistentModel> generateList() {
        final DataUnitPersistentModel schema1 = processGenerate(DATA_UNIT_ID_1, ModelConstants.DATA_UNIT_SCHEMA_ID_1,
                generateProperties());
        final DataUnitPersistentModel schema2 = processGenerate(DATA_UNIT_ID_2, ModelConstants.DATA_UNIT_SCHEMA_ID_2,
                generateProperties());
        final DataUnitPersistentModel schema3 = processGenerate(DATA_UNIT_ID_3, ModelConstants.DATA_UNIT_SCHEMA_ID_3,
                generateProperties());

        return Arrays.asList(schema1, schema2, schema3);
    }
}
