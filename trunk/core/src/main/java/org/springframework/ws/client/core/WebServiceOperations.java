/*
 * Copyright 2006 the original author or authors.
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

package org.springframework.ws.client.core;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.WebServiceClientException;

/**
 * Specifies a basic set of Web service operations. Implemented by {@link WebServiceTemplate}. Not often used directly,
 * but a useful option to enhance testability, as it can easily be mocked or stubbed.
 *
 * @author Arjen Poutsma
 * @see WebServiceTemplate
 */
public interface WebServiceOperations {

    /**
     * Sends a web service message that can be manipulated with the given callback, reading the result with a
     * <code>WebServiceMessageExtractor</code>.
     * <p/>
     * This will only work with a default uri specified!
     *
     * @param requestCallback   the requestCallback to be used for manipulating the request message
     * @param responseExtractor object that will extract results
     * @return an arbitrary result object, as returned by the <code>WebServiceMessageExtractor</code>
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    Object sendAndReceive(WebServiceMessageCallback requestCallback, WebServiceMessageExtractor responseExtractor)
            throws WebServiceClientException;

    /**
     * Sends a web service message that can be manipulated with the given callback, reading the result with a
     * <code>WebServiceMessageExtractor</code>.
     *
     * @param uri               the URI to send the message to
     * @param requestCallback   the requestCallback to be used for manipulating the request message
     * @param responseExtractor object that will extract results
     * @return an arbitrary result object, as returned by the <code>WebServiceMessageExtractor</code>
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    Object sendAndReceive(String uri,
                          WebServiceMessageCallback requestCallback,
                          WebServiceMessageExtractor responseExtractor) throws WebServiceClientException;

    /**
     * Sends a web service message that can be manipulated with the given request callback, handling the response with a
     * response callback.
     * <p/>
     * This will only work with a default uri specified!
     *
     * @param requestCallback  the callback to be used for manipulating the request message
     * @param responseCallback the callback to be used for manipulating the response message
     * @return <code>true</code> if a response was received; <code>false</code> otherwise
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    boolean sendAndReceive(WebServiceMessageCallback requestCallback, WebServiceMessageCallback responseCallback)
            throws WebServiceClientException;

    /**
     * Sends a web service message that can be manipulated with the given request callback, handling the response with a
     * response callback.
     *
     * @param uri              the URI to send the message to
     * @param requestCallback  the callback to be used for manipulating the request message
     * @param responseCallback the callback to be used for manipulating the response message
     * @return <code>true</code> if a response was received; <code>false</code> otherwise
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    boolean sendAndReceive(String uri,
                           WebServiceMessageCallback requestCallback,
                           WebServiceMessageCallback responseCallback) throws WebServiceClientException;

    //-----------------------------------------------------------------------------------------------------------------
    // Convenience methods for sending and receiving marshalled messages
    //-----------------------------------------------------------------------------------------------------------------

    /**
     * Sends a web service message that contains the given payload, marshalled by the configured
     * <code>Marshaller</code>. Returns the unmarshalled payload of the response message, if any.
     * <p/>
     * This will only work with a default specified!
     *
     * @param requestPayload the object to marshal into the request message payload
     * @return the unmarshalled payload of the response message, or <code>null</code> if no response is given
     * @throws XmlMappingException       if there is a problem marshalling or unmarshalling
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     * @see WebServiceTemplate#setMarshaller(org.springframework.oxm.Marshaller)
     * @see WebServiceTemplate#setUnmarshaller(org.springframework.oxm.Unmarshaller)
     */
    Object marshalSendAndReceive(Object requestPayload) throws XmlMappingException, WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload, marshalled by the configured
     * <code>Marshaller</code>. Returns the unmarshalled payload of the response message, if any.
     *
     * @param uri            the URI to send the message to
     * @param requestPayload the object to marshal into the request message payload
     * @return the unmarshalled payload of the response message, or <code>null</code> if no response is given
     * @throws XmlMappingException       if there is a problem marshalling or unmarshalling
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     * @see WebServiceTemplate#setMarshaller(org.springframework.oxm.Marshaller)
     * @see WebServiceTemplate#setUnmarshaller(org.springframework.oxm.Unmarshaller)
     */
    Object marshalSendAndReceive(String uri, Object requestPayload)
            throws XmlMappingException, WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload, marshalled by the configured
     * <code>Marshaller</code>. Returns the unmarshalled payload of the response message, if any. The given callback
     * allows changing of the request message after the payload has been marshalled to it.
     * <p/>
     * This will only work with a default specified!
     *
     * @param requestPayload  the object to marshal into the request message payload
     * @param requestCallback callback to change message, can be <code>null</code>
     * @return the unmarshalled payload of the response message, or <code>null</code> if no response is given
     * @throws XmlMappingException       if there is a problem marshalling or unmarshalling
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     * @see WebServiceTemplate#setMarshaller(org.springframework.oxm.Marshaller)
     * @see WebServiceTemplate#setUnmarshaller(org.springframework.oxm.Unmarshaller)
     */
    Object marshalSendAndReceive(Object requestPayload, WebServiceMessageCallback requestCallback)
            throws XmlMappingException, WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload, marshalled by the configured
     * <code>Marshaller</code>. Returns the unmarshalled payload of the response message, if any. The given callback
     * allows changing of the request message after the payload has been marshalled to it.
     *
     * @param uri             the URI to send the message to
     * @param requestPayload  the object to marshal into the request message payload
     * @param requestCallback callback to change message, can be <code>null</code>
     * @return the unmarshalled payload of the response message, or <code>null</code> if no response is given
     * @throws XmlMappingException       if there is a problem marshalling or unmarshalling
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     * @see WebServiceTemplate#setMarshaller(org.springframework.oxm.Marshaller)
     * @see WebServiceTemplate#setUnmarshaller(org.springframework.oxm.Unmarshaller)
     */
    Object marshalSendAndReceive(String uri, Object requestPayload, WebServiceMessageCallback requestCallback)
            throws XmlMappingException, WebServiceClientException;

