/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.sshd.common.future;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.sshd.common.SshException;

/**
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public class DefaultKeyExchangeFuture extends DefaultSshFuture<KeyExchangeFuture>implements KeyExchangeFuture {
    public DefaultKeyExchangeFuture(Object lock) {
        super(lock);
    }

    @Override   // TODO for JDK-8 make this a default method
    public void verify() throws IOException {
        verify(Long.MAX_VALUE);
    }

    @Override   // TODO for JDK-8 make this a default method
    public void verify(long timeout, TimeUnit unit) throws IOException {
        verify(unit.toMillis(timeout));
    }

    @Override   // TODO for JDK-8 make this a default method
    public void verify(long timeoutMillis) throws IOException {
        Boolean result = verifyResult(Boolean.class, timeoutMillis);
        if (!result.booleanValue()) {
            throw new SshException("Key exchange failed");
        }
    }

    @Override   // TODO for JDK-8 make this a default method
    public Throwable getException() {
        Object v = getValue();
        if (v instanceof Throwable) {
            return (Throwable) v;
        } else {
            return null;
        }
    }
}