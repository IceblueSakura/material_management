package com.example.material.utils

import com.example.material.MaterialOuterClass
import com.example.material.entity.Material

fun Material.convertProto(): MaterialOuterClass.Material {
    return MaterialOuterClass.Material.newBuilder()
        .setId(this.id.toString())
        .setMaterialName(this.materialName)
        .setDescription(this.description)
        .setSpecification(this.specification)
        .setMaterialType(this.materialType)
        .build()
}