# 物料管理组件

## 功能

- 管理产品、物料及供应商信息。
- 与日志系统结合，在信息变动时记录变动项及操作人。
- 管理产品
    1. 查询全部物料(不包含deleted=true)
    2. 查询单个物料,包含该物料的全部批次及每个批次的详细信息(包含库存)
    3.

## Model逻辑关系

Material-(one-to-many)-Batch-(many-to-one)-Supplier

一个物料拥有多个批次，一个批次对应一个供应商，但一个供应商可以对应多个批次，其中batch包含material ID和supplier ID

### 查询逻辑
1. 查询一个物料的所有信息：根据物料ID，查找material信息并从batch表找到materialID符合的条目，再依次根据batch中supplierId查询supplier，最终合并
2. 查找供应商正在供应的物料：根据batch表supplierId符合的条目，找到所有material

## 组件结构
