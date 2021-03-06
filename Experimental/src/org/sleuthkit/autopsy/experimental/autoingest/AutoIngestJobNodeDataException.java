/*
 * Autopsy Forensic Browser
 *
 * Copyright 2017 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.experimental.autoingest;

/**
 * Exception thrown when a manifest node contains incompatible data.
 */
public class AutoIngestJobNodeDataException extends Exception {

    /**
     * Constructs an exception thrown when a manifest node contains incompatible
     * data.
     *
     * @param message An error message.
     */
    public AutoIngestJobNodeDataException(String message) {
        super(message);
    }

    /**
     * Constructs an exception thrown when a manifest node contains incompatible
     * data.
     *
     * @param message An error message.
     * @param cause   An exception that caused this exception to be thrown.
     */
    public AutoIngestJobNodeDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
