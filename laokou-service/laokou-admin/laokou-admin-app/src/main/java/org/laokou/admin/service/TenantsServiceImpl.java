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

package org.laokou.admin.service;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.client.api.TenantsServiceI;
import org.laokou.admin.client.dto.common.clientobject.OptionCO;
import org.laokou.admin.client.dto.tenant.TenantOptionListQry;
import org.laokou.admin.command.tenant.query.TenantOptionListQryExe;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class TenantsServiceImpl implements TenantsServiceI {

	private final TenantOptionListQryExe tenantOptionListQryExe;

	@Override
	public Result<List<OptionCO>> optionList(TenantOptionListQry qry) {
		return tenantOptionListQryExe.execute(qry);
	}

}