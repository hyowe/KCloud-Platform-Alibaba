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
package org.laokou.admin.server.application.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.laokou.admin.client.enums.ChannelTypeEnum;
import org.laokou.admin.server.application.service.SysMessageApplicationService;
import org.laokou.admin.server.domain.sys.entity.SysMessageDO;
import org.laokou.admin.server.domain.sys.entity.SysMessageDetailDO;
import org.laokou.admin.server.domain.sys.repository.service.SysMessageDetailService;
import org.laokou.admin.server.domain.sys.repository.service.SysMessageService;
import org.laokou.admin.client.dto.MessageDTO;
import org.laokou.admin.server.infrastructure.feign.im.ImApiFeignClient;
import org.laokou.admin.server.interfaces.qo.SysMessageQo;
import org.laokou.admin.client.vo.MessageDetailVO;
import org.laokou.admin.client.vo.SysMessageVO;
import org.apache.commons.collections.CollectionUtils;
import org.laokou.auth.client.utils.UserUtil;
import org.laokou.common.core.constant.Constant;
import org.laokou.common.core.utils.ConvertUtil;
import org.laokou.common.data.filter.annotation.DataFilter;
import org.laokou.common.swagger.exception.CustomException;
import org.laokou.common.swagger.utils.HttpResult;
import org.laokou.common.swagger.utils.ValidatorUtil;
import org.laokou.im.client.PushMsgDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * @author laokou
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysMessageApplicationServiceImpl implements SysMessageApplicationService {

    private final SysMessageService sysMessageService;

    private final SysMessageDetailService sysMessageDetailService;
    private final ImApiFeignClient imApiFeignClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertMessage(MessageDTO dto) {
        ValidatorUtil.validateEntity(dto);
        SysMessageDO messageDO = ConvertUtil.sourceToTarget(dto, SysMessageDO.class);
        Integer sendChannel = dto.getSendChannel();
        messageDO.setCreateDate(new Date());
        messageDO.setCreator(UserUtil.getUserId());
        messageDO.setDeptId(UserUtil.getDeptId());
        messageDO.setSendChannel(sendChannel);
        sysMessageService.save(messageDO);
        if (ChannelTypeEnum.PLATFORM.ordinal() == sendChannel) {
            Set<String> receiver = dto.getReceiver();
            Iterator<String> iterator = receiver.iterator();
            List<SysMessageDetailDO> detailDOList = new ArrayList<>(receiver.size());
            while (iterator.hasNext()) {
                String next = iterator.next();
                SysMessageDetailDO detailDO = new SysMessageDetailDO();
                detailDO.setMessageId(messageDO.getId());
                detailDO.setUserId(Long.valueOf(next));
                detailDO.setCreateDate(new Date());
                detailDO.setCreator(UserUtil.getUserId());
                detailDOList.add(detailDO);
            }
            if (CollectionUtils.isNotEmpty(detailDOList)) {
                sysMessageDetailService.saveBatch(detailDOList);
            }
            // 平台-发送消息
            if (CollectionUtils.isNotEmpty(receiver)) {
                PushMsgDTO pushMsgDTO = new PushMsgDTO();
                pushMsgDTO.setMsg("您有一条未读消息，请注意查收");
                pushMsgDTO.setReceiver(receiver);
                HttpResult<Boolean> result = imApiFeignClient.push(pushMsgDTO);
                if (!result.success()) {
                    throw new CustomException(result.getCode(), result.getMsg());
                }
            }
        }
        return true;
    }

    @Override
    @DataFilter(tableAlias = "boot_sys_message")
    public IPage<SysMessageVO> queryMessagePage(SysMessageQo qo) {
        ValidatorUtil.validateEntity(qo);
        IPage<SysMessageVO> page = new Page<>(qo.getPageNum(),qo.getPageSize());
        return sysMessageService.getMessageList(page,qo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageDetailVO getMessageByDetailId(Long id) {
        Integer version = sysMessageDetailService.getVersion(id);
        sysMessageService.readMessage(id,version);
        return sysMessageService.getMessageByDetailId(id);
    }

    @Override
    public MessageDetailVO getMessageById(Long id) {
        return sysMessageService.getMessageById(id);
    }

    @Override
    public IPage<SysMessageVO> getUnReadList(SysMessageQo qo) {
        IPage<SysMessageVO> page = new Page<>(qo.getPageNum(),qo.getPageSize());
        final Long userId = UserUtil.getUserId();
        return sysMessageService.getUnReadList(page,qo.getType(),userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long unReadCount() {
        final Long userId = UserUtil.getUserId();
        return sysMessageDetailService.count(Wrappers.lambdaQuery(SysMessageDetailDO.class).eq(SysMessageDetailDO::getUserId,userId)
                .eq(SysMessageDetailDO::getReadFlag, Constant.NO));
    }

}
