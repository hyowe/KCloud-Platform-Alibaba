/*
 * Copyright (c) 2022-2024 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.iot;

import lombok.RequiredArgsConstructor;
import org.laokou.common.core.annotation.EnableTaskExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author laokou
 */
@EnableTaskExecutor
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "org.laokou")
public class IotApp implements CommandLineRunner {

	// private final TDengineMapper tDengineMapper;

	public static void main(String[] args) throws UnknownHostException {
		// -Dnetty.server.parentgroup.size=2
		// -Dnetty.server.childgroup.size=32
		System.setProperty("netty.server.parentgroup.size", "2");
		System.setProperty("netty.server.childgroup.size", String.valueOf(2 * Runtime.getRuntime().availableProcessors()));
		System.setProperty("ip", InetAddress.getLocalHost().getHostAddress());
		new SpringApplicationBuilder(IotApp.class).web(WebApplicationType.SERVLET).run(args);
	}

	@Override
	public void run(String... args) {
		// tDengineMapper.insert();
	}

}
