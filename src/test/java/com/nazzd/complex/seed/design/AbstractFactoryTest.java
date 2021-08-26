package com.nazzd.complex.seed.design;

import com.nazzd.complex.seed.design.abstractfactory.DellComputer;
import com.nazzd.complex.seed.design.abstractfactory.NoteBookComputerFactory;
import org.junit.jupiter.api.Test;

public class AbstractFactoryTest {

    @Test
    public void abstractFactoryTest() {
        NoteBookComputerFactory noteBookComputerFactory = new NoteBookComputerFactory();
        DellComputer dellComputer = noteBookComputerFactory.createDellComputer();
        dellComputer.description();
    }
}
