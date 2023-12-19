package com.example.material.controller

import com.example.material.MaterialSupplierOuterClass
import com.example.material.service.SupplierService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/supplier")
class SupplierController(@Autowired private val supplierService: SupplierService) {
    @GetMapping("", "/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAll(): Flow<MaterialSupplierOuterClass.MaterialSupplier> {
        TODO()
    }
}