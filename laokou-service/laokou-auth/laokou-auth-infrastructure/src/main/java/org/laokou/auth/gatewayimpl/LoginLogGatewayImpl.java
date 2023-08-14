/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
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

package org.laokou.auth.gatewayimpl;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.laokou.auth.common.event.DomainEventPublisher;
import org.laokou.auth.domain.gateway.LoginLogGateway;
import org.laokou.auth.domain.log.LoginLog;
import org.laokou.auth.dto.domainevent.LoginLogEvent;
import org.laokou.common.core.utils.RequestUtil;
import org.laokou.common.ip.region.utils.AddressUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class LoginLogGatewayImpl implements LoginLogGateway {

	private final DomainEventPublisher domainEventPublisher;

	@Override
	public void publish(LoginLog loginLog) {
		domainEventPublisher.publish(getEvent(loginLog));
	}

	private UserAgent getUserAgent() {
		HttpServletRequest request = RequestUtil.getHttpServletRequest();
		return UserAgent.parseUserAgentString(request.getHeader(HttpHeaders.USER_AGENT));
	}

	private LoginLogEvent getEvent(LoginLog loginLog) {
		UserAgent userAgent = getUserAgent();
		// 获取客户端操作系统
		String os = userAgent.getOperatingSystem().getName();
		// 获取客户端浏览器
		String browser = userAgent.getBrowser().getName();
		LoginLogEvent event = new LoginLogEvent(this);
		event.setLoginName(loginLog.getLoginName());
		event.setRequestIp(loginLog.getIp());
		event.setRequestAddress(AddressUtil.getRealAddress(loginLog.getIp()));
		event.setBrowser(browser);
		event.setOs(os);
		event.setMsg(loginLog.getMessage());
		event.setLoginType(loginLog.getLoginType());
		event.setRequestStatus(loginLog.getStatus());
		event.setTenantId(loginLog.getTenantId());
		return event;
	}

}