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
package org.nnsoft.guice.junice.data;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HelloWorld {

    @Inject(optional=true)
    private Service service;

    @Inject(optional=true)
    private TelephonService telephon;

    @Inject(optional=true)
    private List<Service> services;

    public String sayHallo() {
        return "Hello World!!!!";
    }

    public String sayHalloByService() {
        return service.go();
    }

    public void callHelloWorldTelephon() {
        String number = telephon.getTelephonNumber();
        service.call(number);
    }

    public void sayHalloByServiceLists() {
        for (Service service : services) {
           service.go();
        }
    }

}
