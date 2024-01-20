package com.sams.grapgql.dgs.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class EntityFilter {
  private List<EntityType> entityType;
  private OperationType operationType;
  private List<AttributeFilter> attributeFilter;
}

//
//class Operator {
//    private String operationType;
//}

enum EntityType {
    ITEM,
    PRODUCT,
    ITEMGROUP,
    TRADEITEM
}

enum OperationType{
    OR,
    AND
}

@Data
@ToString
class AttributeFilter{
   private String attributeName;
   private String attributeValue;
   private FilterType filterType;
}

enum FilterType{
    EQUALS,
    CONTAINS
}


