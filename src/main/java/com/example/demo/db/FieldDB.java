package com.chazuo.czlib.db;

/**
 * 
 * @author LiQiong
 *
 */
public class FieldDB {
	private String fieldType;
	private String fieldName;
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	@Override
	public String toString() {
		return "FieldDB [fieldType=" + fieldType + ", fieldName=" + fieldName + "]";
	}
}
