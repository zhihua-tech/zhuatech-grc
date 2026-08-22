/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.repository; import cn.zhuatech.grc.model.Assessment; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface AssessmentRepository extends JpaRepository<Assessment,Long>{List<Assessment> findAllByOrderByDueDateAsc();List<Assessment> findByControlDomainCodeOrderByDueDateAsc(String code);long countByStatus(Assessment.Status status);}
