/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.grc.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="grc_control") public class Control extends BaseEntity {
    public enum Status { RUNNING, IDLE, MAINTENANCE, ALARM }
    @Column(nullable=false,unique=true,length=32) private String code; @Column(nullable=false,length=80) private String name; @ManyToOne(optional=false,fetch=FetchType.LAZY) private ControlDomain controlDomain;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status; @Column(nullable=false) private int oee; @Column(nullable=false) private LocalDateTime lastHeartbeat;
    protected Control(){} public Control(String code,String name,ControlDomain controlDomain,Status status,int oee){this.code=code;this.name=name;this.controlDomain=controlDomain;this.status=status;this.oee=oee;this.lastHeartbeat=LocalDateTime.now();}
    public String getCode(){return code;} public String getName(){return name;} public ControlDomain getControlDomain(){return controlDomain;} public Status getStatus(){return status;} public int getOee(){return oee;} public LocalDateTime getLastHeartbeat(){return lastHeartbeat;}
}
