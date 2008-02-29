/*
 * Copyright 2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.ws.context;

import java.io.IOException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.transport.TransportRequest;
import org.springframework.ws.transport.TransportResponse;

/**
 * Context holder for message requests. Contains both the message request as well as the response. Response message are
 * usually lazily created.
 * <p/>
 * <code>MessageContext</code> implementations are constructed using a <code>MessageContextFactory</code>, taking a
 * <code>TransportContext</code> as a parameter.
 *
 * @author Arjen Poutsma
 * @see MessageContextFactory#createContext(org.springframework.ws.transport.TransportContext)
 */
public interface MessageContext {

    /**
     * Returns the request message.
     *
     * @return the request message
     */
    WebServiceMessage getRequest();

    /**
     * Returns the response message. Creates a new response if no response is present.
     *
     * @return the response message
     * @see #hasResponse()
     */
    WebServiceMessage getResponse();

    /**
     * Indicates whether this context has a resonse.
     *
     * @return <code>true</code> if this context has a response; <code>false</code> otherwise
     */
    boolean hasResponse();

    /**
     * Returns the transport request used to create this context. Call be used for URL-based message routing.
     *
     * @return the transport request
     */
    TransportRequest getTransportRequest();

    /**
     * Sends the response to the given transport response.
     *
     * @param transportResponse the transport used for sending
     * @throws IOException if an I/O exception occurs
     */
    void sendResponse(TransportResponse transportResponse) throws IOException;

    /**
     * Sets the name and value of a property associated with the <code>MessageContext</code>. If the
     * <code>MessageContext</code> contains a value of the same property, the old value is replaced.
     *
     * @param name  name of the property associated with the value
     * @param value value of the property
     */
    void setProperty(String name, Object value);

    /**
     * Gets the value of a specific property from the <code>MessageContext</code>.
     *
     * @param name name of the property whose value is to be retrieved
     * @return value of the property
     */
    Object getProperty(String name);

    /**
     * Removes a property from the <code>MessageContext</code>.
     *
     * @param name name of the property to be removed
     */
    void removeProperty(String name);

    /**
     * Check if this message context contains a property with the given name.
     *
     * @param name the name of the property to look fo
     * @return <code>true</code> if the <code>MessageContext</code> contains the property; <code>false</code> otherwise
     */
    boolean containsProperty(String name);

    /**
     * Return the names of all properties in this <code>MessageContext</code>.
     *
     * @return the names of all properties in this context, or an empty array if none defined
     */
    String[] getPropertyNames();

}