package com.sourcestream.plugin.idea.restclient;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * Reflection utility methods.
 */
public class ReflectionUtil
{
    /**
     * Invokes a method (even private methods).
     *
     * @param obj Object on which to invoke a method
     * @param methodName Method to invoke
     */
    public static void invokeMethod(Object obj, String methodName)
    {
        try
        {
            Method m = obj.getClass().getDeclaredMethod(methodName);
            AccessibleObject[] ao = {m};
            AccessibleObject.setAccessible(ao, true);
            m.invoke(obj);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
