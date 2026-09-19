/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.controller;

import cn.zhuatech.grc.common.ApiResponse;
import cn.zhuatech.grc.service.ControlEffectivenessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/admin/control-effectiveness")
public class ControlEffectivenessController {
    private final ControlEffectivenessService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ControlEffectivenessController(ControlEffectivenessService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping
    ApiResponse<ControlEffectivenessService.EffectivenessResult> evaluate(
        @Valid @RequestBody ControlEffectivenessService.EffectivenessRequest request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
