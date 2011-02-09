/**
 * The MIT License
 *
 * Copyright (c) 2011 by Dustin R. Callaway
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
