/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="grc_evidence_record") public class EvidenceRecord extends BaseEntity {
    @ManyToOne(optional=false,fetch=FetchType.LAZY) private Assessment assessment; @Column(nullable=false,length=50) private String operationName; @Column(nullable=false) private int goodQty; @Column(nullable=false) private int defectQty;
    @Column(nullable=false,length=50) private String operatorName; @Column(nullable=false) private LocalDateTime reportedAt; @Column(length=200) private String remark;
    protected EvidenceRecord(){} public EvidenceRecord(Assessment assessment,String operationName,int goodQty,int defectQty,String operatorName,String remark){this.assessment=assessment;this.operationName=operationName;this.goodQty=goodQty;this.defectQty=defectQty;this.operatorName=operatorName;this.reportedAt=LocalDateTime.now();this.remark=remark;}
    public Assessment getAssessment(){return assessment;} public String getOperationName(){return operationName;} public int getGoodQty(){return goodQty;} public int getDefectQty(){return defectQty;} public String getOperatorName(){return operatorName;} public LocalDateTime getReportedAt(){return reportedAt;}
}
