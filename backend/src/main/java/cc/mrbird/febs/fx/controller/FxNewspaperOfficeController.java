package cc.mrbird.febs.fx.controller;


import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.fx.domain.FxNewspaperOffice;
import cc.mrbird.febs.fx.service.IFxNewspaperOfficeService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author moonx
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/newspaper-office")
public class FxNewspaperOfficeController extends BaseController {

    private String message;

    @Autowired
    private IFxNewspaperOfficeService fxNewspaperOfficeService;

    @GetMapping
    //@RequiresPermissions("dict:view")
    public Map<String, Object> list(QueryRequest request, FxNewspaperOffice model) {
        return getDataTable(this.fxNewspaperOfficeService.findPage(request,model));
    }

    @Log("新增报刊")
    @PostMapping
    @RequiresPermissions("newspaper-office:add")
    public void addFxNewspaperOffice(@Valid FxNewspaperOffice model) throws FebsException {
        try {
            this.fxNewspaperOfficeService.save(model);
        } catch (Exception e) {
            message = "新增报刊失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除报刊")
    @DeleteMapping("/{modelIds}")
    @RequiresPermissions("newspaper-office:delete")
    public void deleteFxNewspaperOffices(@NotBlank(message = "{required}") @PathVariable String modelIds) throws FebsException {
        try {
            String[] ids = modelIds.split(StringPool.COMMA);
            this.fxNewspaperOfficeService.removeById(ids);
        } catch (Exception e) {
            message = "删除报刊失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改报刊")
    @PutMapping
    @RequiresPermissions("newspaper-office:update")
    public void updateFxNewspaperOffice(@Valid FxNewspaperOffice model) throws FebsException {
        try {
            this.fxNewspaperOfficeService.updateById(model);
        } catch (Exception e) {
            message = "修改报刊失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

  /*  @PostMapping("excel")
    @RequiresPermissions("newspaper-office:export")
    public void export(FxNewspaperOffice model, QueryRequest request, HttpServletResponse response) throws FebsException {
        try {
            List<FxNewspaperOffice> models = this.fxNewspaperOfficeService.findFxNewspaperOffices(model, request);
            ExcelKit.$Export(FxNewspaperOffice.class, response).downXlsx(models, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }*/

}