    //-----------------------------------------------------------------------------------------------------------------
    // Convenience methods for sending Sources
    //-----------------------------------------------------------------------------------------------------------------

    /**
     * Sends a web service message that contains the given payload, reading the result with a
     * <code>SourceExtractor</code>.
     * <p/>
     * This will only work with a default specified!
     *
     * @param requestPayload    the payload of the request message
     * @param responseExtractor object that will extract results
     * @return an arbitrary result object, as returned by the <code>SourceExtractor</code>
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    Object sendSourceAndReceive(Source requestPayload, SourceExtractor responseExtractor)
            throws WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload, reading the result with a
     * <code>SourceExtractor</code>.
     *
     * @param uri               the URI to send the message to
     * @param requestPayload    the payload of the request message
     * @param responseExtractor object that will extract results
     * @return an arbitrary result object, as returned by the <code>SourceExtractor</code>
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    Object sendSourceAndReceive(String uri, Source requestPayload, SourceExtractor responseExtractor)
            throws WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload, reading the result with a
     * <code>SourceExtractor</code>.
     * <p/>
     * The given callback allows changing of the request message after the payload has been written to it.
     * <p/>
     * This will only work with a default uri specified!
     *
     * @param requestPayload    the payload of the request message
     * @param requestCallback   callback to change message, can be <code>null</code>
     * @param responseExtractor object that will extract results
     * @return an arbitrary result object, as returned by the <code>SourceExtractor</code>
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    Object sendSourceAndReceive(Source requestPayload,
                                WebServiceMessageCallback requestCallback,
                                SourceExtractor responseExtractor) throws WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload, reading the result with a
     * <code>SourceExtractor</code>.
     * <p/>
     * The given callback allows changing of the request message after the payload has been written to it.
     *
     * @param uri               the URI to send the message to
     * @param requestPayload    the payload of the request message
     * @param requestCallback   callback to change message, can be <code>null</code>
     * @param responseExtractor object that will extract results
     * @return an arbitrary result object, as returned by the <code>SourceExtractor</code>
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    Object sendSourceAndReceive(String uri,
                                Source requestPayload,
                                WebServiceMessageCallback requestCallback,
                                SourceExtractor responseExtractor) throws WebServiceClientException;

    //-----------------------------------------------------------------------------------------------------------------
    // Convenience methods for sending Sources and receiving to Results
    //-----------------------------------------------------------------------------------------------------------------

    /**
     * Sends a web service message that contains the given payload. Writes the response, if any, to the given
     * <code>Result</code>.
     * <p/>
     * This will only work with a default uri specified!
     *
     * @param requestPayload the payload of the request message
     * @param responseResult the result to write the response payload to
     * @return <code>true</code> if a response was received; <code>false</code> otherwise
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    boolean sendSourceAndReceiveToResult(Source requestPayload, Result responseResult) throws WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload. Writes the response, if any, to the given
     * <code>Result</code>.
     *
     * @param uri            the URI to send the message to
     * @param requestPayload the payload of the request message
     * @param responseResult the result to write the response payload to
     * @return <code>true</code> if a response was received; <code>false</code> otherwise
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    boolean sendSourceAndReceiveToResult(String uri, Source requestPayload, Result responseResult)
            throws WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload. Writes the response, if any, to the given
     * <code>Result</code>.
     * <p/>
     * The given callback allows changing of the request message after the payload has been written to it.
     * <p/>
     * This will only work with a default uri specified!
     *
     * @param requestPayload  the payload of the request message
     * @param requestCallback callback to change message, can be <code>null</code>
     * @param responseResult  the result to write the response payload to
     * @return <code>true</code> if a response was received; <code>false</code> otherwise
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    boolean sendSourceAndReceiveToResult(Source requestPayload,
                                         WebServiceMessageCallback requestCallback,
                                         Result responseResult) throws WebServiceClientException;

    /**
     * Sends a web service message that contains the given payload. Writes the response, if any, to the given
     * <code>Result</code>.
     * <p/>
     * The given callback allows changing of the request message after the payload has been written to it.
     *
     * @param uri             the URI to send the message to
     * @param requestPayload  the payload of the request message
     * @param requestCallback callback to change message, can be <code>null</code>
     * @param responseResult  the result to write the response payload to
     * @return <code>true</code> if a response was received; <code>false</code> otherwise
     * @throws WebServiceClientException if there is a problem sending or receiving the message
     */
    boolean sendSourceAndReceiveToResult(String uri,
                                         Source requestPayload,
                                         WebServiceMessageCallback requestCallback,
                                         Result responseResult) throws WebServiceClientException;

}
