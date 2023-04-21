package top.testeru.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject interview-code
 * @Description
 * @createTime 2023年04月19日 15:23:00
 */
@Getter
@Setter
@ToString
public class LabelEntity {
    //知识点
    private String chapter;
    //公司  all（代表通用）
    private String company;
    //等级 文本等级
    private String level;
    //其他
    private String other;
}
