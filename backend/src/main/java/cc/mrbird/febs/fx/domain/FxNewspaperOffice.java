package cc.mrbird.febs.fx.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 *
 * @author moonx
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FxNewspaperOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String addr;

    private String linkman;

    private String tel;

    private String fax;

    private String zip;

    private String bank;

    private String account;

    private String corporation;

    private Integer isValid;

    private String mnemonic;

    private String remark;


}
