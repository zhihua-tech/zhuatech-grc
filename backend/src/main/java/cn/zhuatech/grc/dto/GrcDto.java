/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.dto;
import jakarta.validation.constraints.*; import java.time.*; import java.util.List;
public final class GrcDto { private GrcDto(){}
    public record Metric(String label,String value,String hint,String tone){}
    public record AssessmentView(Long id,String orderNo,String productCode,String productName,String controlDomain,String workshop,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,String status,String batchNo,int progress){}
    public record ControlView(String code,String name,String controlDomain,String status,int oee,LocalDateTime lastHeartbeat){}
    public record AuditFindingView(String auditFindingNo,String orderNo,String productName,String auditFindingType,int auditFindingQty,int defectQty,String result,String inspector){}
    public record Dashboard(List<Metric> metrics,List<AssessmentView> assessments,List<ControlView> control,List<AuditFindingView> auditFindings){}
    public record ReportRequest(@NotBlank String operationName,@Positive int goodQty,@PositiveOrZero int defectQty,@Size(max=200) String remark){}
    public record ReportResult(String orderNo,int completedQty,int defectQty,int progress,String status){}
}
