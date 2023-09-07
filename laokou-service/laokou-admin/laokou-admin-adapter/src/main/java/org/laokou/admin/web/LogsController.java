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
package org.laokou.admin.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.api.LogsServiceI;
import org.laokou.admin.dto.log.LogLoginExportCmd;
import org.laokou.admin.dto.log.LogLoginListQry;
import org.laokou.admin.dto.log.LogOperateExportCmd;
import org.laokou.admin.dto.log.LogOperateListQry;
import org.laokou.admin.dto.log.clientobject.LoginLogCO;
import org.laokou.admin.dto.log.clientobject.OperateLogCO;
import org.laokou.admin.domain.annotation.OperateLog;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.trace.annotation.TraceLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laokou
 */
@RestController
@Tag(name = "LogsController", description = "日志管理")
@RequiredArgsConstructor
public class LogsController {

	private final LogsServiceI logsServiceI;

	@TraceLog
	@PostMapping(value = "v1/logs/operate-list")
	@Operation(summary = "日志管理", description = "查询操作日志列表")
	@PreAuthorize("hasAuthority('logs:operate-list')")
	public Result<Datas<OperateLogCO>> operateList(@RequestBody LogOperateListQry qry) {
		return logsServiceI.operateList(qry);
	}

	@TraceLog
	@PostMapping(value = "v1/logs/operate-export")
	@Operation(summary = "日志管理", description = "导出操作日志")
	@OperateLog(module = "日志管理", operation = "导出操作日志")
	@PreAuthorize("hasAuthority('logs:operate-export')")
	public Result<Boolean> operateExport(@RequestBody LogOperateExportCmd cmd) {
		return logsServiceI.operateExport(cmd);
	}

	@TraceLog
	@PostMapping(value = "v1/logs/login-list")
	@Operation(summary = "日志管理", description = "查询登录日志列表")
	@PreAuthorize("hasAuthority('logs:login-list')")
	public Result<Datas<LoginLogCO>> loginList(@RequestBody LogLoginListQry qry) {
		return logsServiceI.loginList(qry);
	}

	@TraceLog
	@PostMapping(value = "v1/logs/login-export")
	@Operation(summary = "日志管理", description = "导出登录日志")
	@OperateLog(module = "日志管理", operation = "导出登录日志")
	@PreAuthorize("hasAuthority('logs:login-export')")
	public Result<Boolean> loginExport(@RequestBody LogLoginExportCmd cmd) {
		return logsServiceI.loginExport(cmd);
	}

}
