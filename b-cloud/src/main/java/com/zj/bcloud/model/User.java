package com.zj.bcloud.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/20
 * Time: 17:49
 * CopyRight: Zhouji
 */
@Data
@Builder
public class User {

    @Min(1)
    @Max(1000)
    @ApiModelProperty(value="主键", hidden=false, notes="主键，隐藏", required=true, dataType="Long")// 使用该注解描述属性信息,当hidden=true时，该属性不会在api中显示
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value="姓名")
    @NotNull
    private String name;

    @Min(1)
    @Max(200)
    private Integer age;

    @ApiModelProperty(hidden = true)
    private List<String> attr;
}
