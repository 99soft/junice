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

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

@RunWith( JUniceRunner.class )
public class InjectJSR330ModuleClassTestCase
    extends AbstractModule
{

    public void configure()
    {
        bind( Integer.class ).annotatedWith( Names.named( "numeber.version" ) ).toInstance( 10 );
    }

    @Inject
    @Named( "numeber.version" )
    private Integer version;

    @Test
    public void testInjectModuleClass()
    {
        Assert.assertNotNull( version );
        Assert.assertEquals( 10, version.intValue() );
    }

}
