/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *   http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laokou.admin.server.application.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.laokou.admin.client.dto.SysOssDTO;
import org.laokou.admin.server.interfaces.qo.SysOssQo;
import org.laokou.oss.client.vo.SysOssVO;

/**
 * @author laokou
 */
public interface SysOssApplicationService {

    /**
     * 新增oss
     * @param dto
     * @return
     */
    Boolean insertOss(SysOssDTO dto);

    /**
     * 修改oss
     * @param dto
     * @return
     */
    Boolean updateOss(SysOssDTO dto);

    /**
     * 删除oss
     * @param id
     * @return
     */
    Boolean deleteOss(Long id);

    /**
     * 查询oss
     * @param qo
     * @return
     */
    IPage<SysOssVO> queryOssPage(SysOssQo qo);


    /**
     * 查询详情
     * @param id
     * @return
     */
    SysOssVO getOssById(Long id);

}