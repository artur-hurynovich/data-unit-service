package com.hurynovich.data_unit_service.model_generator;

import com.hurynovich.data_unit_service.model.Identified;

import java.util.List;

public interface ModelGenerator<T extends Identified<?>> {

    T generateWithNullId();

    T generate();

    List<T> generateListWithNullIds();

    List<T> generateList();
}
