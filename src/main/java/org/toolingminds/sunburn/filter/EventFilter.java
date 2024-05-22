package org.toolingminds.sunburn.filter;

public interface EventFilter {
    /**
     * Evaluate the event and return the result.
     * @param event The event to evaluate.
     * @return The result of the evaluation.
     */
    boolean evaluate(String event);
}
