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
package org.laokou.admin.api;

import org.laokou.admin.dto.oss.*;
import org.laokou.admin.dto.oss.clientobject.FileCO;
import org.laokou.admin.dto.oss.clientobject.OssCO;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;

/**
 * @author laokou
 */
public interface OssServiceI {

	Result<Boolean> insert(OssInsertCmd cmd);

	Result<Boolean> update(OssUpdateCmd cmd);

	Result<Boolean> delete(OssDeleteCmd cmd);

	Result<Boolean> use(OssUseCmd cmd);

	Result<OssCO> get(OssGetQry qry);

	Result<Datas<OssCO>> list(OssListQry qry);

	Result<FileCO> upload(OssUploadCmd cmd);

}