package com.example.material.controller

import com.example.material.MaterialOuterClass
import com.example.material.service.MaterialService
import com.example.material.utils.convertProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/material")
class MaterialController(@Autowired private val materialService: MaterialService) {
    @GetMapping("", "/", produces = [MediaType.APPLICATION_PROTOBUF_VALUE])
    suspend fun getAll(): Flow<MaterialOuterClass.Material> {
        return materialService.getAllMaterial()
            .map { it.convertProto() }  // ORM data class-> (map function)Class<? extend Protobuf Message> -> (spring)Convert and encode
    }

}