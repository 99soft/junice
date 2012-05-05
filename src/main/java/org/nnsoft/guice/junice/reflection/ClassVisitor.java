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
package org.nnsoft.guice.junice.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * <p>
 * Class visitor engine.
 * </p>
 * <p>
 * Visit the input class and all super classes and invokes handler to register annotations.
 * </p>
 */
public final class ClassVisitor
{
    private static final Log logger = LogFactory.getLog( ClassVisitor.class );

    private final Multimap<Class<? extends Annotation>, AnnotationHandler<? extends Annotation, ? extends AnnotatedElement>> handlers =
        ArrayListMultimap.create();

    public <A extends Annotation> void registerHandler( Class<A> annotationType,
                                                        AnnotationHandler<A, ? extends AnnotatedElement> handler )
    {
        this.handlers.put( annotationType, handler );
    }

    public void visit( final Class<?> type )
        throws HandleException
    {
        if ( logger.isDebugEnabled() )
        {
            logger.debug( "  Visit class: " + type );
        }
        if ( Object.class == type )
        {
            return;
        }

        this.handle( type );
        this.handle( type.getDeclaredFields() );
        this.handle( type.getDeclaredMethods() );

        this.visit( type.getSuperclass() );
    }

    @SuppressWarnings( "unchecked" )
    private void handle( AnnotatedElement... elements )
        throws HandleException
    {
        for ( AnnotatedElement element : elements )
        {
            for ( Annotation annotation : element.getAnnotations() )
            {
                for ( AnnotationHandler<? extends Annotation, ? extends AnnotatedElement> handler : this.handlers.get( annotation.annotationType() ) )
                {
                    ( (AnnotationHandler<Annotation, AnnotatedElement>) handler ).handle( annotation, element );
                }
            }
        }
    }

}
