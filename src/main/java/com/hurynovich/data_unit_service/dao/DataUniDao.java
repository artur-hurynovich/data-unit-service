package com.hurynovich.data_unit_service.dao;

import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.paginator.PaginationParams;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUniDao {

    Mono<DataUnitPersistentModel> save(@NonNull DataUnitPersistentModel dataUnit);

    Mono<DataUnitPersistentModel> findById(@NonNull String id);

    Mono<List<DataUnitPersistentModel>> findAllBySchemaId(@NonNull String schemaId, @NonNull PaginationParams params);

    Mono<DataUnitPersistentModel> deleteById(@NonNull String id);

    Mono<Long> countBySchemaId(@NonNull String schemaId);
}
