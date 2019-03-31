package cc.mrbird.febs.fx.service.impl;

import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.fx.domain.FxNewspaperOffice;
import cc.mrbird.febs.fx.dao.FxNewspaperOfficeMapper;
import cc.mrbird.febs.fx.service.IFxNewspaperOfficeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author moonx
 */
@Slf4j
@Service("fxNewspaperOfficeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FxNewspaperOfficeServiceImpl extends ServiceImpl<FxNewspaperOfficeMapper, FxNewspaperOffice>
        implements IFxNewspaperOfficeService {

    @Autowired
    ObjectMapper objectMapper;

    public IPage<FxNewspaperOffice> findPage(QueryRequest request, FxNewspaperOffice model){
        try {
            QueryWrapper<FxNewspaperOffice> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("is_valid",1);
            Page<FxNewspaperOffice> page = new Page<>(request.getPageNum(), request.getPageSize());
            return this.page(page,queryWrapper);
        } catch (Exception e) {
            log.error("获取失败", e);
            return null;
        }

    }

}
