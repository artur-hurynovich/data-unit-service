package com.hurynovich.data_unit_service.dao.filter.impl;

import com.hurynovich.data_unit_service.dao.filter.DataUnitQueryCriteriaBuilder;
import com.hurynovich.data_unit_service.dao.filter.exception.DataUnitQueryCriteriaBuilderException;
import com.hurynovich.data_unit_service.dao.filter.model.CriteriaComparison;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitPropertyCriteria;
import com.hurynovich.data_unit_service.model.impl.DataUnitPropertyServiceModelImpl_;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl_;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class DataUnitQueryCriteriaBuilderImpl implements DataUnitQueryCriteriaBuilder {

    private final Map<CriteriaComparison, BiConsumer<Criteria, DataUnitPropertyCriteria>> valueCriteriaAppliersByComparison;

    public DataUnitQueryCriteriaBuilderImpl(
            @NonNull final Map<CriteriaComparison, BiConsumer<Criteria, DataUnitPropertyCriteria>> valueCriteriaAppliersByComparison) {
        this.valueCriteriaAppliersByComparison = valueCriteriaAppliersByComparison;
    }

    @Override
    public Criteria build(@NonNull final DataUnitFilter filter) {
        final Criteria resultCriteria = buildSchemaIdCriteria(filter);
        final Criteria[] propertyCriteria = filter.criteria().stream().
                map(this::buildPropertyCriteria).
                toArray(Criteria[]::new);
        if (propertyCriteria.length > 0) {
            resultCriteria.andOperator(propertyCriteria);
        }

        return resultCriteria;
    }

    private Criteria buildSchemaIdCriteria(final @NonNull DataUnitFilter filter) {
        return Criteria.where(DataUnitServiceModelImpl_.SCHEMA_ID).is(filter.schemaId());
    }

    private Criteria buildPropertyCriteria(final @NonNull DataUnitPropertyCriteria filterCriteria) {
        final Criteria elemMatchCriteria = Criteria
                .where(DataUnitPropertyServiceModelImpl_.SCHEMA_ID)
                .is(filterCriteria.propertySchemaId());
        applyValueCriteria(elemMatchCriteria, filterCriteria);

        return Criteria
                .where(DataUnitServiceModelImpl_.PROPERTIES)
                .elemMatch(elemMatchCriteria);
    }

    private void applyValueCriteria(final @NonNull Criteria elemMatchCriteria,
                                    final @NonNull DataUnitPropertyCriteria filterCriteria) {
        final CriteriaComparison comparison = filterCriteria.comparison();
        final BiConsumer<Criteria, DataUnitPropertyCriteria> applier = valueCriteriaAppliersByComparison
                .get(comparison);
        if (applier != null) {
            applier.accept(elemMatchCriteria.and(DataUnitPropertyServiceModelImpl_.VALUE), filterCriteria);
        } else {
            throw new DataUnitQueryCriteriaBuilderException("Value criteria applier for comparison = '" +
                    comparison + "' not found");
        }
    }
}
