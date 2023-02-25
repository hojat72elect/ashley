package com.badlogic.ashley.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ComponentTypeTests {

    private static class ComponentA implements Component {

    }

    private static class ComponentB implements Component {

    }

    @Test
    public void validComponentType() {
        assertNotNull(ComponentType.getFor(ComponentA.class));
        assertNotNull(ComponentType.getFor(ComponentB.class));
    }

    @Test
    public void sameComponentType() {
        ComponentType componentType1 = ComponentType.getFor(ComponentA.class);
        ComponentType componentType2 = ComponentType.getFor(ComponentA.class);

        assertEquals(true, componentType1.equals(componentType2));
        assertEquals(true, componentType2.equals(componentType1));
        assertEquals(componentType1.getIndex(), componentType2.getIndex());
        assertEquals(componentType1.getIndex(), ComponentType.getIndexFor(ComponentA.class));
        assertEquals(componentType2.getIndex(), ComponentType.getIndexFor(ComponentA.class));
    }

    @Test
    public void differentComponentType() {
        ComponentType componentType1 = ComponentType.getFor(ComponentA.class);
        ComponentType componentType2 = ComponentType.getFor(ComponentB.class);

        assertEquals(false, componentType1.equals(componentType2));
        assertEquals(false, componentType2.equals(componentType1));
        assertNotEquals(componentType1.getIndex(), componentType2.getIndex());
        assertNotEquals(componentType1.getIndex(), ComponentType.getIndexFor(ComponentB.class));
        assertNotEquals(componentType2.getIndex(), ComponentType.getIndexFor(ComponentA.class));
    }
}
