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

import com.google.gwtmockito.GwtMockitoTestRunner;

import org.eclipse.che.api.core.model.machine.Machine;
import org.eclipse.che.api.core.model.machine.MachineConfig;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.extension.machine.client.actions.SelectCommandComboBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** @author Artem Zatsarynnyi */
@RunWith(GwtMockitoTestRunner.class)
public class TargetNameIndicatorTest {

    @Mock
    private ResourceIndicatorView view;
    @Mock
    private SelectCommandComboBox selectCommandAction;

    @Mock
    private Machine         machine;
    @Mock
    private MachineConfig   machineConfig;
    @Mock
    private Promise<String> promise;

    @InjectMocks
    private TargetNameIndicator targetNameIndicator;

    @Test
    public void testGetValue() throws Exception {
        when(machineConfig.getName()).thenReturn("machine_name");
        when(machine.getId()).thenReturn("machine_id");
        when(machine.getConfig()).thenReturn(machineConfig);
        when(selectCommandAction.getSelectedMachine()).thenReturn(machine);

        targetNameIndicator.getValue();

        verify(selectCommandAction).getSelectedMachine();
    }
}
