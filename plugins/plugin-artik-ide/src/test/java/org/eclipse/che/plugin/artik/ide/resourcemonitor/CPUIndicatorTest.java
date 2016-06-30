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
import com.google.inject.Provider;

import org.eclipse.che.api.core.model.machine.Machine;
import org.eclipse.che.api.promises.client.Function;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.extension.machine.client.actions.SelectCommandComboBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** @author Artem Zatsarynnyi */
@RunWith(GwtMockitoTestRunner.class)
public class CPUIndicatorTest {

    @Mock
    private ResourceIndicatorView     view;
    @Mock
    private Provider<ResourceMonitor> resourceMonitorProvider;
    @Mock
    private SelectCommandComboBox     selectCommandAction;

    @Mock
    private ResourceMonitor                          resourceMonitor;
    @Mock
    private Machine                                  machine;
    @Mock
    private Promise<String>                          promise;
    @Captor
    private ArgumentCaptor<Function<String, String>> functionCaptor;

    @InjectMocks
    private CPUIndicator cpuIndicator;

    @Test
    public void testGetValue() throws Exception {
        when(machine.getId()).thenReturn("machine_id");
        when(selectCommandAction.getSelectedMachine()).thenReturn(machine);
        when(resourceMonitor.getCpuUtilization(anyString())).thenReturn(promise);
        when(resourceMonitorProvider.get()).thenReturn(resourceMonitor);

        cpuIndicator.getValue();

        verify(selectCommandAction).getSelectedMachine();
        verify(resourceMonitorProvider).get();
        verify(resourceMonitor).getCpuUtilization(anyString());
        verify(promise).then(functionCaptor.capture());
        String value = functionCaptor.getValue().apply("0.5");
        assertEquals("CPU: 50.0%", value);
    }
}
