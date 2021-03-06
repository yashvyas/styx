/*
  Copyright (C) 2013-2019 Expedia Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.hotels.styx.support.configuration

import com.hotels.styx.StyxServerSupport._
import com.hotels.styx.support.configuration.ProxyConfig.proxyServerDefaults


case class ProxyConfig(connectors: Connectors = Connectors(HttpConnectorConfig(), null),
                       bossThreadCount: Int = 1,
                       workerThreadsCount: Int = 1,
                       nioAcceptorBacklog: Int = proxyServerDefaults.nioAcceptorBacklog(),
                       maxInitialLength: Int = proxyServerDefaults.maxInitialLength(),
                       maxHeaderSize: Int = proxyServerDefaults.maxHeaderSize(),
                       maxChunkSize: Int = proxyServerDefaults.maxChunkSize(),
                       requestTimeoutMillis: Int = proxyServerDefaults.requestTimeoutMillis(),
                       keepAliveTimeoutMillis: Int = proxyServerDefaults.keepAliveTimeoutMillis(),
                       maxConnectionsCount: Int = proxyServerDefaults.maxConnectionsCount(),
                       clientWorkerThreadsCount: Int = 1) {
}


object ProxyConfig {
  val httpConfig = HttpConnectorConfig(0)
  val proxyServerDefaults = newProxyServerConfigBuilder(httpConfig.asJava, null).build()
}

