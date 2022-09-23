/**
 * Copyright (c) 2022 KCloud-Platform Authors. All Rights Reserved.
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
 */
package org.laokou.admin.domain.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.laokou.common.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公众号自定义菜单
 *
 * @author Kou Shenhai
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("mp_menu")
public class WXMpMenuDO extends BaseDO {
	private static final long serialVersionUID = 1L;

	/**
	* 菜单json数据
	*/
	private String menu;
	/**
	* AppID
	*/
	private String appId;
}