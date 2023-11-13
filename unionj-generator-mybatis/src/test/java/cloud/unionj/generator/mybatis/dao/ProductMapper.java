package cloud.unionj.generator.mybatis.dao;

import cloud.unionj.generator.mybatis.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

//  void insert(Product product);
//
//  List<Product> selectByIds(List<Long> ids, Boolean isDeleted);
//
//  int deletequalityByPrimaryKey(int id);

  List<Product> getRoleListByGroup(@Param("roleGroup") Product.RoleType roleGroup);

//  Set<Long> getTaskByVersion(@Param("versions") String[] versions, @Param("versionsIds") Set<Long> versionsIds, @Param("projectId") Long projectId);
}
