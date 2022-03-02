package com.hurynovich.data_unit_service.dao.impl;

import com.hurynovich.data_unit_service.dao.DataUniDao;
import com.hurynovich.data_unit_service.dao.filter.DataUnitQueryCriteriaBuilder;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.model.impl.DataUnitDocument;
import com.hurynovich.data_unit_service.model.impl.DataUnitDocument_;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.paginator.PaginationParams;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataUnitMongoDbDao implements DataUniDao {

    private final ReactiveMongoTemplate template;

    private final DataUnitQueryCriteriaBuilder criteriaBuilder;

    public DataUnitMongoDbDao(@NonNull final ReactiveMongoTemplate template,
                              @NonNull final DataUnitQueryCriteriaBuilder criteriaBuilder) {
        this.template = template;
        this.criteriaBuilder = criteriaBuilder;
    }

    @Override
    public Mono<DataUnitPersistentModel> save(@NonNull final DataUnitPersistentModel dataUnit) {
        return template.save(dataUnit);
    }

    @Override
    public Mono<DataUnitPersistentModel> findById(@NonNull final String id) {
        return template
                .findById(id, DataUnitDocument.class)
                .flatMap(t -> Mono.justOrEmpty((DataUnitPersistentModel) t));
    }

    @Override
    public Mono<List<DataUnitPersistentModel>> findAllBySchemaId(@NonNull final String schemaId,
                                                                 @NonNull final DataUnitFilter filter,
                                                                 @NonNull final PaginationParams params) {
        final Query query = new Query()
                .addCriteria(criteriaBuilder.build(filter))
                .skip(params.getOffset())
                .limit(params.getLimit());

        return template
                .find(query, DataUnitDocument.class)
                .collectList()
                .flatMap(schemas -> Mono.justOrEmpty(
                        schemas.stream()
                                .map(schema -> (DataUnitPersistentModel) schema)
                                .collect(Collectors.toList())));
    }

    @Override
    public Mono<DataUnitPersistentModel> deleteById(@NonNull final String id) {
        final Query query = new Query()
                .addCriteria(Criteria.where(DataUnitDocument_.ID).is(id));

        return template.findAndRemove(query, DataUnitDocument.class)
                .flatMap(t -> Mono.justOrEmpty((DataUnitPersistentModel) t));
    }

    @Override
    public Mono<Long> countBySchemaId(@NonNull final String schemaId) {
        final Query query = new Query()
                .addCriteria(Criteria.where(DataUnitDocument_.SCHEMA_ID).is(schemaId));

        return template.count(query, DataUnitDocument.class);
    }
}
