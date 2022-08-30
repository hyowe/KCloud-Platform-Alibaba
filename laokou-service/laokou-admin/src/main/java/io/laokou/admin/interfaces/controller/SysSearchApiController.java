package io.laokou.admin.interfaces.controller;

import io.laokou.admin.application.service.SysSearchApplicationService;
import io.laokou.admin.infrastructure.common.feign.elasticsearch.form.SearchForm;
import io.laokou.admin.infrastructure.common.feign.elasticsearch.form.SearchVO;
import io.laokou.common.utils.HttpResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 搜索管理控制器
 * @author Kou Shenhai
 */
@RestController
@Api(value = "搜索管理API",protocols = "http",tags = "搜索管理API")
@RequestMapping("/sys/search/api")
public class SysSearchApiController {

    @Autowired
    private SysSearchApplicationService sysSearchApplicationService;

    @PostMapping("/resource")
    public HttpResultUtil<SearchVO> searchResource(@RequestBody SearchForm form) {
        return new HttpResultUtil<SearchVO>().ok(sysSearchApplicationService.searchResource(form));
    }

}
