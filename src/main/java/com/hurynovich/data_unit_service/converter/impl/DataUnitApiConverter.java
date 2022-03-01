package com.hurynovich.data_unit_service.converter.impl;

import com.hurynovich.data_unit_service.converter.ApiConverter;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModelImpl.DataUnitPropertyApiModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl.DataUnitPropertyServiceModelImpl;
import com.hurynovich.data_unit_service.utils.MassProcessingUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataUnitApiConverter implements ApiConverter<DataUnitApiModel, DataUnitServiceModel> {

    @Override
    public DataUnitServiceModel convert(@Nullable final DataUnitApiModel source) {
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
            @Nullable final DataUnitPropertyApiModel source) {
        final DataUnitPropertyServiceModelImpl target;
        if (source != null) {
            target = new DataUnitPropertyServiceModelImpl(source.getSchemaId(), source.getValue());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitApiModel convert(@Nullable final DataUnitServiceModel source) {
        final DataUnitApiModelImpl target;
        if (source != null) {
            target = new DataUnitApiModelImpl(source.getId(), source.getSchemaId(),
                    MassProcessingUtils.processQuietly(source.getProperties(),
                            this::convertProperty));
        } else {
            target = null;
        }

        return target;
    }

    private DataUnitPropertyApiModel convertProperty(
            @Nullable final DataUnitPropertyServiceModel source) {
        final DataUnitPropertyApiModelImpl target;
        if (source != null) {
            target = new DataUnitPropertyApiModelImpl(source.getSchemaId(), source.getValue());
        } else {
            target = null;
        }

        return target;
    }
}
