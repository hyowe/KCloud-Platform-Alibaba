/*
 * Copyright (c) 2022-2024 KCloud-Platform-IOT Author or Authors. All Rights Reserved.
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

package org.laokou.admin.config;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.laokou.admin.common.utils.DsUtil;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;

import static org.laokou.common.i18n.common.DSConstant.TENANT;

/**
 * 租户拦截器.
 *
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class DsTenantProcessor extends DsProcessor {

	private final DsUtil dsUtil;

	@Override
	public boolean matches(String key) {
		return key.startsWith(TENANT);
	}

	@Override
	public String doDetermineDatasource(MethodInvocation invocation, String key) {
		return dsUtil.loadDs(UserUtil.getSourceName());
	}

}
