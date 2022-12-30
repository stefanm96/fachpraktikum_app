package de.fuh.michel.fachpraktikum_wi2022.model;

import java.io.Serializable;

import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitable;

public interface Definition extends Visitable, Serializable {
    String getDefinitionType();
    String getName();
}
