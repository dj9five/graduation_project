package com.me.crm.web.action;

import com.me.bean.CompanySearch;
import com.me.crm.container.ServiceProvider;
import com.me.crm.dao.ISysOperateLogDao;
import com.me.crm.domain.*;
import com.me.crm.service.*;
import com.me.crm.util.*;
import com.me.crm.web.form.CompanyForm;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by DJ on 2017/4/25.
 */
public class CompanyAction extends BaseAction implements ModelDriven<CompanyForm> {
    private CompanyForm companyForm = new CompanyForm();
    //获取客户的业务层
    private ICompanyService companyService =
            (ICompanyService) ServiceProvider.getService(ICompanyService.SERVICE_NAME);
    //获取处理下拉选的业务层
    private ISysDictionaryTypeService sysDictionaryTypeService =
            (ISysDictionaryTypeService) ServiceProvider.getService(ISysDictionaryTypeService.SERVICE_NAME);
    //获取省的业务层
    private IProvinceService provinceService =
            (IProvinceService) ServiceProvider.getService(IProvinceService.SERVICE_NAME);
    //获取城市的业务层
    private ICityService cityService =
            (ICityService) ServiceProvider.getService(ICityService.SERVICE_NAME);
    //获取部门的业务层
    private ISysUserGroupService sysUserGroupService =
            (ISysUserGroupService) ServiceProvider.getService(ISysUserGroupService.SERVICE_NAME);
    //获取用户的业务层
    private ISysUserService sysUserService=
            (ISysUserService)ServiceProvider.getService(ISysUserService.SERVICE_NAME);
    @Resource(name = ISysOperateLogDao.SERVICE_NAME)
    private ISysOperateLogDao sysOperateLogDao;
    public String list() {
        //处理客户等级的下拉选
        List<SysDictionaryType> gradesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.GRADE);
        request.setAttribute("gradesSelect", gradesSelect);
        //处理客户来源
        List<SysDictionaryType> sourcesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.SOURCE);
        request.setAttribute("sourcesSelect", sourcesSelect);
        //处理客户性质
        List<SysDictionaryType> qualitySelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.QUALITY);
        request.setAttribute("qualitySelect", qualitySelect);
        //实例化
        CompanySearch companySearch = new CompanySearch();
        companySearch.setCode(companyForm.getCode());
        companySearch.setName(companyForm.getName());
        companySearch.setPycode(companyForm.getPycode());
        companySearch.setGrade(companyForm.getGrade());
        companySearch.setSource(companyForm.getSource());
        companySearch.setQuality(companyForm.getQuality());
        companySearch.setTel1(companyForm.getTel1());
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysCondition(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "list";
        }
        return null;
    }
    /*显示客户添加页面*/
    public String add() {
        //处理客户编码
        String code = companyService.getCompanyCodeByTabName("c_company");
        request.setAttribute("code", code);
        //处理客户下拉选
        //处理客户等级的下拉选
        List<SysDictionaryType> gradesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.GRADE);
        request.setAttribute("gradesSelect", gradesSelect);
        //处理区域名称的下拉选
        List<SysDictionaryType> regionNamesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.REGIONNAME);
        request.setAttribute("regionNamesSelect", regionNamesSelect);
        //获取所有省的信息
        List<Province> provincesSelect = provinceService.findAllProvince();
        request.setAttribute("provincesSelect", provincesSelect);
        //获取当前登录用户
        SysUser curSysUser = SessionUtils.getSysUserFromSession(request);
        if (curSysUser != null) {
            //创建修改人，所属人，所属人id，创建日期，修改日期
            companyForm.setCreater(curSysUser.getCnname());
            companyForm.setUpdater(curSysUser.getCnname());
            companyForm.setDispensePerson(curSysUser.getCnname());
            companyForm.setOwnerUser(curSysUser.getId()+"");
            String curDate= DateFormatUtils.format(new java.util.Date(),"yyyy-MM-dd HH:mm:ss");
            companyForm.setCreateTime(curDate);
            companyForm.setUpdateTime(curDate);
            return "add";
        }
        return null;
    }


    /*
    * 显示省下对应的城市*/
    public String showCity() throws IOException {
        //获取省的名称
        String name = request.getParameter("name");
        if (StringUtils.isNotBlank(name)) {
            //获得省的名称
            Province province = provinceService.findProvinceByName(name);
            if (province != null) {
                //根据省的id查询省的城市信息
                List<City> citiesSelect = cityService.findCtiesByPid(province.getId());
                JsonConfig config = new JsonConfig();
                config.setExcludes(new String[]{"id", "pid", "pycode", "postcode", "areacode"});
                JSONArray jsonArray = JSONArray.fromObject(citiesSelect, config);
                System.out.println(jsonArray.toString());
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(jsonArray.toString());
            }
        }
        return null;
    }

    public String pinyin() throws IOException {
        //获取客户名称
        String name = request.getParameter("name");
        if (StringUtils.isNotBlank(name)) {
            String pin = PingyinUtils.converterToSpell(name.trim());
            response.getWriter().println(pin);
        }
        return null;
    }

    public String save() throws InvocationTargetException, IllegalAccessException {
        //实例化po
        Company company = new Company();
        //注册转化器
        ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);
        //复制po到vo
        BeanUtils.copyProperties(company, companyForm);
        /**
         * 处理特殊情况
         */
        String userid = companyForm.getOwnerUser();
        if (StringUtils.isNotBlank(userid)) {
            SysUser sysUser = new SysUser();
            sysUser.setId(Integer.parseInt(userid.trim()));
            company.setSysUser(sysUser);
        }
        //设置分配给所属人的日期
        company.setDispenseDate(companyForm.getCreateTime());
        company.setShareFlag('N');
        /*
        * 保存 获取当前用户*/
        SysUser curSysUser = SessionUtils.getSysUserFromSession(request);
        if (curSysUser != null) {
            companyService.saveCompany(curSysUser, company);
            return "listAction";
        }
        return null;
    }
    public String update() throws InvocationTargetException, IllegalAccessException {
        //实例化po
        Company company = new Company();
        //注册转化器
        ConvertUtils.register(new SQLDateConverter(), java.sql.Date.class);
        //复制po到vo
        BeanUtils.copyProperties(company, companyForm);
        /**
         * 处理特殊情况
         */
        String userid = companyForm.getOwnerUser();
        if (StringUtils.isNotBlank(userid)) {
            SysUser sysUser = new SysUser();
            sysUser.setId(Integer.parseInt(userid.trim()));
            company.setSysUser(sysUser);
            company.setShareFlag('N');
        /*
        * 保存 获取当前用户*/
        SysUser curSysUser = SessionUtils.getSysUserFromSession(request);
        if (curSysUser != null) {
            companyService.updateCompany(curSysUser, company);
            return "listAction";
        }
        }
        return null;
    }
    /*资源客户升阶*/
    public String advance(){
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        String sid=request.getParameter("ids");
        Integer id=Integer.parseInt(sid.trim());
        Company company=companyService.findCompanyById(id);
        company.setGrade("潜在客户");
        companyService.updateCompany1(curSysuser,company);
        CompanySearch companySearch = new CompanySearch();
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionSource(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listziyuan";

        }
        return "null";
    }
    /*潜在客户升阶*/
    public String advance_b(){
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        String sid=request.getParameter("ids");
        Integer id=Integer.parseInt(sid.trim());
        Company company=companyService.findCompanyById(id);
        company.setGrade("重要客户");
        companyService.updateCompany2(curSysuser,company);
        CompanySearch companySearch = new CompanySearch();

        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionSource(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listqianzai";
        }
        return "null";
    }
    /*重要客户升阶*/
    public String advance_c(){
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        String sid=request.getParameter("ids");
        Integer id=Integer.parseInt(sid.trim());
        Company company=companyService.findCompanyById(id);
        company.setGrade("正式客户");
        companyService.updateCompany3(curSysuser,company);
        CompanySearch companySearch = new CompanySearch();
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionSource(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listzhongyao";
        }
        return "null";
    }
    /*无效客户复活*/
    public String advance_d(){
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        String sid=request.getParameter("ids");
        Integer id=Integer.parseInt(sid.trim());
        Company company=companyService.findCompanyById(id);
        company.setGrade("资源客户");
        companyService.updateCompany4(curSysuser,company);
        CompanySearch companySearch = new CompanySearch();
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionSource(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listwuxiao";
        }
        return "null";
    }
     /*资源客户*/
    public String listziyuan() {
        CompanySearch companySearch = new CompanySearch();
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionSource(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listziyuan";
        }
        return "null";
    }
    /*潜在客户*/
    public String listqianzai() {
        CompanySearch companySearch = new CompanySearch();
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionqianzai(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listqianzai";
        }
        return "null";
    }
    /*重要客户*/
    public String listzhongyao() {
        CompanySearch companySearch = new CompanySearch();
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionzhongyao(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listzhongyao";
        }
        return null;
    }
    /*正式客户*/
    public String listzhengshi() {
        CompanySearch companySearch = new CompanySearch();
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionzhengshi(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listzhengshi";
        }
        return null;
    }
    /*无效客户*/
    public String listwuxiao() {
        CompanySearch companySearch = new CompanySearch();
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionwuxiao(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "listwuxiao";
        }
        return null;
    }
    public CompanyForm getModel() {

        return companyForm;
    }

    public String edit() throws InvocationTargetException, IllegalAccessException {
        //处理客户等级的下拉选
        List<SysDictionaryType> gradesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.GRADE);
        request.setAttribute("gradesSelect", gradesSelect);
        //处理区域名称的下拉选
        List<SysDictionaryType> regionNamesSelect = sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.REGIONNAME);
        request.setAttribute("regionNamesSelect", regionNamesSelect);
        //获取所有省的信息
        List<Province> provincesSelect = provinceService.findAllProvince();
        request.setAttribute("provincesSelect", provincesSelect);

        //获取客户的id
        String sid = request.getParameter("id");
        if (StringUtils.isNotBlank(sid)) {
            //通过客户id获取客户的信息
            Integer id = Integer.parseInt(sid.trim());
            Company company = companyService.findCompanyById(id);
            //赋值客户值到vo中
            BeanUtils.copyProperties(companyForm, company);
            //特殊处理
            //获得省的名称
            String pname = company.getProvince();
            Province province = provinceService.findProvinceByName(pname);
            if (province != null) {
                //根据省的id查询省的城市信息
                List<City> citiesSelect = cityService.findCtiesByPid(province.getId());
                request.setAttribute("citiesSelect", citiesSelect);
            }
            if (company.getSysUser()!=null){
                //处理所属人id
                companyForm.setOwnerUser(company.getSysUser().getId()+"");
            }

            return "edit";
        }

        return null;
    }

    public String delete(){
        String[] sids=request.getParameterValues("ids");
        String id1=request.getParameter("ids");
        Integer id2=Integer.parseInt(id1.trim());
        Company company=companyService.findCompanyById(id2);
        SysUser curSysUser = SessionUtils.getSysUserFromSession(request);
        if (sids!=null&&sids.length>0){
            Integer ids[]= DataType.converterStringArray2IntegerArray(sids);
            companyService.deleteCompanyById(ids,company,curSysUser);
        }
        return "listAction";
    }

    /*下次联系时间*/
    public String showNextTouchTime() {
        return "showNextTouchTime";
    }

    /*修改下次联系时间*/
    public String updateNextTouchTime() {
        //获取客户id
        String ids = request.getParameter("ids");
        if (StringUtils.isNotBlank(ids)) {
            String sid[] = ids.split(",");
            Integer id[] = DataType.converterStringArray2IntegerArray(sid);
//           获取下次联系时间
            String snext_touch_date = request.getParameter("next_touch_date");
            java.sql.Date next_touch_date = java.sql.Date.valueOf(snext_touch_date);
            companyService.updateNextTouchTime(id, next_touch_date);
            return "updateNextTouchTime";
        }
        return null;
    }
    /*显示客户转单页面*/
    public String showChangePerson(){
//        获取系统的用户
        List<SysUser> sysUserSelect=sysUserService.findAllSysUser();
        request.setAttribute("sysUserSelect",sysUserSelect);
        return "showChangePerson";
    }
    /*客户转单操作*/
    public String changeHandler(){
//        获取用户id
        String ids=request.getParameter("ids");
        SysUser curSysUser = SessionUtils.getSysUserFromSession(request);
        if (StringUtils.isNotBlank(ids)){
            String sid[]=ids.split(",");
            Integer id[]=DataType.converterStringArray2IntegerArray(sid);
//            获取要转给的用户id
            String snew_owner=request.getParameter("new_owner");
            if (StringUtils.isNotBlank(snew_owner)){
                Integer new_owner=Integer.parseInt(snew_owner.trim());
                companyService.changeHandler(id,new_owner,curSysUser);
                return "changeHandler";
            }
        }
        return null;
    }

}