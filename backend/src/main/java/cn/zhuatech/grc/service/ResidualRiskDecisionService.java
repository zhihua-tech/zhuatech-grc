/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.grc.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResidualRiskDecisionService {
    public Result evaluate(Request request) {
        int residualScore = (int) Math.round(request.inherentRiskScore()
            * (1 - request.controlEffectiveness()));
        if (request.regulatoryImpact()) residualScore += 15;
        residualScore += Math.min(24, request.openFindings() * 8);
        if (request.evidenceAgeDays() > 90) residualScore += 10;
        residualScore = Math.min(100, residualScore);
        String decision = residualScore >= 60
            || request.financialExposure().compareTo(new BigDecimal("1000000")) >= 0 ? "ESCALATE"
            : residualScore >= 25 ? "MITIGATE" : "ACCEPT";

        List<String> actions = new ArrayList<>();
        if (request.openFindings() > 0) actions.add("关闭审计发现并上传验证证据");
        if (request.evidenceAgeDays() > 90) actions.add("刷新控制执行证据与抽样结果");
        if (request.regulatoryImpact()) actions.add("提交合规负责人复核并记录监管依据");
        if ("ESCALATE".equals(decision)) actions.add("提交风险委员会决定降低、转移或接受方案");
        if (actions.isEmpty()) actions.add("记录风险接受人、期限和持续监控指标");
        return new Result(request.riskCode(), residualScore, decision,
            request.financialExposure(), actions);
    }

    public record Request(@NotBlank String riskCode,
                          @Min(0) @Max(100) int inherentRiskScore,
                          @DecimalMin("0") @DecimalMax("1") double controlEffectiveness,
                          @DecimalMin("0") BigDecimal financialExposure,
                          boolean regulatoryImpact, @Min(0) int openFindings,
                          @Min(0) int evidenceAgeDays) {}

    public record Result(String riskCode, int residualRiskScore, String decision,
                         BigDecimal financialExposure, List<String> actions) {}
}
