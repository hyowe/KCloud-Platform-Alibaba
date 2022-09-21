/**
 * Copyright 2020-2022 Kou Shenhai
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
package io.laokou.security.utils;
import io.laokou.common.exception.CustomException;
import io.laokou.common.user.SecurityUser;
import io.laokou.common.user.UserDetail;
import io.laokou.common.utils.HttpContextUtil;
import io.laokou.common.utils.HttpResultUtil;
import io.laokou.security.feign.auth.AuthApiFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
@Component
public class UserDetailUtil {

    @Autowired
    private AuthApiFeignClient authApiFeignClient;

    public UserDetail getUserDetail(HttpServletRequest request) {
        String Authorization = SecurityUser.getAuthorization(request);
        String language = HttpContextUtil.getLanguage();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        HttpResultUtil<UserDetail> result = authApiFeignClient.resource(language, Authorization, uri, method);
        if (!result.success()) {
            throw new CustomException(result.getCode(),result.getMsg());
        }
        return result.getData();
    }

}
