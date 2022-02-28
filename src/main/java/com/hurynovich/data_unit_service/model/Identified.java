package com.hurynovich.data_unit_service.model;

import java.io.Serializable;

public interface Identified<T extends Serializable> {

    T getId();
}
