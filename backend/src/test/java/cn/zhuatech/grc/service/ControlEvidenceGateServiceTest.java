/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ControlEvidenceGateServiceTest {
    private final ControlEvidenceGateService service = new ControlEvidenceGateService();

    @Test void confirmsEffectiveControl() {
        var result = service.assess(new ControlEvidenceGateService.Request(
            "CTRL-001", "owner-a", "2026-Q3", 5, 3, 10, 30, true, false, false, false, 95));
        assertThat(result.decision()).isEqualTo(ControlEvidenceGateService.Decision.EFFECTIVE);
    }

    @Test void rejectsInsufficientOrUnapprovedEvidence() {
        var result = service.assess(new ControlEvidenceGateService.Request(
            "CTRL-002", "owner-b", "2026-Q3", 1, 3, 60, 30, false, true, false, true, 60));
        assertThat(result.decision()).isEqualTo(ControlEvidenceGateService.Decision.INEFFECTIVE);
        assertThat(result.blockers()).hasSize(5);
    }

    @Test void reviewsApprovedExceptionAndCoverage() {
        var result = service.assess(new ControlEvidenceGateService.Request(
            "CTRL-003", "owner-c", "2026-Q3", 4, 3, 10, 30, true, true, true, false, 80));
        assertThat(result.decision()).isEqualTo(ControlEvidenceGateService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(2);
    }
}
