/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.controller;

import cn.zhuatech.grc.common.ApiResponse;
import cn.zhuatech.grc.service.ControlEvidenceGateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/grc")
public class ControlEvidenceGateController {
    private final ControlEvidenceGateService service;
    public ControlEvidenceGateController(ControlEvidenceGateService service) { this.service = service; }

    @PostMapping("/control-evidence-gate")
    public ApiResponse<ControlEvidenceGateService.Assessment> assess(
        @Valid @RequestBody ControlEvidenceGateService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
