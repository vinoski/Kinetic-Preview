/**
 * Copyright (c) 2013 Seagate Technology LLC
 * All rights reserved.

 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:

 * 1) Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.

 * 2) Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.

 * 3) Neither the name of Seagate Technology nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission
 * from Seagate Technology.

 * 4) No patent or trade secret license whatsoever, either express or implied, is granted by Seagate
 * Technology or its contributors by this copyright license.

 * 5) All modifications must be reposted in source code form in a manner that allows user to
 * readily access the source code.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, NONINFRINGEMENT AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  THE COPYRIGHT
 * HOLDERS AND CONTRIBUTORS DISCLAIM ALL LIABILITY FOR
 * INTELLECTUAL PROPERTY INFRINGEMENT RELATED TO THIS SOFTWARE.
 */
package com.seagate.kinetic.example.client.async;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.seagate.kinetic.client.AsyncKineticException;
import com.seagate.kinetic.client.CallbackHandler;
import com.seagate.kinetic.client.CallbackResult;
import com.seagate.kinetic.client.Entry;
import com.seagate.kinetic.client.KineticClient;

/**
 * 
 * Put async callback handler example.
 * 
 * @see AsyncApiUsage
 * @see CallbackHandler
 * @see KineticClient#putForcedAsync(Entry, CallbackHandler)
 * @see KineticClient#putAsync(Entry, byte[], CallbackHandler)
 */
public class PutAsyncCallbackHandler implements CallbackHandler<Entry> {

	private final static java.util.logging.Logger logger = Logger
			.getLogger(PutAsyncCallbackHandler.class.getName());

	private AsyncApiUsage master = null;

	public PutAsyncCallbackHandler(AsyncApiUsage master) {
		this.master = master;
	}

	@Override
	public void onSuccess(CallbackResult<Entry> result) {

		// inform master that entry has persisted in store
		this.master.asyncPutInStore(result.getResult());
	}

	@Override
	public void onError(AsyncKineticException exception) {

		// log exception
		logger.log(Level.WARNING, exception.getMessage(), exception);
	}

}
