package com.hurynovich.data_unit_service.model_asserter.impl;

import com.hurynovich.data_unit_service.model.impl.DataUnitApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitDocument_;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_service.utils.MassProcessingUtils;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Set;

public class DataUnitAsserter implements ModelAsserter<DataUnitApiModel, DataUnitServiceModel, DataUnitPersistentModel> {

    @Override
    public void assertEquals(final DataUnitPersistentModel expected,
                             final DataUnitPersistentModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitServiceModel expected,
                             final DataUnitServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitApiModel expected,
                             final DataUnitApiModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitApiModel expected,
                             final DataUnitServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitServiceModel expected,
                             final DataUnitApiModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitServiceModel expected,
                             final DataUnitPersistentModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPersistentModel expected,
                             final DataUnitServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitWrapper.of(expected), DataUnitWrapper.of(actual), ignoreProperties);
    }

    private void processAssertEquals(final DataUnitWrapper expected,
                                     final DataUnitWrapper actual,
                                     final String... ignoreProperties) {
        final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
        if (!ignorePropertiesSet.contains(DataUnitDocument_.ID)) {
            Assertions.assertEquals(expected.id(), actual.id());
        }

        if (!ignorePropertiesSet.contains(DataUnitDocument_.SCHEMA_ID)) {
            Assertions.assertEquals(expected.schemaId, actual.schemaId());
        }

        if (!ignorePropertiesSet.contains(DataUnitDocument_.PROPERTIES)) {
            final List<DataUnitPropertyWrapper> expectedProperties = expected.properties();
            final List<DataUnitPropertyWrapper> actualProperties = actual.properties();

            Assertions.assertEquals(expectedProperties.size(), actualProperties.size());
            for (int i = 0; i < expectedProperties.size(); i++) {
                final DataUnitPropertyWrapper expectedProperty = expectedProperties.get(i);
                final DataUnitPropertyWrapper actualProperty = actualProperties.get(i);

                Assertions.assertEquals(expectedProperty.schemaId(), actualProperty.schemaId());
                Assertions.assertEquals(expectedProperty.value(), actualProperty.value());
            }
        }
    }

    private record DataUnitWrapper(String id, String schemaId, List<DataUnitPropertyWrapper> properties) {

        public static DataUnitWrapper of(final DataUnitApiModel dataUnit) {
            return new DataUnitWrapper(dataUnit.getId(), dataUnit.getSchemaId(),
                    MassProcessingUtils.processQuietly(dataUnit.getProperties(), DataUnitPropertyWrapper::of));
        }

        public static DataUnitWrapper of(final DataUnitServiceModel dataUnit) {
            return new DataUnitWrapper(dataUnit.getId(), dataUnit.getSchemaId(),
                    MassProcessingUtils.processQuietly(dataUnit.getProperties(), DataUnitPropertyWrapper::of));
        }

        public static DataUnitWrapper of(final DataUnitPersistentModel dataUnit) {
            return new DataUnitWrapper(dataUnit.getId(), dataUnit.getSchemaId(),
                    MassProcessingUtils.processQuietly(dataUnit.getProperties(), DataUnitPropertyWrapper::of));
        }
    }

    private record DataUnitPropertyWrapper(String schemaId, Object value) {

        public static DataUnitPropertyWrapper of(final DataUnitPropertyApiModel dataUnitProperty) {
            return new DataUnitPropertyWrapper(dataUnitProperty.getSchemaId(), dataUnitProperty.getValue());
        }

        public static DataUnitPropertyWrapper of(final DataUnitPropertyServiceModel dataUnitProperty) {
            return new DataUnitPropertyWrapper(dataUnitProperty.getSchemaId(), dataUnitProperty.getValue());
        }

        public static DataUnitPropertyWrapper of(final DataUnitPropertyPersistentModel dataUnitProperty) {
            return new DataUnitPropertyWrapper(dataUnitProperty.getSchemaId(), dataUnitProperty.getValue());
        }
    }
}
