package com.hurynovich.data_unit_service.service.impl;

import com.hurynovich.data_unit_service.converter.impl.DataUnitServiceConverter;
import com.hurynovich.data_unit_service.dao.DataUnitDao;
import com.hurynovich.data_unit_service.dao.filter.model.DataUnitFilter;
import com.hurynovich.data_unit_service.model.impl.DataUnitApiModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitPersistentModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModel;
import com.hurynovich.data_unit_service.model.impl.DataUnitServiceModelImpl_;
import com.hurynovich.data_unit_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_service.model_asserter.impl.DataUnitAsserter;
import com.hurynovich.data_unit_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_service.model_generator.impl.DataUnitPersistentModelGenerator;
import com.hurynovich.data_unit_service.model_generator.impl.DataUnitServiceModelGenerator;
import com.hurynovich.data_unit_service.service.DataUnitService;
import com.hurynovich.paginator.PaginationParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hurynovich.data_unit_service.model_generator.ModelConstants.DATA_UNIT_ID_1;

@ExtendWith(MockitoExtension.class)
public class DataUnitServiceImplTest {

    @Mock
    private DataUnitDao dao;

    @Mock
    private DataUnitServiceConverter converter;

    @Mock
    private PaginationParams params;

    @Mock
    private DataUnitFilter filter;

    private DataUnitService service;

    private final ModelGenerator<DataUnitServiceModel> serviceModelGenerator =
            new DataUnitServiceModelGenerator();

    private final ModelGenerator<DataUnitPersistentModel> persistentModelGenerator =
            new DataUnitPersistentModelGenerator();

    private final ModelAsserter<DataUnitApiModel, DataUnitServiceModel, DataUnitPersistentModel> asserter =
            new DataUnitAsserter();

    @BeforeEach
    public void initService() {
        service = new DataUnitServiceImpl(dao, converter);
    }

    @Test
    void saveTest() {
        final DataUnitServiceModel serviceModel = serviceModelGenerator.generateWithNullId();
        final DataUnitPersistentModel persistentModel = persistentModelGenerator.generate();
        Mockito.when(converter.convert(serviceModel)).thenReturn(persistentModel);
        Mockito.when(dao.save(persistentModel)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModelGenerator.generate());

        StepVerifier
                .create(service.save(serviceModel))
                .assertNext(savedDatUnit -> {
                    asserter.assertEquals(serviceModel, savedDatUnit, DataUnitServiceModelImpl_.ID);
                    Assertions.assertNotNull(savedDatUnit.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findByIdTest() {
        final DataUnitPersistentModel persistentModel = persistentModelGenerator.generate();
        final String id = persistentModel.getId();
        Mockito.when(dao.findById(id)).thenReturn(Mono.just(persistentModel));

        final DataUnitServiceModel serviceModel = serviceModelGenerator.generate();
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModel);

        StepVerifier
                .create(service.findById(id))
                .assertNext(dataUnit -> asserter.assertEquals(serviceModel, dataUnit))
                .expectComplete()
                .verify();
    }

    @Test
    void findByIdEmptyTest() {
        Mockito.when(dao.findById(DATA_UNIT_ID_1)).thenReturn(Mono.justOrEmpty(Optional.empty()));
        StepVerifier
                .create(service.findById(DATA_UNIT_ID_1))
                .expectComplete()
                .verify();
    }

    @Test
    void findAllTest() {
        final List<DataUnitPersistentModel> persistentModels = persistentModelGenerator.generateList();
        Mockito.when(dao.findAll(filter, params)).thenReturn(Mono.just(persistentModels));

        final List<DataUnitServiceModel> serviceModels = serviceModelGenerator.generateList();
        for (int i = 0; i < persistentModels.size(); i++) {
            final DataUnitServiceModel serviceModel = serviceModels.get(i);
            Mockito.when(converter.convert(persistentModels.get(i))).thenReturn(serviceModel);
        }

        StepVerifier
                .create(service.findAll(filter, params))
                .assertNext(dataUnits -> {
                    Assertions.assertNotNull(dataUnits);
                    Assertions.assertFalse(dataUnits.isEmpty());
                    Assertions.assertEquals(serviceModels.size(), dataUnits.size());
                    for (int i = 0; i < serviceModels.size(); i++) {
                        asserter.assertEquals(serviceModels.get(i), dataUnits.get(i));
                    }
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findAllEmptyTest() {
        Mockito.when(dao.findAll(filter, params)).thenReturn(Mono.just(new ArrayList<>()));

        StepVerifier
                .create(service.findAll(filter, params))
                .assertNext(dataUnits -> {
                    Assertions.assertNotNull(dataUnits);
                    Assertions.assertTrue(dataUnits.isEmpty());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void deleteTest() {
        final DataUnitPersistentModel persistentModel = persistentModelGenerator.generate();
        final String id = persistentModel.getId();
        Mockito.when(dao.deleteById(id)).thenReturn(Mono.just(persistentModel));

        final DataUnitServiceModel serviceModel = serviceModelGenerator.generate();
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModel);

        StepVerifier
                .create(service.deleteById(id))
                .assertNext(deletedDataUnit -> {
                    Assertions.assertNotNull(deletedDataUnit);

                    asserter.assertEquals(serviceModelGenerator.generate(), deletedDataUnit);
                })
                .expectComplete()
                .verify();
    }

    @Test
    void countBySchemaIdTest() {
        final List<DataUnitPersistentModel> persistentModels = persistentModelGenerator.generateList();
        final long count = persistentModels.size();
        Mockito.when(dao.count(filter)).thenReturn(Mono.just(count));

        StepVerifier
                .create(service.count(filter))
                .assertNext(dataUnitsCount -> Assertions.assertEquals(count, dataUnitsCount))
                .expectComplete()
                .verify();
    }
}
