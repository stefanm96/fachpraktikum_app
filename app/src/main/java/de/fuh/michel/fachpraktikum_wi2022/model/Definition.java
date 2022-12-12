package de.fuh.michel.fachpraktikum_wi2022.model;

import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitable;

public interface Definition extends Visitable {
    String getDefinitionType();
    String getName();
}
