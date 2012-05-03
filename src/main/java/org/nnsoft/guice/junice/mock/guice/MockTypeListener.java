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

    final private Map<Field, Object> mockedObjects;

    public MockTypeListener(Map<Field, Object> mockedObjects) {
        this.mockedObjects = mockedObjects;
    }

    @SuppressWarnings("unchecked")
    public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
        if ( typeLiteral.getRawType() == Object.class) {
            return;
        }

        for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
            if (field.isAnnotationPresent(Mock.class)) {
                typeEncounter.register(
                        new MockMembersInjector<T>(field, this.mockedObjects));
            }
        }

        //visit super-class
        this.hear((TypeLiteral<T>) TypeLiteral.get(typeLiteral.getRawType().getSuperclass()), typeEncounter);
    }

}
