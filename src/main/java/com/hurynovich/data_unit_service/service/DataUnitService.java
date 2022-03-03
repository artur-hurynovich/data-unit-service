package com.hurynovich.data_unit_service.service;

import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.paginator.PaginationParams;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitService {

    Mono<DataUnitServiceModel> save(@NonNull DataUnitServiceModel dataUnit);

    Mono<DataUnitServiceModel> findById(@NonNull String id);

    Mono<List<DataUnitServiceModel>> findAll(@NonNull DataUnitFilter filter,
                                             @NonNull PaginationParams params);

    Mono<DataUnitServiceModel> deleteById(@NonNull String id);

    Mono<Long> count(@NonNull DataUnitFilter filter);
}
