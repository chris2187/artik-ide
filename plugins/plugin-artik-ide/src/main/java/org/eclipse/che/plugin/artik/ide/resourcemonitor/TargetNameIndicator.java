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

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.eclipse.che.api.core.model.machine.Machine;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.api.promises.client.js.Promises;
import org.eclipse.che.ide.extension.machine.client.actions.SelectCommandComboBox;

/**
 * Indicator that displays name of the currently monitored target.
 *
 * @author Artem Zatsarynnyi
 */
@Singleton
public class TargetNameIndicator extends AbstractResourceIndicator {

    private final SelectCommandComboBox selectCommandAction;

    @Inject
    public TargetNameIndicator(ResourceIndicatorView view, SelectCommandComboBox selectCommandAction) {
        super(view);

        this.selectCommandAction = selectCommandAction;
    }

    @Override
    protected Promise<String> getValue() {
        final Machine selectedMachine = selectCommandAction.getSelectedMachine();
        if (selectedMachine == null) {
            return Promises.resolve("N/A");
        }

        final String machineName = selectedMachine.getConfig().getName();

        return Promises.resolve("Target: " + machineName);
    }
}
