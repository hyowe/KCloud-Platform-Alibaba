/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laokou.common.data.cache.aspect;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.laokou.common.core.utils.SpringUtil;
import org.laokou.common.data.cache.annotation.DataCache;
import org.laokou.common.data.cache.enums.CacheEnum;
import org.laokou.redis.utils.RedisKeyUtil;
import org.laokou.redis.utils.RedisUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
/**
 * @author laokou
 */
@Component
@Aspect
@RequiredArgsConstructor
public class CacheAspect {
    private final RedisUtil redisUtil;
    private final Cache<String,Object> caffeineCache;

    @Around("@annotation(org.laokou.common.data.cache.annotation.DataCache)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = signature.getParameterNames();
        DataCache dataCache = method.getAnnotation(DataCache.class);
        if (dataCache == null) {
            dataCache = AnnotationUtils.findAnnotation(method,DataCache.class);
        }
        long expire = dataCache.expire();
        CacheEnum type = dataCache.type();
        String key = dataCache.key();
        String name = dataCache.name();
        Object[] args = point.getArgs();
        key = RedisKeyUtil.getDataCacheKey(name,SpringUtil.parse(key,parameterNames,args,Long.class));
        Object value = point.proceed();
        switch (type) {
            case GET -> {
                return get(key,value,expire);
            }
            case PUT -> put(key,value,expire);
            case DEL -> del(key);
            default -> {}
        }
        return value;
    }

    private void put(String key ,Object value,long expire) {
        redisUtil.set(key,value,expire);
        caffeineCache.put(key,value);
    }

    private Object get(String key,Object value,long expire) {
        Object obj = caffeineCache.get(key,t -> redisUtil.get(key));
        if (obj != null) {
            return obj;
        }
        redisUtil.setIfExists(key,value,expire);
        return value;
    }

    private void del(String key) {
        redisUtil.delete(key);
        caffeineCache.invalidate(key);
    }

}