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
        if(sysCodeRule.getAvailable().equals("Y")){
            //* 获取 流水位=3
            Integer glideBit=sysCodeRule.getGlideBit();
            //* 生成第一个流水号 001
            String firstGlideNumber=DataType.geneFirstGlideNumber(glideBit);
            //* 计算下一个流水号 002
            String nextGlideNumber=DataType.geneNextGlideNumber(firstGlideNumber);

            //* 获取系统的当前时间 格式yyyyMMdd  20110914
            String curDate=DateFormatUtils.format(new Date(), "yyyyMMdd");

            //* 生成客户编码
            //* 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001  c-2011-09-14-001
            String code=sysCodeRule.getAreaPrefix()+"-"
                    +DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime())+"-"+firstGlideNumber;

            //* 修改代码规则表
            //*  下一个序列号="002"
            sysCodeRule.setNextseq(nextGlideNumber);
            //*  当前日期  20110914
            sysCodeRule.setCurDate(curDate);
            //*  是否被修改过='N'
            sysCodeRule.setAvailable("N");
            sysCodeRuleDao.update(sysCodeRule);
            return code;
        }else{  //是否被修改过或新添加的=='N'
            //* 获取代码规则表中的当前日期字段的值
            String curDate=sysCodeRule.getCurDate();

            //* 获取系统的当前日期、
            String sysCurDate=DateFormatUtils.format(new Date(), "yyyyMMdd");
            //* 如果代码规则表中的当前日期字段的值==系统的当前日期、
            if(curDate.equals(sysCurDate)) {
                //* 获取下一个序列号 ="002"
                String nextseq=sysCodeRule.getNextseq();

                //* 计算新的流水号 003
                String nextGlideNumber=DataType.geneNextGlideNumber(nextseq);

                //* 生成客户编码
                //* 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001
                String code=sysCodeRule.getAreaPrefix()+"-"
                        +DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime())+"-"+nextseq;
                //* 修改代码规则表
                //*  下一个序列号="003"
                sysCodeRule.setNextseq(nextGlideNumber);
                //*  当前日期  20110914
                //*  是否被修改过='N'
                sysCodeRuleDao.update(sysCodeRule);
                return code;
            }else{ //如果代码规则表中的当前日期字段的值!=系统的当前日期、

                //* 获取 流水位=3
                Integer glideBit=sysCodeRule.getGlideBit();
                //* 生成第一个流水号 001
                String firstGlideNumber=DataType.geneFirstGlideNumber(glideBit);
                //* 计算下一个流水号 002
                String nextGlideNumber=DataType.geneNextGlideNumber(firstGlideNumber);
                //* 生成客户编码
                //* 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001

                String code=sysCodeRule.getAreaPrefix()+"-"
                        +DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime())+"-"+firstGlideNumber;

                //* 修改代码规则表
                //*  下一个序列号="002"
                sysCodeRule.setNextseq(nextGlideNumber);
                //*  当前日期  20110915
                sysCodeRule.setCurDate(sysCurDate);
                //*  是否被修改过='N'
                sysCodeRule.setAvailable("N");
                sysCodeRuleDao.update(sysCodeRule);
                return code;
            }
        }
    }}

