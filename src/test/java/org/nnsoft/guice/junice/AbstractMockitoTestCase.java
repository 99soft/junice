package org.nnsoft.guice.junice;

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

import org.mockito.Mockito;
import org.nnsoft.guice.junice.annotation.Mock;
import org.nnsoft.guice.junice.annotation.MockFramework;
import org.nnsoft.guice.junice.annotation.MockType;
import org.nnsoft.guice.junice.data.Service;

@MockFramework( MockType.MOCKITO )
abstract public class AbstractMockitoTestCase
    extends AbstractEmptyTestCase
{

    // Create and inject a Provided EasyMock
    @Mock( providedBy = "getMock" )
    protected Service providedMock;

    // @MockProvider
    public static Service getMock()
    {
        // Create the mock object and inject the dependency via Google-guice into HelloWorld
        return Mockito.mock( Service.class );
    }

}
