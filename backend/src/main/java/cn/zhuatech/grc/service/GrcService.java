/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.grc.service;
import cn.zhuatech.grc.common.BusinessException; import cn.zhuatech.grc.dto.GrcDto.*; import cn.zhuatech.grc.model.*; import cn.zhuatech.grc.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service @Transactional(readOnly=true) public class GrcService {
    private final AssessmentRepository orders; private final EvidenceRecordRepository reports; private final ControlRepository control; private final AuditFindingRepository auditFindings; private final CurrentUserService current;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public GrcService(AssessmentRepository orders,EvidenceRecordRepository reports,ControlRepository control,AuditFindingRepository auditFindings,CurrentUserService current){this.orders=orders;this.reports=reports;this.control=control;this.auditFindings=auditFindings;this.current=current;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Dashboard shopfloorDashboard(){String center=current.get().getControlDomainCode();List<Assessment> list=center==null?orders.findAllByOrderByDueDateAsc():orders.findByControlDomainCodeOrderByDueDateAsc(center);return dashboard(list);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Dashboard adminDashboard(){return dashboard(orders.findAllByOrderByDueDateAsc());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<AssessmentView> assessments(){return orders.findAllByOrderByDueDateAsc().stream().map(this::view).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Transactional public ReportResult report(Long id,ReportRequest request){Assessment order=orders.findById(id).orElseThrow(()->new BusinessException("评估任务不存在"));if(order.getStatus()==Assessment.Status.COMPLETED)throw new BusinessException("已完成任务不能继续反馈");if(order.getCompletedQty()+request.goodQty()>order.getPlannedQty())throw new BusinessException("完成数量不能超过任务剩余数量");order.report(request.goodQty(),request.defectQty());reports.save(new EvidenceRecord(order,request.operationName(),request.goodQty(),request.defectQty(),current.get().getFullName(),request.remark()));return new ReportResult(order.getOrderNo(),order.getCompletedQty(),order.getDefectQty(),progress(order),order.getStatus().name());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private Dashboard dashboard(List<Assessment> list){int planned=list.stream().mapToInt(Assessment::getPlannedQty).sum(),done=list.stream().mapToInt(Assessment::getCompletedQty).sum(),defects=list.stream().mapToInt(Assessment::getDefectQty).sum();int rate=planned==0?0:Math.round(done*100f/planned);List<Metric> metrics=List.of(new Metric("今日评估",String.format("%,d",planned),list.size()+" 张评估任务","blue"),new Metric("评估完成率",rate+"%",String.format("%,d / %,d",done,planned),"green"),new Metric("结果合规率",String.format("%.1f%%",done+defects==0?100d:done*100d/(done+defects)),defects+" 项异常","warn"),new Metric("控制措施异常",control.countByStatus(Control.Status.ALARM)+"",auditFindings.countByResult(AuditFinding.Result.PENDING)+" 项待审计","red"));return new Dashboard(metrics,list.stream().map(this::view).toList(),control.findAllByOrderByCodeAsc().stream().map(e->new ControlView(e.getCode(),e.getName(),e.getControlDomain().getName(),e.getStatus().name(),e.getOee(),e.getLastHeartbeat())).toList(),auditFindings.findTop10ByOrderByIdDesc().stream().map(i->new AuditFindingView(i.getAuditFindingNo(),i.getAssessment().getOrderNo(),i.getAssessment().getProductName(),i.getAuditFindingType(),i.getAuditFindingQty(),i.getDefectQty(),i.getResult().name(),i.getInspector())).toList());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private AssessmentView view(Assessment o){return new AssessmentView(o.getId(),o.getOrderNo(),o.getProductCode(),o.getProductName(),o.getControlDomain().getName(),o.getControlDomain().getWorkshop(),o.getPlannedQty(),o.getCompletedQty(),o.getDefectQty(),o.getDueDate(),o.getStatus().name(),o.getBatchNo(),progress(o));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private int progress(Assessment o){return o.getPlannedQty()==0?0:Math.min(100,Math.round(o.getCompletedQty()*100f/o.getPlannedQty()));}
}
