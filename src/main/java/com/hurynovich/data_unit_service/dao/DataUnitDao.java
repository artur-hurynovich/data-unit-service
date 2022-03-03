package com.hurynovich.data_unit_service.dao;

import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.paginator.PaginationParams;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitDao {

    Mono<DataUnitPersistentModel> save(@NonNull DataUnitPersistentModel dataUnit);

    Mono<DataUnitPersistentModel> findById(@NonNull String id);

    Mono<List<DataUnitPersistentModel>> findAll(@NonNull DataUnitFilter filter,
                                                @NonNull PaginationParams params);

    Mono<DataUnitPersistentModel> deleteById(@NonNull String id);

    Mono<Long> count(@NonNull DataUnitFilter filter);
}
