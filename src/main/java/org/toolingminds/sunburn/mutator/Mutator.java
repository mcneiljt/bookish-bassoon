package org.toolingminds.sunburn.mutator;

public interface Mutator {
    /**
     * Mutate the event and return the result.
     * @param event The event to mutate.
     * @return The result of the mutation.
     */
    String mutate(String event);
}
