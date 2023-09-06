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
import org.laokou.admin.client.api.UsersServiceI;
import org.laokou.admin.client.dto.user.*;
import org.laokou.admin.client.dto.user.clientobject.UserProfileCO;
import org.laokou.admin.domain.annotation.OperateLog;
import org.laokou.common.core.vo.OptionVO;
import org.laokou.common.data.cache.annotation.DataCache;
import org.laokou.common.data.cache.enums.Cache;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.trace.annotation.TraceLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laokou
 */
@RestController
@Tag(name = "UsersController", description = "用户管理")
@RequiredArgsConstructor
public class UsersController {

	private final UsersServiceI usersServiceI;

	@TraceLog
	@PutMapping("v1/users")
	@Operation(summary = "用户管理", description = "修改用户")
	@OperateLog(module = "用户管理", operation = "修改用户")
	@PreAuthorize("hasAuthority('users:update')")
	@DataCache(name = "users", key = "#dto.id", type = Cache.DEL)
	public Result<Boolean> update() {
		return Result.of(null);
	}

	@TraceLog
	@PostMapping("v1/users/online-list")
	@PreAuthorize("hasAuthority('users:online-list')")
	@Operation(summary = "在线用户", description = "在线用户查询")
	public Result<?> onlineList() {
		return Result.of(null);
	}

	@TraceLog
	@DeleteMapping("v1/users/online-kill")
	@Operation(summary = "在线用户", description = "在线用户强踢")
	@OperateLog(module = "用户管理", operation = "在线用户强踢")
	@PreAuthorize("hasAuthority('users:online-kill')")
	public Result<Boolean> onlineKill(@RequestBody UserOnlineKillCmd cmd) {
		return Result.of(null);
	}

	@TraceLog
	@GetMapping("v1/users/profile")
	@Operation(summary = "个人中心", description = "用户信息")
	public Result<UserProfileCO> profile() {
		return usersServiceI.profile(new UserProfileGetQry());
	}

	@TraceLog
	@GetMapping("v1/users/option-list")
	@Operation(summary = "用户管理", description = "下拉列表")
	public Result<List<OptionVO>> optionList() {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/profile")
	@Operation(summary = "个人中心", description = "修改信息")
	public Result<Boolean> profile(@RequestBody Object obj) {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/status")
	@Operation(summary = "用户管理", description = "修改状态")
	@OperateLog(module = "用户管理", operation = "修改状态")
	@PreAuthorize("hasAuthority('users:status')")
	public Result<Boolean> status(@RequestBody UserStatusUpdateCmd cmd) {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/reset-password")
	@Operation(summary = "用户管理", description = "重置密码")
	@OperateLog(module = "用户管理", operation = "重置密码")
	@PreAuthorize("hasAuthority('users:reset-password')")
	public Result<Boolean> resetPassword(@RequestBody UserPasswordRestCmd cmd) {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/users/profile-password")
	@Operation(summary = "个人中心", description = "修改密码")
	public Result<Boolean> profilePassword(@RequestBody UserPasswordRestCmd cmd) {
		return Result.of(null);
	}

	@TraceLog
	@PostMapping("v1/users")
	@Operation(summary = "用户管理", description = "新增用户")
	@OperateLog(module = "用户管理", operation = "新增用户")
	@PreAuthorize("hasAuthority('users:insert')")
	public Result<Boolean> insert(@RequestBody UserInsertCmd cmd) {
		return usersServiceI.insert(cmd);
	}

	@TraceLog
	@GetMapping("v1/users/{id}")
	@Operation(summary = "用户管理", description = "查看用户")
	@DataCache(name = "users", key = "#id")
	public Result<?> get(@PathVariable("id") Long id) {
		return Result.of(null);
	}

	@TraceLog
	@DeleteMapping("v1/users/{id}")
	@Operation(summary = "用户管理", description = "删除用户")
	@OperateLog(module = "用户管理", operation = "删除用户")
	@PreAuthorize("hasAuthority('users:delete')")
	@DataCache(name = "users", key = "#id", type = Cache.DEL)
	public Result<Boolean> delete(@PathVariable("id") Long id) {
		return Result.of(null);
	}

	@TraceLog
	@PostMapping("v1/users/list")
	@Operation(summary = "用户管理", description = "查询用户")
	@PreAuthorize("hasAuthority('users:list')")
	public Result<?> list() {
		return Result.of(null);
	}

}
