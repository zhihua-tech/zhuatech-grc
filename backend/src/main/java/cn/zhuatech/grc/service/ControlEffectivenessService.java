/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ControlEffectivenessService {
    public EffectivenessResult evaluate(EffectivenessRequest request) {
        int score = (int) Math.round(request.designScore() * 0.30
            + request.operatingScore() * 0.40 + request.evidenceCoverage() * 0.30)
            - Math.min(25, request.exceptions() * 5)
            - Math.min(20, request.overdueActions() * 4)
            - (request.keyControl() && request.operatingScore() < 70 ? 10 : 0);
        score = Math.max(0, Math.min(100, score));
        String rating = score >= 80 ? "EFFECTIVE" : score >= 60 ? "PARTIAL" : "INEFFECTIVE";
        List<String> actions = new ArrayList<>();
        if (request.designScore() < 70) actions.add("重新设计控制目标、责任人与执行频率");
        if (request.operatingScore() < 70) actions.add("补充控制执行测试并分析失效原因");
        if (request.evidenceCoverage() < 90) actions.add("补齐可验证的控制证据和抽样记录");
        if (request.overdueActions() > 0) actions.add("升级催办逾期整改并确认剩余风险");
        if (actions.isEmpty()) actions.add("维持当前控制并按计划执行持续监测");
        return new EffectivenessResult(score, rating, actions);
    }

    public record EffectivenessRequest(@NotNull @Min(0) @Max(100) Integer designScore,
        @NotNull @Min(0) @Max(100) Integer operatingScore,
        @NotNull @Min(0) @Max(100) Integer evidenceCoverage,
        @NotNull @Min(0) @Max(1000) Integer exceptions,
        @NotNull @Min(0) @Max(1000) Integer overdueActions,
        @NotNull Boolean keyControl) {}
    public record EffectivenessResult(int score, String rating, List<String> actions) {}
}
