package com.me.crm.service.impl;

import com.me.bean.CompanySearch;
import com.me.crm.dao.ICompanyDao;
import com.me.crm.dao.ISysCodeRuleDao;
import com.me.crm.dao.ISysOperateLogDao;
import com.me.crm.dao.ISysUserDao;
import com.me.crm.domain.Company;
import com.me.crm.domain.SysCodeRule;
import com.me.crm.domain.SysOperateLog;
import com.me.crm.domain.SysUser;
import com.me.crm.service.ICompanyService;
import com.me.crm.util.DataType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/26.
 */
@Transactional(readOnly = true)
@Service(ICompanyService.SERVICE_NAME)
public class CompanyServiceImpl implements ICompanyService {
    @Resource(name = ISysCodeRuleDao.service_name)
    private ISysCodeRuleDao sysCodeRuleDao;
    @Resource(name = ICompanyDao.SERVICE_NAME)
    private ICompanyDao companyDao;
    @Resource(name = ISysUserDao.SERVICE_NAME)
    private ISysUserDao sysUserDao;
    @Resource(name = ISysOperateLogDao.SERVICE_NAME)
    private ISysOperateLogDao sysOperateLogDao;
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
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveCompany(SysUser curSysUser, Company company) {
        if (curSysUser != null && company != null) {
            companyDao.save(company);
//            添加日志
            SysOperateLog log = new SysOperateLog();
            log.setUserName(curSysUser.getName());
            log.setCnname(curSysUser.getCnname());
            log.setActionType("客户新增");
            String actionContent = "新增一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
            log.setActionContent(actionContent);
            log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
            sysOperateLogDao.save(log);
        }
    }

    public List<Company> findCompanysCondition(SysUser curSysuser, CompanySearch companySearch) {
        if (curSysuser != null && companySearch != null) {
            String whereHql = "";
            List paramsList = new ArrayList();
            if (curSysuser.getId() != null) {
                whereHql = whereHql + " and o.sysUser.id=?";
                paramsList.add(curSysuser.getId());
            }
            if (StringUtils.isNotBlank(companySearch.getCode())) {
                whereHql = whereHql + " and o.code like ?";
                paramsList.add("%" + companySearch.getCode().trim() + "%");
            }
            if (StringUtils.isNotBlank(companySearch.getName())) {
                whereHql = whereHql + " and o.name like ?";
                paramsList.add("%" + companySearch.getName().trim() + "%");
            }
            if (StringUtils.isNotBlank(companySearch.getGrade())) {
                whereHql = whereHql + " and o.grade like ?";
                paramsList.add("%" + companySearch.getGrade().trim() + "%");
            }
            if (StringUtils.isNotBlank(companySearch.getPycode())) {
                whereHql = whereHql + " and o.pycode like ?";
                paramsList.add("%" + companySearch.getPycode().trim() + "%");
            }
            if (StringUtils.isNotBlank(companySearch.getQuality())) {
                whereHql = whereHql + " and o.quality like ?";
                paramsList.add("%" + companySearch.getCode().trim() + "%");
            }
            if (StringUtils.isNotBlank(companySearch.getSource())) {
                whereHql = whereHql + " and o.source like ?";
                paramsList.add("%" + companySearch.getSource().trim() + "%");
            }
            if (StringUtils.isNotBlank(companySearch.getTel1())) {
                whereHql = whereHql + " and o.tel1 like ? ";
                paramsList.add("%" + companySearch.getTel1().trim() + "%");
            }
            LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
            orderby.put("o.id", "asc");
            return companyDao.findObjectsByConditionWithNoPage(whereHql, paramsList.toArray(), orderby);
        }
        return null;
    }

