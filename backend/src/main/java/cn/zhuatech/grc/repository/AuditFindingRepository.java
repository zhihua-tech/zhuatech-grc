/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.repository; import cn.zhuatech.grc.model.AuditFinding; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface AuditFindingRepository extends JpaRepository<AuditFinding,Long>{List<AuditFinding> findTop10ByOrderByIdDesc();long countByResult(AuditFinding.Result result);}
