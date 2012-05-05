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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nnsoft.guice.junice.annotation.MockFramework;
import org.nnsoft.guice.junice.annotation.MockType;
import org.nnsoft.guice.junice.reflection.ClassHandler;
import org.nnsoft.guice.junice.reflection.ClassVisitor;
import org.nnsoft.guice.junice.reflection.HandleException;

/**
 * Handler class to handle all {@link MockFramework} annotations.
 * 
 * @see ClassVisitor
 * @see MockFramework
 */
public final class MockFrameworkHandler
    implements ClassHandler<MockFramework>
{

    final static private Log logger = LogFactory.getLog( MockFrameworkHandler.class );

    private MockType mockType;

    /**
     * @return the mockType
     */
    public MockType getMockType()
    {
        return mockType;
    }

    /**
     * {@inheritDoc}
     */
    public void handle( MockFramework annotation, Class<?> element )
        throws HandleException
    {
        if ( this.mockType != null && this.mockType != annotation.value() )
        {
            throw new HandleException( "Inconsistent mock framework found. " + "Mock framework already set [setted: "
                + mockType + " now found: " + annotation.value() + "]" );
        }

        if ( logger.isDebugEnabled() )
        {
            logger.debug( "  Found MockFramework: " + annotation.value() );
        }

        this.mockType = annotation.value();
    }

}
