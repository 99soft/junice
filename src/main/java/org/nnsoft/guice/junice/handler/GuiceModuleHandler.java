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
package org.nnsoft.guice.junice.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nnsoft.guice.junice.annotation.GuiceModules;
import org.nnsoft.guice.junice.reflection.ClassHandler;
import org.nnsoft.guice.junice.reflection.ClassVisitor;
import org.nnsoft.guice.junice.reflection.HandleException;

import com.google.inject.Module;

/**
 * Handler class to handle all {@link GuiceModules} annotations.
 *
 * @see ClassVisitor
 * @author Marco Speranza
 * @version $Id: GuiceModuleHandler.java 000 2009-12-01 00:00:00Z marco.speranza79 $
 */
public final class GuiceModuleHandler implements ClassHandler<GuiceModules> {

    private static final Log logger = LogFactory.getLog(GuiceModuleHandler.class);

    final private List<Module> modules;

    public GuiceModuleHandler() {
        this.modules = new ArrayList<Module>(1);
    }

    /**
     * @return the modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * {@inheritDoc}
     */
    public void handle(GuiceModules annotation, Class<?> element) throws HandleException {
        for (Class<? extends Module> module : annotation.modules()) {
            if (logger.isDebugEnabled()) {
                logger.debug("   Try to create module: " + module);
            }
            try {
                this.modules.add(module.newInstance());
            } catch (Exception e) {
                throw new HandleException(e);
            }
        }
    }

}
