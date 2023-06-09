package com.hi.deptspring.deptspring.controller.rest;

import com.hi.deptspring.deptspring.domain.DeptDTO;
import com.hi.deptspring.deptspring.domain.DeptRegistRequest;
import com.hi.deptspring.deptspring.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/depts")
@Log4j2
public class DeptRestController {


    @Autowired
    private DeptListService listService;

    @Autowired
    private DeptReadService readService;

    @Autowired
    private DeptRegistService registService;

    @Autowired
    private DeptModifyService modifyService;

    @Autowired
    private DeptDeleteService deleteService;

    // get : 전체 리스트
    @GetMapping // /api/v1/depts
    public ResponseEntity<List<DeptDTO>> getDeptlist() {

        // ResponseEntity<T> => 응답 데이터를 제네릭으로 정의
        // body(응답 데이터), httpHeader, statusCode
        // new ResponseEntity<> (응답데이터, httpHeader, statusCode)
        // new ResponseEntity<> (응답데이터, statusCode)

        // Header 정의 => HttpHeaders
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("some-header", "some-value");

        ResponseEntity<List<DeptDTO>> entity =
                new ResponseEntity<>(
                        listService.getList(),
                        httpHeaders,
                        HttpStatus.NOT_FOUND);

        return entity;

    }

    // get /{no} : 부서 하나의 정보
    @GetMapping("/{no}")
    public ResponseEntity<DeptDTO> getDept(
            @PathVariable("no") int deptno
    ) {
        //return readService.getDept(deptno);
        return new ResponseEntity<>(readService.getDept(deptno), HttpStatus.OK);
    }

    // post : JSON 형식의 데이터를 받아서 데이터베이스에 저장
    @PostMapping
    public ResponseEntity<String> regDept(
            @RequestBody DeptRegistRequest registRequest
    ) {
        // JSON 데이터를 JAVA 객체로 받는다.
        log.info("JSON -> DeptRegistRequest : " + registRequest);

        int result = registService.registDept(registRequest);

        return new ResponseEntity<>("insert " + (result == 1 ? "OK" : "FAIL"), HttpStatus.OK);
        // insert OK / Insert FAIL
    }

    // put /{no} : 하나의 부서 정보를 수정
    @PutMapping("/{no}")
    public ResponseEntity<String> edit(
            @PathVariable("no") int deptno,
            @RequestBody DeptDTO dept
    ) {
        log.info("JSON -> DeptDTO : " + dept);

        int result = modifyService.modifyDept(dept);
        String msg = "update " + (result == 1 ? "OK" : "FAIL");
        //return "update";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    // delete /{no} : 부서 정보 하나를 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<String> delete(
            @PathVariable("no") int deptno
    ) {
        log.info("delete >> " + deptno);
        int result = deleteService.deleteDept(deptno);
        String msg = "delete " + (result == 1 ? "OK" : "FAIL");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
