/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="grc_audit_finding") public class AuditFinding extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String auditFindingNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private Assessment assessment;
    @Column(nullable=false,length=30) private String auditFindingType; @Column(nullable=false) private int auditFindingQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected AuditFinding(){} /**
                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                */
public AuditFinding(String auditFindingNo,Assessment assessment,String auditFindingType,int auditFindingQty,int defectQty,Result result,String inspector){this.auditFindingNo=auditFindingNo;this.assessment=assessment;this.auditFindingType=auditFindingType;this.auditFindingQty=auditFindingQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getAuditFindingNo(){return auditFindingNo;} /**
                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                               */
public Assessment getAssessment(){return assessment;} /**
                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                     */
public String getAuditFindingType(){return auditFindingType;} /**
                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                   */
public int getAuditFindingQty(){return auditFindingQty;} /**
                                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                            */
public int getDefectQty(){return defectQty;} /**
                                                                                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                         */
public Result getResult(){return result;} /**
                                                                                                                                                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                   */
public String getInspector(){return inspector;}
}
