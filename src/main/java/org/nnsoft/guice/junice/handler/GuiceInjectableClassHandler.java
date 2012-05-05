/*
 *    Copyright 2010-2012 The 99 Software Foundation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.nnsoft.guice.junice.handler;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.nnsoft.guice.junice.reflection.AnnotationHandler;
import org.nnsoft.guice.junice.reflection.ClassVisitor;
import org.nnsoft.guice.junice.reflection.HandleException;

import com.google.inject.Inject;

/**
 * Handler class to handle all {@link Inject} annotations.
 *
 * @see ClassVisitor
 */
public final class GuiceInjectableClassHandler
    implements AnnotationHandler<Inject, AccessibleObject>
{
    private static final Logger logger = Logger.getLogger( GuiceInjectableClassHandler.class.getName() );

    protected final Set<Class<?>> classes = new HashSet<Class<?>>();

    /**
     * Return all {@link Class} that contains at last one {@link Inject} annotation.
     *
     * @return {@link Class} array.
     */
    public Class<?>[] getClasses()
    {
        return this.classes.toArray( new Class<?>[this.classes.size()] );
    }

    /**
     * {@inheritDoc}
     */
    public void handle( Inject annotation, AccessibleObject element )
        throws HandleException
    {
        Class<?> type = null;

        if ( element instanceof Member )
        {
            type = ( (Member) element ).getDeclaringClass();
        }

        if ( type != null && !this.classes.contains( type ) )
        {
            if ( logger.isLoggable( Level.FINER ) )
            {
                logger.finer( "   Found injectable type: " + type );
            }
            this.classes.add( type );
        }
    }

}
