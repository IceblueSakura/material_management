package com.example.material.controller

import com.example.material.MaterialBatchOuterClass
import com.example.material.service.BatchService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/batch")
class BatchController(@Autowired private val batchService: BatchService) {
    @GetMapping("", "/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAll(): Flow<MaterialBatchOuterClass.MaterialBatch> {
        TODO()
    }
}