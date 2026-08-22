/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.repository; import cn.zhuatech.grc.model.Control; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ControlRepository extends JpaRepository<Control,Long>{List<Control> findAllByOrderByCodeAsc();long countByStatus(Control.Status status);}
