/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.controller;

import cn.zhuatech.grc.common.ApiResponse;
import cn.zhuatech.grc.service.ResidualRiskDecisionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/grc/insights")
public class ResidualRiskDecisionController {
    private final ResidualRiskDecisionService service;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ResidualRiskDecisionController(ResidualRiskDecisionService service) {
        this.service = service;
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/residual-risk-decision")
    public ApiResponse<ResidualRiskDecisionService.Result> evaluate(
        @Valid @RequestBody ResidualRiskDecisionService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
