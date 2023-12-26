package com.example.material.controller

import com.example.material.MaterialSupplierOuterClass
import com.example.material.dto.ApiResponse
import com.example.material.dto.MatchSupplierDTO
import com.example.material.service.SupplierService
import com.example.material.utils.convertToProtobuf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/supplier")
class SupplierController(@Autowired private val supplierService: SupplierService) {
    @GetMapping("", "/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAll(): Flow<MaterialSupplierOuterClass.MaterialSupplier> {
        return supplierService.getAllSupplier().map { it.convertToProtobuf() }
    }

    @GetMapping("/id/{id}", "/id/{id}/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getById(@PathVariable("id") id: String): MaterialSupplierOuterClass.MaterialSupplier {
        return supplierService.getSupplierById(UUID.fromString(id)).convertToProtobuf()
    }

    @GetMapping("/name/{name}", "/name/{name}/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getByNameFuzzy(@PathVariable("name") name: String): Flow<MaterialSupplierOuterClass.MaterialSupplier> {
        return supplierService.getSupplierByNameFuzzy(name).map { it.convertToProtobuf() }
    }

    @PutMapping("/id/{id}", "/id/{id}/")
    suspend fun update(@PathVariable("id") id: String, supplierDTO: MatchSupplierDTO): ApiResponse<Boolean> {
        return ApiResponse(
            status = true,
            data = supplierService.updateSupplierDTO(supplierDTO.copy(id = id))
        )
    }

    @PostMapping("/id/{id}", "/")
    suspend fun insert(supplierDTO: MatchSupplierDTO): ApiResponse<String> {
        return ApiResponse(status = true, data = supplierService.insertSupplierDTO(supplierDTO).toString())
    }

    @DeleteMapping("/id/{id}", "/id/{id}/")
    suspend fun deleteById(@PathVariable("id") id: String): ApiResponse<Boolean> {
        return ApiResponse(status = true, data = supplierService.deleteSupplierById(UUID.fromString(id)))

    }
}