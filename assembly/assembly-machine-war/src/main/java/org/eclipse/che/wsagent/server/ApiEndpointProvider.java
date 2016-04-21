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
package org.eclipse.che.wsagent.server;

import com.google.common.base.Strings;

import javax.inject.Provider;

/**
 * Provides value of Che API endpoint URL for usage inside machine to be able to connect to host machine using docker host IP.
 *
 * @author Artem Zatsarynnyi
 */
public class ApiEndpointProvider implements Provider<String> {

    public static final String API_ENDPOINT_URL_VARIABLE = "CHE_API_ENDPOINT";

    @Override
    public String get() {
        return Strings.nullToEmpty(System.getenv(API_ENDPOINT_URL_VARIABLE));
    }
}
