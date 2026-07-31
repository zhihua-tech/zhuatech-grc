/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.grc.repository; import cn.zhuatech.grc.model.ControlDomain; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface ControlDomainRepository extends JpaRepository<ControlDomain,Long>{Optional<ControlDomain> findByCode(String code);}
