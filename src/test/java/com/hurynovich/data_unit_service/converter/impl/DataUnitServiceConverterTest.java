package com.hurynovich.data_unit_service.converter.impl;

import com.hurynovich.data_unit_service.converter.ServiceConverter;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_service.model_asserter.impl.DataUnitAsserter;
import com.hurynovich.data_unit_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_service.model_generator.impl.DataUnitPersistentModelGenerator;
import com.hurynovich.data_unit_service.model_generator.impl.DataUnitServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataUnitServiceConverterTest {

    private final ServiceConverter<DataUnitServiceModel, DataUnitPersistentModel> converter =
            new DataUnitServiceConverter();

    private final ModelGenerator<DataUnitServiceModel> serviceModelGenerator =
            new DataUnitServiceModelGenerator();

    private final ModelGenerator<DataUnitPersistentModel> persistentModelGenerator =
            new DataUnitPersistentModelGenerator();

    private final ModelAsserter<DataUnitApiModel, DataUnitServiceModel, DataUnitPersistentModel> asserter =
            new DataUnitAsserter();

    @Test
    void convertPersistentModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitPersistentModel) null));
    }

    @Test
    void convertPersistentModelNotNullTest() {
        final DataUnitPersistentModel persistentModel = persistentModelGenerator.generate();
        final DataUnitServiceModel serviceModel = converter.convert(persistentModel);
        asserter.assertEquals(persistentModel, serviceModel);
    }

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitServiceModel) null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitPersistentModel persistentModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, persistentModel);
    }
}
