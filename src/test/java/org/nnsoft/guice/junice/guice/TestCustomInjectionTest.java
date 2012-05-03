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
package org.nnsoft.guice.junice.guice;

import org.easymock.classextension.EasyMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nnsoft.guice.junice.JUniceRunner;
import org.nnsoft.guice.junice.annotation.GuiceModules;
import org.nnsoft.guice.junice.annotation.Mock;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.nnsoft.guice.junice.data.HelloWorld;
import org.nnsoft.guice.junice.data.Service;
import org.nnsoft.guice.junice.data.ServiceModule;
import org.nnsoft.guice.junice.data.TelephonService;

/**
 * 
 * 
 * @author Marco Speranza
 * @version $Id: TestCustomInjectionTest.java 000 2009-12-01 00:00:00Z marco.speranza79 $
 */
@RunWith(JUniceRunner.class)
@GuiceModules(modules=ServiceModule.class)
public class TestCustomInjectionTest {

    @Mock
    private static Service service;
    
    @Inject
    private TelephonService telephonService;

    @Inject
    private HelloWorld helloWorld;
    
    @BeforeClass
    public static void setUp() {
        Assert.assertNotNull(service);
        //service.go();
    }

    @Test
    public void test() throws Exception {
        Assert.assertNotNull(service);
        Assert.assertNotNull(telephonService);
        Assert.assertNotNull(helloWorld);
    }
    
    @Test
    public void testOverideModule() throws Exception {
        Assert.assertNotNull(service);
        Assert.assertNotNull(telephonService);
        Assert.assertEquals("It's real class", telephonService.getTelephonNumber());
        
        
        EasyMock.expect(service.go()).andReturn("Mocked injected class");
        EasyMock.replay(service);
        
        Assert.assertEquals("Mocked injected class", helloWorld.sayHalloByService());
        EasyMock.verify(service);
    }
}
