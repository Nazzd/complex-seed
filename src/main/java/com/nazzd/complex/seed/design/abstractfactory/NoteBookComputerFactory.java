package com.nazzd.complex.seed.design.abstractfactory;

public class NoteBookComputerFactory implements ComputerFactory {

    @Override
    public HPComputer createHPComputer() {
        return new HPNoteBookComputer();
    }

    @Override
    public DellComputer createDellComputer() {
        return new DellNoteBookComputer();
    }
}
