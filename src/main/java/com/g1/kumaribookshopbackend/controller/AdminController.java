package com.g1.kumaribookshopbackend.controller;

import com.g1.kumaribookshopbackend.dto.AdminDto;
import com.g1.kumaribookshopbackend.service.AdminService;
import com.g1.kumaribookshopbackend.service.impl.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.g1.kumaribookshopbackend.util.AppConstant.ADMIN_ROLE;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UtilService utilService;

    @GetMapping("/getAll")
    public ResponseEntity getAllAdmins(@RequestHeader("Authorization") String token) {
        if (utilService.requestAuthentication(token,ADMIN_ROLE)){
            return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/save")
    public ResponseEntity saveAdmin(@RequestHeader("Authorization") String token, @RequestBody AdminDto adminDto) {
        if (utilService.requestAuthentication(token,ADMIN_ROLE)){
            return new ResponseEntity<>(adminService.saveAdmin(adminDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
