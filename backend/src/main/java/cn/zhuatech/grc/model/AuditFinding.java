/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.grc.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="grc_audit_finding") public class AuditFinding extends BaseEntity {
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String auditFindingNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private Assessment assessment;
    @Column(nullable=false,length=30) private String auditFindingType; @Column(nullable=false) private int auditFindingQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    protected AuditFinding(){} public AuditFinding(String auditFindingNo,Assessment assessment,String auditFindingType,int auditFindingQty,int defectQty,Result result,String inspector){this.auditFindingNo=auditFindingNo;this.assessment=assessment;this.auditFindingType=auditFindingType;this.auditFindingQty=auditFindingQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    public String getAuditFindingNo(){return auditFindingNo;} public Assessment getAssessment(){return assessment;} public String getAuditFindingType(){return auditFindingType;} public int getAuditFindingQty(){return auditFindingQty;} public int getDefectQty(){return defectQty;} public Result getResult(){return result;} public String getInspector(){return inspector;}
}
