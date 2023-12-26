package com.example.material.controller

import com.example.material.MaterialDetailsOuterClass
import com.example.material.MaterialOuterClass
import com.example.material.dto.ApiResponse
import com.example.material.dto.MaterialDTO
import com.example.material.service.MaterialService
import com.example.material.utils.convertToProtobuf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/material")
class MaterialController(@Autowired private val materialService: MaterialService) {
    @GetMapping("", "/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAll(): Flow<MaterialOuterClass.Material> {
        return materialService.getAllMaterial()
            .map { it.convertToProtobuf() }  // ORM data class-> (map function)Class<? extend Protobuf Message> -> (spring)Convert and encode
    }

    @GetMapping("/id/{id}", "/id/{id}/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getById(@PathVariable("id") id: String): MaterialDetailsOuterClass.MaterialDetails {
        return materialService.getMaterialDetailsById(UUID.fromString(id))
    }

    @GetMapping("/criteria", "/criteria/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAllByCriteria(@RequestParam criteriaMap: Map<String, String>): Flow<MaterialOuterClass.Material> {
        return materialService.getMaterialByCriteria(criteriaMap).map { it.convertToProtobuf() }
    }

    @PutMapping("/id/{id}", "/id/{id}/")
    suspend fun update(@PathVariable("id") id: String, materialDTO: MaterialDTO): ApiResponse<Boolean> {
        return ApiResponse(status = true, data = materialService.updateMaterialDTO(materialDTO.copy(id = id)))
    }

    @PostMapping("", "/")
    suspend fun insert(materialDTO: MaterialDTO): ApiResponse<String> {
        return ApiResponse(status = true, data = materialService.insertMaterialDTO(materialDTO).toString())
    }

    @DeleteMapping("/id/{id}", "/id/{id}/")
    suspend fun deleteById(@PathVariable("id") id: String): ApiResponse<Boolean> {
        return ApiResponse(status = true, data = materialService.deleteMaterialById(UUID.fromString(id)))

    }

}