/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.model;
import jakarta.persistence.*;
@Entity @Table(name="grc_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, GRC_MANAGER, COMPLIANCE_ANALYST, QUALITY }
    @Column(nullable=false,unique=true,length=32) private String username; @Column(nullable=false) private String password;
    @Column(nullable=false,length=50) private String fullName; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
    @Column(name="control_domain_code",length=32) private String controlDomainCode; @Column(nullable=false) private boolean enabled=true;
    protected UserAccount(){}
    public UserAccount(String username,String password,String fullName,Role role,String controlDomainCode){this.username=username;this.password=password;this.fullName=fullName;this.role=role;this.controlDomainCode=controlDomainCode;}
    public String getUsername(){return username;} public String getPassword(){return password;} public String getFullName(){return fullName;} public Role getRole(){return role;} public String getControlDomainCode(){return controlDomainCode;} public boolean isEnabled(){return enabled;}
}
