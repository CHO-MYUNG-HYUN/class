package com.hi.deptspring.deptspring.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeptDTO {

    private int deptno;
    private String dname;
    private String loc;
}