package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * @author ${author}
 */
<#if restControllerStyle>
@Slf4j
@Validated
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/${entity?uncap_first}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} extends BaseController{
    </#if>
    private String message;

    @Autowired
    private ${table.serviceName} ${entity?uncap_first}Service;

    @GetMapping
    @RequiresPermissions("${entity?uncap_first}:view")
    public Map<String, Object> list(QueryRequest request, ${entity} model) {
      return getDataTable(this.${entity?uncap_first}Service.findPage(request,model));
    }

    @Log("新增")
    @PostMapping
    @RequiresPermissions("${entity?uncap_first}:add")
    public void add${entity}(@Valid ${entity} model) throws FebsException {
    try {
      this.${entity?uncap_first}Service.save(model);
    } catch (Exception e) {
      message = "新增失败";
      log.error(message, e);
      throw new FebsException(message);
    }
    }

    @Log("删除")
    @DeleteMapping("/{modelIds}")
    @RequiresPermissions("${entity?uncap_first}:delete")
    public void delete${entity}s(@NotBlank(message = "{required}") @PathVariable String modelIds) throws FebsException {
    try {
      String[] ids = modelIds.split(StringPool.COMMA);
      this.${entity?uncap_first}Service.removeById(ids);
    } catch (Exception e) {
      message = "删除失败";
      log.error(message, e);
      throw new FebsException(message);
    }
    }

    @Log("修改")
    @PutMapping
    @RequiresPermissions("${entity?uncap_first}:update")
    public void update${entity}(@Valid ${entity} model) throws FebsException {
    try {
      this.${entity?uncap_first}Service.updateById(model);
    } catch (Exception e) {
      message = "修改失败";
      log.error(message, e);
      throw new FebsException(message);
    }
    }
}
</#if>
