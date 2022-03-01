package com.hurynovich.data_unit_service.model_generator.impl;

import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl.DataUnitPropertyServiceModelImpl;
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

public class DataUnitServiceModelGenerator implements ModelGenerator<DataUnitServiceModel> {

    @Override
    public DataUnitServiceModel generateWithNullId() {
        return processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_1, generateProperties());
    }

    private DataUnitServiceModel processGenerate(final String id, final String schemaId,
                                                 final List<DataUnitPropertyServiceModel> properties) {
        return new DataUnitServiceModelImpl(id, schemaId, properties);
    }

    private List<DataUnitPropertyServiceModel> generateProperties() {
        return List.of(processGenerateProperty(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_VALUE),
                processGenerateProperty(DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID, DATA_UNIT_INTEGER_PROPERTY_VALUE),
                processGenerateProperty(DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID, DATA_UNIT_FLOAT_PROPERTY_VALUE));
    }

    private DataUnitPropertyServiceModel processGenerateProperty(final String schemaId, final Object value) {
        return new DataUnitPropertyServiceModelImpl(schemaId, value);
    }

    @Override
    public DataUnitServiceModel generate() {
        return processGenerate(DATA_UNIT_ID_1, ModelConstants.DATA_UNIT_SCHEMA_ID_1, generateProperties());
    }

    @Override
    public List<DataUnitServiceModel> generateListWithNullIds() {
        final DataUnitServiceModel schema1 = processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_1,
                generateProperties());
        final DataUnitServiceModel schema2 = processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_2,
                generateProperties());
        final DataUnitServiceModel schema3 = processGenerate(null, ModelConstants.DATA_UNIT_SCHEMA_ID_3,
                generateProperties());

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitServiceModel> generateList() {
        final DataUnitServiceModel schema1 = processGenerate(DATA_UNIT_ID_1, ModelConstants.DATA_UNIT_SCHEMA_ID_1,
                generateProperties());
        final DataUnitServiceModel schema2 = processGenerate(DATA_UNIT_ID_2, ModelConstants.DATA_UNIT_SCHEMA_ID_2,
                generateProperties());
        final DataUnitServiceModel schema3 = processGenerate(DATA_UNIT_ID_3, ModelConstants.DATA_UNIT_SCHEMA_ID_3,
                generateProperties());

        return Arrays.asList(schema1, schema2, schema3);
    }
}
