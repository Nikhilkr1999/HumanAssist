package com.humanassist.scope;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HumanAssistThreadLocal {
    private static final Logger LOGGER  = Logger.getLogger(HumanAssistThreadLocal.class.getCanonicalName());
    private static InheritableThreadLocal<Object> threadLocal = new InheritableThreadLocal<>();

    public static Object setThreadLocal(Object schemaName) {
        LOGGER.log(Level.INFO, "Setting threadLocal for user scope :: " + schemaName);
        Object oldTL = threadLocal.get();
        if(schemaName != null) {
            threadLocal.set(schemaName);
        }
        return oldTL;
    }

    public static Object getThreadLocal(){
        return threadLocal.get();
    }

    public static Object setPublicSchemaToThreadLocal() {
        Object oldTL = threadLocal.get();
        threadLocal.set("public");
        return oldTL;
    }
}
