/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="grc_control") public class Control extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Status { RUNNING, IDLE, MAINTENANCE, ALARM }
    @Column(nullable=false,unique=true,length=32) private String code; @Column(nullable=false,length=80) private String name; @ManyToOne(optional=false,fetch=FetchType.LAZY) private ControlDomain controlDomain;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status; @Column(nullable=false) private int oee; @Column(nullable=false) private LocalDateTime lastHeartbeat;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected Control(){} /**
                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                           */
public Control(String code,String name,ControlDomain controlDomain,Status status,int oee){this.code=code;this.name=name;this.controlDomain=controlDomain;this.status=status;this.oee=oee;this.lastHeartbeat=LocalDateTime.now();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getCode(){return code;} /**
                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                           */
public String getName(){return name;} /**
                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                 */
public ControlDomain getControlDomain(){return controlDomain;} /**
                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                */
public Status getStatus(){return status;} /**
                                                                                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                          */
public int getOee(){return oee;} /**
                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                           */
public LocalDateTime getLastHeartbeat(){return lastHeartbeat;}
}
