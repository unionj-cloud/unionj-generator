package com.tsingxiao.unionj.demo.proto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.tsingxiao.unionj.demo.vo.Order;

public interface StoreProto {

    @GetMapping("/store/inventory")
    Map<String, Integer> getInventory(
        
    );

    @PostMapping("/store/order")
    Order placeOrder(
        @RequestBody(required=false) Order body
    );

    @GetMapping("/store/order/{orderId}")
    Order getOrderById(
        @PathVariable("orderId") Long orderId
    );

    @DeleteMapping("/store/order/{orderId}")
    ResponseEntity<Void> deleteOrder(
        @PathVariable("orderId") Long orderId
    );

}
