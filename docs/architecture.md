# GRC 架构

版权所有 © 2026 上海如静知华信息科技有限公司。

浏览器通过 Vue 管理端或合规分析师端访问 Spring Boot REST API。安全层完成 JWT 与角色鉴权，业务层负责评估任务、控制项、控制措施、审计和结果记录，JPA/Flyway 管理 MySQL 数据。

管理端角色为 `GRC_MANAGER`、`QUALITY`、`ADMIN`；执行端角色为 `COMPLIANCE_ANALYST`。正式部署建议将控制措施连接置于独立采集服务，并隔离治理域网络和办公网络。
