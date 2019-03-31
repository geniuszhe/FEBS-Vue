package cc.mrbird.febs.fx.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
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
