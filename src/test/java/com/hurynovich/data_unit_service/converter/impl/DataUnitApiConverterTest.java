package com.hurynovich.data_unit_service.converter.impl;

import com.hurynovich.data_unit_service.converter.ApiConverter;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_service.model_asserter.impl.DataUnitAsserter;
import com.hurynovich.data_unit_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_service.model_generator.impl.DataUnitApiModelGenerator;
import com.hurynovich.data_unit_service.model_generator.impl.DataUnitServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataUnitApiConverterTest {

    private final ApiConverter<DataUnitApiModel, DataUnitServiceModel> converter =
            new DataUnitApiConverter();

    private final ModelGenerator<DataUnitApiModel> apiModelGenerator =
            new DataUnitApiModelGenerator();

    private final ModelGenerator<DataUnitServiceModel> serviceModelGenerator =
            new DataUnitServiceModelGenerator();

    private final ModelAsserter<DataUnitApiModel, DataUnitServiceModel, DataUnitPersistentModel> asserter =
            new DataUnitAsserter();

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitServiceModel) null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitApiModel apiModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, apiModel);
    }

    @Test
    void convertApiModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitApiModel) null));
    }

    @Test
    void convertApiModelNotNullTest() {
        final DataUnitApiModel apiModel = apiModelGenerator.generate();
        final DataUnitServiceModel serviceModel = converter.convert(apiModel);
        asserter.assertEquals(apiModel, serviceModel);
    }
}
