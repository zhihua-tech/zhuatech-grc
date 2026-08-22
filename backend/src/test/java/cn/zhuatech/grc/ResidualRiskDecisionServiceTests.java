/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc;

import cn.zhuatech.grc.service.ResidualRiskDecisionService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResidualRiskDecisionServiceTests {
    private final ResidualRiskDecisionService service = new ResidualRiskDecisionService();

    @Test
    void escalatesHighResidualRegulatoryRisk() {
        var result = service.evaluate(new ResidualRiskDecisionService.Request(
            "RISK-DATA-01", 80, .40, new BigDecimal("800000"), true, 2, 120));

        assertEquals(89, result.residualRiskScore());
        assertEquals("ESCALATE", result.decision());
    }

    @Test
    void acceptsLowResidualRiskWithFreshEvidence() {
        var result = service.evaluate(new ResidualRiskDecisionService.Request(
            "RISK-OPS-02", 30, .80, new BigDecimal("50000"), false, 0, 30));

        assertEquals(6, result.residualRiskScore());
        assertEquals("ACCEPT", result.decision());
    }
}
