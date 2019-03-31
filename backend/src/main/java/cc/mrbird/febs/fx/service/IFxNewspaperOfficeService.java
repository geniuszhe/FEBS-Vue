package cc.mrbird.febs.fx.service;

import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.fx.domain.FxNewspaperOffice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * @author moonx
 */
public interface IFxNewspaperOfficeService extends IService<FxNewspaperOffice> {

    public IPage<FxNewspaperOffice> findPage(QueryRequest request, FxNewspaperOffice model);

    public boolean removeIdLogic(String [] ids);

}
