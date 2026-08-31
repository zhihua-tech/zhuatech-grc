/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ControlEvidenceGateService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (request.evidenceCount() < request.minimumEvidenceCount()) blockers.add("控制证据数量不足");
        if (request.evidenceFreshnessDays() > request.maxFreshnessDays()) blockers.add("控制证据已超过有效期");
        if (!request.segregationOfDutiesMet()) blockers.add("控制执行与复核未实现职责分离");
        if (request.remediationOverdue()) blockers.add("整改任务已逾期");
        if (request.exceptionOpen() && !request.exceptionApproved()) blockers.add("存在未经批准的控制例外");
        if (!blockers.isEmpty()) {
            actions.add("控制判定无效，补齐证据和整改后重新评估");
            return new Assessment(Decision.INEFFECTIVE, blockers, actions);
        }
        if (request.exceptionOpen() || request.sampleCoveragePercent() < 90) {
            if (request.exceptionOpen()) actions.add("跟踪已批准例外的到期日与补偿性控制");
            if (request.sampleCoveragePercent() < 90) actions.add("扩大抽样覆盖率至至少 90%");
            return new Assessment(Decision.REVIEW, blockers, actions);
        }
        actions.add("确认控制有效并冻结本期证据快照");
        return new Assessment(Decision.EFFECTIVE, blockers, actions);
    }

    public record Request(@NotBlank String controlId, @NotBlank String ownerId,
                          @NotBlank String assessmentPeriod, @Min(0) int evidenceCount,
                          @Min(1) int minimumEvidenceCount, @Min(0) int evidenceFreshnessDays,
                          @Min(1) int maxFreshnessDays, boolean segregationOfDutiesMet,
                          boolean exceptionOpen, boolean exceptionApproved,
                          boolean remediationOverdue,
                          @Min(0) @Max(100) int sampleCoveragePercent) {}
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { EFFECTIVE, REVIEW, INEFFECTIVE }
}
