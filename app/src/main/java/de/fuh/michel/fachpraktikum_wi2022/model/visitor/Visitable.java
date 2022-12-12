package de.fuh.michel.fachpraktikum_wi2022.model.visitor;

public interface Visitable {
    void accept(Visitor visitor);
}
