package org.toolingminds.sunburn.emitter;

public abstract class Emitter {
    static Emitter emitter;

    public static Emitter getOutput() {
        if(emitter == null) {
            String backendClass;
            if(System.getenv("EMITTER") == null)
                throw new RuntimeException("EMITTER environment variable is null.");
            else
                backendClass = System.getenv("EMITTER");
            try {
                Class clazz = Class.forName(backendClass);
                emitter = (Emitter) clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return emitter;
    }

   abstract public void send(String eventName, String eventBody);
}
