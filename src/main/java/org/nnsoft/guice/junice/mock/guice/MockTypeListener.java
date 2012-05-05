/*
 *    Copyright 2012 The 99 Software Foundation
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
package org.nnsoft.guice.junice.mock.guice;

import java.lang.reflect.Field;
import java.util.Map;

import org.nnsoft.guice.junice.annotation.Mock;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * <p>{@link TypeListener} implementation.</p>
 *
 * <p>Creates a specific {@link MockMembersInjector} for each {@link Mock}
 * annotation found.</p>
 *
 * @see MockMembersInjector
 * @see Mock
 * @author Marco Speranza
 * @version $Id: MockTypeListener.java 000 2009-12-01 00:00:00Z marco.speranza79 $
 */
public class MockTypeListener implements TypeListener {

    private static final String JAVA_PACKAGE = "java";

    final private Map<Field, Object> mockedObjects;

    public MockTypeListener(Map<Field, Object> mockedObjects) {
        this.mockedObjects = mockedObjects;
    }

    /**
     * {@inheritDoc}
     */
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        hear(typeLiteral.getRawType(), typeEncounter);
    }

    private <I> void hear(Class<? super I> type, TypeEncounter<I> typeEncounter) {
        if (type.getPackage() != null && type.getPackage().getName().startsWith(JAVA_PACKAGE)) {
            return;
        }

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Mock.class)) {
                typeEncounter.register(new MockMembersInjector<I>(field, mockedObjects));
            }
        }

        //visit super-class
        hear(type.getSuperclass(), typeEncounter);
    }

}
