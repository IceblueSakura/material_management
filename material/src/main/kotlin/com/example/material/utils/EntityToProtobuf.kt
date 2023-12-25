package com.example.material.utils

import com.example.material.MaterialBatchOuterClass
import com.example.material.MaterialOuterClass
import com.example.material.MaterialSupplierOuterClass
import com.example.material.entity.Material
import com.example.material.entity.MaterialBatch
import com.example.material.entity.MaterialSupplier
import com.google.protobuf.Timestamp


/**
 * Converts a [Material] entity to a protobuf representation.
 *
 * The function suspend keyword is optional because the function segment does not have ‘suspend’ methods
 *
 * @return The protobuf representation of [Material] entity.
 */
fun Material.convertToProtobuf(): MaterialOuterClass.Material {
    return MaterialOuterClass.Material.newBuilder().setId(this.id.toString()).setMaterialName(this.materialName)
        .setDescription(this.description).setMaterialType(this.materialType).build()
}

/**
 * Converts a [MaterialBatch] entity to a protobuf representation.
 *
 * The function suspend keyword is optional because the function segment does not have ‘suspend’ methods
 *
 * @return The protobuf representation of the [MaterialBatch] entity.
 */
fun MaterialBatch.convertToProtobuf(): MaterialBatchOuterClass.MaterialBatch {
    return MaterialBatchOuterClass.MaterialBatch.newBuilder().setId(this.id.toString())
        .setMaterialId(this.materialId.toString()).setSupplierId(this.supplierId.toString())
        .setSpecification(this.specification).setDescription(this.description)
        .setCreateAt(Timestamp.newBuilder().setNanos(this.createAt!!.nanos)).build()
}

/**
 * Converts a [MaterialSupplier] entity to a protobuf representation.
 *
 * The function suspend keyword is optional because the function segment does not have ‘suspend’ methods
 *
 * @return The protobuf representation of the [MaterialSupplier] entity.
 */
fun MaterialSupplier.convertToProtobuf(): MaterialSupplierOuterClass.MaterialSupplier {
    return MaterialSupplierOuterClass.MaterialSupplier.newBuilder().setId(this.id.toString())
        .setSupplierName(this.supplierName).setDescription(this.description).setContactInformation(this.contactInfo)
        .build()
}

