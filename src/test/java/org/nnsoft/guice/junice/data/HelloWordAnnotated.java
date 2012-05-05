/*
 *    Copyright 2009 The iBaGuice Team
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
package org.nnsoft.guice.junice.data;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class HelloWordAnnotated {

    @Inject
    @TestAnnotation
    Service service;

    @Inject
    @Named("test.named")
    Service named;

    public String go() {
        return service.go();
    }

    public String getNamed() {
        return named.go();
    }

}
