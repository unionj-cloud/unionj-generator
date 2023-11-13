package cloud.unionj.generator.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface ProductMapper {

//  void insert(Product product);
//
//  List<Product> selectByIds(List<Long> ids, Boolean isDeleted);
//
//  int deletequalityByPrimaryKey(int id);

  Set<Long> getTaskByVersion(@Param("versions") String[] versions, @Param("versionsIds") Set<Long> versionsIds, @Param("projectId") Long projectId);
}
