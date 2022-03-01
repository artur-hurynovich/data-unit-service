package com.hurynovich.data_unit_service.converter.impl;

import com.hurynovich.data_unit_service.converter.ServiceConverter;
import com.hurynovich.data_unit_service.model.impl.DataUnitDocument;
import com.hurynovich.data_unit_service.model.impl.DataUnitDocument.DataUnitPropertyDocument;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl.DataUnitPropertyServiceModelImpl;
import com.hurynovich.data_unit_service.utils.MassProcessingUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataUnitServiceConverter implements ServiceConverter<DataUnitServiceModel, DataUnitPersistentModel> {

    @Override
    public DataUnitPersistentModel convert(@Nullable final DataUnitServiceModel source) {
        final DataUnitDocument target;
        if (source != null) {
            target = new DataUnitDocument();
            target.setId(source.getId());
            target.setSchemaId(source.getSchemaId());
            target.setProperties(MassProcessingUtils.processQuietly(source.getProperties(),
                    this::convertProperty));
        } else {
            target = null;
        }

        return target;
    }

    private DataUnitPropertyPersistentModel convertProperty(
            @Nullable final DataUnitPropertyServiceModel source) {
        final DataUnitPropertyDocument target;
        if (source != null) {
            target = new DataUnitPropertyDocument();
            target.setSchemaId(source.getSchemaId());
            target.setValue(source.getValue());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitServiceModel convert(@Nullable final DataUnitPersistentModel source) {
        final DataUnitServiceModelImpl target;
        if (source != null) {
            target = new DataUnitServiceModelImpl(source.getId(), source.getSchemaId(),
                    MassProcessingUtils.processQuietly(source.getProperties(),
                            this::convertProperty));
        } else {
            target = null;
        }

        return target;
    }

    private DataUnitPropertyServiceModel convertProperty(
            @Nullable final DataUnitPropertyPersistentModel source) {
        final DataUnitPropertyServiceModelImpl target;
        if (source != null) {
            target = new DataUnitPropertyServiceModelImpl(source.getSchemaId(), source.getValue());
        } else {
            target = null;
        }

        return target;
    }
}
