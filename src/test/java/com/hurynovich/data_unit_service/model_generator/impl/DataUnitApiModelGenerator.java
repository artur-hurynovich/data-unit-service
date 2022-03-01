package com.hurynovich.data_unit_service.model_generator.impl;

import com.hurynovich.data_unit_service.model.impl.DataUnitApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModelImpl.DataUnitPropertyApiModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyApiModel;
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
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_3;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_VALUE;

public class DataUnitApiModelGenerator implements ModelGenerator<DataUnitApiModel> {

    @Override
    public DataUnitApiModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_ID_1, generateProperties());
    }

    private DataUnitApiModel processGenerate(final String id, final String schemaId,
                                             final List<DataUnitPropertyApiModel> properties) {
        return new DataUnitApiModelImpl(id, schemaId, properties);
    }

    private List<DataUnitPropertyApiModel> generateProperties() {
        return List.of(processGenerateProperty(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_VALUE),
                processGenerateProperty(DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID, DATA_UNIT_INTEGER_PROPERTY_VALUE),
                processGenerateProperty(DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID, DATA_UNIT_FLOAT_PROPERTY_VALUE));
    }

    private DataUnitPropertyApiModel processGenerateProperty(final String schemaId, final Object value) {
        return new DataUnitPropertyApiModelImpl(schemaId, value);
    }

    @Override
    public DataUnitApiModel generate() {
        return processGenerate(DATA_UNIT_ID_1, DATA_UNIT_SCHEMA_ID_1, generateProperties());
    }

    @Override
    public List<DataUnitApiModel> generateListWithNullIds() {
        final DataUnitApiModel schema1 = processGenerate(null, DATA_UNIT_SCHEMA_ID_1, generateProperties());
        final DataUnitApiModel schema2 = processGenerate(null, DATA_UNIT_SCHEMA_ID_2, generateProperties());
        final DataUnitApiModel schema3 = processGenerate(null, DATA_UNIT_SCHEMA_ID_3, generateProperties());

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitApiModel> generateList() {
        final DataUnitApiModel schema1 = processGenerate(DATA_UNIT_ID_1, DATA_UNIT_SCHEMA_ID_1, generateProperties());
        final DataUnitApiModel schema2 = processGenerate(DATA_UNIT_ID_2, DATA_UNIT_SCHEMA_ID_2, generateProperties());
        final DataUnitApiModel schema3 = processGenerate(DATA_UNIT_ID_3, DATA_UNIT_SCHEMA_ID_3, generateProperties());

        return Arrays.asList(schema1, schema2, schema3);
    }
}
