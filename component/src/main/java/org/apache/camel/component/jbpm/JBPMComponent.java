/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.jbpm;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

public class JBPMComponent extends DefaultComponent {

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        JBPMConfiguration configuration = new JBPMConfiguration();
        setHostAndPort(configuration, remaining);
        setProperties(configuration, parameters);
        return new JBPMEndpoint(uri, this, configuration);
    }

    private void setHostAndPort(JBPMConfiguration configuration, String remaining) throws MalformedURLException {
        if (remaining.startsWith("http")) {
            URL connectionURL = new URL(remaining);
            configuration.setConnectionURL(connectionURL);
        } else {
            String[] hostAndPort = remaining.split(":");
            if (hostAndPort.length > 0 && hostAndPort[0].length() > 0) {
                configuration.setHost(hostAndPort[0]);
            }
            if (hostAndPort.length > 1 && hostAndPort[1].length() > 0) {
                configuration.setPort(Integer.parseInt(hostAndPort[1]));
            }
        }
    }
}