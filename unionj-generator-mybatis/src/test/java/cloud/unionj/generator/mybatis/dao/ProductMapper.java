package cloud.unionj.generator.mybatis.dao;

import cloud.unionj.generator.mybatis.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

  void insert(Product product);

  List<Product> selectByIds(List<Long> ids, Boolean isDeleted);

  int deletequalityByPrimaryKey(int id);
}
