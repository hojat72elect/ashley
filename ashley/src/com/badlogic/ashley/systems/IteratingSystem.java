package com.badlogic.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * A simple EntitySystem that iterates over each entity and calls processEntity() for each entity every time the EntitySystem is
 * updated. This is really just a convenience class as most systems iterate over a list of entities.
 *
 * @author Stefan Bachmann
 */
public abstract class IteratingSystem extends EntitySystem {
    private Family family;
    private ImmutableArray<Entity> entities;

    /**
     * Instantiates a system that will iterate over the entities described by the Family.
     *
     * @param family The family of entities iterated over in this System
     */
    public IteratingSystem(Family family) {
        this(family, 0);
    }

    /**
     * Instantiates a system that will iterate over the entities described by the Family, with a specific priority.
     *
     * @param family   The family of entities iterated over in this System
     * @param priority The priority to execute this system with (lower means higher priority)
     */
    public IteratingSystem(Family family, int priority) {
        super(priority);

        this.family = family;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = null;
    }

    @Override
    public void update(float deltaTime) {
        startProcessing();
        for (int i = 0; i < entities.size(); ++i) {
            processEntity(entities.get(i), deltaTime);
        }
        endProcessing();
    }

    /**
     * @return set of entities processed by the system
     */
    public ImmutableArray<Entity> getEntities() {
        return entities;
    }

    /**
     * @return the Family used when the system was created
     */
    public Family getFamily() {
        return family;
    }

    /**
     * This method is called on every entity on every update call of the EntitySystem. Override this to implement your system's
     * specific processing.
     *
     * @param entity    The current Entity being processed
     * @param deltaTime The delta time between the last and current frame
     */
    protected abstract void processEntity(Entity entity, float deltaTime);

    /**
     * This method is called once on every update call of the EntitySystem, before entity processing begins. Override this method to
     * implement your specific startup conditions.
     */
    public void startProcessing() {
    }

    /**
     * This method is called once on every update call of the EntitySystem after entity processing is complete. Override this method to
     * implement your specific end conditions.
     */
    public void endProcessing() {
    }
}
