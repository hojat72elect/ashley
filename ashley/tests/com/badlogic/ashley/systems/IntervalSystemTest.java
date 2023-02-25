package com.badlogic.ashley.systems;

import org.junit.Test;

import com.badlogic.ashley.core.Engine;

import static org.junit.Assert.*;

public class IntervalSystemTest {
    private static final float deltaTime = 0.1f;

    private static class IntervalSystemSpy extends IntervalSystem {
        public int numUpdates;

        public IntervalSystemSpy() {
            super(deltaTime * 2.0f);
        }

        @Override
        protected void updateInterval() {
            ++numUpdates;
        }
    }

    @Test
    public void intervalSystem() {
        Engine engine = new Engine();
        IntervalSystemSpy intervalSystemSpy = new IntervalSystemSpy();

        engine.addSystem(intervalSystemSpy);

        for (int i = 1; i <= 10; ++i) {
            engine.update(deltaTime);
            assertEquals(i / 2, intervalSystemSpy.numUpdates);
        }
    }

    @Test
    public void testGetInterval() {
        IntervalSystemSpy intervalSystemSpy = new IntervalSystemSpy();
        assertEquals(intervalSystemSpy.getInterval(), deltaTime * 2.0f, 0);
    }
}
