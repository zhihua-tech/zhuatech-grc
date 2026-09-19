/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.repository; import cn.zhuatech.grc.model.ControlDomain; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface ControlDomainRepository extends JpaRepository<ControlDomain,Long>{/**
                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                    */
Optional<ControlDomain> findByCode(String code);}
