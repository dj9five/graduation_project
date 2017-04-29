package com.me.crm.web.action;

import com.me.bean.CompanySearch;
import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.*;
import com.me.crm.service.ICityService;
import com.me.crm.service.ICompanyService;
import com.me.crm.service.IProvinceService;
import com.me.crm.service.ISysDictionaryTypeService;
import com.me.crm.util.*;
import com.me.crm.web.form.CompanyForm;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

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

    public String listziyuan() {
        CompanySearch companySearch = new CompanySearch();
        SysUser curSysuser = SessionUtils.getSysUserFromSession(request);
        if (curSysuser != null) {
            List<Company> companyList = companyService.findCompanysConditionSource(curSysuser, companySearch);
            request.setAttribute("companyList", companyList);
            return "list";
        }
        return "null";
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
        if (sids!=null&&sids.length>0){
            Integer ids[]= DataType.converterStringArray2IntegerArray(sids);
            companyService.deleteCompanyById(ids);
        }
        return "listAction";
    }
}