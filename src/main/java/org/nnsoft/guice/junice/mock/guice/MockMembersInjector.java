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

import com.google.inject.MembersInjector;

/**
 * Class to inject via google-guice mock members into test cases classes.
 */
public class MockMembersInjector<T> implements MembersInjector<T> {

    private final Field field;

    private final Map<Field, Object> mockedObjects;

    public MockMembersInjector(Field field, Map<Field, Object> mockedObjects) {
        this.field = field;
        this.field.setAccessible(true);
        this.mockedObjects = mockedObjects;
    }

    /**
     * {@inheritDoc}
     */
    public void injectMembers(T t) {
        try {
            this.field.set(t, this.mockedObjects.get(this.field));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
