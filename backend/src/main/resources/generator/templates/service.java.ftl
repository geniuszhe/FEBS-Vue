package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import cc.mrbird.febs.common.domain.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * @author ${author}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    public IPage<${entity}> findPage(QueryRequest request, ${entity} model);
}
</#if>
