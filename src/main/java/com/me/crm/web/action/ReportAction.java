package com.me.crm.web.action;

import com.me.bean.ReportBean;
import com.me.crm.container.ServiceProvider;
import com.me.crm.service.IReportService;
import com.me.crm.util.JFreeChartUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
public class ReportAction extends BaseAction {
    private IReportService reportService=
            (IReportService) ServiceProvider.getService(IReportService.SERVICE_NAME);
    public String khflfx() throws IOException {
        List<ReportBean> reportBeans=reportService.findReportBeans();
        request.setAttribute("reportBeans",reportBeans);
//        计算客户总数
        long sum=0L;
        if (reportBeans!=null&&reportBeans.size()>0){
            for (ReportBean reportBean:reportBeans){
                sum=sum+reportBean.getCount();
            }
        }
        request.setAttribute("sum",sum);
        ServletContext sc= ServletActionContext.getServletContext();
//        生成图片
        JFreeChartUtils.generalBarJpeg(reportBeans,request,sc);
        return "khflfx";
    }
}
