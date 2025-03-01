package com.cicc.itgm.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum NationEnum {
    CHINA(1, "中国"),
    AMERICA(2, "美国"),
    RUSSIA(3, "俄罗斯"),
    SERBIA(4, "塞尔维亚"),
    SLOVENIA(5, "斯洛文尼亚"),
    GERMANY(6, "德国"),
    JAPAN(7, "日本");

    @EnumValue
    private final Integer nation;
    private final String nationName;

    NationEnum(Integer nation, String nationName) {
        this.nation = nation;
        this.nationName = nationName;
    }
}
