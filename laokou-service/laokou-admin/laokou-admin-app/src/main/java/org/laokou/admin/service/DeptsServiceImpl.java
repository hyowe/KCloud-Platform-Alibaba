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
import org.laokou.admin.client.api.DeptsServiceI;
import org.laokou.admin.client.dto.dept.*;
import org.laokou.admin.client.dto.dept.clientobject.DeptCO;
import org.laokou.admin.command.dept.DeptInsertCmdExe;
import org.laokou.admin.command.dept.query.DeptListQryExe;
import org.laokou.admin.command.dept.query.DeptTreeGetQryExe;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class DeptsServiceImpl implements DeptsServiceI {

    private final DeptTreeGetQryExe deptTreeGetQryExe;

    private final DeptListQryExe deptListQryExe;

    private final DeptInsertCmdExe deptInsertCmdExe;

    @Override
    public Result<DeptCO> tree(DeptTreeGetQry qry) {
        return deptTreeGetQryExe.execute(qry);
    }

    @Override
    public Result<List<DeptCO>> list(DeptListQry qry) {
        return deptListQryExe.execute(qry);
    }

    @Override
    public Result<Boolean> insert(DeptInsertCmd cmd) {
        return deptInsertCmdExe.execute(cmd);
    }

    @Override
    public Result<Boolean> update(DeptUpdateCmd cmd) {
        return null;
    }

    @Override
    public Result<Boolean> delete(DeptDeleteCmd cmd) {
        return null;
    }

    @Override
    public Result<DeptCO> get(DeptGetQry qry) {
        return null;
    }

}
