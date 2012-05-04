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

/**
 * 
 * 
 * @author Marco Speranza
 * @version $Id: ServiceImpl.java 000 2009-12-01 00:00:00Z marco.speranza79 $
 */
public class ServiceImpl implements Service {

    /* (non-Javadoc)
     * @see com.junice.data.Service#call(java.lang.String)
     */
    public void call(String value) {
        // do nothing
    }

    /* (non-Javadoc)
     * @see com.junice.data.Service#go()
     */
    public String go() {
        return "It's real class";
    }

}