package com.example.material.repository

import com.example.material.entity.Material
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MaterialBatchRepository : CoroutineCrudRepository<Material, UUID> {
}