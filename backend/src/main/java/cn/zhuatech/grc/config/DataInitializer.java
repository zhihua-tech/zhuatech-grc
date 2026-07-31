/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.grc.config;

import cn.zhuatech.grc.model.*;
import cn.zhuatech.grc.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(ControlDomainRepository controlDomains, AssessmentRepository orders,
                           ControlRepository controls, AuditFindingRepository auditFindings,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (controlDomains.count() > 0) return;
            ControlDomain chemistry = controlDomains.save(new ControlDomain("GRC-CHEM", "内控与合规域", "合规中心", 180));
            ControlDomain micro = controlDomains.save(new ControlDomain("GRC-MICRO", "数据隐私治理域", "研发中心", 120));
            ControlDomain material = controlDomains.save(new ControlDomain("GRC-MAT", "业务连续性治理域", "工程中心", 96));

            Assessment t1 = orders.save(new Assessment("ASM-260801-018", "GB-T-228", "采购授权控制", material, 24, 16, 1, LocalDate.now().plusDays(1), Assessment.Status.RUNNING, "S260801-A"));
            Assessment t2 = orders.save(new Assessment("ASM-260801-021", "HPLC-042", "个人信息保护控制", chemistry, 18, 8, 0, LocalDate.now().plusDays(1), Assessment.Status.RUNNING, "S260801-C"));
            Assessment t3 = orders.save(new Assessment("ASM-260802-006", "ISO-4833", "灾备演练控制", micro, 12, 0, 0, LocalDate.now().plusDays(3), Assessment.Status.RELEASED, "S260802-B"));
            Assessment t4 = orders.save(new Assessment("ASM-260731-015", "ICP-017", "特权账号审计", chemistry, 20, 20, 1, LocalDate.now(), Assessment.Status.COMPLETED, "S260731-D"));

            controls.saveAll(List.of(
                new Control("CTL-HPLC-03", "合规证据采集器 03", chemistry, Control.Status.RUNNING, 88),
                new Control("CTL-ICP-02", "控制项自动核验器", chemistry, Control.Status.IDLE, 76),
                new Control("CTL-UTM-05", "业务连续性监测器", material, Control.Status.RUNNING, 91),
                new Control("CTL-INC-08", "隐私风险扫描器 08", micro, Control.Status.ALARM, 62)
            ));
            auditFindings.saveAll(List.of(
                new AuditFinding("FND-260801-032", t1, "留样审计", 6, 0, AuditFinding.Result.PASSED, "周妍"),
                new AuditFinding("FND-260801-011", t2, "前处理审计", 3, 0, AuditFinding.Result.PASSED, "陆承"),
                new AuditFinding("FND-260801-018", t4, "结果审计", 5, 1, AuditFinding.Result.FAILED, "周妍"),
                new AuditFinding("FND-260802-003", t3, "收样确认", 4, 0, AuditFinding.Result.PENDING, "陆承")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "陆承", UserAccount.Role.COMPLIANCE_ANALYST, "GRC-CHEM"),
                new UserAccount("planner", demo, "周妍", UserAccount.Role.GRC_MANAGER, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
