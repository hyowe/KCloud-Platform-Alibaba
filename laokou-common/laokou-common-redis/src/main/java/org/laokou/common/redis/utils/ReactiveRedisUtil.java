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

package org.laokou.common.redis.utils;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author laokou
 */
@RequiredArgsConstructor
public class ReactiveRedisUtil {

	private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

	private final RedissonReactiveClient redissonReactiveClient;

	public Mono<Object> get(String key) {
		return redissonReactiveClient.getBucket(key).get();
	}

	public Mono<Object> hGet(String key, String field) {
		return redissonReactiveClient.getMap(key).get(field);
	}

	public Mono<Boolean> hasKey(String key) {
		return reactiveRedisTemplate.hasKey(key);
	}

	public Mono<Void> set(String key, Object obj, long expire) {
		if (expire == -1) {
			return redissonReactiveClient.getBucket(key).set(obj);
		}
		else {
			return redissonReactiveClient.getBucket(key).set(obj, Duration.ofSeconds(expire));
		}
	}

	public Mono<Long> delete(String key) {
		return redissonReactiveClient.getKeys().delete(key);
	}

}
