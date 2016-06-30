/*******************************************************************************
 * Copyright (c) 2016 Samsung Electronics Co., Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - Initial implementation
 *   Samsung Electronics Co., Ltd. - Initial implementation
 *******************************************************************************/
package org.eclipse.che.plugin.artik.ide.resourcemonitor;

import com.google.gwt.core.client.JsArrayMixed;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import org.eclipse.che.api.promises.client.Function;
import org.eclipse.che.api.promises.client.FunctionException;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.api.promises.client.js.Promises;
import org.eclipse.che.ide.extension.machine.client.actions.SelectCommandComboBox;

/**
 * Indicator that displays memory information.
 *
 * @author Artem Zatsarynnyi
 */
@Singleton
public class MemoryIndicator extends AbstractResourceIndicator {

    private final Provider<ResourceMonitor> resourceMonitorProvider;
    private final SelectCommandComboBox     selectCommandAction;

    @Inject
    public MemoryIndicator(ResourceIndicatorView view,
                           Provider<ResourceMonitor> resourceMonitorProvider,
                           SelectCommandComboBox selectCommandAction) {
        super(view);

        this.resourceMonitorProvider = resourceMonitorProvider;
        this.selectCommandAction = selectCommandAction;
    }

    @Override
    protected Promise<String> getValue() {
        final String machineId = selectCommandAction.getSelectedMachine().getId();

        return Promises.all(new Promise[]{resourceMonitorProvider.get().getTotalMemory(machineId),
                                          resourceMonitorProvider.get().getUsedMemory(machineId)})
                       .then(new Function<JsArrayMixed, String>() {
                           @Override
                           public String apply(JsArrayMixed jsArrayMixed) throws FunctionException {
                               final String totalMemory = jsArrayMixed.getString(0);
                               final String usedMemory = jsArrayMixed.getString(1);
                               return "Mem: " + usedMemory + " MB used of " + totalMemory + " MB";
                           }
                       });
    }
}
