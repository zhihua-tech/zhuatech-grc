/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.repository; import cn.zhuatech.grc.model.Assessment; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface AssessmentRepository extends JpaRepository<Assessment,Long>{/**
                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                              */
List<Assessment> findAllByOrderByDueDateAsc();/**
                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                            */
List<Assessment> findByControlDomainCodeOrderByDueDateAsc(String code);/**
                                                                                                                                                                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                   */
long countByStatus(Assessment.Status status);}
