package com.me.crm.service.impl;

import com.me.crm.dao.ISysCodeRuleDao;
import com.me.crm.domain.SysCodeRule;
import com.me.crm.service.ICompanyService;
import com.me.crm.util.DataType;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by DJ on 2017/4/26.
 */
@Transactional(readOnly = true)
@Service(ICompanyService.SERVICE_NAME)
public class CompanyServiceImpl implements ICompanyService {
    @Resource(name = ISysCodeRuleDao.service_name)
    private ISysCodeRuleDao sysCodeRuleDao;
    //生成客户编码
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public String getCompanyCodeByTabName(String tabName) {
        //获取编码规则，查询sys_code_rule
        String whereHql=" and o.tabName=?";
        Object[]parmas={tabName};
        List<SysCodeRule>list=sysCodeRuleDao.findObjectsByConditionWithNoPage(whereHql,parmas);
        if (list==null||list.size()!=1){
            throw new RuntimeException("不能生成客户的编码");
        }
        SysCodeRule sysCodeRule=list.get(0);
        if (sysCodeRule.getAvailable()=="Y"){
            Integer glideBit=sysCodeRule.getGlideBit();
            //生成第一个流水号
            String firstGlideNumber= DataType.geneFirstGlideNumber(glideBit);
            //生成下一个流水号
            String nextGlideNumber=DataType.geneNextGlideNumber(firstGlideNumber);
            //获取当前日期
            String curDate= DateFormatUtils.format(new Date(),"yyyyMMdd");
            //生成客户编码
            String code=
                    sysCodeRule.getAreaPrefix()+"-"+DateFormatUtils.format(new Date(),sysCodeRule.getAreaTime()+"-"+firstGlideNumber);
            //修改编码
            sysCodeRule.setNextseq(nextGlideNumber);
            sysCodeRule.setCurDate(curDate);
            sysCodeRule.setAvailable("N");
            sysCodeRuleDao.update(sysCodeRule);
          return code;
        }
        return null;
    }
}
