/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
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

package org.laokou.admin.common.utils;

import org.laokou.admin.domain.resource.Resource;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.core.utils.FileUtil;

/**
 * @author laokou
 */
public class BusinessUtil {

	public static void checkResource(Resource resource) {
		String fileExt = FileUtil.getFileExt(resource.getUrl());
		if (!FileUtil.checkFileExt(resource.getCode(), fileExt)) {
			throw new SystemException("资源格式错误");
		}
	}

}