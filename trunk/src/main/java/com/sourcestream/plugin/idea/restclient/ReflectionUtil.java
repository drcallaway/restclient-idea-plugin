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
     * @param args Method arguments
     */
    public static void invokeMethod(Object obj, String methodName, Object... args)
    {
        try
        {
            Class[] arglist = null;
            if (args != null)
            {
                arglist = new Class[args.length];

                for (int i = 0; i < arglist.length; i++)
                {
                    arglist[i] = args[i].getClass();
                }
            }

            Method m = obj.getClass().getDeclaredMethod(methodName, arglist);
            AccessibleObject[] ao = {m};
            AccessibleObject.setAccessible(ao, true);
            m.invoke(obj, args);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
