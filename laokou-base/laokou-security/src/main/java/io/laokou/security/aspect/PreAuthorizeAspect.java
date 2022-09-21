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
package io.laokou.security.aspect;
import io.laokou.common.constant.Constant;
import io.laokou.common.exception.CustomException;
import io.laokou.common.exception.ErrorCode;
import io.laokou.common.user.UserDetail;
import io.laokou.common.utils.HttpContextUtil;
import io.laokou.security.annotation.PreAuthorize;
import io.laokou.security.utils.UserDetailUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
/**
 * @author Kou Shenhai
 * @version 1.0
 * @date 2022/7/18 0018 上午 9:52
 */
@Component
@Aspect
public class PreAuthorizeAspect {

    @Autowired
    private UserDetailUtil userDetailUtil;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(io.laokou.security.annotation.PreAuthorize)")
    public void authorizePointCut() {}

    @Around("authorizePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        final String ticket = request.getHeader(Constant.TICKET);
        //网关如果已经认证，则无需认证
        if (Constant.TICKET.equals(ticket)) {
            return point.proceed();
        }
        if (checkPermission(userDetailUtil.getUserDetail(request),point)) {
            return point.proceed();
        }
        throw new CustomException(ErrorCode.FORBIDDEN);
    }

    private boolean checkPermission(UserDetail userDetail,JoinPoint point) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
        if (preAuthorize == null) {
            preAuthorize = AnnotationUtils.findAnnotation(method,PreAuthorize.class);
        }
        final String[] permissionArray = preAuthorize.value().split(Constant.COMMA);
        final List<String> permissionsList = userDetail.getPermissionsList();
        for(String permission : permissionArray) {
            if (!permissionsList.contains(permission)) {
                return false;
            }
        }
        return true;
    }

}
