package com.api.WorkOrder.controller;

import com.api.WorkOrder.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "/WorkOrder")
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping(value="/getData")
    public String getFileFromExternalAPI() throws IOException, SQLException {
        return workOrderService.getFile();
    }
    @RequestMapping(value ="/updateEmployee")
    public String updateEmp() {
        return workOrderService.updateDeptNO(1, "Prat");

    }
}