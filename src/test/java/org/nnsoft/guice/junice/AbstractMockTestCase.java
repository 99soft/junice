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
package org.nnsoft.guice.junice;

import org.easymock.classextension.EasyMock;
import org.nnsoft.guice.junice.annotation.Mock;

import org.nnsoft.guice.junice.data.Service;

/**
 * 
 * 
 * @author Marco Speranza
 * @version $Id: AbstractMockTestCase.java 000 2009-12-01 00:00:00Z marco.speranza79 $
 */
abstract public class AbstractMockTestCase {

    
    //Create and inject a Provided EasyMock
    @Mock(providedBy="getMock")
    protected Service providedMock;
    
    public static Service getMock() {
        //Create the mock object and inject the dependency via Google-guice into HelloWorld
        return EasyMock.createNiceMock(Service.class);
    }
    
}
