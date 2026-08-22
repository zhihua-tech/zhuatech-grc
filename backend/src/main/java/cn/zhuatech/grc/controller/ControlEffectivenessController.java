/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.controller;

import cn.zhuatech.grc.common.ApiResponse;
import cn.zhuatech.grc.service.ControlEffectivenessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/control-effectiveness")
public class ControlEffectivenessController {
    private final ControlEffectivenessService service;
    public ControlEffectivenessController(ControlEffectivenessService service) { this.service = service; }
    @PostMapping
    ApiResponse<ControlEffectivenessService.EffectivenessResult> evaluate(
        @Valid @RequestBody ControlEffectivenessService.EffectivenessRequest request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
