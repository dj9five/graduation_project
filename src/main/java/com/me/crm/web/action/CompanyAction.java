package com.me.crm.web.action;

import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.City;
import com.me.crm.domain.Province;
import com.me.crm.domain.SysDictionaryType;
import com.me.crm.domain.SysUser;
import com.me.crm.service.ICityService;
import com.me.crm.service.ICompanyService;
import com.me.crm.service.IProvinceService;
import com.me.crm.service.ISysDictionaryTypeService;
import com.me.crm.util.Global;
import com.me.crm.util.PingyinUtils;
import com.me.crm.util.SessionUtils;
import com.me.crm.web.form.CompanyForm;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.io.IOException;
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
        return "list";
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
    public String save(){
        return "listAction";
    }

    public CompanyForm getModel() {
        return companyForm;
    }
}
