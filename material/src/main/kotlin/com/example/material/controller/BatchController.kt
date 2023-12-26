package com.example.material.controller

import com.example.material.MaterialBatchOuterClass
import com.example.material.dto.ApiResponse
import com.example.material.dto.MaterialBatchDTO
import com.example.material.service.BatchService
import com.example.material.utils.convertToProtobuf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/batch")
class BatchController(@Autowired private val batchService: BatchService) {
    @GetMapping("", "/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAll(): Flow<MaterialBatchOuterClass.MaterialBatch> {
        return batchService.getAllBatch().map { it.convertToProtobuf() }
    }

    @GetMapping("/id/{id}", "/id/{id}/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getById(@PathVariable("id") id: String): MaterialBatchOuterClass.MaterialBatch {
        return batchService.getBatchById(UUID.fromString(id)).convertToProtobuf()
    }

    @GetMapping("/material_id/{id}", "/material_id/{id}/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getByMaterialId(@PathVariable("id") id: String): Flow<MaterialBatchOuterClass.MaterialBatch> {
        return batchService.getBatchByMaterialId(UUID.fromString(id)).map { it.convertToProtobuf() }
    }

    @GetMapping(
        "/specification/{specification}",
        "/specification/{specification}/",
        produces = [MediaType.APPLICATION_PROTOBUF_VALUE]
    )
    suspend fun getBySpecificationFuzzy(@PathVariable("specification") specification: String): Flow<MaterialBatchOuterClass.MaterialBatch> {
        return batchService.getBatchBySpecificationFuzzy(specification).map { it.convertToProtobuf() }
    }

    @PutMapping("/id/{id}", "/id/{id}/")
    suspend fun update(@PathVariable("id") id: String, batchDTO: MaterialBatchDTO): ApiResponse<Boolean> {
        return ApiResponse(status = true, data = batchService.updateBatchDTO(batchDTO.copy(id = id)))
    }

    @PostMapping("", "/")
    suspend fun insert(batchDTO: MaterialBatchDTO): ApiResponse<String> {
        return ApiResponse(status = true, data = batchService.insertBatchDTO(batchDTO).toString())
    }

    @DeleteMapping("/id/{id}", "/id/{id}/")
    suspend fun deleteById(@PathVariable("id") id: String): ApiResponse<Boolean> {
        return ApiResponse(status = true, data = batchService.deleteBatchById(UUID.fromString(id)))

    }
}