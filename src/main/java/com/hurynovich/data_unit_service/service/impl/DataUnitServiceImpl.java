package com.hurynovich.data_unit_service.service.impl;

import com.hurynovich.data_unit_service.converter.impl.DataUnitServiceConverter;
import com.hurynovich.data_unit_service.dao.DataUniDao;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.service.DataUnitService;
import com.hurynovich.data_unit_service.utils.MassProcessingUtils;
import com.hurynovich.paginator.PaginationParams;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DataUnitServiceImpl implements DataUnitService {

    private final DataUniDao dao;

    private final DataUnitServiceConverter converter;

    public DataUnitServiceImpl(@NonNull final DataUniDao dao, @NonNull final DataUnitServiceConverter converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public Mono<DataUnitServiceModel> save(@NonNull final DataUnitServiceModel dataUnit) {
        return dao
                .save(converter.convert(dataUnit))
                .flatMap(unit -> Mono.just(converter.convert(unit)));
    }

    @Override
    public Mono<DataUnitServiceModel> findById(@NonNull final String id) {
        return dao.findById(id)
                .flatMap(schema -> Mono.justOrEmpty(converter.convert(schema)));
    }

    @Override
    public Mono<List<DataUnitServiceModel>> findAllBySchemaId(@NonNull final String schemaId,
                                                              @NonNull final DataUnitFilter filter,
                                                              @NonNull final PaginationParams params) {
        return dao.findAllBySchemaId(schemaId, filter, params)
                .flatMap(schemas -> Mono.just(MassProcessingUtils.processQuietly(schemas, converter::convert)));
    }

    @Override
    public Mono<DataUnitServiceModel> deleteById(@NonNull final String id) {
        return dao.deleteById(id)
                .flatMap(schema -> Mono.justOrEmpty(converter.convert(schema)));
    }

    @Override
    public Mono<Long> countBySchemaId(@NonNull final String schemaId) {
        return dao.countBySchemaId(schemaId);
    }
}