    public List<Company> findCompanysConditionSource(SysUser curSysuser, CompanySearch companySearch) {
        if (curSysuser != null && companySearch != null) {
            String whereHql = "";
            List paramsList = new ArrayList();
            if (curSysuser.getId() != null) {
                whereHql = whereHql + " and o.sysUser.id=?";
                paramsList.add(curSysuser.getId());
            }
            whereHql = whereHql + " and o.grade like ?";
            paramsList.add("%" + "资源客户" + "%");
            LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
            orderby.put("o.id", "asc");
            return companyDao.findObjectsByConditionWithNoPage(whereHql, paramsList.toArray(), orderby);
        }
        return null;
    }

    public Company findCompanyById(Integer id) {
        return companyDao.findObjectById(id);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCompany(SysUser curSysUser, Company company) {
        companyDao.update(company);
        SysOperateLog log = new SysOperateLog();
        log.setUserName(curSysUser.getName());
        log.setCnname(curSysUser.getCnname());
        log.setActionType("客户修改");
        String actionContent = "修改一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
        log.setActionContent(actionContent);
        log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
        sysOperateLogDao.save(log);

    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteCompanyById(Integer[] ids, Company company, SysUser curSysUser) {

        companyDao.deleteById((java.io.Serializable[]) ids);
        SysOperateLog log = new SysOperateLog();
        log.setUserName(curSysUser.getName());
        log.setCnname(curSysUser.getCnname());
        log.setActionType("客户删除");
        String actionContent = "删除一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
        log.setActionContent(actionContent);
        log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
        sysOperateLogDao.save(log);
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void addUpdateShareSetOne(String s_module, Integer id, Integer[] uids) {
        if (StringUtils.isNotBlank(s_module) && id != null && uids != null && uids.length > 0) {
            if ("c_company".equals(s_module)) {
                Company company = companyDao.findObjectById(id);
                if (company != null) {
                    StringBuffer buf = new StringBuffer();
                    for (int i = 0; i < uids.length; i++) {
                        buf.append(uids[i] + "#");
                    }
                    if ('N' == company.getShareFlag()) {
                        company.setShareFlag('Y');
                        company.setShareIds("#" + buf.toString());
                        companyDao.update(company);
                    } else {
                        company.setShareFlag('Y');
                        company.setShareIds(company.getShareIds() + buf.toString());
                    }
                }
            }
        }

    }

    public List<Company> findCompanysConditionqianzai(SysUser curSysuser, CompanySearch companySearch) {
        if (curSysuser != null && companySearch != null) {
            String whereHql = "";
            List paramsList = new ArrayList();
            if (curSysuser.getId() != null) {
                whereHql = whereHql + " and o.sysUser.id=?";
                paramsList.add(curSysuser.getId());
            }
            whereHql = whereHql + " and o.grade like ?";
            paramsList.add("%" + "潜在客户" + "%");
            LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
            orderby.put("o.id", "asc");
            return companyDao.findObjectsByConditionWithNoPage(whereHql, paramsList.toArray(), orderby);
        }
        return null;
    }

    public List<Company> findCompanysConditionzhongyao(SysUser curSysuser, CompanySearch companySearch) {
        if (curSysuser != null && companySearch != null) {
            String whereHql = "";
            List paramsList = new ArrayList();
            if (curSysuser.getId() != null) {
                whereHql = whereHql + " and o.sysUser.id=?";
                paramsList.add(curSysuser.getId());
            }
            whereHql = whereHql + " and o.grade like ?";
            paramsList.add("%" + "重要客户" + "%");
            LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
            orderby.put("o.id", "asc");
            return companyDao.findObjectsByConditionWithNoPage(whereHql, paramsList.toArray(), orderby);
        }
        return null;
    }

    public List<Company> findCompanysConditionzhengshi(SysUser curSysuser, CompanySearch companySearch) {
        if (curSysuser != null && companySearch != null) {
            String whereHql = "";
            List paramsList = new ArrayList();
            if (curSysuser.getId() != null) {
                whereHql = whereHql + " and o.sysUser.id=?";
                paramsList.add(curSysuser.getId());
            }
            whereHql = whereHql + " and o.grade like ?";
            paramsList.add("%" + "正式客户" + "%");
            LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
            orderby.put("o.id", "asc");
            return companyDao.findObjectsByConditionWithNoPage(whereHql, paramsList.toArray(), orderby);
        }
        return null;
    }

    public List<Company> findCompanysConditionwuxiao(SysUser curSysuser, CompanySearch companySearch) {
        if (curSysuser != null && companySearch != null) {
            String whereHql = "";
            List paramsList = new ArrayList();
            if (curSysuser.getId() != null) {
                whereHql = whereHql + " and o.sysUser.id=?";
                paramsList.add(curSysuser.getId());
            }
            whereHql = whereHql + " and o.grade like ?";
            paramsList.add("%" + "无效客户" + "%");
            LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
            orderby.put("o.id", "asc");
            return companyDao.findObjectsByConditionWithNoPage(whereHql, paramsList.toArray(), orderby);
        }
        return null;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateNextTouchTime(Integer[] id, java.sql.Date next_touch_date) {
        if (id != null && id.length > 0 && next_touch_date != null) {
            for (int i = 0; i < id.length; i++) {
                Company company = companyDao.findObjectById(id[i]);
                if (company != null) {
                    company.setNextTouchDate(next_touch_date);
                    companyDao.update(company);
                }
            }
        }
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void changeHandler(Integer[] id, Integer new_owner, SysUser curSysUser) {
        if (id != null && id.length > 0 && new_owner != null) {
            SysUser sysUser = sysUserDao.findObjectById(new_owner);
            for (int i = 0; i < id.length; i++) {
                Company company = companyDao.findObjectById(id[i]);
                if (company != null && sysUser != null) {
                    company.setSysUser(sysUser);
                    company.setDispensePerson(sysUser.getCnname());
                    company.setDispenseDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
                    companyDao.update(company);
                    SysOperateLog log = new SysOperateLog();
                    log.setUserName(curSysUser.getName());
                    log.setCnname(curSysUser.getCnname());
                    log.setActionType("客户转单");
                    String actionContent = "转走一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
                    log.setActionContent(actionContent);
                    log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
                    sysOperateLogDao.save(log);

                }
            }
        }
    }

    /*资源客户升阶与日志*/
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCompany1(SysUser curSysuser, Company company) {
        companyDao.update(company);
        SysOperateLog log = new SysOperateLog();
        log.setUserName(curSysuser.getName());
        log.setCnname(curSysuser.getCnname());
        log.setActionType("资源客户转潜在");
        String actionContent = "升阶一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
        log.setActionContent(actionContent);
        log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
        sysOperateLogDao.save(log);
    }
/*潜在客户升阶与日志*/
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCompany2(SysUser curSysuser, Company company) {
    companyDao.update(company);
    SysOperateLog log = new SysOperateLog();
    log.setUserName(curSysuser.getName());
    log.setCnname(curSysuser.getCnname());
    log.setActionType("潜在客户转重要");
    String actionContent = "升阶一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
    log.setActionContent(actionContent);
    log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
    sysOperateLogDao.save(log);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCompany3(SysUser curSysuser, Company company) {
        companyDao.update(company);
        SysOperateLog log = new SysOperateLog();
        log.setUserName(curSysuser.getName());
        log.setCnname(curSysuser.getCnname());
        log.setActionType("重要客户转正式");
        String actionContent = "升阶一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
        log.setActionContent(actionContent);
        log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
        sysOperateLogDao.save(log);
    }
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCompany4(SysUser curSysuser, Company company) {
        companyDao.update(company);
        SysOperateLog log = new SysOperateLog();
        log.setUserName(curSysuser.getName());
        log.setCnname(curSysuser.getCnname());
        log.setActionType("无效客户复活");
        String actionContent = "复活一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:" + company.getCode() + "]";
        log.setActionContent(actionContent);
        log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
        sysOperateLogDao.save(log);
    }
}

